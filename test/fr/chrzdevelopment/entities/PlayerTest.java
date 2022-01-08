package fr.chrzdevelopment.entities;

import static org.junit.jupiter.api.Assertions.*;
import static fr.chrzdevelopment.game.Const.*;

import fr.chrzdevelopment.game.entities.Entity;
import fr.chrzdevelopment.game.MapsEngine;
import fr.chrzdevelopment.game.entities.Player;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


public class PlayerTest
{
    @Test
    public final void checkCollisionTest()
    {
        List<Entity> allSprites = new ArrayList<>();

        MapsEngine mapsEngine = new MapsEngine(6, 6);

        mapsEngine.generateMap();
        // Genere un obstacle manuellement
        mapsEngine.setElementMap(2, 2, WALL, true);
        mapsEngine.setElementMap(3, 2, WALL, true);
        mapsEngine.setElementMap(3, 3, WALL, true);
        mapsEngine.setElementMap(2, 3, WALL, true);

        Player player = mapsEngine.spawnEntity(allSprites);

        player.setXPosition(1);
        player.setYPosition(1);

        // Place le joueur sur la Maps
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), PLAYER, true);
        player.checkCollision(mapsEngine.getCalqueCollide());

        mapsEngine.draw();

        // Check où il entre en collision
        assertTrue(player.getCollideUp());
        assertTrue(player.getCollideLeft());
        assertFalse(player.getCollideRight());
        assertFalse(player.getCollideDown());


        player.setXPosition(3);
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), PLAYER, true);
        mapsEngine.setElementMap(1, 1, EMPTY, false);
        player.checkCollision(mapsEngine.getCalqueCollide());

        mapsEngine.draw();
        /*
        for (int[] c : mapsEngine.getCalqueCollide())
        {
            System.out.println();
            for (int r : c)
                if (r == COLLIDE_OBJ)
                    System.out.print(RECT_RED_IMG);
                else System.out.print(EMPTY_IMG);
        }
         */

        assertTrue(player.getCollideUp());
        assertFalse(player.getCollideLeft());
        assertFalse(player.getCollideRight());
        assertTrue(player.getCollideDown());


        player.setXPosition(4);
        player.setYPosition(3);
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), PLAYER, true);
        mapsEngine.setElementMap(3, 1, EMPTY, false);
        player.checkCollision(mapsEngine.getCalqueCollide());

        mapsEngine.draw();

        assertFalse(player.getCollideUp());
        assertTrue(player.getCollideLeft());
        assertTrue(player.getCollideRight());
        assertFalse(player.getCollideDown());
    }
}
