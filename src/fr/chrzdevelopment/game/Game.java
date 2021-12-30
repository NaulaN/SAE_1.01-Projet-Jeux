package fr.chrzdevelopment.game;
// https://r12a.github.io/app-conversion/   Java char compatibility

import static fr.chrzdevelopment.game.constantes.Const.*;

import fr.chrzdevelopment.game.entity.Monster;
import fr.chrzdevelopment.game.entity.Player;


public class Game
{
    public final KeyboardInput keyboardInput = new KeyboardInput();
    public MapsEngine mapsEngine;

    private boolean running = true;


    public Game() { creates(); }

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
        Player  player = mapsEngine.getPlayer();
        mapsEngine.draw();

        // Affiche les pieces du joueur obtenu et sont nombre de point de vie total (Nombre de <3)
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

    /** Actualise les valeurs qui ont besoin d'etre actualisé à chaque passage de la boucle */
    public void updates()
    {
        for (Monster monster : mapsEngine.getAllMonsters())
        {
            monster.checkCollision(mapsEngine.getCalqueCollide());
            monster.randomMove();
        }

        Player player = mapsEngine.getPlayer();
        player.checkCollision(mapsEngine.getCalqueCollide());
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
        while (running)
        {
            // Clear la console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            updates();
            draws();

            keyboardInput.getInput();
        }
    }
}
