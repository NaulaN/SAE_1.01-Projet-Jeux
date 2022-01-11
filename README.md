# `SAE_1.01 & 1.02` **Projet-Jeux**
## _IUT de Montreuil - BUT Informatique_

Ce repository Github est consacré pour la `SAE_1.01` et la `SAE_1.02` qui est un Projet sur un jeu où l'affichage sera
uniquement sur un terminal et non sur une interface telle que JavaFX par exemple.

* Le jeu est un jeu de type aventure dans une sorte de donjon où il y va avoir des monstres qui vas vous embêter lors de
collecte de nombreuse pieces qui sera sur votre chemin ou dans les coffres qui sera générer aléatoirement sur la carte.
* Ce jeu possède beaucoup de RNG (Aléatoire) comme la generation supplémentaire des obstacles, le spawn du joueur et des 
monstres (Aussi les clés, coffres ect)
* L'affichage du jeu est uniquement sur la console, des emojis sera là pour faire plus joli dans la console (Au lieu 
d'avoir des chiffres ou des lettres qui sera illisible).
* Il faut récolter toutes les pieces pour gagner un niveau !

![titleScreen](https://eapi.pcloud.com/getpubthumb?code=XZo2nFZUojd9tO3JSyUVNKtgPqibhbjnQcy&linkpassword=undefined&size=1127x281&crop=0&type=auto)
### Comment le lancé et où l'obtenir ?
Il faut suffit d'aller dans `Releases`. Et de choisir un `tag` _(Alpha ou definitive de preference)_. 
Ensuite de télécharger le fichier `.jar` qui est un format compilé et portatif. 
Et enfin, de lancé cette commande dans la console:

`java -jar SAE_1.01-Projet-jeux_v{version}`

### Comment y joué ?
#### Que représente quoi ?
Les choses représenté sur le terminal lors du lancement du jeu est different selon l'OS !
Windows aura un affichage ASCII alors que Linux aura un affichage beaucoup plus beau en UTF-8
##### Sur windows:
![illustrationMapOnWindows](https://eapi.pcloud.com/getpubthumb?code=XZMq9FZ4N5iPPInMp8xQ9MUJXXqCjH1gRgk&linkpassword=undefined&size=300x300&crop=0&type=auto)
* ![player](https://eapi.pcloud.com/getpubthumb?code=XZHWnFZk3IXz5H7jFudThzH32e6Qf8nFtR7&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Ce joli petit pixel représente le joueur.
* ![monster](https://eapi.pcloud.com/getpubthumb?code=XZ8WnFZ6dUw4UjdSbB83xVtnPOaNQpkATeX&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Oooh ! Qu'est-ce qu'il est beau ce pixel toute rouge menacent, il représente un monstre.
* ![coin](https://eapi.pcloud.com/getpubthumb?code=XZmWnFZkrHqbGdEdrQtjHuoTOhQEQx42uhy&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Cette element représente une piece, le seul moyen de gagné un niveau.
* ![chest](https://eapi.pcloud.com/getpubthumb?code=XZSWnFZKLL7gmEeqQmIfv6IYORNikV5EMYX&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Ce truc vert est un coffre... Je n'ai pas trouvé mieux 🙁 Une fois ouvert, il devient bleu.
* ![wall](https://eapi.pcloud.com/getpubthumb?code=XZjWnFZoITQkBECz0hLHcEHeiBfWHzKY0Vk&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Un mur, juste un mur.
* ![key](https://eapi.pcloud.com/getpubthumb?code=XZft9FZHwcGC1shg2kab63IONGqYJ6Y3Bc7&linkpassword=undefined&size=20x20&crop=0&type=auto) 
**>** Représente une clé qui permet d'ouvrir les coffres.

##### Sur Linux:
![illustrationMapOnLinux](https://eapi.pcloud.com/getpubthumb?code=XZSbgFZ7ry8QY4VqdF9rJFn1M7kSXitV1WV&linkpassword=undefined&size=482x684&crop=0&type=auto)
* 🤠 **>** Désigne le joueur, un joli cowboy !
* 🧱 **>** Désigne un mur.
* 👾 **>** Désigne un monstre.
* 🧰 **>** Désigne un coffre.
* 💰 **>** Désigne la moula, les pieces sur le niveau, seul moyen de gagné le niveau.
* 🔑 **>** Désigne une clé qui permet d'ouvrir les coffres.
* ⚡ **>** Désigne un projectile enemie qui engendra un dégât au joueur.

#### Les contrôles
Les touches nécessaires sont les suivants → `z`, `q`, `s`, `d`, `a`.
* `z` **>** Permet de bougé vers le haut.
* `s` **>** Permet de bougé vers le bas.
* `q` **>** Permet de bougé vers la gauche.
* `d` **>** Permet de bougé vers la droite.
* `a` **>** Permet de d'actionné un element autours de vous.

Une fois que vous avez entrée votre touche dans le terminal, appuyé sur `enter` de votre clavier.

~~**Tips:** Si vous voulez vous déplacé constament vers la droite, il vous suffit de rentré qu'une fois votre touche de 
déplacement et de spam votre touche entrer de votre clavier~~ _(Plus besoin depuis la version définitive)_