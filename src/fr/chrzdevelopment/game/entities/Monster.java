package fr.chrzdevelopment.game.entities;

import static fr.chrzdevelopment.game.constantes.Const.*;
import java.util.Random;


public class Monster extends Entity
{
    private final Random random = new Random();

    private int offsetWhereShooting = -1;


    public Monster(int x, int y, int velocity) { super("Monster", x, y, velocity); }

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
}
