package fr.chrzdevelopment.game;

import static fr.chrzdevelopment.game.Const.*;

import fr.chrzdevelopment.game.entities.Chest;
import fr.chrzdevelopment.game.entities.Entity;
import fr.chrzdevelopment.game.entities.Monster;
import fr.chrzdevelopment.game.entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * <h1 style="font-size: 115%;">Le générateur de cartes</h1>
 * <h2 style="font-size: 105%; text-decoration: underline;">Qu'est-ce qu'il fait ?</h2>
 * <ul>
 *     <li><p>Genere une carte avec la taille qu'on lui a mit lors de l'initialisation grâce à la fonction "generationMap()".</p></li>
 *     <li><p>Genere des obstacles, le nombre d'obstacles est générer de facon aleatoire y compris la taille de celui-ci, mais pas que, le placement des obstacles le sont aussi sur la carte.</p></li>
 *     <li><p>Place le joueur sur la carte qui sera obtenable grâce au getter "getPlayer()" ou bien dans cette classe grâce a la variable player.</p></li>
 *     <li><p>Genere les monstres avec des positions aléatoire grâce a "findALocation()" et range les monstres dans "allSprites", c'est Sprite sont updates dans la fonction "updates()" de cette classe</p></li>
 *     <li><p>Genere les coffres et les pieces qui permet une victoire sur la carte (Sur les nombres de pieces obtenue)</p></li>
 *     <li><p>Dessine toutes les choses present sur la carte grâce a la fonction "draw()"</p></li>
 * </ul>
 *
 * @see fr.chrzdevelopment.game.Game
 * @since 1.0
 * @author CHRZASZCZ Naulan
 */
public class MapsEngine
{
    // Entities
    private final List<Entity> allSprites = new ArrayList<>();
    private Chest[] allChest;
    private Player player;
    // Calques
    private boolean[][] calqueCollide;
    private int[][] map;
    // Map size
    private int width;
    private int height;


    /**
     * @param width La taille initiale de la carte en largeur
     * @param height La taille initiale de la carte en hauteur
     */
    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;

        map = new int[height][width];
        calqueCollide = new boolean[height][width];
    }

    /** Determine une position et fait en sorte que ne soit pas dans un mur. */
    private int[] findALocation()
    {
        int[] loc = new int[2];
        do {
            loc[0] = RANDOM.nextInt(0, map[0].length);
            loc[1] = RANDOM.nextInt(0, map.length);
        } while (map[loc[1]][loc[0]] == WALL);

        return loc;
    }

    /** Crée et place les monstres et le joueur sur la carte */
    private void spawnEntity()
    {
        int x; int y; int[] loc;
        int nbMonster = RANDOM.nextInt(0, 6);

        for (int m = 0; m < nbMonster; m++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            // Crée le monstre et le range dans le tableau.
           new Monster(allSprites, x, y, 1);
        }
        loc = findALocation();
        x = loc[0]; y = loc[1];

        // Crée le joueur
        player = new Player(allSprites, x, y, 1);
    }

    /**
     * <p>Ecrit sur la carte, une donnée propre a l'entité selon la position en x et en y</p>
     * <ul>
     *     <li>Si, il ne sait pas déplacé... Il ne clear pas la dernier frame.</li>
     *     <li>Sinon, il clear la frame si, il ce deplace.</li>
     * </ul>
     * @param entity Un Sprite (ou une entité) qui doit avoir un clear de la frame precedent.
     */
    private void updateEntity(Entity entity)
    {
        // Modifier la carte selon la position du joueur
        setElementMap(entity.getXPosition(), entity.getYPosition(), entity.getDataImg(), true);
        if (entity.getXPreviousPosition() != -1)    // Dans le cas où, le joueur ne se serait pas déplacé
            // Clear la dernière "frame"
            setElementMap(entity.getXPreviousPosition(), entity.getYPreviousPosition(), EMPTY, false);
    }

    public int[][] getMap() { return map; }
    /** Donne une matrice de 0 et de 1 qui determine sur la map, qu'est-ce qui ont la fonction "collide" */
    public boolean[][] getCalqueCollide() { return calqueCollide; }
    public Player getPlayer() { return player; }
    public List<Entity> getAllSpritesGroup() { return allSprites; }

    public void setWidth(int newWidthSize) { width = newWidthSize; }
    public void setHeight(int newHeightSize) { height = newHeightSize; }
    /** Place un element sur la matrice de la carte puis determine sur le calque de collision, si c'est un object de type "collide" */
    public void setElementMap(int x, int y, int val, boolean isCollideObject)
    {
        calqueCollide[y][x] = isCollideObject;
        map[y][x] = val;
    }

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
        int nbObstacle = RANDOM.nextInt(1, 4);

        // Generation des obstacles
        for (int o = 0; o <= nbObstacle; o++)
        {
            // Détermine la taille
            int h = RANDOM.nextInt(1, 5);
            int w = RANDOM.nextInt(1, 5);
            // Creation de l'obstacle
            char[][] obstacle = new char[h][w];
            for (int c = 0; c < h; c++)
                for (int r = 0; r < w; r++)
                    obstacle[c][r] = WALL;

            // Placement sur la Map
            int x = Math.abs(RANDOM.nextInt(0, width)-(obstacle[0].length-1));
            int y = Math.abs(RANDOM.nextInt(0, height)-(obstacle.length-1));
            // Ecriture sur la Map
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
        // TODO: Clean c'te fonction
        allChest = new Chest[2];    // 2 coffres max

        for (int c = 0; c < allChest.length; c++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            // Crée le coffre, on lui dit ce qu'il va loot et on le place dans le tableau
            new Chest(allSprites, LOOTS[RANDOM.nextInt(0, LOOTS.length)], x, y);
        }
    }

    public void draw()
    {
        for (int[] row : map)
        {
            StringBuilder line = new StringBuilder();
            for (int column : row)
                allDataObjImg.computeIfPresent(column, (a, b) -> { line.append(b); return b; });
            System.out.println(line.toString());
        }
    }

    public void updates()
    {
        for (Entity sprite : allSprites) {
            sprite.checkCollision(getCalqueCollide());
            sprite.updates();
            updateEntity(sprite);
        }
    }
}
