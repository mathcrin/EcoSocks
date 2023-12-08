package fr.nuitdelinfo.EcoSocks.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetScoreResponse {
    // private String utilisateur;
    private Integer score;
}
