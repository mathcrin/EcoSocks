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
    Integer id;
    String pseudo;
    List<Jeu> jeux;
}