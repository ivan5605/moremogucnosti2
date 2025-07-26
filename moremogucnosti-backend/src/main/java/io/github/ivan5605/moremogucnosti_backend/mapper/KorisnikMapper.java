package io.github.ivan5605.moremogucnosti_backend.mapper;

import io.github.ivan5605.moremogucnosti_backend.dto.KorisnikDto;
import io.github.ivan5605.moremogucnosti_backend.entity.Korisnik;
import org.springframework.stereotype.Component;

@Component
public class KorisnikMapper {
    public KorisnikDto mapToKorisnikDto(Korisnik korisnik){
        if (korisnik==null){
            return null;
        }
        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setId(korisnik.getId());
        korisnikDto.setIme(korisnik.getIme());
        korisnikDto.setPrezime(korisnik.getPrezime());
        korisnikDto.setEmail(korisnik.getEmail());
        korisnikDto.setLozinka(korisnik.getLozinka());
        return korisnikDto;
    }

    public Korisnik mapToKorisnik(KorisnikDto korisnikDto){
        if (korisnikDto==null){
            return null;
        }
        return new Korisnik(
                korisnikDto.getId(),
                korisnikDto.getEmail(),
                korisnikDto.getIme(),
                korisnikDto.getPrezime(),
                korisnikDto.getLozinka()
        );
    }
}
