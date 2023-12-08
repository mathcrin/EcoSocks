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

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        if (id == null) {
            List<Carte> cartes = questionRepository.findAll();
            if (cartes.isEmpty())
                return null;
            Carte temp = cartes.get(r.nextInt(cartes.size()));
            temp.setId(r.nextInt((int)1E9));
            return temp;
        }

        Optional<Jeu> optJeu = jeuRepository.findById(id);
        Jeu j = null;
        // Si vide, c'est le premier choix de l'utilisateur, il n'y a pas encore de partie pour lui, on la crée
        if (optJeu.isEmpty()) {
            Jeu u = new Jeu(id);
            j = jeuRepository.save(u);
        }
        j = optJeu.get();
        List<Carte> cartes = questionRepository.findAllByIdNotIn(optJeu.get().getIdParcourus().stream().map(Carte::getId).toList());
        if (cartes.isEmpty())
            return null;
        Carte temp = cartes.get(r.nextInt(cartes.size()));
        j.getIdParcourus().add(temp);
        jeuRepository.save(j);
        return temp;
    }
}
