package fr.nuitdelinfo.EcoSocks.controllers;

import fr.nuitdelinfo.EcoSocks.dto.GetCarteResponse;
import fr.nuitdelinfo.EcoSocks.entities.Carte;
import fr.nuitdelinfo.EcoSocks.dto.CreateCarteRequest;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cartes")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<Carte> ajouterCarte(@RequestBody CreateCarteRequest body) {
        Carte temp;
        try {
            temp = questionService.ajouterCarte(body);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<GetCarteResponse> startCartes(@PathVariable Integer id){
        if (!(id == 1 || id == 2)) return ResponseEntity.badRequest().build();
        Carte c = questionService.carteDebut(id);
        if (c == null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(GetCarteResponse.builder()
                    .emissionCO2(c.getEmissionCO2())
                    .imageURL(c.getImageURL())
                    .intitule(c.getIntitule())
                    .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCarteResponse> getCarte(@PathVariable Integer id){
        // Si l'id est null, on change l'id de la carte pour qu'elle contienne l'id attribué à la partie
        Carte temp;
        try {
            temp = questionService.obtenirCarte(id);
        } catch (NotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return (temp == null ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(
                GetCarteResponse.builder()
                        .emissionCO2(temp.getEmissionCO2())
                        .intitule(temp.getIntitule())
                        .imageURL(temp.getImageURL())
                        .build()
        ));
    }

    @PostMapping("/all")
    public ResponseEntity<List<Carte>> createCartes(@RequestBody List<Carte> cartes) {
        List<Carte> createdCartes;
        try {
            createdCartes = questionService.ajouterCartes(cartes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCartes);
    }
}