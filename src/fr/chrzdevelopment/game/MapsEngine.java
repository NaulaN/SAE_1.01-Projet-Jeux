package fr.chrzdevelopment.game;

import fr.chrzdevelopment.game.entities.*;

import java.util.List;

import static fr.chrzdevelopment.game.Const.*;


/**
 * <h1 style="font-size: 115%;">Le générateur de cartes</h1>
 * <h2 style="font-size: 105%; text-decoration: underline;">A quoi sert-il ?</h2>
 * <ul>
 *     <li><p>Genere des obstacles, le nombre d'obstacles est générer de facon aleatoire y compris la taille de celui-ci, mais pas que, le placement des obstacles le sont aussi sur la carte.</p></li>
 *     <li><p>Genere les monstres avec des positions aléatoire grâce a "findALocation()"</p></li>
 *     <li><p>Genere les coffres et les pieces qui permet une victoire sur la carte (Sur les nombres de pieces obtenue)</p></li>
 *     <li><p>Genere les clés qui permet d'ouvrir les coffres</p></li>
 *     <li><p>Dessine la carte grâce a la fonction "draw()"</p></li>
 * </ul>
 *
 * @see fr.chrzdevelopment.game.Game
 *
 * @see fr.chrzdevelopment.game.entities.Player
 * @see fr.chrzdevelopment.game.entities.Monster
 * @see fr.chrzdevelopment.game.entities.Chest
 * @see fr.chrzdevelopment.game.entities.Coin
 * @see fr.chrzdevelopment.game.entities.Key

 * @author CHRZASZCZ Naulan
 */
public class MapsEngine
{
    private boolean[][] calqueCollide;
    private int[][] map;
    // Map size
    private int width;
    private int height;

    private int determinateCoins;
    private int mapLvl = 1;
    private boolean isGenerate = false;


