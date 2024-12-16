package org.code.Exercice2.dao;

import java.util.List;

public interface IProfesseurDAO {
        List<Professeur> getAllProfesseurs();
        Professeur getProfesseurById(int id);
        boolean addProfesseur(Professeur professeur);
        boolean updateProfesseur(Professeur professeur);
        boolean deleteProfesseur(int id);
}
