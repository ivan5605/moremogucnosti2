package io.github.ivan5605.moremogucnosti_backend.service.impl;

import io.github.ivan5605.moremogucnosti_backend.dto.KorisnikDto;
import io.github.ivan5605.moremogucnosti_backend.entity.Korisnik;
import io.github.ivan5605.moremogucnosti_backend.exception.ResourceNotFoundException;
import io.github.ivan5605.moremogucnosti_backend.mapper.KorisnikMapper;
import io.github.ivan5605.moremogucnosti_backend.repository.KorisnikRepository;
import io.github.ivan5605.moremogucnosti_backend.service.KorisnikService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final KorisnikMapper korisnikMapper;

    @Override
    public List<KorisnikDto> getAllKorisnici() {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDto> korisniciDto = new ArrayList<>();
        for (Korisnik korisnik : korisnici){
            KorisnikDto korisnikDto = korisnikMapper.mapToKorisnikDto(korisnik);
            korisniciDto.add(korisnikDto);
        }
        //korisnici.stream().map((korisnik) -> korisnikMapper.mapToKorisnikDto(korisnik)).collect(Collectors.toList());

        return korisniciDto;

    }

    @Override
    public KorisnikDto addKorisnik(KorisnikDto korisnikDto) {
        Korisnik korisnik = korisnikMapper.mapToKorisnik(korisnikDto);
        Korisnik savedKorisnik = korisnikRepository.save(korisnik);
        return korisnikMapper.mapToKorisnikDto(savedKorisnik);
    }

    @Override
    public void deleteKorisnik(Long id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik sa id-em " + id + " ne postoji"));
        korisnikRepository.deleteById(id);
    }
}
