package fr.chrzdevelopment.game;
// https://r12a.github.io/app-conversion/   Java char compatibility

import static fr.chrzdevelopment.game.Const.*;

import fr.chrzdevelopment.game.entities.Player;

import java.io.IOException;


/**
 * <p>Permet le bon fonctionnement du jeu</p>
 * <p>Rassemble tous !</p>
 */
public class Game
{
    public final KeyboardInput keyboardInput = new KeyboardInput();
    public MapsEngine mapsEngine;
    public final String OS = System.getProperty("os.name");
    // clear terminal commands
    public final ProcessBuilder processBuilder = (OS.equalsIgnoreCase("windows")) ? new ProcessBuilder("cmd", "/c", "cls") : new ProcessBuilder("clear");

    private boolean running = true;


    public Game()
    {
        if (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10"))
            windowsGraphics();
        else linuxGraphics();

        // Crée la taille de la carte
        mapsEngine = new MapsEngine(15, 10);
        // La generation.
        mapsEngine.generateMap();
        mapsEngine.generateObstacles();
        mapsEngine.generateLoots();
    }

    /**
     * Nettoie tout ce qui est present et afficher sur le terminal.
     * @exception IOException Essaye avec une deuxième méthode pour nettoyer la console
     * @exception InterruptedException Essaye encore une fois tout le processus
     */
    private void clearConsole()
    {
        // TODO: Demander a l'enseignant si cela est correct. J'ai essayé de bien faire pour qu'il est deux essaie au cas où
        // Essaye encore une fois si le premier essaie ne marche pas sinon il casse la boucle.
        for (int r = 0; r < 2; r++)
        {
            Process process = null;
            try {
                process = processBuilder.inheritIO().start();
            } catch (IOException IOe) {
                IOe.printStackTrace();
                if (!OS.equalsIgnoreCase("windows") || !OS.equalsIgnoreCase("windows 10")) {
                    // la deuxième méthode qui permet de nettoyer la console...
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            }

            if (process != null)
                try {
                    process.waitFor();
                    if (process.exitValue() == 0)
                        return;
                } catch (InterruptedException ignored) {
                    if (process.isAlive())
                        process.destroyForcibly();
                }
        }
    }

    /** Dessine les elements qui nécessite à voir sur la console */
    public void draws()
    {
        clearConsole();

        Player player = mapsEngine.getPlayer();

        System.out.println("\t" + ANSI_RED + "NIVEAUX: " + mapsEngine.getMapLvl());
        System.out.println(ANSI_GREEN + "Vous devez avoir " + mapsEngine.getDeterminateCoins() + " " + COIN_IMG + " pour pouvoir gagner le niveau" + ANSI_RESET);
        mapsEngine.draw();

        // Affiche les pieces du joueur obtenu et sont nombre de point de vie total (Nombre de <3)
        StringBuilder msgHud = new StringBuilder()
                .append(COIN_IMG)
                .append(": ")
                .append(player.getCoins())
                .append("   ");
        for (int h = 1; h <= player.getHealth(); h++)
            msgHud.append(HEART_IMG)
                    .append(" ");
        System.out.print("\t" + msgHud.toString());
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

        // TODO: faire un truc plus propre pour les changements de niveau
        if (player.getCoins() == mapsEngine.getDeterminateCoins())
        {
            mapsEngine.addMapLvl();
            running = false;
        }
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
