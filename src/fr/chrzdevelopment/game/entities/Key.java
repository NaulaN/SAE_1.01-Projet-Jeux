package fr.chrzdevelopment.game.entities;

import java.util.List;

import static fr.chrzdevelopment.game.Const.KEY;


public class Key extends Entity
{


    public Key(List<Entity> allSprites, int x, int y)
    {
        super(allSprites, "key", x, y, 0);
        setDataImg(KEY);
    }
}
