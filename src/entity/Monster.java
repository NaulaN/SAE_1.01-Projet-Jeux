package entity;


public class Monster extends Entity
{
    public Monster(int x, int y, int velocity)
    {
        super();

        setImg("\uD83D\uDC79");
        setXPosition(x);
        setYPosition(y);
        setVelocity(velocity);
    }
}
