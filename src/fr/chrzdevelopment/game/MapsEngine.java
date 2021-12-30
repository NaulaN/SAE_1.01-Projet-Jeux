package fr.chrzdevelopment.game;

import static fr.chrzdevelopment.game.constantes.Const.*;

import fr.chrzdevelopment.game.entities.Chest;
import fr.chrzdevelopment.game.entities.Entity;
import fr.chrzdevelopment.game.entities.Monster;
import fr.chrzdevelopment.game.entities.Player;
import fr.chrzdevelopment.game.entities.Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapsEngine
{
    private final Random random = new Random();
    // Entities
    private List<Monster> allMonsters = new ArrayList<>();
    private List<Coin> allCoins = new ArrayList<>();
    // private Monster[] allMonsters;
    private Chest[] allChest;
    private Player player;
    // Calques
    private boolean[][] calqueCollide;
    private int[][] map;
    // Map size
    private int width;
    private int height;


    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
        map = new int[height][width];
        calqueCollide = new boolean[height][width];
    }

    /* private function */
    /** Determine une position et fait en sorte que ne soit pas dans un mur. */
    private int[] findALocation()
    {
        int[] loc = new int[2];
        do {
            loc[0] = random.nextInt(0, map[0].length);
            loc[1] = random.nextInt(0, map.length);
        } while (map[loc[1]][loc[0]] == WALL);

        return loc;
    }

    /** Crée et place les monstres et le joueur sur la carte */
    private void spawnEntity()
    {
        int x; int y; int[] loc;
        int nbMonster = random.nextInt(0, 6);

        for (int m = 0; m < nbMonster; m++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            // Crée le monstre et le range dans le tableau.
            allMonsters.add(new Monster(x, y, 1));
        }
        loc = findALocation();
        x = loc[0]; y = loc[1];

        // Crée le joueur
        player = new Player(x, y, 1);
    }

    private void updateEntity(Entity entity)
    {
        // Modifier la carte selon la position du joueur
        setElementMap(entity.getXPosition(), entity.getYPosition(), entity.getDataImg(), true);
        if (entity.getXPreviousPosition() != -1)    // Dans le cas où, le joueur ne se serait pas déplacé
            // Clear la dernière "frame"
            setElementMap(entity.getXPreviousPosition(), entity.getYPreviousPosition(), EMPTY, false);
    }

    /* getters */
    public int[][] getMap() { return map; }
    /** getCalqueCollide() -> Donne une matrice de 0 et de 1 qui determine sur la map, qu'est-ce qui ont la fonction "collide" */
    public boolean[][] getCalqueCollide() { return calqueCollide; }
    public Player getPlayer() { return player; }
    public List<Monster> getAllMonsters() { return allMonsters; }

    /* setter */
    public void setWidth(int newWidthSize) { width = newWidthSize; }
    public void setHeight(int newHeightSize) { height = newHeightSize; }
    /** Place un element sur la matrice de la carte puis determine sur le calque de collision, si c'est un object de type "collide" */
    public void setElementMap(int x, int y, int val, boolean isCollideObject)
    {
        calqueCollide[y][x] = isCollideObject;
        map[y][x] = val;
    }

    /* public function */
    /** Genere une map selon la taille specifié lors de l'initialisation de la classe */
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

        for (int o = 0; o <= nbObstacle; o++) {
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

    /** Génère les coffres, les pieces et les/la clé(s) */
    public void generateLoots()
    {
        int x; int y; int[] loc;
        allChest = new Chest[2];    // 2 coffres max

        for (int c = 0; c < allChest.length; c++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            // Crée le coffre, on lui dit ce qu'il va loot et on le place dans le tableau
            allChest[c] = new Chest(LOOTS[random.nextInt(0, LOOTS.length)], x, y);
            setElementMap(x, y, allChest[c].getDataImg(), true);
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
                    case COIN -> System.out.print(COIN_IMG);

                    case EMPTY -> System.out.print(EMPTY_IMG);
                }
        }
    }

    public void updates()
    {
        updateEntity(player);

        for (Monster monster : allMonsters) {
            if (monster.getHealth() <= 0)   // Libere la mémoire si un monstre est died
                allMonsters.remove(monster);
            updateEntity(monster);
        }

        /*
        for (Coin coin : allCoins) {
            if ()
        }
         */
    }
}
