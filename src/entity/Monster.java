package entity;


public class Monster extends Entity
{
    public Monster(int x, int y, int velocity)
    {
        super(x, y, velocity);

        setImg("\uD83D\uDC79");
    }

    /**
     * Fait bougé le monstre aléatoirement
     */
    public void randomMove(String[][] maps)
    {
        int moveRandomly = (int) (Math.random()*8);
        boolean[] collision = checkCollision(maps);

        // Gauche
        if (moveRandomly == 0)
            if (!collision[2])
                moveLeft();
        // Diagonal ↖
        if (moveRandomly == 1)
            if (!collision[1] || !collision[2]) {
                moveDown();
                moveLeft();
            }
        // Haut
        if (moveRandomly == 2)
            if (!collision[0])
                moveUp();
        // Diagonal ↗
        if (moveRandomly == 3)
            if (!collision[3] || !collision[0]) {
                moveRight();
                moveUp();
            }
        // Droite
        if (moveRandomly == 4)
            if (!collision[3])
                moveRight();
        // Diagonal ↘
        if (moveRandomly == 5)
            if (!collision[3] || !collision[1]) {
                moveRight();
                moveDown();
            }
        // Bas
        if (moveRandomly == 6)
            if (!collision[1])
                moveDown();
        // Diagonal ↙
        if (moveRandomly == 7)
            if (!collision[2] || !collision[1]) {
                moveLeft();
                moveDown();
            }
    }
}
