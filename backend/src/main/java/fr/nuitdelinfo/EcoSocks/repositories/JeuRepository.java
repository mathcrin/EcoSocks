package fr.nuitdelinfo.EcoSocks.repositories;

import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeuRepository extends JpaRepository<Jeu, Integer> {
}
