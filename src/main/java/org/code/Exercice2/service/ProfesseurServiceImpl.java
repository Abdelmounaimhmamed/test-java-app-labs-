package org.code.Exercice2.service;


import org.code.Exercice2.dao.IProfesseurDAO;
import org.code.Exercice2.dao.Professeur;

import java.util.List;
import java.util.stream.Collectors;

public class ProfesseurServiceImpl implements ProfesseurService {

    private final IProfesseurDAO professeurDAO;

    // Injection du DAO via le constructeur
    public ProfesseurServiceImpl(IProfesseurDAO professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    @Override
    public Professeur enregistrer(int id, String nom, String specialite) {
        if (nom == null || nom.isEmpty() || specialite == null || specialite.isEmpty()) {
            throw new IllegalArgumentException("Nom et spécialité doivent être renseignés");
        }

        Professeur professeur = new Professeur(id, nom, specialite);
        if (id == 0) { // Ajout d'un nouveau professeur
            if (professeurDAO.addProfesseur(professeur)) {
                return professeur;
            } else {
                throw new RuntimeException("Erreur lors de l'ajout du professeur");
            }
        } else { // Modification d'un professeur existant
            if (professeurDAO.updateProfesseur(professeur)) {
                return professeur;
            } else {
                throw new RuntimeException("Erreur lors de la modification du professeur");
            }
        }
    }

    @Override
    public List<Professeur> chercherParNom(String nom) {
        return professeurDAO.getAllProfesseurs()
                .stream()
                .filter(prof -> prof.getNom().equalsIgnoreCase(nom))
                .collect(Collectors.toList());
    }

    @Override
    public List<Professeur> chercherParSpecialite(String specialite) {
        return professeurDAO.getAllProfesseurs()
                .stream()
                .filter(prof -> prof.getSpecialite().equalsIgnoreCase(specialite))
                .collect(Collectors.toList());
    }

    @Override
    public List<Professeur> getAll() {
        return professeurDAO.getAllProfesseurs();
    }

    @Override
    public void supprimer(int id) {
        if (!professeurDAO.deleteProfesseur(id)) {
            throw new RuntimeException("Erreur lors de la suppression du professeur");
        }
    }
}

