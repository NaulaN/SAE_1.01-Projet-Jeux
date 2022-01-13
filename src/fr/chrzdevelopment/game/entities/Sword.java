package fr.chrzdevelopment.game.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sword extends Entity
{
    private Monster monsterAtTrack;

    private boolean isThrow = false;


    public Sword(List<Entity> allSprites, int x, int y) { super(allSprites, "sword", x, y, 1); }

    public void launch()
    {
        if (!isThrow) {
            Map<Integer, Monster> diffBetweenLoc = new HashMap<>();
            for (Entity sprite : getGroup())
                if (sprite instanceof Monster)
                    diffBetweenLoc.put((sprite.getXPosition()+sprite.getYPosition())-(getXPosition()+getYPosition()), (Monster) sprite);

            final Integer[] min = {null};
            diffBetweenLoc.forEach((integer, monster) -> {
                if (min[0] == null)
                    min[0] = integer;

                if (min[0] >= integer)
                    min[0] = integer;
            });

            monsterAtTrack = diffBetweenLoc.get(min[0]);
            isThrow = true;
        }
    }

    public void setXPreviousPosition(int position) { this.previousPos[0] = position; }
    public void setYPreviousPosition(int position) { this.previousPos[1] = position; }

    public boolean getIsThrow() { return isThrow; }
    public Monster getMonsterAtTrack() { return monsterAtTrack; }
}

