import static entity.Const.EMPTY;
import static entity.Const.WALL;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class MapEngineTest
{
    @Test
    public final void generateMapTest()
    {
        // test si il fait bien la generation.
        MapsEngine mapEngine = new MapsEngine(5, 5);

        char[][] mapAttendu = {
                {WALL, WALL, WALL, WALL, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL,EMPTY,EMPTY,EMPTY, WALL},
                {WALL, WALL, WALL, WALL, WALL}
        };
        mapEngine.generateMap();
        assertArrayEquals(mapAttendu, mapEngine.getMap());

        for (char[] y : mapEngine.getMap())
        {
            System.out.println();
            for (char x : y)
                System.out.print(x);
        }
    }
}
