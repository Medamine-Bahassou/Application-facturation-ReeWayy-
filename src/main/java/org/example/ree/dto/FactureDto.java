package org.example.ree.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class FactureDto {

    private Long id;

    @NotNull(message = "Client ID ne doit pas être vide")
    private Long clientId;

    private LocalDate date;

    private List<LigneFactureDto> lignes;

}
