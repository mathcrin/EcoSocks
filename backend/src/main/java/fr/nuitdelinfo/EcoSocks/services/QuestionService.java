package fr.nuitdelinfo.EcoSocks.services;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import fr.nuitdelinfo.EcoSocks.dto.CreateCarteRequest;
import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import fr.nuitdelinfo.EcoSocks.entities.Utilisateur;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.repositories.JeuRepository;
import fr.nuitdelinfo.EcoSocks.repositories.QuestionRepository;
import fr.nuitdelinfo.EcoSocks.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public Carte ajouterCarte(CreateCarteRequest body){
        Carte temp = Carte.builder()
                .emissionCO2(body.getEmissionCO2())
                .imageURL(body.getImageURL())
                .intitule(body.getIntitule())
                .build();
        return questionRepository.save(temp);
    }

    public Carte obtenirCarte(Integer id) throws NotFoundException {
        Random r = new Random();
        Jeu j = null;

        Optional<Jeu> optJeu = jeuRepository.findById(id);
        // Si vide, c'est le premier choix de l'utilisateur, il n'y a pas encore de partie pour lui, on la crée
        if (optJeu.isEmpty()) {
            j = new Jeu(id);
            j = jeuRepository.save(j);
        } else {
            j = optJeu.get();
        }
        List<Carte> cartes = questionRepository.findAll();
        Jeu finalJ = j;
        if (optJeu.isPresent())
            cartes = cartes.stream().filter(x -> !finalJ.getIdParcourus().contains(x)).toList();
        if (cartes.isEmpty()) {
            return null;
        }

        Carte temp = cartes.get(r.nextInt(cartes.size()));
        j.getIdParcourus().add(temp);
        j.setScore(j.getScore()+1);
        jeuRepository.save(j);
        return temp;
    }

    public List<Carte> ajouterCartes(List<Carte> cartes) {
        return questionRepository.saveAll(cartes);
    }

    public Carte carteDebut(Integer id) {
        Optional<Carte> optCarte = this.questionRepository.findById(id);
        return optCarte.orElse(null);
    }
}
