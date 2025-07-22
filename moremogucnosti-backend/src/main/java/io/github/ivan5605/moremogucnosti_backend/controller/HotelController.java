package io.github.ivan5605.moremogucnosti_backend.controller;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;
import io.github.ivan5605.moremogucnosti_backend.service.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Anotacija koja označava da je ova klasa kontroler, a to znači da će obrađivati HTTP zahtjeve
@RequestMapping("/api/hoteli") // Definira osnovnu putanju za sve metode u ovom kontroleru

@AllArgsConstructor

public class HotelController {
    // Ovdje će se definirati metode za upravljanje hotelima, npr. za kreiranje, dohvaćanje, ažuriranje i brisanje hotela
    // Svaka metoda će biti anotirana s odgovarajućom HTTP metodom (npr. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping)
    // i bit će injektiran servis koji će obavljati poslovnu logiku vezanu uz hotele

    private HotelService hotelService; // Injektira se servis za upravljanje hotelima

    // Add hotel REST API
    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@Valid @RequestBody HotelDto hotelDto) { // Prima HotelDto iz tijela zahtjeva i vraća kreirani hotel

        HotelDto savedHotel = hotelService.createHotel(hotelDto);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }
}
