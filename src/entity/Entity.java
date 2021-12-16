package entity;

public class Entity
{
    // UP, DOWN, LEFT, RIGHT
    private final boolean[] whereMoving = {false, false, false, false};
    // x, y
    private final int[] previousPos = new int[2];
    private final int[] pos = new int[2];

    private int health = 3;
    private int velocity;

    private String img;


    public Entity(int x, int y, int velocity)
    {
        pos[0] = x;
        pos[1] = y;
        this.velocity = velocity;
    }

    public String getImg() { return img; }

    public int[] getPosition() { return pos; }

    public int getXPosition() { return pos[0]; }

    public int getYPosition() { return pos[1]; }

    public int getXPreviousPosition() { return previousPos[0]; }

    public int getYPreviousPosition() { return previousPos[1]; }

    public int getHealth() { return health; }

    public void setImg(String newImg) { img = newImg; }

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

    public boolean checkCollision(String[][] maps)
    {
        // UP check
        if (whereMoving[0] && pos[1]-1 >= 0)
            return maps[pos[1]-1][pos[0]].equals("**");
        // DOWN check
        else if (whereMoving[1] && pos[1]+1 < maps.length)
            return maps[pos[1]+1][pos[0]].equals("**");
        // LEFT check
        else if (whereMoving[2] && pos[0]-1 >= 0)
            return maps[pos[1]][pos[0]-1].equals(" *") || maps[pos[1]][pos[0]+1].equals("* ");
        // RIGHT check
        else if (whereMoving[3] && pos[0]+1 < maps[pos[1]].length)
            return maps[pos[1]][pos[0]+1].equals(" *") || maps[pos[1]][pos[0]+1].equals("* ");
        return false;
    }

    public void moveUp()
    {
        for (int i = 0; i < whereMoving.length; i++)
            whereMoving[i] = i == 0;

        previousPos[1] = pos[1];
        previousPos[0] = pos[0];
        pos[1] -= velocity;
    }

    public void moveDown()
    {
        for (int i = 0; i < whereMoving.length; i++)
            whereMoving[i] = i == 1;

        previousPos[1] = pos[1];
        previousPos[0] = pos[0];
        pos[1] += velocity;
    }

    public void moveLeft()
    {
        for (int i = 0; i < whereMoving.length; i++)
            whereMoving[i] = i == 2;

        previousPos[0] = pos[0];
        previousPos[1] = pos[1];
        pos[0] -= velocity;
    }

    public void moveRight()
    {
        for (int i = 0; i < whereMoving.length; i++)
            whereMoving[i] = i == 3;

        previousPos[0] = pos[0];
        previousPos[1] = pos[1];
        pos[0] += velocity;
    }

    public void hit() { health--; }
}
