package entity;


import static entity.Const.*;

public class Monster extends Entity
{
    public Monster(int x, int y, int velocity) { super("Monster", x, y, velocity); }

    /**
     * Fait bougé le monstre aléatoirement
     */
    public void randomMove(char[][] maps)
    {
        int moveRandomly = (int) (Math.random()*RIGHT+1);
        boolean[] collision = checkCollision(maps); // {UP, DOWN, LEFT, RIGHT}

        if (moveRandomly == UP && !collision[0])
            moveUp();
        else if (moveRandomly == DOWN && !collision[1])
            moveDown();
        else if (moveRandomly == LEFT && !collision[2])
            moveLeft();
        else if (moveRandomly == RIGHT && !collision[3])
            moveRight();
    }
}
