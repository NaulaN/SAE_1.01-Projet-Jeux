package fr.chrzdevelopment.game;

import java.util.Scanner;

import static fr.chrzdevelopment.game.Const.*;


/**
 * Gere tous les imputs qui sera entré dans le terminal grâce au clavier
 *
 * @see fr.chrzdevelopment.game.Game
 * @author CHRZASZCZ Naulan
 */
public class KeyboardInput
{
    private final Scanner sc = new Scanner(System.in);
    private int offset = -1;

    public synchronized void input()
    {
        String input = sc.nextLine();
        associateInputWithOffset(input);
    }

    public synchronized void input(String input) { associateInputWithOffset(input); }

    private void associateInputWithOffset(String input)
    {
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
                    case 'e':
                        offset = LAUNCH;
                        break;
                    default:
                        offset = -1;
                        break;
                }
            }
        }
    }

    /** Permet de faire des inputs plus ciblés (moins général) et personnalisé */
    public String getStringInput() { return sc.nextLine(); }

    /* Les inputs généraux */
    public boolean getQuitAction() { return offset == QUIT; }
    public boolean getMoveUp() { return offset == UP; }
    public boolean getMoveDown() { return offset == DOWN; }
    public boolean getMoveLeft() { return offset == LEFT; }
    public boolean getMoveRight() { return offset == RIGHT; }
    public boolean getSelect() { return offset == SELECT; }
    public boolean getLaunch() { return offset == LAUNCH; }
}

