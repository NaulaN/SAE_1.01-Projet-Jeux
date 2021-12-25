package entity;

import static constantes.Const.*;


public class Monster extends Entity
{
    private int offsetWhereShooting = -1;


    public Monster(int x, int y, int velocity) { super("Monster", x, y, velocity); }

    /** Bouge le monstre aléatoirement. */
    public void randomMove()
    {
        int moveRandomly = (int) (Math.random()*RIGHT+1);

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
        int moveRandomly = (int) ((Math.random()*RIGHT+1)-Math.random()*-1);

        if (moveRandomly != -1)
            offsetWhereShooting = moveRandomly;
    }
}
