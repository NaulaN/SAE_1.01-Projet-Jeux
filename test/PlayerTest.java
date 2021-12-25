import static entity.Const.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.Player;
import org.junit.jupiter.api.Test;


public class PlayerTest
{
    @Test
    public final void checkCollisionTest()
    {
        MapsEngine mapsEngine = new MapsEngine(6, 6);
        mapsEngine.generateMap();

        // Genere un obstacle manuellement
        mapsEngine.setElementMap(2, 2, WALL, true);
        mapsEngine.setElementMap(3, 2, WALL, true);
        mapsEngine.setElementMap(3, 3, WALL, true);
        mapsEngine.setElementMap(2, 3, WALL, true);

        Player player = mapsEngine.getPlayer();
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
    }
}
