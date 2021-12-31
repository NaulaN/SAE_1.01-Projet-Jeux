package fr.chrzdevelopment.game;
// https://r12a.github.io/app-conversion/   Java char compatibility

import static fr.chrzdevelopment.game.Const.*;

import fr.chrzdevelopment.game.entities.Player;


public class Game
{
    public final KeyboardInput keyboardInput = new KeyboardInput();
    public MapsEngine mapsEngine;
    public final String OS = System.getProperty("os.name");
    public final ProcessBuilder processBuilder = (OS.equalsIgnoreCase("windows")) ? new ProcessBuilder("cmd", "/c", "cls") : new ProcessBuilder("clear");

    private boolean running = true;


    public Game() { creates(); }

    private void clearConsole()
    {
        try {
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    /** Crée les premieres resources essentiel au démarrage du jeu */
    public void creates()
    {
        // Crée la taille de la carte
        mapsEngine = new MapsEngine(15, 10);
        // La generation
        mapsEngine.generateMap();
        mapsEngine.generateObstacles();
        mapsEngine.generateLoots();
    }

    /** Dessine les elements qui nécessite à voir sur la console */
    public void draws()
    {
        clearConsole();

        Player  player = mapsEngine.getPlayer();
        mapsEngine.draw();

        // Affiche les pieces du joueur obtenu et sont nombre de point de vie total (Nombre de <3)
        StringBuilder msgHud = new StringBuilder().append("\t")
                .append(COIN_IMG)
                .append(": ")
                .append(player.getCoins())
                .append("   ");
        for (int h = 1; h <= player.getHealth(); h++)
            msgHud.append(HEART_IMG)
                    .append(" ");
        System.out.print(msgHud.toString());
    }

    /** Actualise les valeurs qui ont besoin d'etre actualisé à chaque passage de la boucle */
    public void updates()
    {
        Player player = mapsEngine.getPlayer();
        // les déplacements du joueur
        if (!player.getCollideUp() && keyboardInput.getMoveUp())
            player.moveUp();
        if (!player.getCollideDown() && keyboardInput.getMoveDown())
            player.moveDown();
        if (!player.getCollideLeft() && keyboardInput.getMoveLeft())
            player.moveLeft();
        if (!player.getCollideRight() && keyboardInput.getMoveRight())
            player.moveRight();

        mapsEngine.updates();
    }

    /** Démarre la boucle principale du jeu */
    public void loop()
    {
        while (running) {
            updates();
            draws();

            keyboardInput.getInput();
        }
    }
}
