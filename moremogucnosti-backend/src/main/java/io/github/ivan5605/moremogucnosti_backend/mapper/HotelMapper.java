package io.github.ivan5605.moremogucnosti_backend.mapper;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;
import io.github.ivan5605.moremogucnosti_backend.entity.Hotel;
import org.springframework.stereotype.Component;

// Mapper klasa za pretvaranje entiteta Hotel u DTO HotelDto i obrnuto

@Component // anotacija za Spring da zna da je ovo komponenta koja će biti automatski injektirana
public class HotelMapper {

    public HotelDto mapToHotelDto(Hotel hotel){
        if (hotel==null) {
            return null; // ili baciti iznimku, ovisno o potrebama
        }
        return new HotelDto(
                hotel.getId(),
                hotel.getNaziv(),
                hotel.getLokacija(),
                hotel.getCijenaNocenja()
        );
    }

    public Hotel mapToHotel(HotelDto hotelDto){
        if (hotelDto==null) {
            return null;
        }
        return new Hotel(
                hotelDto.getId(),
                hotelDto.getNaziv(),
                hotelDto.getLokacija(),
                hotelDto.getCijenaNocenja()
        );
    }
}
