package fr.chrzdevelopment.game.entities;

import fr.chrzdevelopment.game.Const;
import fr.chrzdevelopment.game.errors.AttacksListBadIndex;

import java.util.List;


/**
 *
 */
public class Player extends Entity
{
    // Les attaques spéciales du joueur
    private final int[] attacks = {-1, -1, -1, -1};

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

        // Les capacités par défaut que possède le joueur.
        attacks[0] = Const.CHARGE;
    }

    public int getCoins() { return coins; }
    public int getLvl() { return lvl; }
    public int getExp() { return exp; }
    public boolean getHaveAKey() { return haveKey; }
    public int[] getListAttacks() { return attacks; }
    public int getFirstAttack() { return attacks[0]; }
    public int getSecondAttack() { return attacks[1]; }
    public int getThirdAttack() { return attacks[2]; }
    public int getFourthAttack() { return attacks[3]; }

    public void setAttacks(int which, int newAttack)
    {
        if (which < attacks.length)
            attacks[which] = newAttack;
        else throw new AttacksListBadIndex(which);  // Pour faire une erreur plus personnalisée et plus propre.
    }

    public void haveKey() { haveKey = true; }
    public void haventKey() { haveKey = false; }

    public void addLvls(int addNewLvl) { lvl += addNewLvl; }
    public void addExps(int addNewExp) { exp += addNewExp; }
    public void addCoin() { coins++; }

    @Override
    public void updates()
    {

    }
}
