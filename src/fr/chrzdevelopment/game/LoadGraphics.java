package fr.chrzdevelopment.game;

import java.util.HashMap;
import java.util.Map;

public class LoadGraphics implements ANSIs, TilesData
{
    public static final Map<Integer, String> allDataObjImg = new HashMap<>();
    public static final Map<String, Integer> allDataObj = new HashMap<>();

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


    public static void initGraphics(String OS)
    {
        if (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10")) {
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
        } else {
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
    }

    public static void initGraphics()
    {
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

        allDataObj.put("wall", WALL);
        allDataObj.put("monster", MONSTER);
        allDataObj.put("player", PLAYER);
        allDataObj.put("chest", CHEST);
        allDataObj.put("chest_open", CHEST_OPEN);
        allDataObj.put("coin", COIN);
        allDataObj.put("key", KEY);
        allDataObj.put("sword", SWORD);
    }
}
