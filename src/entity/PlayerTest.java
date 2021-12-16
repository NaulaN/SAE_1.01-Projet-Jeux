package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class PlayerTest
{
    @Test
    public final void checkCollisionTest()
    {
        // TODO: A refaire.
        /*
        Player ply = new Player(1, 2, 1);

        ply.moveUp();   // pour mettre le whereMoving[0] à vrai et y à 1.
        String[][] maps0 = {
                {"*","*","*"},
                {"*","0"," "},  // 0 est un repère visuel pour le joueur.
                {"*"," "," "}
        };
        assertTrue(ply.checkCollision(maps0),
                "Il est en collision si il y a un déplacement vers le haut.");

        ply.setXPosition(2);
        ply.moveLeft();     // Pour mettre le whereMoving[2] à vrai et x à 1.
        assertTrue(ply.checkCollision(maps0),
                "Il est en collision si il y a un déplacement vers la gauche.");

        maps0 = new String[][] {
                {"*","*","*"},
                {" ","0","*"},
                {" "," ","*"}
        };
        ply.setXPosition(0);
        ply.moveRight();
        assertTrue(ply.checkCollision(maps0),
                "Il est en collision si il y a un déplacement vers la droite");

        maps0 = new String[][] {
                {"*"," "," "},
                {"*","0"," "},
                {"*","*","*"}
        };
        ply.setXPosition(1);
        ply.setYPosition(0);
        ply.moveDown();
        assertTrue(ply.checkCollision(maps0));
        */
    }
}
