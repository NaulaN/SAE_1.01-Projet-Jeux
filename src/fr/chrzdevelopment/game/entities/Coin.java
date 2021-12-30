package fr.chrzdevelopment.game.entities;


public class Coin extends Entity
{
    private boolean isPickup = false;


    public Coin(int x, int y, int velocity)
    {
        super("Coin", x, y, velocity);
    }
}
