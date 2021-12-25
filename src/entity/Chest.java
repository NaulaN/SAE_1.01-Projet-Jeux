package entity;

import static constantes.Const.CHEST;


public class Chest extends Entity
{
    private String whatInside;


    public Chest(String whatInside, int x, int y)
    {
        super("Chest", x, y, 0);

        this.whatInside = whatInside;
        setHealth(1);
        setDataImg(CHEST);
    }
}
