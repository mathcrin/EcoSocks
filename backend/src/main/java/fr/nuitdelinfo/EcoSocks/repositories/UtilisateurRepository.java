package fr.nuitdelinfo.EcoSocks.repositories;


import fr.nuitdelinfo.EcoSocks.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
