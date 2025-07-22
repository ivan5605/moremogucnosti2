package io.github.ivan5605.moremogucnosti_backend.service;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto); // Kreira novi hotel, vraća kreirani hotel DTO
}
