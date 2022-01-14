package fr.chrzdevelopment.game.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sword extends Entity
{
    private Monster monsterAtTrack;

    private boolean isThrow = false;


    public Sword(List<Entity> allSprites, int x, int y) { super(allSprites, "sword", x, y, 1); }

    /**
     * Regarde dans la liste de sprite, quel est le plus proche du joueur.
     *      Range les valeurs de difference (x-y) dans une HashMap.
     * Détecte la plus petite valeur dans cette HashMap.
     * Change l'etat de la variable isThrow pour annoncé qu'il est jetée.
     */
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

    public boolean getIsThrow() { return isThrow; }
    public Monster getMonsterAtTrack() { return monsterAtTrack; }
}

