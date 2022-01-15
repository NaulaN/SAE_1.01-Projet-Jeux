# Document d'analyse.
## Presentation du jeux.
_Extrait du [README.md](https://github.com/NaulaN/SAE_1.01-Projet-Jeux#readme)_

* Le jeu est un jeu de type aventure dans une sorte de donjon où il y va avoir des monstres qui vont vous embêter lors
  de collectes de nombreuses pièces qui seront sur votre chemin ou dans les coffres qui sera généré aléatoirement sur la carte.
* Ce jeu possède beaucoup de RNG (Aléatoire) comme la génération supplémentaire des obstacles, le spawn du joueur et des
  monstres (aussi les clés, coffres, etc.)
* L'affichage du jeu est uniquement sur la console, des émojis seront là pour faire plus joli dans la console
  (au lieu d'avoir des chiffres ou des lettres qui seront illisibles).
* Il faut récolter toutes les pieces pour gagner un niveau !

### Que représente quoi ?
Les choses représentées sur le terminal lors du lancement du jeu sont different selon l'OS !
Windows aura un affichage ASCII alors que Linux aura un affichage beaucoup plus beau en UTF-8
#### Sur windows:
![illustrationMapOnWindows](https://eapi.pcloud.com/getpubthumb?code=XZMq9FZ4N5iPPInMp8xQ9MUJXXqCjH1gRgk&linkpassword=undefined&size=300x300&crop=0&type=auto)
* ![player](https://eapi.pcloud.com/getpubthumb?code=XZHWnFZk3IXz5H7jFudThzH32e6Qf8nFtR7&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Ce joli petit pixel représente le joueur.
* ![monster](https://eapi.pcloud.com/getpubthumb?code=XZ8WnFZ6dUw4UjdSbB83xVtnPOaNQpkATeX&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Ooh ! Qu'est-ce qu'il est beau ce pixel tout rouge menacent, il représente un monstre.
* ![coin](https://eapi.pcloud.com/getpubthumb?code=XZmWnFZkrHqbGdEdrQtjHuoTOhQEQx42uhy&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Cette element représente une pièce, le seul moyen de gagner un niveau.
* ![chest](https://eapi.pcloud.com/getpubthumb?code=XZSWnFZKLL7gmEeqQmIfv6IYORNikV5EMYX&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Ce truc vert est un coffre... Je n'ai pas trouvé mieux 🙁 Une fois ouvert, il devient bleu.
* ![wall](https://eapi.pcloud.com/getpubthumb?code=XZjWnFZoITQkBECz0hLHcEHeiBfWHzKY0Vk&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Un mur, juste un mur.
* ![key](https://eapi.pcloud.com/getpubthumb?code=XZft9FZHwcGC1shg2kab63IONGqYJ6Y3Bc7&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Représente une clé qui permet d'ouvrir les coffres.
* ![sword](https://eapi.pcloud.com/getpubthumb?code=XZgUwFZJA7WoKuTudkLtzYhpn9ae8vhV3rX&linkpassword=undefined&size=20x20&crop=0&type=auto)
  **>** Représente une épée qui permet de tuer des monstres.

#### Sur Linux:
![illustrationMapOnLinux](https://eapi.pcloud.com/getpubthumb?code=XZSbgFZ7ry8QY4VqdF9rJFn1M7kSXitV1WV&linkpassword=undefined&size=300x300&crop=0&type=auto)
* 🤠 **>** Désigne le joueur, un joli cowboy !
* 🧱 **>** Désigne un mur.
* 👾 **>** Désigne un monstre.
* 🧰 **>** Désigne un coffre.
* 💰 **>** Désigne la moula, les pieces sur le niveau, seul moyen de gagné le niveau.
* 🔑 **>** Désigne une clé qui permet d'ouvrir les coffres.
* ⚡ **>** Désigne un projectile enemie qui engendra un dégât au joueur.
* 🪓️ **>** Désigne une épée qui à pour but de tuer un ennemie.

#### Les contrôles
Les touches nécessaires sont les suivants → `z`, `q`, `s`, `d`, `a` et `e`.
* `z` **>** Permet de bouger vers le haut.
* `s` **>** Permet de bouger vers le bas.
* `q` **>** Permet de bouger vers la gauche.
* `d` **>** Permet de bouger vers la droite.
* `a` **>** Permets d'actionner un element autour de vous.
* `e` **>** Permets de lancer une épée si on en possede une dans notre inventaire.

Une fois que vous êtes entrée votre touche dans le terminal, appuyé sur `enter` de votre clavier.


##Different solutions:
### Detection d'un monstre le plus proche:
####__Avant:__
````java
if (!isThrow) {
    Map<Integer, Monster> diffBetweenLoc = new HashMap<>();
    for (Entity sprite : getGroup())
        if (sprite instanceof Monster)
            diffBetweenLoc.put((sprite.getXPosition()+sprite.getYPosition())-(getXPosition()+getYPosition()), (Monster) sprite);

    final Integer[] min = {null};
    diffBetweenLoc.forEach((integer, monster) -> {
        if (min[0] == null)
            min[0] = integer;

        if (min[0] >= integer)
            min[0] = integer;
    });

    monsterAtTrack = diffBetweenLoc.get(min[0]);
    isThrow = true;
}
````
> Cette algo permet de detecté le monstre le plus pres et de le rangé dans une HashMap
> 
>       Ex: key: -1, value: Obj@45546Monster
>           key: -2, value: Obj@45856Monster
>           key: 3, value: Obj@455446Monster
> 
> Puis lance une detection de quel est le plus petit dans les clés contenue de la HashMap
> Range le monstre dans un variable et dit que le projectile est lancé grâce au changement d'etat de la variable `isThrow`.
* Avantage ? `Aucun`,
* Inconvénients ? `Calcul mal faite, HashMap inutile, detection des valeurs maximum non optimisé`

####__Apres:__
````java
boolean i = true;
if (!isThrow) {
    int minHypo = 0;

    for (Entity sprite : getGroup()){
        if (sprite instanceof Monster) {
            int coteAdjacent = sprite.getYPosition()-this.getXPosition();
            int coteOppose = sprite.getYPosition()-this.getYPosition();

            int hypotenuse = (int) Math.sqrt(Math.pow(coteAdjacent, 2) + Math.pow(coteOppose, 2));
            // Tres moche, mais pas le choix
            if (i) { minHypo = hypotenuse; monsterAtTrack = (Monster) sprite; i = false; }

            if (hypotenuse < minHypo) {
                minHypo = hypotenuse;
                monsterAtTrack = (Monster) sprite;
            }
        }
    }
    isThrow = true;
}
````
> Cette algo permet de detecté le monstre le plus pres.
> 
> Utilise le theorems de Pythagore et utilise l'Hypotenuse pour détermine le monstre le plus pres du joueur
> 
> Une condition juste apres avoir calculé l'hypoténuse et la pour detecté la valeurs la plus petite
> Range le monstre dans un variable et dit que le projectile est lancé grâce au changement d'etat de la variable `isThrow`.
* `Avantage ?` Permet de mieux calculer la distance entre le joueur et l'ennemie grâce au theorem de Pythagore.
prend moins d'espace mémoire car il ne possede pas de HasMap et les variables sont utilisé de manière different.
* `Inconvénients ?` Calcul mal faite, HashMap inutile, detection des valeurs maximum non optimisé.


### Lancement des projectiles.
__Avant:__
````java
int shoot = (int) (Math.random()*50);

if (shoot == 0 || shoot == 1 || shoot == 2 || shoot == 3) {
    getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), shoot));
    
    Sound.play("laserShoot.wav", 0);
}
````
> Cette algo génère un nombre de 0 à 49 et lorsqu'il est sur 0, 1, 2 ou 3 ⇒ Il lance un projectile.
* Avantage ? `Lisible et comprehensible`,
* Inconvénients ? `Il est un peut nul cette algo`


__Apres:__
````java
if (player.getYPosition() == getYPosition() || player.getXPosition() == getXPosition()) {
    if (player.getXPosition() > getXPosition() && player.getYPosition() == getYPosition()) {
        for (int x1 = getXPosition(); x1 < XMax; x1++)
            if (x1 == player.getXPosition()) {
                getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), 1));
                Sound.play("laserShoot.wav", 0);
                return;
            }
    } else if (player.getXPosition() < getXPosition() && player.getYPosition() == getYPosition()) {
        for (int x2 = getXPosition(); x2 > 0; x2--)
            if (x2 == player.getXPosition()) {
                getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), 2));
                Sound.play("laserShoot.wav", 0);
                return;
            }
    }

    if (player.getYPosition() > getYPosition() && player.getXPosition() == getXPosition()) {
        for (int y1 = getYPosition(); y1 < YMax; y1++)
            if (y1 == player.getYPosition()) {
                getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), 3));
                Sound.play("laserShoot.wav", 0);
                return;
            }
    } else if (player.getYPosition() < getYPosition() && player.getXPosition() == getXPosition()) {
        for (int y2 = getYPosition(); y2 > 0; y2--)
            if (y2 == player.getYPosition()) {
                getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), 0));
                Sound.play("laserShoot.wav", 0);
                return;
            }
    }
} else {
    int shoot = (int) (Math.random()*50);

    if (shoot == 0 || shoot == 1 || shoot == 2 || shoot == 3) {
        getGroup().add(new Laser(getGroup(), getXPosition(), getYPosition(), shoot));

        Sound.play("laserShoot.wav", 0);
    }
}
````
> Cette algo génère un nombre de 0 à 49 et lorsqu'il est sur 0, 1, 2 ou 3 ⇒ Il lance un projectile.
> 
> Sinon, si le joueur se trouve sur la même axe que celui du monstre, le monstre vas tirer plusieurs lasers vers la direction du joueur.
* Avantage ? `Tire enemie plus intelligent.`,
* Inconvénients ? `Légerement moins lisible au niveau des conditions`

