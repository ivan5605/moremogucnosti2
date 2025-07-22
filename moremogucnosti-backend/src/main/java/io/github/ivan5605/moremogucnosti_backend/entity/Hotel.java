package io.github.ivan5605.moremogucnosti_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//koristim Lombok anotacije za automatsko generiranje gettera, settera, konstruktora...
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//koristim JPA anotacije za mapiranje klase na tablicu u bazi podataka
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatski generira ID, inkrementira ga
    private Long id;

    @Column(name = "naziv", nullable = false) //mapira atribut na kolonu u tablici
    private String naziv;

    @Column(name = "lokacija", nullable = false)
    private String lokacija;

    @Column(name = "cijena_nocenja", nullable = false)
    private int cijenaNocenja;
}
