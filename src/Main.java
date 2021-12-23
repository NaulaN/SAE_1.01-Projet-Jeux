// https://r12a.github.io/app-conversion/  Java char compatibility
import entity.Monster;
import entity.Player;

import static entity.Const.*;


public class Main
{
    public static KeyboardInput keyboardInput = new KeyboardInput();
    public static MapsEngine mapsEngine;

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
        mapsEngine = new MapsEngine(15, 10);
        mapsEngine.generateMap();
        mapsEngine.generateObstacle();
    }

    /**
     * Dessine les elements qui necessite a voir sur la console
     */
    public static void draws()
    {
        Player player = mapsEngine.getPlayer();

        // Modifier la carte selon la position du joueur
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), PLAYER);
        // Clear la derniere "frame"
        mapsEngine.setElementMap(player.getXPreviousPosition(), player.getYPreviousPosition(), EMPTY);

        for (Monster monster : mapsEngine.getAllMonsters())
        {
            // Modifier la carte selon la position du monstre
            mapsEngine.setElementMap(monster.getXPosition(), monster.getYPosition(), MONSTER);
            // Clear la derniere "frame"
            mapsEngine.setElementMap(monster.getXPreviousPosition(), monster.getYPreviousPosition(), EMPTY);
        }
        mapsEngine.draw();
    }

    /**
     * Actualise les valeurs qui ont besoin d'etres actualisé a chaque passage de la boucle
     */
    public static void updates()
    {
        Player player = mapsEngine.getPlayer();

        keyboardInput.getInput();
        for (Monster monster : mapsEngine.getAllMonsters())
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
