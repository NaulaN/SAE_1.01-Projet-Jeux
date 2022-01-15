package fr.chrzdevelopment.game.threads;

import fr.chrzdevelopment.game.Game;


/**
 * <p>Ce Thread représente un processus parallel qui se charge de l'actualisation et l'affichage du jeu.</p>
 * @see fr.chrzdevelopment.game.Game
 */
public class RefreshAndDisplayThread extends Thread
{
    private int timeout = 150;

    private final Game game;

    private boolean running = true;


    /** @param game La classe principale (Main) du jeu */
    public RefreshAndDisplayThread(Game game)
    {
        super();
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
                    this.wait(timeout);
                } catch (InterruptedException ignored) { return; }

                game.updates();
                game.draws();
            }
        }
    }

    public void setThreadTimeout(int newTimeout) { timeout = newTimeout; }
}
