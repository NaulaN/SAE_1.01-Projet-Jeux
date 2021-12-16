package entity;


public class Player extends Entity
{
    public Player(int x, int y, int velocity)
    {
        super();

        setImg("\uD83E\uDD20");
        setXPosition(x);
        setYPosition(y);
        setVelocity(velocity);
    }
}
