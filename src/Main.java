// https://r12a.github.io/app-conversion/   Java char compatibility
import static constantes.Const.*;
import entity.Monster;
import entity.Player;


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

    /** Crée les premieres resources essentiel au démarrage du jeux */
    public static void creates()
    {
        // Crée la taille de la carte
        mapsEngine = new MapsEngine(15, 10);
        // La generation
        mapsEngine.generateMap();
        mapsEngine.generateObstacles();
        mapsEngine.generateLoots();
    }

    /** Dessine les elements qui necessite a voir sur la console */
    public static void draws()
    {
        Player player = mapsEngine.getPlayer();

        // Modifier la carte selon la position du joueur
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), PLAYER, true);
        if (player.getXPreviousPosition() != -1)
            // Clear la derniere "frame"
            mapsEngine.setElementMap(player.getXPreviousPosition(), player.getYPreviousPosition(), EMPTY, false);

        for (Monster monster : mapsEngine.getAllMonsters())
        {
            // Modifier la carte selon la position du monstre
            mapsEngine.setElementMap(monster.getXPosition(), monster.getYPosition(), MONSTER, true);
            if (monster.getXPreviousPosition() != -1)
                // Clear la derniere "frame"
                mapsEngine.setElementMap(monster.getXPreviousPosition(), monster.getYPreviousPosition(), EMPTY, false);
        }
        mapsEngine.draw();

        System.out.println();
        StringBuilder msgHud = new StringBuilder().append('\t')
                .append(COIN_IMG)
                .append(": ")
                .append(player.getCoins())
                .append("   ");
        for (int h = 1; h <= player.getHealth(); h++)
            msgHud.append(HEART_IMG)
                    .append(" ");
        System.out.print(msgHud.toString());
    }

    /** Actualise les valeurs qui ont besoin d'etres actualisé a chaque passage de la boucle */
    public static void updates()
    {
        keyboardInput.getInput();

        Player player = mapsEngine.getPlayer();
        player.checkCollision(mapsEngine.getCalqueCollide());

        for (Monster monster : mapsEngine.getAllMonsters())
        {
            monster.checkCollision(mapsEngine.getCalqueCollide());
            monster.randomMove();
        }

        // les déplacements du joueur
        if (!player.getCollideUp() && keyboardInput.getMoveUp())
            player.moveUp();
        if (!player.getCollideDown() && keyboardInput.getMoveDown())
            player.moveDown();
        if (!player.getCollideLeft() && keyboardInput.getMoveLeft())
            player.moveLeft();
        if (!player.getCollideRight() && keyboardInput.getMoveRight())
            player.moveRight();
    }

    /** Demarre la boucle principal du jeux */
    public static void loop()
    {
        while (running)
        {
            // Clear la console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            draws();
            updates();
        }
    }
}
