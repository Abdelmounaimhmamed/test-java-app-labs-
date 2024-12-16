package org.code;

import org.code.Exercice2.dao.IProfesseurDAO;
import org.code.Exercice2.dao.Professeur;
import org.code.Exercice2.service.ProfesseurService;
import org.code.Exercice2.service.ProfesseurServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfesseurServiceImplTest {

    private ProfesseurService professeurService;
    private IProfesseurDAO professeurDAO;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting all tests...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("All tests completed.");
    }

    @BeforeEach
    void setUp() {
        professeurDAO = mock(IProfesseurDAO.class);
        professeurService = new ProfesseurServiceImpl(professeurDAO);
    }


    @Test
    void testEnregistrerAjout() {
        Professeur professeur = new Professeur(0, "John Doe", "Mathematics");

        when(professeurDAO.addProfesseur(any(Professeur.class))).thenReturn(true);

        Professeur result = professeurService.enregistrer(0, "John Doe", "Mathematics");

        assertEquals("John Doe", result.getNom());
        verify(professeurDAO, times(1)).addProfesseur(any(Professeur.class));
    }

    @Test
    void testEnregistrerModification() {
        Professeur professeur = new Professeur(1, "John Doe", "Mathematics");

        when(professeurDAO.updateProfesseur(any(Professeur.class))).thenReturn(true);

        Professeur result = professeurService.enregistrer(1, "John Doe", "Mathematics");

        assertEquals("John Doe", result.getNom());
        verify(professeurDAO, times(1)).updateProfesseur(any(Professeur.class));
    }

    @Test
    void testChercherParNom() {
        List<Professeur> professeurs = Arrays.asList(
                new Professeur(1, "Alice", "Physics"),
                new Professeur(2, "Bob", "Mathematics"),
                new Professeur(3, "Alice", "Chemistry")
        );

        when(professeurDAO.getAllProfesseurs()).thenReturn(professeurs);

        List<Professeur> result = professeurService.chercherParNom("Alice");

        assertEquals(2, result.size());
        verify(professeurDAO, times(1)).getAllProfesseurs();
    }

    @Test
    void testChercherParSpecialite() {
        List<Professeur> professeurs = Arrays.asList(
                new Professeur(1, "Alice", "Physics"),
                new Professeur(2, "Bob", "Mathematics"),
                new Professeur(3, "Charlie", "Physics")
        );

        when(professeurDAO.getAllProfesseurs()).thenReturn(professeurs);

        List<Professeur> result = professeurService.chercherParSpecialite("Physics");

        assertEquals(2, result.size());
        verify(professeurDAO, times(1)).getAllProfesseurs();
    }

    @Test
    void testSupprimer() {
        when(professeurDAO.deleteProfesseur(1)).thenReturn(true);

        assertDoesNotThrow(() -> professeurService.supprimer(1));
        verify(professeurDAO, times(1)).deleteProfesseur(1);
    }

    @Test
    void testSupprimerErreur() {
        when(professeurDAO.deleteProfesseur(1)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> professeurService.supprimer(1));
        verify(professeurDAO, times(1)).deleteProfesseur(1);
    }
}
