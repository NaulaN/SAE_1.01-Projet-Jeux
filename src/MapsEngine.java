import entity.Const;

import static entity.Const.*;

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
                if ((y == 0 || y == height-1) || (x == 0 || x == width-1))
                    map[y][x] = WALL; // Wall
                else map[y][x] = Const.EMPTY;  // Fill
    }

    public void draw()
    {
        for (char[] row : map) {
            System.out.println();
            for (char column : row)
                switch (column)
                {
                    case WALL -> System.out.print(WALL_IMG);
                    case MONSTER -> System.out.print(MONSTER_IMG);
                    case PLAYER -> System.out.print(PLAYER_IMG);

                    case EMPTY -> System.out.print(EMPTY_IMG);
                }
        }
    }

    public char[][] getMap() { return map; }
    public void setElementMap(int x, int y, char val) { map[y][x] = val; }
}
