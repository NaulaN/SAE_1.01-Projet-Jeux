package entity;

import static entity.Const.*;


public class Monster extends Entity
{
    public Monster(int x, int y, int velocity) { super("Monster", x, y, velocity); }

    /**
     * Bouge le monstre aléatoirement.
     * @param collideCalque Donnez la matrice de donnée de la Maps
     */
    public void randomMove(int[][] collideCalque)
    {
        int moveRandomly = (int) (Math.random()*RIGHT+1);
        boolean[] collision = checkCollision(collideCalque);     // {UP, DOWN, LEFT, RIGHT}

        if (moveRandomly == UP && !collision[0])
            moveUp();
        if (moveRandomly == DOWN && !collision[1])
            moveDown();
        if (moveRandomly == LEFT && !collision[2])
            moveLeft();
        if (moveRandomly == RIGHT && !collision[3])
            moveRight();
    }
}
