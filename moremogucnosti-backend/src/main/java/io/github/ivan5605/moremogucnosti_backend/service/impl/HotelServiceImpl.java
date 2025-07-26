package io.github.ivan5605.moremogucnosti_backend.service.impl;

import io.github.ivan5605.moremogucnosti_backend.exception.ResourceNotFoundException;
import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;
import io.github.ivan5605.moremogucnosti_backend.entity.Hotel;
import io.github.ivan5605.moremogucnosti_backend.mapper.HotelMapper;
import io.github.ivan5605.moremogucnosti_backend.repository.HotelRepository;
import io.github.ivan5605.moremogucnosti_backend.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Anotacija za Spring da zna da je ovo servis koji će biti automatski injektiran
@AllArgsConstructor

public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository; // Injektira se repozitorij za pristup podacima o hotelima
    private final HotelMapper hotelMapper; // Injektira se mapper za pretvaranje između entiteta i DTO-a

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.mapToHotel(hotelDto); // Pretvara DTO u entitet
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.mapToHotelDto(savedHotel);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hoteli = hotelRepository.findAll(); // Dohvaća sve hotele iz baze podataka
        return hoteli.stream().map((hotel) -> hotelMapper.mapToHotelDto(hotel)).collect(Collectors.toList());
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel sa id-em " + id + " ne postoji!")); // Provjerava postoji li hotel s danim ID-om
        hotelRepository.deleteById(id); // Briše hotel po ID-u
    }

    @Override
    public HotelDto updateHotel(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel sa ID-jem " + id + " ne postoji!"));

        //hotel = hotelMapper.mapToHotel(hotelDto);

        hotel.setNaziv(hotelDto.getNaziv());
        hotel.setLokacija(hotelDto.getLokacija());
        hotel.setCijenaNocenja(hotelDto.getCijenaNocenja());

        Hotel updatedHotel = hotelRepository.save(hotel);
        //metoda save prepoznaje da objekt hotel već ima postavljen ID, zato ne kreira novi nego update-a stari

        return hotelMapper.mapToHotelDto(updatedHotel);
    }

    @Override
    public HotelDto getHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel sa ID-jem " + id + " ne postoji!"));

        return hotelMapper.mapToHotelDto(hotel);
    }
}
