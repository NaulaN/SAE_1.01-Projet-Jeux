package fr.chrzdevelopment.game;

import java.util.HashMap;
import java.util.Map;


public class Const
{
    public static final String DEFAULT_RESOURCE_PATH = "res/";

    // Détermine une couleur dans un terminal.
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
    public static String SWORD_IMG;
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
    public static final int SWORD = 9;
    public static final int LASER_VERTICAL = 20;
    public static final int LASER_HORIZONTAL = 21;

    // Les valeurs qui determines les mouvements d'un objet
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    // Les valeurs qui determines une interaction.
    public static final int SELECT = 10;
    public static final int LAUNCH = 11;
    public static final int QUIT = 12;

    public static final int TIME_INVULNERABILITY = 20;


    /** Range toutes les données dans une HashMap */
    private static void initGraphics()
    {
        allDataObjImg = new HashMap<>();
        allDataObjImg.put(EMPTY, EMPTY_IMG);
        allDataObjImg.put(WALL, WALL_IMG);
        allDataObjImg.put(MONSTER, MONSTER_IMG);
        allDataObjImg.put(PLAYER, PLAYER_IMG);
        allDataObjImg.put(CHEST, CHEST_IMG);
        allDataObjImg.put(CHEST_OPEN, CHEST_OPEN_IMG);
        allDataObjImg.put(COIN, COIN_IMG);
        allDataObjImg.put(KEY, KEY_IMG);
        allDataObjImg.put(SWORD, SWORD_IMG);
        allDataObjImg.put(LASER_VERTICAL, LASER_VERTICAL_IMG);
        allDataObjImg.put(LASER_HORIZONTAL, LASER_HORIZONTAL_IMG);

        allDataObj = new HashMap<>();
        allDataObj.put("wall", WALL);
        allDataObj.put("monster", MONSTER);
        allDataObj.put("player", PLAYER);
        allDataObj.put("chest", CHEST);
        allDataObj.put("chest_open", CHEST_OPEN);
        allDataObj.put("coin", COIN);
        allDataObj.put("key", KEY);
        allDataObj.put("sword", SWORD);
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
        SWORD_IMG = "\uD83E\uDE93";
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
        SWORD_IMG = ANSI_WHITE + "/" + ANSI_RESET;
        RECT_RED_IMG = "C" + ANSI_RESET;
        KEY_IMG = ANSI_YELLOW + "*" + ANSI_RESET;
        LASER_VERTICAL_IMG = ANSI_BLUE + "\u25A0" + ANSI_RESET;
        LASER_HORIZONTAL_IMG = ANSI_BLUE +  "\u25A0" + ANSI_RESET;

        initGraphics();
    }
}