### Generation des pieces
````java
determinateCoins = (int) (1+Math.random()*10);
int initDeterminateCoins = determinateCoins;

boolean generateCircleCoins = false;
int x; int y; int[] loc;
for (int c = 0; c < initDeterminateCoins; c++) {
    loc = findALocation();
    /*  private int[] findALocation()
        {
            int[] loc = new int[2];
            do {
                loc[0] = (int) (1+Math.random()*map[0].length-1);
                loc[1] = (int) (1+Math.random()*map.length-1);
            } while (map[loc[1]][loc[0]] == WALL || map[loc[1]][loc[0]] == MONSTER || map[loc[1]][loc[0]] == CHEST || map[loc[1]][loc[0]] == COIN || map[loc[1]][loc[0]] == SWORD);
    
            return loc;
        } */
        
    x = loc[0]; y = loc[1];

    // Genère pour une seule piece, un rond
    if (!generateCircleCoins) {
        int rayon = 3;
        // C = 2*pi*rayon
        int circonference = (int) (2*PI*rayon);

        for (int t = 1; t < circonference; t++) {
            /* x(temps) = r * cos(temps + angleDeDépart)
               y(temps) = r * sin(temps + angleDeDépart) */
            x = (int) (loc[0] + (rayon * Math.cos(t)));
            y = (int) (loc[1] + (rayon * Math.sin(t)));

            if (x < map[0].length-1 && x > 0)
                if (y < map.length-1 && y > 0)
                    if (map[y][x] != WALL && map[y][x] != MONSTER && map[y][x] != CHEST && map[y][x] != SWORD) {
                        new Coin(allSprites, x, y);
                        determinateCoins++;
                    }
                else break;
            else break;
        }
        generateCircleCoins = true;
    } else {
        new Coin(allSprites, x, y);
    }
}
````
> Cette algo genere des pieces a des endroits different.
> Genere les pieces une seul fois en forme de rond.

