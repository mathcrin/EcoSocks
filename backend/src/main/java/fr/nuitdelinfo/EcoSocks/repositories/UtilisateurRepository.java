package fr.nuitdelinfo.EcoSocks.repositories;


import fr.nuitdelinfo.EcoSocks.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
