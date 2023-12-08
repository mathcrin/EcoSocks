package fr.nuitdelinfo.EcoSocks.controllers;

import fr.nuitdelinfo.EcoSocks.entities.Carte;
import fr.nuitdelinfo.EcoSocks.dto.CreateCarteRequest;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Carte> getCarte(@PathVariable(required = false) Integer id){
        // Si l'id est null, on change l'id de la carte pour qu'elle contienne l'id attribué à la partie
        if (id == null){
            // Création d'un utilisateur
            

        }
        Carte temp;
        try {
            temp = questionService.obtenirCarte(id);
        } catch (NotFoundException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return temp == null ? ResponseEntity.noContent().build(): ResponseEntity.ok(temp);
    }
}