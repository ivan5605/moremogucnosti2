package io.github.ivan5605.moremogucnosti_backend.repository;

import io.github.ivan5605.moremogucnosti_backend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository nije potrebno ovdje jer JpaRepository već sadrži @Repository anotaciju
//sve metode koje su definirane u JpaRepository su anotirane s @Transactional

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // metode već definirane u JpaRepository, ne trebam ih ponovno pisati
    // mogu dodati dodatne metode ako su potrebne, npr. za pretraživanje hotela po nazivu ili lokaciji
}
