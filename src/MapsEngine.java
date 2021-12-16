

public class MapsEngine
{
    private String[][] map;
    private int width;
    private int height;


    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
        map = new String[height][width];
    }

    public String[][] getMap() { return map; }

    public void setElementMap(int x, int y, String val) { map[y][x] = val; }

    /**
     * Genere une map selon la taille specifié lors de la cretion de l'instance de la classe
     */
    public void generateMap()
    {
        for (int y = 0; y < width; y++)
            for (int x = 0; x < height; x++)
            {
                if (y == 0 || y == height-1)
                    map[y][x] = "**";    // Wall
                else {
                    if (x == 0 || x == width-1)
                        map[y][x] = (x != 0) ? " *" : "* ";    // Border wall
                    else map[y][x] = "  ";  // Fill
                }
            }
    }
}
