package fr.chrzdevelopment.game.entities;

import static fr.chrzdevelopment.game.Const.*;

import java.util.List;
import java.util.Random;


/**
 * <h1 style="font-size: 115%">Objet Monster qui hérite de la classe Entity</h1>
 * <h2 style="font-size: 105%; text-decoration: underline;">Qu'est-ce qu'il fait ?</h2>
 * <ul>
 *     <li><p>Bouge sur la carte aléatoirement si aucune collision est présente grâce à la fonction "randomMove()" sur les 4 directions possible qui est droite, gauche, haut et bas</p></li>
 *     <li><p>Tire une sorte de rayon laser aléatoirement grâce à la fonction "randomShoot()" et que lorsque le joueurs rentre en collision avec ce rayon laser, démarre la scene de combat. La variable offsetWhereShooting determine où vas t-il tirée, une animation va ce produire lorsqu'il tire (Un laser rouge)</p></li>
 *     <li><p>Aussi, lors d'un contact physique avec le Joueur, il va aussi ce produire une scene de combat</p></li>
 *     <li><p>Lorsque le monstre meurs, il s'auto supprime dans la liste "group" grâce a "getGroup()" dans Entity. Tous cela se passe dans "updates()" de cette classe.</p></li>
 * </ul>
 *
 * @see fr.chrzdevelopment.game.entities.Entity
 * @since v1.0
 * @author CHRZASZCZ Naulan
 */
public class Monster extends Entity
{
    private final Random random = new Random();

    private int offsetWhereShooting = -1;   // TODO: Codé la fonction "randomShoot()" grâce a cette variable


    /**
     * @param group Un endroit où on place tous les Sprites (Les entités).
     * @param x La localisation en x du Sprite.
     * @param y La localisation en y du Sprite.
     * @param velocity La vitesse du Monstre.
     */
    public Monster(List<Entity> group, int x, int y, int velocity) {
        super(group, "Monster", x, y, velocity);
    }

    /** Bouge le monstre aléatoirement sur les 4 directions possible. */
    public void randomMove()
    {
        int moveRandomly = random.nextInt(0, 4);

        if (moveRandomly == UP && !getWhereCollide()[0])
            moveUp();
        if (moveRandomly == DOWN && !getWhereCollide()[1])
            moveDown();
        if (moveRandomly == LEFT && !getWhereCollide()[2])
            moveLeft();
        if (moveRandomly == RIGHT && !getWhereCollide()[3])
            moveRight();
    }

    /** Fait tirée un rayon laser aléatoirement au monstre sur 4 directions possible. */
    public void randomShoot() {
        // TODO: Codé les rayons laser.
    }

    /** Fait apparaitre un laser rouge sur la carte. */
    public void laserAnimation(int[][] map) {
        // TODO: Réflechir et codé la fonction qui permet de faire l'animation du rayon laser
    }

    @Override
    public void updates()
    {
        randomMove();

        // TODO: Demander a l'enseignant si ceci clear bien l'objet de la mémoire.
        if (getHealth() <= 0)
            getGroup().remove(this);
    }
}
