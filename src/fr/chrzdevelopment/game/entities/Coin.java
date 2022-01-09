package fr.chrzdevelopment.game.entities;

import java.util.List;


/**
 *
 */
public class Coin extends Entity
{


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités).
     * @param x La localisation en x du Sprite.
     * @param y La localisation en y du Sprite.
     */
    public Coin(List<Entity> group, int x, int y)
    {
        super(group, "Coin", x, y, 0);
    }
}
