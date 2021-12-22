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
        int moveRandomly = (int) (Math.random()*(BOTTOM_RIGHT+1));
        boolean[] collision = checkCollision(maps); // {UP, DOWN, LEFT, RIGHT}

        if (moveRandomly == UP && !collision[0])
            moveUp();
        if (moveRandomly == TOP_LEFT && (!collision[0] && !collision[2])) {
            moveDown();
            moveLeft();
        }
        if (moveRandomly == DOWN && !collision[0])
            moveDown();
        if (moveRandomly == TOP_RIGHT && (!collision[0] && !collision[3])) {
            moveRight();
            moveDown();
        }
        if (moveRandomly == LEFT && !collision[2])
            moveLeft();
        if (moveRandomly == BOTTOM_LEFT && (!collision[1] && !collision[2])) {
            moveRight();
            moveUp();
        }
        if (moveRandomly == RIGHT && !collision[3])
            moveDown();
        if (moveRandomly == BOTTOM_RIGHT && (!collision[1] && !collision[3])) {
            moveLeft();
            moveDown();
        }
    }
}
