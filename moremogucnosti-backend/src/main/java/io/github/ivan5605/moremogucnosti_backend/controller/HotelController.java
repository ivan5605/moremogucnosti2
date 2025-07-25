package io.github.ivan5605.moremogucnosti_backend.controller;

import io.github.ivan5605.moremogucnosti_backend.dto.HotelDto;
import io.github.ivan5605.moremogucnosti_backend.service.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Anotacija koja označava da je ova klasa kontroler, a to znači da će obrađivati HTTP zahtjeve
@RequestMapping("/api/hoteli") // Definira osnovnu putanju za sve metode u ovom kontroleru

@AllArgsConstructor

@CrossOrigin("*") // Omogućuje CORS (Cross-Origin Resource Sharing) za sve domene, što omogućuje React aplikaciji da komunicira s ovim API-jem bez ograničenja
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

    // Get all hotels REST API
    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() { // Vraća listu svih hotela

        List<HotelDto> hoteliDto = hotelService.getAllHotels();
        return new ResponseEntity<>(hoteliDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) { // Briše hotel po ID-u
        hotelService.deleteHotel(id);
        return new ResponseEntity<>("Hotel obrisan!", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable("id") Long id,
                                                @RequestBody HotelDto hotelDto){
        HotelDto updatedHotelDto = hotelService.updateHotel(id, hotelDto);
        return new ResponseEntity<>(updatedHotelDto, HttpStatus.OK);
    }
}
