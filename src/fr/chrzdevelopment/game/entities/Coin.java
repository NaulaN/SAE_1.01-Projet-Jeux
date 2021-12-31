package fr.chrzdevelopment.game.entities;

import java.util.List;


public class Coin extends Entity
{
    private boolean isPickup = false;


    public Coin(List<Entity> group, int x, int y, int velocity)
    {
        super(group, "Coin", x, y, velocity);
    }

    @Override
    public void updates()
    {
        if (isPickup)
            getGroup().remove(this);
    }
}
