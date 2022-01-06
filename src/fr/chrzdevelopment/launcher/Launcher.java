package fr.chrzdevelopment.launcher;

import fr.chrzdevelopment.game.Game;


public class Launcher
{
    public static void main(String[] args)
    {
        Game game = new Game();

        // game.mapsEngine.setHeight(20);
        // game.mapsEngine.setWidth(40);

        // Démarre le jeu
        game.loop();
    }
}
