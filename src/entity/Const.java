package entity;


/**
 * Je voulais utilisé un fichier ".json" mais il faut Gradle pour importer des libraries externe,
 *      Par peur de pas pouvoir me faire noté, je le met dans une classe du nom de Const pour les Constantes
 */
public class Const
{
    public static final String[] LOOTS = {"coins", "nothing", "health"};

    public static final String WALL_IMG = "\uD83E\uDDF1";
    public static final String EMPTY_IMG = "  ";
    public static final String MONSTER_IMG = "\uD83D\uDC7E";
    public static final String PLAYER_IMG = "\uD83E\uDD20";
    public static final String CHEST_IMG = "\uD83E\uDDF0";

    public static final char WALL = '1';
    public static final char EMPTY = '0';
    public static final char MONSTER = '3';
    public static final char PLAYER = '4';
    public static final char CHEST = '5';

    public static final int COLLIDE_OBJ = 1;
    public static final int N0_COLLIDE_OBJ = 0;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int SELECT = 10;
}
