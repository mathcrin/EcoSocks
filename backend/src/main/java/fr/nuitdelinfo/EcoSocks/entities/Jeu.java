package fr.nuitdelinfo.EcoSocks.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jeu")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    @Column(name = "finish", nullable = false)
    private boolean finish;

    @Column(name = "score")
    private Integer score;

    @OneToMany
    @Column(name = "idParcourus")
    private List<Carte> idParcourus;

    public Jeu (Integer id){
        this.id = id;
        this.utilisateur = null;
        this.score = 1;
        this.idParcourus = new ArrayList<>();
    }
}
