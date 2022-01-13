package fr.chrzdevelopment.game.entities;

import java.util.List;


/**
 * @see fr.chrzdevelopment.game.entities.Entity
 * @author CHRZASZCZ Naulan
 */
public class Player extends Entity
{
    private boolean isInvulnerability = true;
    // Pour ouvrir les coffres sur la carte
    private boolean haveKey = false;
    private boolean haveSword = false;

    private int coins = 0;
    private int lvl = 0;
    private int exp = 0;


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param x La localisation en x du Sprite.
     * @param y La localisation en x du Sprite.
     * @param velocity La vitesse du joueur.
     */
    public Player(List<Entity> group, int x, int y, int velocity)
    {
        super(group, "Player", x, y, velocity);
    }

    public int getCoins() { return coins; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }
    public boolean getHaveAKey() { return haveKey; }
    public boolean getHaveSword() { return haveSword; }
    public boolean getHaveInvulnerability() { return isInvulnerability; }

    public void haveKey() { haveKey = true; }
    public void haveSword() { haveSword = true; }
    public void haventKey() { haveKey = false; }
    public void haventSword() { haveSword = false; }
    public void enableInvulnerability() { isInvulnerability = true; }
    public void disableInvulnerability() { isInvulnerability = false; }

    public void addLvls(int addNewLvl) { lvl += addNewLvl; }
    public void addExps(int addNewExp) { exp += addNewExp; }
    public void addCoin() { coins++; }

    @Override
    public void updates() { }
}
