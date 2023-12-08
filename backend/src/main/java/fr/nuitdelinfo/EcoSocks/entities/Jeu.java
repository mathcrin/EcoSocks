package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "jeu")
public class Jeu implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany
    private List<Utilisateur> utilisateur;

    @Column(name = "finish", nullable = false)
    private boolean finish;

    @Column(name = "score")
    private Integer score;

    @ManyToMany
    @JoinTable(name="JEU_CARTE",
            joinColumns=@JoinColumn(name="id_jeu"),
            inverseJoinColumns=@JoinColumn(name="id_carte")
    )
    private List<Carte> idParcourus;

    public Jeu (Integer id){
        this.id = id;
        this.utilisateur = null;
        this.score = 1;
        this.idParcourus = new ArrayList<>();
    }
}
