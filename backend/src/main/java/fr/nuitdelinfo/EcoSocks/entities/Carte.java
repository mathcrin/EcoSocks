package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carte")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @URL
    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "image_url", length = 500)
    private String imageURL;

    @Column(name = "emission_co2", nullable = false)
    private Double emissionCO2;

    @Column(name = "intitule", nullable = false, length = 500)
    private String intitule;

}