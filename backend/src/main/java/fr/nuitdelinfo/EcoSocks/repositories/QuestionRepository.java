package fr.nuitdelinfo.EcoSocks.repositories;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Carte, Integer> {

}
