package fr.chrzdevelopment.game.entities;

import java.util.List;

import static fr.chrzdevelopment.game.Const.CHEST;
import static fr.chrzdevelopment.game.Const.CHEST_OPEN;


/**
 * @see fr.chrzdevelopment.game.entities.Entity
 * @author CHRZASZCZ Naulan
 */
public class Chest extends Entity
{
    private String whatInside;

    private boolean isOpen = false;


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param whatInside Détermine ce qy'il doit donner au joueur lors de l'ouverture.
     * @param x Les coordonnées en x pour l'entité.
     * @param y Les coordonnées en y pour l'entité.
     */
    public Chest(List<Entity> group, String whatInside, int x, int y)
    {
        super(group, "Chest", x, y, 0);

        this.whatInside = whatInside;
        setHealth(1);
        setDataImg(CHEST);
    }

    public String getWhatInside() { return whatInside; }

    @Override
    public void updates()
    {
        if (getHealth() <= 0)
            isOpen = true;

        setDataImg((isOpen) ? CHEST : CHEST_OPEN);
    }
}
