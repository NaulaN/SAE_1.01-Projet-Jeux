package fr.chrzdevelopment.game.threads;

import fr.chrzdevelopment.game.Game;
import fr.chrzdevelopment.game.KeyboardInput;


/**
 * <p>Ce Thread représente un processus parallel qui se charge des inputs au clavier.</p>
 * @see fr.chrzdevelopment.game.KeyboardInput
 * @see fr.chrzdevelopment.game.Game
 */
public class KeyboardInputThread extends Thread
{
    private final KeyboardInput keyboardInput;
    private final Game game;

    private volatile boolean running = true;


    /**
     * @param game La classe principale (Main) du jeu
     * @param keyboardInput La classe qui gère les inputs dans le terminal
     */
    public KeyboardInputThread(Game game, KeyboardInput keyboardInput)
    {
        super();
        this.keyboardInput = keyboardInput;
        this.game = game;
    }

    public void terminate()
    {
        running = false;
        this.interrupt();
    }

    @Override
    public void run()
    {
        synchronized (this) {
            while (running && !this.isInterrupted()) {
                try {
                    this.wait(100);
                } catch (InterruptedException ignored) { return; }

                keyboardInput.input();
            }
        }
    }
}
