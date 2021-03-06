package fr.chrzdevelopment.game.entities;

import java.lang.reflect.Parameter;
import java.util.List;

import static fr.chrzdevelopment.game.LoadGraphics.allDataObj;


/**
 * @see fr.chrzdevelopment.game.entities.Chest
 * @see fr.chrzdevelopment.game.entities.Coin
 * @see fr.chrzdevelopment.game.entities.Monster
 * @see fr.chrzdevelopment.game.entities.Player
 * @see fr.chrzdevelopment.game.entities.Key
 * @see fr.chrzdevelopment.game.entities.Laser
 * @author CHRZASZCZ Naulan
 */
public abstract class Entity
{
    private final List<Entity> group;

    private final boolean[] collisions = {false, false, false, false};
    // x, y
    protected final int[] previousPos = {-1, -1};
    protected final int[] pos = new int[2];

    private int health = 3;
    private int velocity;

    private int dataImg;


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités) et qui permet de les faire fonctionner.
     * @param type Pour savoir de quel type d'entité qu'on va crée.
     * @param x Les coordonnées en x pour l'entité.
     * @param y Les coordonnées en y pour l'entité.
     * @param velocity La vitesse de déplacement.
     */
    public Entity(List<Entity> group, String type, int x, int y, int velocity)
    {
        this.group = group;
        if (type.equalsIgnoreCase("player"))
            group.add(0, this);
        else group.add(this);

        dataImg = allDataObj.get(type.toLowerCase());

        pos[0] = x;
        pos[1] = y;
        this.velocity = velocity;
    }

    public Entity(List<Entity> group, String type, int x, int y)
    {
        this.group = group;
        if (!type.equalsIgnoreCase("player"))
            group.add(0, this);
        else group.add(this);

        dataImg = allDataObj.get(type.toLowerCase());

        pos[0] = x;
        pos[1] = y;
    }

    protected List<Entity> getGroup() { return group; }

    public abstract void updates(Parameter... parameters);

    public int getDataImg() { return dataImg; }
    public int getHealth() { return health; }
    public int getXPosition() { return pos[0]; }
    public int getYPosition() { return pos[1]; }
    public int getXPreviousPosition() { return previousPos[0]; }
    public int getYPreviousPosition() { return previousPos[1]; }
    public boolean getCollideUp() { return collisions[0]; }
    public boolean getCollideDown() { return collisions[1]; }
    public boolean getCollideLeft() { return collisions[2]; }
    public boolean getCollideRight() { return collisions[3]; }

    public void setDataImg(int newDataImg) { dataImg = newDataImg; }
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
        int y = (pos[1] == 0) ? 0 : pos[1]-1;
        int x = getXPosition();
        collisions[0] = collideCalque[y][x];
        y = (pos[1] == 0) ? 0 : (pos[1] < collideCalque.length-1) ? pos[1]+1 : pos[1];
        collisions[1] = collideCalque[y][x];
        y = getYPosition();
        x = (pos[0] == 0) ? 0 : pos[0]-1;
        collisions[2] = collideCalque[y][x];
        x = (pos[0] == 0) ? 0 : (pos[0] < collideCalque[0].length-1) ? pos[0]+1 : pos[0];
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

    public void hit() {
        health--;
    }
}
