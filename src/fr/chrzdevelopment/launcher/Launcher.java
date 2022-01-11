package fr.chrzdevelopment.launcher;
/*
    Developer par CHRZASZCZ Naulan
        Jeu avec un affichage uniquement sur le terminal
 */

import fr.chrzdevelopment.game.Game;


/**
 * Classe qui démarre le jeu
 * @see fr.chrzdevelopment.game.Game
 */
public class Launcher
{


    public static void main(String[] args)
    {
        Game game = new Game();
        game.loop();
    }
}
