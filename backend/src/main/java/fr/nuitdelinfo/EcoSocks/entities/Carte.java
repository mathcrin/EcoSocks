package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "carte")
public class Carte implements Serializable {
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

    @Column(name = "emission_co2")
    private Double emissionCO2;

    @Column(name = "intitule", length = 500)
    private String intitule;

    @ManyToMany
    @JoinTable(name="JEU_CARTE",
            joinColumns=@JoinColumn(name="id_carte"),
            inverseJoinColumns=@JoinColumn(name="id_jeu")
    )
    private List<Jeu> jeux_id;
}