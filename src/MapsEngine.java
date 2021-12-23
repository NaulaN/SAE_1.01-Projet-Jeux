import entity.Monster;
import entity.Player;

import static entity.Const.*;


public class MapsEngine
{
    private Player player;
    private Monster[] allMonsters;
    private char[][] map;
    private int width;
    private int height;


    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }

    private void spawnEntity()
    {
        int x; int y;
        allMonsters = new Monster[3];

        for (int m = 0; m < allMonsters.length; m++)
        {
            do {
                x = (int) (Math.random()*map[0].length-1);
                y = (int) (Math.random()*map.length-1);
            } while (map[y][x] == WALL);

            allMonsters[m] = new Monster(x, y, 1);
        }

        do {
            x = (int) (Math.random()*map[0].length-1);
            y = (int) (Math.random()*map.length-1);
        } while (map[y][x] == WALL);
        player = new Player(x, y, 1);
    }

    public char[][] getMap() { return map; }
    public Player getPlayer() { return player; }
    public Monster[] getAllMonsters() { return allMonsters; }
    public void setWidth(int newWidthSize) { width = newWidthSize; }
    public void setHeight(int newHeightSize) { height = newHeightSize; }
    public void setElementMap(int x, int y, char val) { map[y][x] = val; }

    /**
     * Genere une map selon la taille specifié lors de la cretion de l'instance de la classe
     */
    public void generateMap()
    {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if ((y == 0 || y == height-1) || (x == 0 || x == width-1))
                    setElementMap(x, y, WALL);
                else setElementMap(x, y, EMPTY);
        spawnEntity();
    }

    public void generateObstacle()
    {
        // Determine le nombre d'obstacle a prevoir
        int nbObstacle = (int) (Math.random()*4)+1;

        for (int o = 0; o <= nbObstacle; o++)
        {
            // Generation des obstacles
            int h = (int) (Math.random()*5)+1;
            int w = (int) (Math.random()*5)+1;

            char[][] obstacle = new char[h][w];
            for (int c = 0; c < h; c++)
                for (int r = 0; r < w; r++)
                    obstacle[c][r] = WALL;

            // Placement sur la Maps
            int x = (int) Math.abs(((Math.random()*width)-(obstacle[0].length-1)));
            int y = (int) Math.abs(((Math.random()*height)-(obstacle.length-1)));

            for (int yMap = 0; yMap < y; yMap++)
                for (int xMap = 0; xMap < x; xMap++)
                    if (y+yMap < map.length && x+xMap < map[0].length)
                        setElementMap(x+xMap, y+yMap, WALL);
        }
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
}
