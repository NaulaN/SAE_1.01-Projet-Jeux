package fr.chrzdevelopment.game;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** Je voulais utilisé un fichier ".json" mais il faut Gradle pour importer des libraries externe,
 *      Par peur de pas pouvoir me faire noté, je le met dans une classe du nom de Const pour les Constantes */
public class Const
{
    public static final Random RANDOM = new Random();


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Les choses qui vont être dessiné à l'écran.
    public static String EMPTY_IMG;
    public static String WALL_IMG;
    public static String MONSTER_IMG;
    public static String PLAYER_IMG;
    public static String CHEST_IMG;
    public static String CHEST_OPEN_IMG;
    public static String COIN_IMG;
    public static String HEART_IMG;
    public static String RECT_RED_IMG;   // Pour le debug des collisions

    public static Map<Integer, String> allDataObjImg;
    public static Map<String, Integer> allDataObj;

    // Une liste qui determine qu'est-ce qu'un objet vas drop (loot)
    public static final String[] LOOTS = {"coins", "nothing", "health", "cp"};

    // Les données des objects.
    public static final int EMPTY = 0;

    public static final int WALL = 1;
    public static final int MONSTER = 3;
    public static final int PLAYER = 4;
    public static final int CHEST = 5;
    public static final int CHEST_OPEN = 6;
    public static final int COIN = 7;

    // Les valeurs qui determines les mouvements d'un objet
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    // Les valeurs qui determines une interaction.
    public static final int SELECT = 10;


    private static void initGraphics()
    {
        allDataObjImg = new HashMap<>() {{
            put(EMPTY, EMPTY_IMG);
            put(WALL, WALL_IMG);
            put(MONSTER, MONSTER_IMG);
            put(PLAYER, PLAYER_IMG);
            put(CHEST, CHEST_IMG);
            put(CHEST_OPEN, CHEST_OPEN_IMG);
            put(COIN, COIN_IMG);
        }};

        allDataObj = new HashMap<>() {{
            put("wall", WALL);
            put("monster", MONSTER);
            put("player", PLAYER);
            put("chest", CHEST);
            put("chest_open", CHEST_OPEN);
            put("coin", COIN);
        }};
    }

    /** Charge les graphismes compatible pour un terminal et un kernel Linux */
    public static void linuxGraphics()
    {
        EMPTY_IMG = "  ";
        WALL_IMG = "\uD83E\uDDF1";
        MONSTER_IMG = "\uD83D\uDC7E";
        PLAYER_IMG = "\uD83E\uDD20";
        CHEST_IMG = "\uD83E\uDDF0";
        CHEST_OPEN_IMG = "\uD83E\uDDF0";
        COIN_IMG = "\uD83D\uDCB0";
        HEART_IMG = "\u2764\uFE0F";
        RECT_RED_IMG = "\uD83D\uDFE5";

        initGraphics();
    }

    /** Charge les graphismes compatible pour une console Windows */
    public static void windowsGraphics()
    {
        EMPTY_IMG = " ";
        WALL_IMG = "\u2588";
        MONSTER_IMG.concat(ANSI_RED)
                .concat("\u25A0");
        PLAYER_IMG.concat(ANSI_PURPLE)
                .concat("\u25A0");
        CHEST_IMG.concat(ANSI_CYAN)
                .concat("\u25A0");
        CHEST_OPEN_IMG.concat(ANSI_GREEN)
                .concat("\u25A0");
        COIN_IMG.concat(ANSI_YELLOW)
                .concat("\u25A0");
        HEART_IMG.concat(ANSI_RED)
                .concat("\u2665");
        RECT_RED_IMG = "C";

        initGraphics();
        allDataObjImg.forEach((integer, s) -> s.concat(ANSI_RESET));
    }
}
