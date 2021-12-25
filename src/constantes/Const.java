package constantes;


/** Je voulais utilisé un fichier ".json" mais il faut Gradle pour importer des libraries externe,
 *      Par peur de pas pouvoir me faire noté, je le met dans une classe du nom de Const pour les Constantes */
public class Const
{
    // Les attaques spéciales
    // TODO: Traduire les variables en anglais de preference dans le code.
    public static final int CHARGE = 0;
    public static final int VIVE_ATTAQUE = 1;
    public static final int ULTRALASER = 2;
    public static final int ABRIS = 3;
    public static final int LUMICANON = 4;
    public static final int COUP_DE_BOULE = 5;
    public static final int STACKOVERFLOW = 6;

    // Une liste qui determine qu'est-ce qu'un objet vas drop (loot)
    public static final String[] LOOTS = {"coins", "nothing", "health", "cp"};
    public static final int[] LOOTS_CP = {ULTRALASER, LUMICANON, STACKOVERFLOW};

    // Les choses qui vont être dessiné à l'écran.
    public static final String EMPTY_IMG = "  ";
    public static final String WALL_IMG = "\uD83E\uDDF1";
    public static final String MONSTER_IMG = "\uD83D\uDC7E";
    public static final String PLAYER_IMG = "\uD83E\uDD20";
    public static final String CHEST_IMG = "\uD83E\uDDF0";
    public static final String COIN_IMG = "\uD83E\uDE99";
    public static final String HEART_IMG = "\u2764\uFE0F";
    public static final String RECT_RED_IMG = "\uD83D\uDFE5";   // Pour le debug

    // Les données des objects.
    public static final int WALL = 1;
    public static final int EMPTY = 0;
    public static final int MONSTER = 3;
    public static final int PLAYER = 4;
    public static final int CHEST = 5;

    // Permet de savoir si un objet est un object qui doit avoir des collisions
    public static final int COLLIDE_OBJ = 1;
    public static final int N0_COLLIDE_OBJ = 0;

    // Les valeurs qui determines les mouvements d'un objet
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    // Les valeurs qui determines une interaction.
    public static final int SELECT = 10;
}
