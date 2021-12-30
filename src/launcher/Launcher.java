package launcher;

import game.Game;


public class Launcher
{
    public static final Game game = new Game();


    public static void main(String[] args) { game.loop(); }
}
