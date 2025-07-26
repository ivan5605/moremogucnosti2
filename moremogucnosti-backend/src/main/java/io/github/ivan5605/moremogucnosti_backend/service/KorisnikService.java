package io.github.ivan5605.moremogucnosti_backend.service;

import io.github.ivan5605.moremogucnosti_backend.dto.KorisnikDto;

import java.util.List;

public interface KorisnikService {
    List<KorisnikDto> getAllKorisnici();

    KorisnikDto addKorisnik(KorisnikDto korisnikDto);

    void deleteKorisnik(Long id);

    KorisnikDto updateKorisnik(Long id, KorisnikDto korisnikDto);

    KorisnikDto getKorisnik(Long id);
}
