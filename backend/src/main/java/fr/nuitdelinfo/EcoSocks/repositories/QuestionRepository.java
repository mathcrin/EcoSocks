package fr.nuitdelinfo.EcoSocks.repositories;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Carte, Integer> {
    public List<Carte> findAllByIdNotIn(List<Integer> list_id);
}
