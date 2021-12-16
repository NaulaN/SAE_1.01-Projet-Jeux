// https://r12a.github.io/app-conversion/
import entity.Player;


public class Main
{
    public static KeyboardInputListener keyboardInputListener = new KeyboardInputListener();
    public static ConsoleFrame consoleFrame = new ConsoleFrame();
    public static MapsEngine mapsEngine;
    public static Player player;


    public static void main(String[] args)
    {
        creates();
        loop();
    }

    public static void creates()
    {
        mapsEngine = new MapsEngine(10, 10);
        mapsEngine.generateMap();
        player = new Player(1, 1, 1);
    }

    public static void draws()
    {
        consoleFrame.draws();
        mapsEngine.setElementMap(player.getXPosition(), player.getYPosition(), player.getImg());
        mapsEngine.setElementMap(player.getXPreviousPosition(), player.getYPreviousPosition(), "  ");

        for (String[] l : mapsEngine.getMap())
        {
            System.out.println();
            for (String r : l)
                System.out.print(r);
        }
    }

    public static void updates()
    {
        keyboardInputListener.getInput();

        boolean collide = player.checkCollision(mapsEngine.getMap());
        if (!collide) {
            if (keyboardInputListener.getMoveUp())
                player.moveUp();
            else if (keyboardInputListener.getMoveDown())
                player.moveDown();
            else if (keyboardInputListener.getMoveLeft())
                player.moveLeft();
            else if (keyboardInputListener.getMoveRight())
                player.moveRight();
        }
    }

    public static void loop()
    {
        while (true) {
            draws();
            updates();
        }
    }
}
