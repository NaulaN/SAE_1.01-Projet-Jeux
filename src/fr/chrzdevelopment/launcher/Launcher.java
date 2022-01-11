package fr.chrzdevelopment.launcher;

import fr.chrzdevelopment.game.Game;


public class Launcher
{


    public static void main(String[] args)
    {
        Game game = new Game();

        // Démarre le jeu
        game.loop();
    }
}
