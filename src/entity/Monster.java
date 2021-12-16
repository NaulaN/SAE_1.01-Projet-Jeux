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
    public void randomMove()
    {
        int moveRandomly = (int) (Math.random()*8);

        // Gauche
        if (moveRandomly == 0)
            moveLeft();
        // Diagonal ↖
        else if (moveRandomly == 1) {
            moveDown();
            moveLeft();
        }
        // Haut
        else if (moveRandomly == 2)
            moveUp();
        // Diagonal ↗
        else if (moveRandomly == 3) {
            moveRight();
            moveUp();
        }
        // Droite
        else if (moveRandomly == 4)
            moveRight();
        // Diagonal ↘
        else if (moveRandomly == 5) {
            moveRight();
            moveDown();
        }
        // Bas
        else if (moveRandomly == 6)
            moveDown();
        // Diagonal ↙
        else if (moveRandomly == 7) {
            moveLeft();
            moveDown();
        }
    }
}
