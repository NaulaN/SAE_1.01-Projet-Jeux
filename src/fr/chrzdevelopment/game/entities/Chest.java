package fr.chrzdevelopment.game.entities;

import java.util.List;

import static fr.chrzdevelopment.game.Const.CHEST;
import static fr.chrzdevelopment.game.Const.CHEST_OPEN;


public class Chest extends Entity
{
    private String whatInside;

    private boolean isOpen = false;


    public Chest(List<Entity> group, String whatInside, int x, int y)
    {
        super(group, "Chest", x, y, 0);

        this.whatInside = whatInside;
        setHealth(1);
        setDataImg(CHEST);
    }

    @Override
    public void updates()
    {
        if (getHealth() <= 0)
            isOpen = true;

        setDataImg((isOpen) ? CHEST : CHEST_OPEN);
    }
}
