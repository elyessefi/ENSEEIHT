package allumettes;
import org.junit.*;
import static org.junit.Assert.*;

public class RapideTest {

    private Jeu jeu13, jeu12, jeu11, jeu10, jeu9, jeu8, jeu7, jeu6, jeu5, jeu4,
                jeu3,jeu2, jeu1, jeuc13, jeuc12, jeuc11, jeuc10, jeuc9, jeuc8,
                jeuc7, jeuc6, jeuc5, jeuc4, jeuc3, jeuc2, jeuc1;
    private Joueur joueurRapide;
    @Before
    public void setUp() {
        joueurRapide = new Joueur("n7", new Rapide());
        jeu13 = new JeuReelImp(13, "confiant");
        jeu12 = new JeuReelImp(12, "confiant");
        jeu11 = new JeuReelImp(11, "confiant");
        jeu10 = new JeuReelImp(10, "confiant");
        jeu9 = new JeuReelImp(9, "confiant");
        jeu8 = new JeuReelImp(8, "confiant");
        jeu7 = new JeuReelImp(7, "confiant");
        jeu6 = new JeuReelImp(6, "confiant");
        jeu5 = new JeuReelImp(5, "confiant");
        jeu4 = new JeuReelImp(4, "confiant");
        jeu3 = new JeuReelImp(3, "confiant");
        jeu2 = new JeuReelImp(2, "confiant");
        jeu1 = new JeuReelImp(1, "confiant");

        jeuc13 = new JeuReelImp(13, "!confiant");
        jeuc12 = new JeuReelImp(12, "!confiant");
        jeuc11 = new JeuReelImp(11, "!confiant");
        jeuc10 = new JeuReelImp(10, "!confiant");
        jeuc9 = new JeuReelImp(9, "!confiant");
        jeuc8 = new JeuReelImp(8, "!confiant");
        jeuc7 = new JeuReelImp(7, "!confiant");
        jeuc6 = new JeuReelImp(6, "!confiant");
        jeuc5 = new JeuReelImp(5, "!confiant");
        jeuc4 = new JeuReelImp(4, "!confiant");
        jeuc3 = new JeuReelImp(3, "!confiant");
        jeuc2 = new JeuReelImp(2, "!confiant");
        jeuc1 = new JeuReelImp(1, "!confiant");
    }

    @Test
    public void testRapide() {
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu13));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu12));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu11));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu10));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu9));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu8));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu7));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu6));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu5));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu4));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeu3));
                assertEquals("Prise invalide !", 2, joueurRapide.getPrise(jeu2));
                assertEquals("Prise invalide !", 1, joueurRapide.getPrise(jeu1));

                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc13));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc12));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc11));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc10));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc9));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc8));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc7));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc6));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc5));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc4));
                assertEquals("Prise invalide !", 3, joueurRapide.getPrise(jeuc3));
                assertEquals("Prise invalide !", 2, joueurRapide.getPrise(jeuc2));
                assertEquals("Prise invalide !", 1, joueurRapide.getPrise(jeuc1));
    }
}