    /**
     * @param width La taille initiale de la carte en largeur
     * @param height La taille initiale de la carte en hauteur
     */
    public MapsEngine(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /** Determine une position et fait en sorte que ne soit pas dans un mur ou dans une autre entité. */
    private int[] findALocation()
    {
        int[] loc = new int[2];
        do {
            loc[0] = (int) (1+Math.random()*map[0].length-1);
            loc[1] = (int) (1+Math.random()*map.length-1);
        } while (map[loc[1]][loc[0]] == WALL || map[loc[1]][loc[0]] == MONSTER || map[loc[1]][loc[0]] == CHEST || map[loc[1]][loc[0]] == COIN);

        return loc;
    }

    /**
     * Crée le joueur sur la carte
     * @param allSprites Permet de ranger et de faire fonctionner les entités générer
     */
    public Player spawnPlayer(List<Entity> allSprites)
    {
        int x; int y; int[] loc;
        loc = findALocation();
        x = loc[0]; y = loc[1];

        // Crée le joueur
        return new Player(allSprites, x, y, 1);
    }

    /**
     * Génère les monstres grâce à un nombre qui est déterminé aléatoirement.
     * @param allSprites Permet de ranger et de faire fonctionner les entités générer
     */
    public void spawnMonster(List<Entity> allSprites)
    {
        int nbMonster = (int) (Math.random()*6);
        finalSpawnMonster(allSprites, nbMonster);
    }

    public void spawnMonster(List<Entity> allSprites, int howMany, int x, int y) { finalSpawnMonster(allSprites, howMany, x, y); }

    private void finalSpawnMonster(List<Entity> allSprites, int nbMonster)
    {
        int x; int y; int[] loc;
        for (int m = 0; m < nbMonster; m++) {
            loc = findALocation();
            x = loc[0];
            y = loc[1];

            // Crée le monstre et le range dans le tableau.
            new Monster(allSprites, x, y, 1);
        }
    }

    private void finalSpawnMonster(List<Entity> allSprites, int nbMonster, int x, int y)
    {
        for (int m = 0; m < nbMonster; m++) {
            // Crée le monstre et le range dans le tableau.
            new Monster(allSprites, x, y, 1);
        }
    }

    /**
     * Génère et determine de manière non definitive les pieces qui faudrait avoir pour gagner le niveau.
     * Le nombre de piece est déterminé de facon aléatoires (Le max est de 10).
     * @param allSprites Permet de ranger et de faire fonctionner les entités générer
     */
    public void spawnCoin(List<Entity> allSprites)
    {
        determinateCoins = (int) (1+Math.random()*11);

        int x; int y; int[] loc;
        // TODO: Faire un truc plus complet avec des formes et des chemins de piece
        for (int c = 0; c < determinateCoins; c++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            new Coin(allSprites, x, y);
        }
    }

    /**
     * Génère les coffres et determine (Si un coffre a une piece ou non) les pieces à avoir de facon definitive pour gagner le niveau
     * Les loots du coffre est determine de facon aléatoire
     * @param allSprites Permet de ranger et de faire fonctionner les entités générer
     * @see fr.chrzdevelopment.game.Const
     */
    public void spawnChest(List<Entity> allSprites)
    {
        int nbChest = 2;

        int x; int y; int[] loc;
        for (int c = 0; c < 2; c++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            // Crée le coffre, on lui dit ce qu'il va loot et on le place dans le tableau
            String loot = LOOTS[(int) (Math.random()*LOOTS.length)];
            if (loot.equalsIgnoreCase("coin"))
                determinateCoins++;
            new Chest(allSprites, loot, x, y);
        }
    }

    /**
     * Génère les clés (Max 2) qui permet d'ouvrir les coffres sur la carte.
     * @param allSprites Permet de ranger et de faire fonctionner les entités générer
     */
    public void spawnKey(List<Entity> allSprites)
    {
        int nbKey = 2;

        int x; int y; int[] loc;
        for (int k = 0; k < nbKey; k++) {
            loc = findALocation();
            x = loc[0]; y = loc[1];

            new Key(allSprites, x, y);
        }
    }

    /**
     * <p>Ecrit sur la carte, une donnée propre a l'entité selon la position en x et en y</p>
     * Si, il ne sait pas déplacé... Il ne clear pas la dernier frame.
     * Sinon, il clear la frame si, il ce deplace.
     * @param entity Un Sprite (ou une entité) qui doit avoir un clear de la frame precedent.
     * @see fr.chrzdevelopment.game.Const
     */
    public void updateEntity(Entity entity, boolean collide)
    {
        // Modifier la carte selon la position du joueur
        setElementMap(entity.getXPosition(), entity.getYPosition(), entity.getDataImg(), collide);
        if (entity.getXPreviousPosition() != -1)    // Dans le cas où, le joueur ne se serait pas déplacé
            // Clear la dernière "frame"
            setElementMap(entity.getXPreviousPosition(), entity.getYPreviousPosition(), EMPTY, false);
    }

    public int[][] getMap() { return map; }
    public boolean getIsGenerate() { return isGenerate; }
    public boolean[][] getCalqueCollide() { return calqueCollide; }
    public int getDeterminateCoins() { return determinateCoins; }
    public int getMapLvl() { return mapLvl; }

    public void setWidth(int newWidthSize) { width = newWidthSize; }
    public void setHeight(int newHeightSize) { height = newHeightSize; }
    /** Place un element sur la matrice de la carte puis determine sur le calque de collision, si c'est un object de type "collide" */
    public void setElementMap(int x, int y, int val, boolean isCollideObject)
    {
        calqueCollide[y][x] = isCollideObject;
        map[y][x] = val;
    }

    /**
     * Mets "isGenerate" en false pour regenerate un niveau (Le niveau suivant).
     * Ajoute +1 a mapLvl.
     */
    public void addMapLvl()
    {
        isGenerate = false;
        mapLvl++;
    }

    /**
     * Genere une map selon la taille contenue dans "width" et "height"
     * @see fr.chrzdevelopment.game.Const
     */
    public void generateMap()
    {
        map = new int[height][width];
        calqueCollide = new boolean[height][width];

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if ((y == 0 || y == height-1) || (x == 0 || x == width-1))
                    setElementMap(x, y, WALL, true);
                else setElementMap(x, y, EMPTY, false);
        isGenerate = true;
    }

    /**
     * <p>Determine premièrement combien d'obstacle il pourrait y avoir.</p>
     * <p>Génère un obstacle dans une table propre a lui (La taille de l'obstacle est determiner aléatoirement)</p>
     * <p>Trouve un endroit ou il pourrait placer l'obstacle</p>
     * @see fr.chrzdevelopment.game.Const
     */
    public void generateObstacles()
    {
        // Determine le nombre d'obstacle a prevoir
        int nbObstacle = (int) (1+Math.random()*4);

        // Generation des obstacles
        for (int o = 0; o <= nbObstacle; o++) {
            // Détermine la taille
            int h = (int) (1+Math.random()*5);
            int w = (int) (1+Math.random()*5);
            // Creation de l'obstacle
            char[][] obstacle = new char[h][w];
            for (int c = 0; c < h; c++)
                for (int r = 0; r < w; r++)
                    obstacle[c][r] = WALL;

            // Placement sur la Map
            int x = Math.abs((int) ((Math.random()*width)-(obstacle[0].length-1)));
            int y = Math.abs((int) ((Math.random()*height)-(obstacle.length-1)));
            // Ecriture sur la Map
            for (int yMap = 0; yMap < y; yMap++)
                for (int xMap = 0; xMap < x; xMap++)
                    if (y+yMap < map.length && x+xMap < map[0].length)
                        setElementMap(x+xMap, y+yMap, WALL, true);
        }
    }

    public void draw()
    {
        for (int[] row : map) {
            StringBuilder line = new StringBuilder();
            for (int column : row)
                allDataObjImg.computeIfPresent(column, (a, b) -> { line.append(b); return b; });
            System.out.println(line);
        }
    }
}
