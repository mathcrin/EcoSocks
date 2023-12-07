package fr.nuitdelinfo.EcoSocks.dto;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Carte}
 */
@Value
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateCarteRequest implements Serializable {
    String imageURL;
    Double emissionCO2;
}