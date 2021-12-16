// https://r12a.github.io/app-conversion/
import entity.Monster;
import entity.Player;


public class Main
{
    public static KeyboardInputListener keyboardInputListener = new KeyboardInputListener();
    public static ConsoleFrame consoleFrame = new ConsoleFrame();
    public static MapsEngine mapsEngine;
    public static Player player;
    // TODO: Seulement pour le test, a remplacer
    public static Monster monster;


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
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), player.getImg());
        // Clear la derniere "frame"
        mapsEngine.setElementMap(player.getXPreviousPosition(), player.getYPreviousPosition(), "  ");

        // TODO: Pour le test !
        // Modifier la carte selon la position du monstre
        mapsEngine.setElementMap(monster.getXPosition(), monster.getYPosition(), monster.getImg());
        // Clear la derniere "frame"
        mapsEngine.setElementMap(monster.getXPreviousPosition(), monster.getYPreviousPosition(), "  ");

        // TODO: A ranger !
        // Dessine la carte
        for (String[] l : mapsEngine.getMap())
        {
            System.out.println();
            for (String r : l)
                System.out.print(r);
        }
    }

    /**
     * Actualise les valeurs qui ont besoin d'etres actualisé a chaque passage de la boucle
     */
    public static void updates()
    {
        keyboardInputListener.getInput();

        // TODO: Pour le test !
        monster.randomMove();

        // TODO: Pas parfait, a corrigé !
        // Gere les collisions et les déplacements du joueur
        boolean collide = player.checkCollision(mapsEngine.getMap());
        if (!collide)
        {
            if (keyboardInputListener.getMoveUp())
                player.moveUp();
            else if (keyboardInputListener.getMoveDown())
                player.moveDown();
            else if (keyboardInputListener.getMoveLeft())
                player.moveLeft();
            else if (keyboardInputListener.getMoveRight())
                player.moveRight();
        }
    }

    /**
     * Demarre la boucle principal du jeux
     */
    public static void loop()
    {
        // TODO: Faire une boucle while correct
        while (true) {
            draws();
            updates();
        }
    }
}
