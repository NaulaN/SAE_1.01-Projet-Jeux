package entity;

import static entity.Const.CHEST;


public class Chest extends Entity
{
    public Chest(String whatInside, int x, int y)
    {
        super("Chest", x, y, 0);

        setHealth(1);
        setDataImg(CHEST);
    }
}
