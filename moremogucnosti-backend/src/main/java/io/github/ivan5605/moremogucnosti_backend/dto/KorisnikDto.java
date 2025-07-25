package io.github.ivan5605.moremogucnosti_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class KorisnikDto {
    private Long id;

    @NotBlank(message = "Unesite email!")
    private String email;

    @NotBlank(message = "Unesite ime!")
    private String ime;

    @NotBlank(message = "Unesite prezime!")
    private String prezime;

    @Size(min = 5, message = "Lozinka mora imati najmanje 5 znakova!")
    @NotBlank(message = "Unesite lozinku!")
    @Pattern(regexp = ".*[A-Z].*", message = "Lozinka mora sadržavati barem jedno veliko slovo")
    private String lozinka;
}
