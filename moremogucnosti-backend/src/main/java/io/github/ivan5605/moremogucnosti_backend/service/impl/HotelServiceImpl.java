package io.github.ivan5605.moremogucnosti_backend.service.impl;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;
import io.github.ivan5605.moremogucnosti_backend.entity.Hotel;
import io.github.ivan5605.moremogucnosti_backend.mapper.HotelMapper;
import io.github.ivan5605.moremogucnosti_backend.repository.HotelRepository;
import io.github.ivan5605.moremogucnosti_backend.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
