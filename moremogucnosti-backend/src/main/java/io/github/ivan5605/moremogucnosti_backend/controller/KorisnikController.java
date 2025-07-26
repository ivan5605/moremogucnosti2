package io.github.ivan5605.moremogucnosti_backend.controller;

import io.github.ivan5605.moremogucnosti_backend.dto.KorisnikDto;
import io.github.ivan5605.moremogucnosti_backend.service.KorisnikService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")

@AllArgsConstructor

@CrossOrigin("*")
public class KorisnikController {

    private final KorisnikService korisnikService;
    //private - kontroliramo pristup toj varijabli samo unutar klase
    //final - varijabla mora biti inicijalizirana u konstruktor i više se ne može promijeniti
    //osigurava da sping ubaci bean samo jednom i da se referenca ne može promijeniti - savršeno da dependency injection

    @GetMapping
    public ResponseEntity<List<KorisnikDto>> getAllKorisnici(){
        List<KorisnikDto> korisniciDto = korisnikService.getAllKorisnici();
        return new ResponseEntity<>(korisniciDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KorisnikDto> addKorisnik(@Valid @RequestBody  KorisnikDto korisnikDto){
        KorisnikDto savedKorisnik = korisnikService.addKorisnik(korisnikDto);
        return new ResponseEntity<>(savedKorisnik, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKorisnik(@PathVariable("id") Long id){
        korisnikService.deleteKorisnik(id);
        return new ResponseEntity<>("Korisnik sa ID-jem  " + id + "uspješno izbrisan!", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<KorisnikDto> updateKorisnik(@PathVariable("id") Long id, @RequestBody KorisnikDto korisnikDto){
        KorisnikDto korisnikDtoUpdated = korisnikService.updateKorisnik(id, korisnikDto);
        return new ResponseEntity<>(korisnikDtoUpdated, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<KorisnikDto> getKorisnik(@PathVariable("id") Long id){
        KorisnikDto korisnikDto = korisnikService.getKorisnik(id);
        return new ResponseEntity<>(korisnikDto, HttpStatus.OK);
    }
}
