package fr.nuitdelinfo.EcoSocks.controllers;

import fr.nuitdelinfo.EcoSocks.dto.GetScoreResponse;
import fr.nuitdelinfo.EcoSocks.dto.PutJeuResponse;
import fr.nuitdelinfo.EcoSocks.entities.Jeu;
import fr.nuitdelinfo.EcoSocks.exceptions.NotFoundException;
import fr.nuitdelinfo.EcoSocks.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jeux")
public class JeuController {

    @Autowired
    private JeuService jeuService;

    @PutMapping("/{id}")
    public ResponseEntity<PutJeuResponse> finirJeu(@PathVariable Integer id){
        try{
            Jeu j = jeuService.finirPartie(id);
            if (j == null) throw new NotFoundException();
            return ResponseEntity.ok(PutJeuResponse.builder()
                            .finish(j.isFinish())
                            .score(j.getScore())
                            .id(j.getId())
                            .build());
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<Integer> generateId(){
        return ResponseEntity.ok(jeuService.generateId());
    }

    @GetMapping("/score/{id}")
    public ResponseEntity<Integer> getScore(@PathVariable Integer id){
        return ResponseEntity.ok(jeuService.getScore(id));
    }

    @GetMapping("/scores")
    public ResponseEntity<List<GetScoreResponse>> getScore(){
        List<GetScoreResponse> scores = this.jeuService.getScore();
        return ResponseEntity.ok(scores);
    }
}
