package fr.chrzdevelopment;

import static fr.chrzdevelopment.game.Const.EMPTY;
import static fr.chrzdevelopment.game.Const.WALL;
import static org.junit.jupiter.api.Assertions.*;

import fr.chrzdevelopment.game.MapsEngine;
import org.junit.jupiter.api.Test;


public class MapEngineTest
{
    @Test
    public final void generateMapTest()
    {
        // test si il fait bien la generation.
        MapsEngine mapEngine = new MapsEngine(5, 5);

        int[][] mapAttendu = {
                {WALL, WALL, WALL, WALL, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL, WALL, WALL, WALL, WALL}
        };
        mapEngine.generateMap();
        assertArrayEquals(mapAttendu, mapEngine.getMap());

        for (int[] y : mapEngine.getMap())
        {
            System.out.println();
            for (int x : y)
                System.out.print(x);
        }
    }
}
