package fr.nuitdelinfo.EcoSocks.repositories;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Carte, Integer> {
}
