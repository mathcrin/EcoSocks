package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pseudo")
    private String pseudo;

    @ManyToMany(mappedBy = "utilisateur")
    private List<Jeu> jeux = new ArrayList<>();

    public Utilisateur(Integer id){
        this.id = id;
        this.pseudo = null;
        this.jeux = new ArrayList<>();
    }

}