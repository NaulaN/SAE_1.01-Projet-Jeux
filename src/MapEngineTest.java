import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class MapEngineTest
{
    @Test
    public final void generateMapTest()
    {
        // test si il fait bien la generation.
        MapsEngine mapEngine = new MapsEngine(5, 5);

        String[][] mapAttendu = {
                {"**","**","**","**","**"},
                {"* ","  ","  ","  "," *"},
                {"* ","  ","  ","  "," *"},
                {"* ","  ","  ","  "," *"},
                {"**","**","**","**","**"}
        };
        mapEngine.generateMap();
        assertArrayEquals(mapAttendu, mapEngine.getMap());

        for (String[] y : mapEngine.getMap())
        {
            System.out.println();
            for (String x : y)
                System.out.print(x);
        }
    }
}
