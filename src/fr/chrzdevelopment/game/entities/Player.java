package fr.chrzdevelopment.game.entities;

import java.util.List;


/**
 *
 */
public class Player extends Entity
{
    // Pour ouvrir les coffres sur la carte
    private boolean haveKey = false;
    private int coins = 0;
    private int lvl = 0;
    private int exp = 0;


    /**
     * @param group
     * @param x
     * @param y
     * @param velocity
     */
    public Player(List<Entity> group, int x, int y, int velocity)
    {
        super(group, "Player", x, y, velocity);
    }

    public int getCoins() { return coins; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }
    public boolean getHaveAKey() { return haveKey; }

    public void haveKey() { haveKey = true; }
    public void haventKey() { haveKey = false; }

    public void addLvls(int addNewLvl) { lvl += addNewLvl; }
    public void addExps(int addNewExp) { exp += addNewExp; }
    public void addCoin() { coins++; }

    @Override
    public void updates() { }
}
