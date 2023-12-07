package fr.nuitdelinfo.EcoSocks.dto;

import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link fr.nuitdelinfo.EcoSocks.entities.Utilisateur}
 */
@Value
public class CreateUtilisateurRequest implements Serializable {
    @NotNull
    Integer id;

    Jeu jeu;
}