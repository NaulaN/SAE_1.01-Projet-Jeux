package fr.chrzdevelopment.game.entities;

import java.lang.reflect.Parameter;
import java.util.List;


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
        boolean i = true;
        if (!isThrow) {
            int minHypo = 0;

            for (Entity sprite : getGroup()){
                if (sprite instanceof Monster) {
                    int coteAdjacent = sprite.getYPosition()-this.getXPosition();
                    int coteOppose = sprite.getYPosition()-this.getYPosition();

                    int hypotenuse = (int) Math.sqrt(Math.pow(coteAdjacent, 2) + Math.pow(coteOppose, 2));
                    // Tres moche, mais pas le choix
                    if (i) {
                        minHypo = hypotenuse;
                        monsterAtTrack = (Monster) sprite;
                        i = false;
                    }

                    if (hypotenuse < minHypo) {
                        minHypo = hypotenuse;
                        monsterAtTrack = (Monster) sprite;
                    }
                }
            }

            isThrow = true;
        }
    }

    public boolean getIsThrow() { return isThrow; }
    public Monster getMonsterAtTrack() { return monsterAtTrack; }

    @Override
    public void updates(Parameter... parameters) { }
}

