package fr.chrzdevelopment.game.entities;

import java.util.List;

import static fr.chrzdevelopment.game.Const.KEY;


/**
 * @see fr.chrzdevelopment.game.entities.Entity
 * @author CHRZASZCZ Naulan
 */
public class Key extends Entity
{


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param x Les coordonnées en x pour l'entité.
     * @param y Les coordonnées en y pour l'entité.
     */
    public Key(List<Entity> group, int x, int y)
    {
        super(group, "key", x, y, 0);
        setDataImg(KEY);
    }
}
