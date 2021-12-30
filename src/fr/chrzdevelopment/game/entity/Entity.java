package fr.chrzdevelopment.game.entity;

import static fr.chrzdevelopment.game.constantes.Const.*;


public class Entity
{
    private final boolean[] collisions = {false, false, false, false};
    // x, y
    private final int[] previousPos = {-1, -1};
    private final int[] pos = new int[2];

    private int health = 3;
    private int velocity;

    private int dataImg = -1;


    public Entity(String type, int x, int y, int velocity)
    {
        if (type.equalsIgnoreCase("player"))
            dataImg = PLAYER;
        else if (type.equalsIgnoreCase("monster"))
            dataImg = MONSTER;

        pos[0] = x;
        pos[1] = y;
        this.velocity = velocity;
    }

    public int getDataImg() { return dataImg; }
    public int[] getPosition() { return pos; }
    public int getXPosition() { return pos[0]; }
    public int getYPosition() { return pos[1]; }
    public int getXPreviousPosition() { return previousPos[0]; }
    public int getYPreviousPosition() { return previousPos[1]; }
    public int getHealth() { return health; }
    public boolean[] getWhereCollide() { return collisions; }
    public boolean getCollideUp() { return collisions[0]; }
    public boolean getCollideDown() { return collisions[1]; }
    public boolean getCollideLeft() { return collisions[2]; }
    public boolean getCollideRight() { return collisions[3]; }

    public void setDataImg(int newDataImg) { dataImg = newDataImg; }
    public void setVelocity(int newVelocity) { velocity = newVelocity; }
    public void setHealth(int newHealth) { health = newHealth; }

    public void setXPosition(int newXPosition)
    {
        pos[0] = newXPosition;
        previousPos[0] = -1;
    }

    public void setYPosition(int newYPosition)
    {
        pos[1] = newYPosition;
        previousPos[1] = -1;
    }

    public void checkCollision(boolean[][] collideCalque)
    {
        int y = (pos[1] == 0) ? 0 : pos[1]-1; int x = getXPosition();
        collisions[0] = collideCalque[y][x];
        y = (pos[1] == 0) ? 0 : pos[1]+1;
        collisions[1] = collideCalque[y][x];
        y = getYPosition(); x = (pos[0] == 0) ? 0 : pos[0]-1;
        collisions[2] = collideCalque[y][x];
        x = (pos[0] == 0) ? 0 : pos[0]+1;
        collisions[3] = collideCalque[y][x];
    }

    public void moveUp()
    {
        previousPos[1] = pos[1]; previousPos[0] = pos[0];
        pos[1] -= velocity;
    }

    public void moveDown()
    {
        previousPos[1] = pos[1]; previousPos[0] = pos[0];
        pos[1] += velocity;
    }

    public void moveLeft()
    {
        previousPos[0] = pos[0]; previousPos[1] = pos[1];
        pos[0] -= velocity;
    }

    public void moveRight()
    {
        previousPos[0] = pos[0]; previousPos[1] = pos[1];
        pos[0] += velocity;
    }

    public void hit() { health--; }
}
