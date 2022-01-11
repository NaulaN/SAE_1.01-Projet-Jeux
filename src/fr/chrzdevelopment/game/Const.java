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
    public static String KEY_IMG;
    public static String RECT_RED_IMG;   // Pour le debug des collisions
    public static String LASER_VERTICAL_IMG;
    public static String LASER_HORIZONTAL_IMG;

    public static Map<Integer, String> allDataObjImg;
    public static Map<String, Integer> allDataObj;

    // Une liste qui determine qu'est-ce qu'un objet vas drop (loot)
    public static final String[] LOOTS = {"coin", "nothing", "health"};

    // Les données des objects.
    public static final int EMPTY = 0;

    public static final int WALL = 1;
    public static final int MONSTER = 3;
    public static final int PLAYER = 4;
    public static final int CHEST = 5;
    public static final int CHEST_OPEN = 6;
    public static final int COIN = 7;
    public static final int KEY = 8;
    public static final int LASER_VERTICAL = 20;
    public static final int LASER_HORIZONTAL = 21;

    // Les valeurs qui determines les mouvements d'un objet
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    // Les valeurs qui determines une interaction.
    public static final int SELECT = 10;
    public static final int QUIT = 11;


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
            put(KEY, KEY_IMG);
            put(LASER_VERTICAL, LASER_VERTICAL_IMG);
            put(LASER_HORIZONTAL, LASER_HORIZONTAL_IMG);
        }};

        allDataObj = new HashMap<>() {{
            put("wall", WALL);
            put("monster", MONSTER);
            put("player", PLAYER);
            put("chest", CHEST);
            put("chest_open", CHEST_OPEN);
            put("coin", COIN);
            put("key", KEY);
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
        KEY_IMG = "\uD83D\uDD11";
        LASER_VERTICAL_IMG = "\u26A1";
        LASER_HORIZONTAL_IMG = "\u26A1";

        initGraphics();
    }

    /** Charge les graphismes compatible pour une console Windows */
    public static void windowsGraphics()
    {
        EMPTY_IMG = " " + ANSI_RESET;
        WALL_IMG = "\u2588" + ANSI_RESET;
        MONSTER_IMG = ANSI_RED + "\u25A0" + ANSI_RESET;
        PLAYER_IMG = ANSI_PURPLE + "\u25A0" + ANSI_RESET;
        CHEST_IMG = ANSI_CYAN + "\u25A0" + ANSI_RESET;
        CHEST_OPEN_IMG = ANSI_GREEN + "\u25A0" + ANSI_RESET;
        COIN_IMG = ANSI_YELLOW + "\u25A0" + ANSI_RESET;
        HEART_IMG = ANSI_RED + "\u2665" + ANSI_RESET;
        RECT_RED_IMG = "C" + ANSI_RESET;
        KEY_IMG = ANSI_YELLOW + "*" + ANSI_RESET;
        LASER_VERTICAL_IMG = ANSI_BLUE + "\u25A0" + ANSI_RESET;
        LASER_HORIZONTAL_IMG = ANSI_BLUE +  "\u25A0" + ANSI_RESET;

        initGraphics();
    }
}
