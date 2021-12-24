package entity;


public class Player extends Entity
{
    private boolean haveKey = false;
    private int coins = 0;


    public Player(int x, int y, int velocity) { super("Player", x, y, velocity); }

    public int getCoins() { return coins; }
    public boolean getKey() { return haveKey; }
}
