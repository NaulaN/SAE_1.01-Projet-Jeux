

public class MapsEngine
{
    private char[][] map;
    private int width;
    private int height;


    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }

    /**
     * Genere une map selon la taille specifié lors de la cretion de l'instance de la classe
     */
    public void generateMap()
    {
        for (int y = 0; y < width; y++)
            for (int x = 0; x < height; x++)
                if (y == 0 || y == height-1)
                    map[y][x] = '1';    // Wall
                else
                    if (x == 0 || x == width-1)
                        map[y][x] = (x != 0) ? '2' : '3';    // Border wall
                    else map[y][x] = '0';  // Fill
    }

    public char[][] getMap() { return map; }
    public void setElementMap(int x, int y, char val) { map[y][x] = val; }
}
