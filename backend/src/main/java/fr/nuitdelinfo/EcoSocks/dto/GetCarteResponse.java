package fr.nuitdelinfo.EcoSocks.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCarteResponse {
    private String imageURL;
    private Double emissionCO2;
    private String intitule;
}
