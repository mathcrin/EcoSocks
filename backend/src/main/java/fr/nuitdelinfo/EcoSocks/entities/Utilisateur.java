package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "pseudo")
    private String pseudo;

    @OneToMany(mappedBy = "utilisateur", orphanRemoval = true)
    private List<Jeu> jeux = new ArrayList<>();

    public Utilisateur(Integer id){
        this.id = id;
        this.pseudo = null;
        this.jeux = new ArrayList<>();
    }

}