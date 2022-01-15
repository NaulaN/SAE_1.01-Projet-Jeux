package fr.chrzdevelopment.game.entities;

import java.lang.reflect.Parameter;
import java.util.List;


/**
 * @see fr.chrzdevelopment.game.entities.Entity
 * @author CHRZASZCZ Naulan
 */
public class Coin extends Entity
{


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param x La localisation en x du Sprite.
     * @param y La localisation en y du Sprite.
     */
    public Coin(List<Entity> group, int x, int y)
    {
        super(group, "Coin", x, y, 1);
    }

    @Override
    public void updates(Parameter... parameters)
    {

    }
}
