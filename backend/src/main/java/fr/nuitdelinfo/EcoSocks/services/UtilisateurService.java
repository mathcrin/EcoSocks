package fr.nuitdelinfo.EcoSocks.services;

import fr.nuitdelinfo.EcoSocks.dto.CreateUtilisateurRequest;
import fr.nuitdelinfo.EcoSocks.dto.PutUtilisateurRequest;
import fr.nuitdelinfo.EcoSocks.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.nuitdelinfo.EcoSocks.entities.Utilisateur;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    public Utilisateur modifierUtilisateur(PutUtilisateurRequest body) {
        Utilisateur utilisateur = Utilisateur.builder()
                .id(body.getId())
                .pseudo(body.getPseudo())
                .jeux(body.getJeux())
                .build();
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur ajouterUtilisateur(CreateUtilisateurRequest body) {
        Utilisateur utilisateur = Utilisateur.builder()
                .id(body.getId())
                .build();
        return utilisateurRepository.save(utilisateur);
    }

}
