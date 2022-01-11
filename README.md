# `SAE_1.01 & 1.02` **Projet-Jeux**
## _IUT de Montreuil - BUT Informatique_

Ce repository Github est consacré pour la `SAE_1.01` et la `SAE_1.02` qui est un projet sur un jeu où l'affichage sera
uniquement sur un terminal et non sur une interface telle que JavaFX par exemple.

* Le jeu est un jeu de type aventure dans une sorte de donjon où il y va avoir des monstres qui vont vous embêter lors 
de collectes de nombreuses pièces qui seront sur votre chemin ou dans les coffres qui sera généré aléatoirement sur la carte.
* Ce jeu possède beaucoup de RNG (Aléatoire) comme la génération supplémentaire des obstacles, le spawn du joueur et des 
monstres (aussi les clés, coffres, etc.)
* L'affichage du jeu est uniquement sur la console, des émojis seront là pour faire plus joli dans la console 
(au lieu d'avoir des chiffres ou des lettres qui seront illisibles).
* Il faut récolter toutes les pieces pour gagner un niveau !

![titleScreen](https://eapi.pcloud.com/getpubthumb?code=XZo2nFZUojd9tO3JSyUVNKtgPqibhbjnQcy&linkpassword=undefined&size=1127x281&crop=0&type=auto)
### Comment le lancé et où l'obtenir ?
Il faut suffit d'aller dans `Releases`. Et de choisir un `tag` _(Alpha ou définitive de préférence)_. 
Ensuite de télécharger le fichier `.jar` qui est un format compilé et portatif.
Et enfin, de lancer cette commande dans la console:

`java -jar SAE_1.01-Projet-jeux_v{version}`

### Comment y jouer ?
#### Que représente quoi ?
Les choses représentées sur le terminal lors du lancement du jeu sont different selon l'OS !
Windows aura un affichage ASCII alors que Linux aura un affichage beaucoup plus beau en UTF-8
##### Sur windows:
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

##### Sur Linux:
![illustrationMapOnLinux](https://eapi.pcloud.com/getpubthumb?code=XZSbgFZ7ry8QY4VqdF9rJFn1M7kSXitV1WV&linkpassword=undefined&size=300x300&crop=0&type=auto)
* 🤠 **>** Désigne le joueur, un joli cowboy !
* 🧱 **>** Désigne un mur.
* 👾 **>** Désigne un monstre.
* 🧰 **>** Désigne un coffre.
* 💰 **>** Désigne la moula, les pieces sur le niveau, seul moyen de gagné le niveau.
* 🔑 **>** Désigne une clé qui permet d'ouvrir les coffres.
* ⚡ **>** Désigne un projectile enemie qui engendra un dégât au joueur.

#### Les contrôles
Les touches nécessaires sont les suivants → `z`, `q`, `s`, `d`, `a`.
* `z` **>** Permet de bouger vers le haut.
* `s` **>** Permet de bouger vers le bas.
* `q` **>** Permet de bouger vers la gauche.
* `d` **>** Permet de bouger vers la droite.
* `a` **>** Permets d'actionner un element autour de vous.

Une fois que vous êtes entrée votre touche dans le terminal, appuyé sur `enter` de votre clavier.

~~**Tips:** Si vous voulez vous déplacé constament vers la droite, il vous suffit de rentré qu'une fois votre touche de 
déplacement et de spam votre touche entrer de votre clavier~~ _(Plus besoin depuis la version définitive)_