package game;

import static game.constantes.Const.*;
import game.entity.Chest;
import game.entity.Monster;
import game.entity.Player;
import java.util.Random;


public class MapsEngine
{
    private final Random random = new Random();
    private Player player;
    private Monster[] allMonsters;
    private Chest[] allChest;

    private int[][] calqueCollide;
    private int[][] map;
    private int width;
    private int height;


    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
        map = new int[height][width];
        calqueCollide = new int[height][width];
    }

    private void spawnEntity()
    {
        int x; int y;
        allMonsters = new Monster[3];

        for (int m = 0; m < allMonsters.length; m++)
        {
            do {
                x = random.nextInt(0, map[0].length);
                y = random.nextInt(0, map.length);
            } while (map[y][x] == WALL);

            allMonsters[m] = new Monster(x, y, 1);
        }

        do {
            x = random.nextInt(0, map[0].length);
            y = random.nextInt(0, map.length);
        } while (map[y][x] == WALL);

        player = new Player(x, y, 1);
    }

    public void generateLoots()
    {
        int x; int y;
        allChest = new Chest[2];

        for (int c = 0; c < allChest.length; c++)
        {
            do {
                x = random.nextInt(0, map[0].length);
                y = random.nextInt(0, map.length);
            } while (map[y][x] == WALL);

            allChest[c] = new Chest(LOOTS[random.nextInt(0, LOOTS.length)], x, y);
            map[y][x] = allChest[c].getDataImg();
            calqueCollide[y][x] = COLLIDE_OBJ;
        }
    }

    public int[][] getMap() { return map; }
    public int[][] getCalqueCollide() { return calqueCollide; }
    public Player getPlayer() { return player; }
    public Monster[] getAllMonsters() { return allMonsters; }

    public void setWidth(int newWidthSize) { width = newWidthSize; }
    public void setHeight(int newHeightSize) { height = newHeightSize; }
    public void setElementMap(int x, int y, int val, boolean isCollideObject)
    {
        calqueCollide[y][x] = (isCollideObject) ? COLLIDE_OBJ : N0_COLLIDE_OBJ;
        map[y][x] = val;
    }

    /**
     * Genere une map selon la taille specifié lors de la cretion de l'instance de la classe
     */
    public void generateMap()
    {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if ((y == 0 || y == height-1) || (x == 0 || x == width-1))
                    setElementMap(x, y, WALL, true);
                else setElementMap(x, y, EMPTY, false);
        spawnEntity();
    }

    public void generateObstacles()
    {
        // Determine le nombre d'obstacle a prevoir
        int nbObstacle = random.nextInt(1, 4);

        for (int o = 0; o <= nbObstacle; o++)
        {
            // Generation des obstacles
            int h = random.nextInt(1, 5);
            int w = random.nextInt(1, 5);

            char[][] obstacle = new char[h][w];
            for (int c = 0; c < h; c++)
                for (int r = 0; r < w; r++)
                    obstacle[c][r] = WALL;

            // Placement sur la Maps
            int x = Math.abs(random.nextInt(0, width)-(obstacle[0].length-1));
            int y = Math.abs(random.nextInt(0, height)-(obstacle.length-1));

            for (int yMap = 0; yMap < y; yMap++)
                for (int xMap = 0; xMap < x; xMap++)
                    if (y+yMap < map.length && x+xMap < map[0].length)
                        setElementMap(x+xMap, y+yMap, WALL, true);
        }
    }

    public void draw()
    {
        for (int[] row : map) {
            System.out.println();
            for (int column : row)
                switch (column)
                {
                    case WALL -> System.out.print(WALL_IMG);
                    case MONSTER -> System.out.print(MONSTER_IMG);
                    case PLAYER -> System.out.print(PLAYER_IMG);
                    case CHEST -> System.out.print(CHEST_IMG);

                    case EMPTY -> System.out.print(EMPTY_IMG);
                }
        }
    }
}
