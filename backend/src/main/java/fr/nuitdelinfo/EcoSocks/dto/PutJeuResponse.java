package fr.nuitdelinfo.EcoSocks.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PutJeuResponse {
    private Integer id;
    private boolean finish;
    private Integer score;


}
