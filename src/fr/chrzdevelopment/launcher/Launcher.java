package fr.chrzdevelopment.launcher;

import fr.chrzdevelopment.game.Game;


public class Launcher
{
    public static final Game game = new Game();


    public static void main(String[] args)
    {
        // game.mapsEngine.setHeight(20);
        // game.mapsEngine.setWidth(40);

        // Démarre le jeu
        game.loop();
    }
}
