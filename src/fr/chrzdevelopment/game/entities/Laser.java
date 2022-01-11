package fr.chrzdevelopment.game.entities;

import java.util.List;

import static fr.chrzdevelopment.game.Const.LASER_HORIZONTAL;
import static fr.chrzdevelopment.game.Const.LASER_VERTICAL;


public class Laser extends Entity
{
    private int direction;


    public Laser(List<Entity> allSprites, int x, int y, int direction)
    {
        super(allSprites, "monster", x, y, 1);
        this.direction = direction;

        setDataImg((direction != 1 && direction != 3) ? LASER_VERTICAL : LASER_HORIZONTAL);
    }

    public int getDirection() { return direction; }
}
