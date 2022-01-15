package fr.chrzdevelopment.game.entities;

import java.lang.reflect.Parameter;
import java.util.List;

import static fr.chrzdevelopment.game.Const.LASER_HORIZONTAL;
import static fr.chrzdevelopment.game.Const.LASER_VERTICAL;


/**
 * @see fr.chrzdevelopment.game.entities.Entity
 * @author CHRZASZCZ Naulan
 */
public class Laser extends Entity
{
    private int direction;


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param x Les coordonnées en x pour l'entité.
     * @param y Les coordonnées en y pour l'entité.
     * @param direction Détermine dans quel sens doit partir le laser.
     */
    public Laser(List<Entity> group, int x, int y, int direction)
    {
        super(group, "monster", x, y, 1);
        this.direction = direction;

        setDataImg((direction != 1 && direction != 3) ? LASER_VERTICAL : LASER_HORIZONTAL);
    }

    public int getDirection() { return direction; }

    @Override
    public void updates(Parameter... parameters)
    {

    }
}
