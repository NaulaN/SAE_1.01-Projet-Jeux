import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class KeyboardInputListener
{
    private final Map<Character, Boolean> keys = new HashMap<>();
    private final Scanner sc = new Scanner(System.in);

    public void getInput()
    {
        keys.put('z', false);
        keys.put('s', false);
        keys.put('q', false);
        keys.put('d', false);
        keys.put('a', false);

        System.out.println();
        System.out.print("Que faire > ");
        String input = sc.nextLine();
        // Evite une erreur
        if (input.length() != 0) {
            char key = input.charAt(0);
            if (key == 'z' || key == 's' || key == 'q' || key == 'd' || key == 'a')
                keys.put(key, true);
        }
    }

    public boolean getMoveUp() { return keys.get('z'); }

    public boolean getMoveDown() { return keys.get('s'); }

    public boolean getMoveLeft() { return keys.get('q'); }

    public boolean getMoveRight() { return keys.get('d'); }

    public boolean getSelect() { return keys.get('a'); }
}

