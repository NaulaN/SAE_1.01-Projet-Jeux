package fr.chrzdevelopment.game;

import static fr.chrzdevelopment.game.Const.*;

import java.util.Scanner;


public class KeyboardInput
{
    private final Scanner sc = new Scanner(System.in);
    private int offset = -1;


    public synchronized void getInput()
    {
        String input = sc.nextLine();
        // évite une erreur
        if (input.length() != 0) {
            if (input.equalsIgnoreCase("quit"))
                offset = QUIT;
            else {
                char key = input.toLowerCase().charAt(0);
                switch (key)
                {
                    case 'z':
                        offset = UP;
                        break;
                    case 's':
                        offset = DOWN;
                        break;
                    case 'q':
                        offset = LEFT;
                        break;
                    case 'd':
                        offset = RIGHT;
                        break;
                    case 'a':
                        offset = SELECT;
                        break;
                    default:
                        offset = -1;
                        break;
                }
            }
        }
    }

    public String getStringInput() { return sc.nextLine(); }

    public boolean getQuitAction() { return offset == QUIT; }
    public boolean getMoveUp() { return offset == UP; }
    public boolean getMoveDown() { return offset == DOWN; }
    public boolean getMoveLeft() { return offset == LEFT; }
    public boolean getMoveRight() { return offset == RIGHT; }
    public boolean getSelect() { return offset == SELECT; }
}

