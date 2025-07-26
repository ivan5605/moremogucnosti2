package io.github.ivan5605.moremogucnosti_backend.repository;

import io.github.ivan5605.moremogucnosti_backend.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    boolean existsByEmail(String email);
}
