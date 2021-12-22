import static entity.Const.*;

import java.util.Scanner;


public class KeyboardInput
{
    private final Scanner sc = new Scanner(System.in);
    public int offset = -1;


    public void getInput()
    {
        System.out.println();
        System.out.print("Que faire > ");
        String input = sc.nextLine();
        // Evite une erreur
        if (input.length() != 0)
        {
            char key = input.toLowerCase().charAt(0);
            switch (key)
            {
                case 'z' -> offset = UP;
                case 's' -> offset = DOWN;
                case 'q' -> offset = LEFT;
                case 'd' -> offset = RIGHT;
                case 'a' -> offset = SELECT;
                default ->  offset = -1;
            }
        }
    }

    public boolean getMoveUp() { return offset == UP; }
    public boolean getMoveDown() { return offset == DOWN; }
    public boolean getMoveLeft() { return offset == LEFT; }
    public boolean getMoveRight() { return offset == RIGHT; }
    public boolean getSelect() { return offset == SELECT; }
}

