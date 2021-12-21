// https://r12a.github.io/app-conversion/  Java char compatibility
import entity.Monster;
import entity.Player;


public class Main
{
    public static KeyboardInput keyboardInput = new KeyboardInput();
    public static ConsoleFrame consoleFrame = new ConsoleFrame();
    public static MapsEngine mapsEngine;
    public static Player player;
    // TODO: Seulement pour le test, a remplacer
    public static Monster monster;

    public static boolean running = true;


    public static void main(String[] args)
    {
        creates();
        loop();
    }

    /**
     * Crée les premieres resources essentiel du jeux
     */
    public static void creates()
    {
        // Crée la taille de la carte et génère la carte
        mapsEngine = new MapsEngine(10, 10);
        mapsEngine.generateMap();
        // Crée le joueur sur les cordonnées '1' en x et '1' en y avec une vitesse de 1
        player = new Player(1, 1, 1);

        monster = new Monster(3, 3, 1);
    }

    /**
     * Dessine les elements qui necessite a voir sur la console
     */
    public static void draws()
    {
        // TODO: Ne fait rien pour l'instant :)
        consoleFrame.draws();
        // Modifier la carte selon la position du joueur
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), '8');
        // Clear la derniere "frame"
        mapsEngine.setElementMap(player.getXPreviousPosition(), player.getYPreviousPosition(), '0');

        // TODO: Pour le test ! Probleme clear
        // Modifier la carte selon la position du monstre
        mapsEngine.setElementMap(monster.getXPosition(), monster.getYPosition(), '9');
        // Clear la derniere "frame"
        mapsEngine.setElementMap(monster.getXPreviousPosition(), monster.getYPreviousPosition(), '0');

        // TODO: A ranger !
        // Dessine la carte
        for (char[] l : mapsEngine.getMap())
        {
            System.out.println();
            for (char r : l)
                System.out.print(r);
        }
    }

    /**
     * Actualise les valeurs qui ont besoin d'etres actualisé a chaque passage de la boucle
     */
    public static void updates()
    {
        keyboardInput.getInput();

        // TODO: Pour le test !
        monster.randomMove(mapsEngine.getMap());

        // Gere les collisions et les déplacements du joueur
        boolean[] collide = player.checkCollision(mapsEngine.getMap());
        if (!collide[0] && keyboardInput.getMoveUp())
            player.moveUp();
        else if (!collide[1] && keyboardInput.getMoveDown())
            player.moveDown();
        else if (!collide[2] && keyboardInput.getMoveLeft())
            player.moveLeft();
        else if (!collide[3] && keyboardInput.getMoveRight())
            player.moveRight();
    }

    /**
     * Demarre la boucle principal du jeux
     */
    public static void loop()
    {
        // TODO: Faire une boucle while correct
        while (running)
        {
            draws();
            updates();
        }
    }
}
