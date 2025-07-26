package io.github.ivan5605.moremogucnosti_backend.service;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto); // Kreira novi hotel, vraća kreirani hotel DTO

    List<HotelDto> getAllHotels(); // Vraća listu svih hotela kao DTO-ove

    void deleteHotel(Long id); // Briše hotel po ID-u

    HotelDto updateHotel(Long id, HotelDto hotelDto);

    HotelDto getHotel(Long id);
}
