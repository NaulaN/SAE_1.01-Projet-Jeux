package entity;

import static entity.Const.*;


public class Entity
{
    // x, y
    private final int[] previousPos = new int[2];
    private final int[] pos = new int[2];

    private int health = 3;
    private int velocity;

    private int offsetWhereMoving = -1;
    private char dataImg = '/';


    public Entity(String type, int x, int y, int velocity)
    {
        if (type.equalsIgnoreCase("player"))
            dataImg = MONSTER;
        else if (type.equalsIgnoreCase("monster"))
            dataImg = PLAYER;

        pos[0] = x;
        pos[1] = y;
        this.velocity = velocity;
    }

    public char getDataImg() { return dataImg; }
    public int[] getPosition() { return pos; }
    public int getXPosition() { return pos[0]; }
    public int getYPosition() { return pos[1]; }
    public int getXPreviousPosition() { return previousPos[0]; }
    public int getYPreviousPosition() { return previousPos[1]; }
    public int getHealth() { return health; }

    public void setDataImg(char newDataImg) { dataImg = newDataImg; }
    public void setVelocity(int newVelocity) { velocity = newVelocity; }
    public void setHealth(int newHealth) { health = newHealth; }

    public void setXPosition(int newXPosition)
    {
        pos[0] = newXPosition;
        previousPos[0] = newXPosition;
    }

    public void setYPosition(int newYPosition)
    {
        pos[1] = newYPosition;
        previousPos[1] = newYPosition;
    }

    public boolean[] checkCollision(int[][] collideCalque)
    {
        if ((offsetWhereMoving == UP && pos[1]-1 >= 0) ||
                (offsetWhereMoving == DOWN && pos[1]+1 < collideCalque.length) ||
                (offsetWhereMoving == LEFT && pos[0]-1 >= 0) ||
                (offsetWhereMoving == RIGHT && pos[0]+1 < collideCalque[pos[1]].length))
            return new boolean[] {
                    collideCalque[pos[1]-1][pos[0]] == COLLIDE_OBJ,
                    collideCalque[pos[1]+1][pos[0]] == COLLIDE_OBJ,
                    collideCalque[pos[1]][pos[0]-1] == COLLIDE_OBJ,
                    collideCalque[pos[1]][pos[0]+1] == COLLIDE_OBJ
                    };
        // Pas de collision
        return new boolean[] {false, false, false, false};
    }

    public void moveUp()
    {
        offsetWhereMoving = UP;

        previousPos[1] = pos[1];
        previousPos[0] = pos[0];
        pos[1] -= velocity;
    }

    public void moveDown()
    {
        offsetWhereMoving = DOWN;

        previousPos[1] = pos[1];
        previousPos[0] = pos[0];
        pos[1] += velocity;
    }

    public void moveLeft()
    {
        offsetWhereMoving = LEFT;

        previousPos[0] = pos[0];
        previousPos[1] = pos[1];
        pos[0] -= velocity;
    }

    public void moveRight()
    {
        offsetWhereMoving = RIGHT;

        previousPos[0] = pos[0];
        previousPos[1] = pos[1];
        pos[0] += velocity;
    }

    public void hit() { health--; }
}
