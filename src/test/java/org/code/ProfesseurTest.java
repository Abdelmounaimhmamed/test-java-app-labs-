package org.code;

import org.code.Exercice2.dao.Professeur;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class ProfesseurTest {

    @Test
    void testGettersAndSetters() {
        Professeur professeur = new Professeur();
        professeur.setId(1);
        professeur.setNom("John Doe");
        professeur.setSpecialite("Mathematics");

        assertEquals(1, professeur.getId());
        assertEquals("John Doe", professeur.getNom());
        assertEquals("Mathematics", professeur.getSpecialite());
    }

    @Test
    void testToString() {
        Professeur professeur = new Professeur(1, "Jane Doe", "Physics");
        assertEquals("Professeur{id=1, nom='Jane Doe', specialite='Physics'}", professeur.toString());
    }

    @Test
    void testEquals() {
        Professeur prof1 = new Professeur(1, "John Doe", "Math");
        Professeur prof2 = new Professeur(1, "John Doe", "Math");
        assertEquals(prof1, prof2);
    }
}
