package fr.chrzdevelopment.entities;

import fr.chrzdevelopment.game.MapsEngine;
import fr.chrzdevelopment.game.Sound;
import fr.chrzdevelopment.game.entities.Entity;
import fr.chrzdevelopment.game.entities.Laser;
import fr.chrzdevelopment.game.entities.Monster;
import fr.chrzdevelopment.game.entities.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static fr.chrzdevelopment.game.Const.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

        Player player = mapsEngine.spawnPlayer(allSprites);

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

    @Test
    public final void hitWithLaserTest()
    {
        final String OS = System.getProperty("os.name");
        // Load graphics
        if (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10"))
            windowsGraphics();
        else linuxGraphics();


        List<Entity> allSprites = new CopyOnWriteArrayList<>();

        MapsEngine mapsEngine = new MapsEngine(10, 3);
        mapsEngine.generateMap();

        Player player = mapsEngine.spawnPlayer(allSprites);
        player.setXPosition(1); player.setYPosition(1);
        mapsEngine.spawnMonster(allSprites, 1, 4, 1);


        long time = 0;
        while (player.getHealth() > 0 && time <= 100)
        {
            // Calme le programme
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) { e.printStackTrace(); }

            mapsEngine.draw();

            for (Entity sprite : allSprites)
            {
                sprite.checkCollision(mapsEngine.getCalqueCollide());
                boolean collide = sprite.getDataImg() != COIN && sprite.getDataImg() != KEY && sprite.getDataImg() != LASER_VERTICAL && sprite.getDataImg() != LASER_HORIZONTAL;
                mapsEngine.updateEntity(sprite, collide);

                if (sprite instanceof Monster)
                {
                    Monster monster = (Monster) sprite;
                    monster.shoot(player, mapsEngine.getMap()[0].length, mapsEngine.getMap().length);
                }

                if (sprite instanceof Laser) {
                    Laser laser = (Laser) sprite;
                    // Si il touche un joueur.
                    if (laser.getDirection() == 0 && (
                            (laser.getYPosition()-1 == player.getYPosition() || laser.getYPosition() == player.getYPosition()) &&
                                    (player.getXPosition() == laser.getXPosition())
                    )) {
                        player.hit();
                        mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                        allSprites.remove(laser);

                        Sound.play("hitHurt.wav", 1);
                    } else if (laser.getDirection() == 1 && (
                            (laser.getXPosition()+1 == player.getXPosition() || laser.getXPosition() == player.getXPosition()) &&
                                    (player.getYPosition() == laser.getYPosition())
                    )) {
                        player.hit();
                        mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                        allSprites.remove(laser);

                        Sound.play("hitHurt.wav", 0);
                    } else if (laser.getDirection() == 3 && (
                            (laser.getYPosition()+1 == player.getYPosition() || laser.getYPosition() == player.getYPosition()) &&
                                    (player.getXPosition() == laser.getXPosition())
                    )) {
                        player.hit();
                        mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                        allSprites.remove(laser);

                        Sound.play("hitHurt.wav", 0);
                    } else if (laser.getDirection() == 2 && (
                            (laser.getXPosition()-1 == player.getXPosition() || laser.getXPosition() == player.getXPosition()) &&
                                    (player.getYPosition() == laser.getYPosition())
                    )) {
                        player.hit();
                        mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                        allSprites.remove(laser);

                        Sound.play("hitHurt.wav", 0);
                    } else {
                        // Regarde si il rentre pas en collision et regarde dans quelle direction il va.
                        if (!laser.getCollideUp() && laser.getDirection() == 0)
                            laser.moveUp();
                        else if (!laser.getCollideDown() && laser.getDirection() == 3)
                            laser.moveDown();
                        else if (!laser.getCollideRight() && laser.getDirection() == 1)
                            laser.moveRight();
                        else if (!laser.getCollideLeft() && laser.getDirection() == 2)
                            laser.moveLeft();
                        else {
                            mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                            allSprites.remove(laser);
                        }
                    }
                }

                sprite.updates();
                time++;
            }
        }

        boolean noLife = player.getHealth() <= 0;
        assertTrue(noLife);
    }
}
