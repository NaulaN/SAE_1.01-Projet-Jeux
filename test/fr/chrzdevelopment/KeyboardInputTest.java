package fr.chrzdevelopment;

import static org.junit.jupiter.api.Assertions.*;

import fr.chrzdevelopment.game.KeyboardInput;
import org.junit.jupiter.api.Test;


public class KeyboardInputTest
{

    @Test
    public final void getInputTest()
    {
        KeyboardInput keyboardInput = new KeyboardInput();
        String[] inputs = {"q", "s", "d", "z", "a", "quit"};

        for (String input : inputs)
        {
            keyboardInput.input(input);
            if (input.equalsIgnoreCase("z")) {
                assertTrue(keyboardInput.getMoveUp());
                assertFalse(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getSelect());
                assertFalse(keyboardInput.getQuitAction());
            } else if (input.equalsIgnoreCase("s")) {
                assertTrue(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveUp());
                assertFalse(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getSelect());
                assertFalse(keyboardInput.getQuitAction());
            } else if (input.equalsIgnoreCase("q")) {
                assertTrue(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveUp());
                assertFalse(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getSelect());
                assertFalse(keyboardInput.getQuitAction());
            } else if (input.equalsIgnoreCase("d")) {
                assertTrue(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveUp());
                assertFalse(keyboardInput.getSelect());
                assertFalse(keyboardInput.getQuitAction());
            } else if (input.equalsIgnoreCase("a")) {
                assertFalse(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveUp());
                assertTrue(keyboardInput.getSelect());
                assertFalse(keyboardInput.getQuitAction());
            } else if (input.equalsIgnoreCase("quit")) {
                assertFalse(keyboardInput.getMoveRight());
                assertFalse(keyboardInput.getMoveLeft());
                assertFalse(keyboardInput.getMoveDown());
                assertFalse(keyboardInput.getMoveUp());
                assertFalse(keyboardInput.getSelect());
                assertTrue(keyboardInput.getQuitAction());
            }
        }
    }
}
