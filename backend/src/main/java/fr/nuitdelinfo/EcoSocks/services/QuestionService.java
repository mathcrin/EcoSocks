package fr.nuitdelinfo.EcoSocks.services;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import fr.nuitdelinfo.EcoSocks.dto.CreateCarteRequest;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.repositories.QuestionRepository;
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

    public Carte ajouterCarte(CreateCarteRequest body){
        Carte temp = Carte.builder()
                .emissionCO2(body.getEmissionCO2())
                .imageURL(body.getImageURL())
                .build();
        return questionRepository.save(temp);
    }

    public Carte obtenirCarte() throws NotFoundException {
        List<Carte> cartes = questionRepository.findAll();
        if (cartes.isEmpty())
            throw new NotFoundException();
        Random r = new Random();
        return cartes.get(r.nextInt(cartes.size()));
    }

    public Carte obtenirCarte(Integer id) throws NotFoundException {
        Optional<Carte> optCarte = questionRepository.findById(id);
        if (optCarte.isPresent())
            return optCarte.get();
        else
            throw new NotFoundException();
    }
}
