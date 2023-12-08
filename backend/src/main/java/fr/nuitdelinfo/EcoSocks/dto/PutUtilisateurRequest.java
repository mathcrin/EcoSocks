package fr.nuitdelinfo.EcoSocks.dto;

import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link fr.nuitdelinfo.EcoSocks.entities.Utilisateur}
 */
@Value
public class PutUtilisateurRequest implements Serializable {
    private Integer id;
    private String pseudo;
    private List<Jeu> jeux;
}