package fr.nuitdelinfo.EcoSocks.services;

import fr.nuitdelinfo.EcoSocks.dto.GetScoreResponse;
import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.repositories.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JeuService {

    @Autowired
    private JeuRepository jeuRepository;
    public Jeu finirPartie(Integer id) throws NotFoundException {
        Optional<Jeu> optJeu = jeuRepository.findById(id);
        if (optJeu.isPresent()){
            Jeu j = optJeu.get();
            j.setFinish(true);
            j = jeuRepository.save(j);
            return j;
        } else {
            return null;
        }
    }

    public Integer generateId() {
        Integer id;
        Random r = new Random(System.nanoTime());
        do {
            id = r.nextInt((int) 1E9);
        } while(this.jeuRepository.findById(id).isPresent());
        return id;
    }
}
