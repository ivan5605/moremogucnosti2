package io.github.ivan5605.moremogucnosti_backend.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// DTO (Data Transfer Object) klasa za prijenos podataka o hotelu na klijentsku stranu (frontend)
public class HotelDto {
    private Long id;

    @NotBlank(message = "Naziv hotela ne smije biti prazan")
    private String naziv;

    @NotBlank(message = "Lokacija hotela ne smije biti prazna")
    private String lokacija;

    //@NotBlank(message = "Cijena noćenja ne smije biti prazna"), anotacija nije moguca jer je cijenaNocenja tipa int, a ne String
    @NotNull(message = "Cijena noćenja ne smije biti prazna")
    @Min(value = 1, message = "Cijena noćenja mora biti veća od 0")
    private int cijenaNocenja;
}
