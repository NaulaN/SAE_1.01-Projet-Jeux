package fr.chrzdevelopment.game.entities;

import static fr.chrzdevelopment.game.Const.*;

import java.util.List;
import java.util.Random;


public class Monster extends Entity
{
    private final Random random = new Random();

    private int offsetWhereShooting = -1;


    public Monster(List<Entity> group, int x, int y, int velocity) { super(group, "Monster", x, y, velocity); }

    /** Bouge le monstre aléatoirement. */
    public void randomMove()
    {
        int moveRandomly = random.nextInt(0, 4);

        if (moveRandomly == UP && !getWhereCollide()[0])
            moveUp();
        if (moveRandomly == DOWN && !getWhereCollide()[1])
            moveDown();
        if (moveRandomly == LEFT && !getWhereCollide()[2])
            moveLeft();
        if (moveRandomly == RIGHT && !getWhereCollide()[3])
            moveRight();
    }

    public void randomShoot()
    {
        // TODO
    }

    @Override
    public void updates()
    {
        randomMove();

        if (getHealth() <= 0)
            getGroup().remove(this);
    }
}
