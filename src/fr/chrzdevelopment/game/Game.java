package fr.chrzdevelopment.game;
// https://r12a.github.io/app-conversion/   Java char compatibility

import fr.chrzdevelopment.game.entities.*;
import fr.chrzdevelopment.game.threads.KeyboardInputThread;
import fr.chrzdevelopment.game.threads.RefreshAndDisplayThread;
import org.json.JSONObject;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static fr.chrzdevelopment.game.Const.*;


/**
 * <p>Rassemble tous les éléments du jeu !</p>
 * @author CHRZASZCZ Naulan
 * @see KeyboardInputThread
 * @see RefreshAndDisplayThread
 * @see fr.chrzdevelopment.game.MapsEngine
 * @see fr.chrzdevelopment.game.KeyboardInput
 * @see fr.chrzdevelopment.game.Const
 */
public class Game
{
    private final List<Entity> allSprites = new CopyOnWriteArrayList<>();
    private Player player;

    // TODO:
    private JSONObject saveFile;
    private String playerName;

    private final KeyboardInput keyboardInput = new KeyboardInput();
    private final MapsEngine mapsEngine;

    private final String OS;
    private final ProcessBuilder processBuilder;

    private final RefreshAndDisplayThread refreshAndDisplayThread = new RefreshAndDisplayThread(this);
    private final KeyboardInputThread keyboardInputThread = new KeyboardInputThread(this, keyboardInput);

    private int timerInvulnerability = 0;


    /**
     * <p>Charge le fichier de sauvegarde .json</p>
     * <p>Charge les graphics selon l'OS utilisé</p>
     * <p>Charge le moteur de la carte et genere une carte par default</p>
     * <p>Charge et fait spawn tous les entity</p>
     * <p>Ne charge pas les murs, seulement à partir du Niveau2 et +</p>
     */
    public Game()
    {
        OS = System.getProperty("os.name");
        initGraphics(OS);
        // clear terminal commands
        processBuilder = (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10")) ? new ProcessBuilder("cmd", "/c", "cls") : new ProcessBuilder("clear");

        saveFile = SaveFile.load("res/");
        playerName = saveFile.getString("playerName");

        // Crée la taille de la carte
        mapsEngine = new MapsEngine(30, 20);

        // La generation.
        mapsEngine.generateMap();
        spawnEntity();
    }

    public void loop()
    {
        titleScreen();

        // Game loops
        refreshAndDisplayThread.start();
        keyboardInputThread.setPriority(Thread.MAX_PRIORITY);  // Au cas où :eyes:
        keyboardInputThread.start();
    }

    private void initGraphics(String OS)
    {
        if (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10"))
            windowsGraphics();
        else linuxGraphics();
    }

    private void clearConsole()
    {
        // Essaye encore une fois si le premier essaie ne marche pas sinon il casse la boucle.
        Process process = null;
        try {
            process = processBuilder.inheritIO().start();
        } catch (IOException IOe) {
            if (!OS.equalsIgnoreCase("windows") && !OS.equalsIgnoreCase("windows 10")) {
                // la deuxième méthode qui permet de nettoyer la console...
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }

        if (process != null)
            try {
                process.waitFor();
            } catch (InterruptedException ignored) { }
    }

    /** Chaque changement de niveau, cette fonction est appelée et qui permet de tous regenerate proprement */
    private void spawnEntity()
    {
        allSprites.clear();
        player = mapsEngine.spawnPlayer(allSprites);
        mapsEngine.spawnMonster(allSprites);
        mapsEngine.spawnCoin(allSprites);
        mapsEngine.spawnChest(allSprites);
        mapsEngine.spawnKey(allSprites);
        mapsEngine.spawnSword(allSprites);
    }

    private void titleScreen()
    {
        Sound.play("music.wav", -1);
        clearConsole();

        System.out.println();
        // Title screen
        System.out.println(ANSI_RED + "                 ▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄       ▄                 ");
        System.out.println("                ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌                ");
        System.out.println("               ▐░▌       ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀▀█░█▀▀▀       ▐░▌               ");
        System.out.println("              ▐░▌            ▐░▌     ▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌           ▐░▌           ▐░▌              ");
        System.out.println(" ▄▄▄▄▄▄▄▄▄▄▄ ▐░▌             ▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌           ▐░▌            ▐░▌ ▄▄▄▄▄▄▄▄▄▄▄ ");
        System.out.println("▐░░░░░░░░░░░▌▐░▌             ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌           ▐░▌            ▐░▌▐░░░░░░░░░░░▌");
        System.out.println(" ▀▀▀▀▀▀▀▀▀▀▀ ▐░▌             ▐░▌     ▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀      ▐░▌           ▐░▌            ▐░▌ ▀▀▀▀▀▀▀▀▀▀▀ ");
        System.out.println("              ▐░▌            ▐░▌     ▐░▌     ▐░▌  ▐░▌               ▐░▌           ▐░▌           ▐░▌              ");
        System.out.println("               ▐░▌       ▄▄▄▄█░█▄▄▄▄ ▐░▌      ▐░▌ ▐░▌           ▄▄▄▄█░█▄▄▄▄  ▄▄▄▄▄█░▌          ▐░▌               ");
        System.out.println("                ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░▌         ▐░▌                ");
        System.out.println("                  ▀      ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀            ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀           ▀                 " + ANSI_RESET);
        System.out.println();

        System.out.println();
        System.out.println("Bienvenue chèr(e) aventurièr(e) ! Ici, multiple aventure vous attend, entre les monstres, les coffres, les obstacles... Il a de quoi faire !");
        System.out.println(ANSI_RED + "============================================================================================================================================" + ANSI_RESET);
        System.out.print("\t" + ANSI_GREEN + "Entrez votre nom > ");
        playerName = keyboardInput.getStringInput();

        saveFile.put("playerName", playerName);
        saveFile.put("howManyPlay", saveFile.getInt("howManyPlay")+1);
    }

    private void gameOverScreen()
    {
        System.out.println(ANSI_RED + "                 ▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄       ▄▄  ▄▄▄▄▄▄▄▄▄▄▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄               ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄       ▄                 ");
        System.out.println("                ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌     ▐░░▌▐░░░░░░░░░░░▌     ▐░░░░░░░░░░░▌▐░▌             ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌                ");
        System.out.println("               ▐░▌      ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀▀▀      ▐░█▀▀▀▀▀▀▀█░▌ ▐░▌           ▐░▌ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌      ▐░▌               ");
        System.out.println("              ▐░▌       ▐░▌          ▐░▌       ▐░▌▐░▌▐░▌ ▐░▌▐░▌▐░▌               ▐░▌       ▐░▌  ▐░▌         ▐░▌  ▐░▌          ▐░▌       ▐░▌       ▐░▌              ");
        System.out.println(" ▄▄▄▄▄▄▄▄▄▄▄ ▐░▌        ▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░▌       ▐░▌   ▐░▌       ▐░▌   ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌        ▐░▌ ▄▄▄▄▄▄▄▄▄▄▄ ");
        System.out.println("▐░░░░░░░░░░░▌▐░▌        ▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌     ▐░▌       ▐░▌    ▐░▌     ▐░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌        ▐░▌▐░░░░░░░░░░░▌");
        System.out.println(" ▀▀▀▀▀▀▀▀▀▀▀ ▐░▌        ▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀▀▀      ▐░▌       ▐░▌     ▐░▌   ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀         ▐░▌ ▀▀▀▀▀▀▀▀▀▀▀ ");
        System.out.println("              ▐░▌       ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░▌               ▐░▌       ▐░▌      ▐░▌ ▐░▌      ▐░▌          ▐░▌     ▐░▌         ▐░▌              ");
        System.out.println("               ▐░▌      ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄      ▐░█▄▄▄▄▄▄▄█░▌       ▐░▐░▌       ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌       ▐░▌               ");
        System.out.println("                ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌     ▐░░░░░░░░░░░▌        ▐░▌        ▐░░░░░░░░░░░▌▐░▌       ▐░▌     ▐░▌                ");
        System.out.println("                 ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀       ▀▀▀▀▀▀▀▀▀▀▀          ▀          ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀       ▀                 " + ANSI_RESET);
        System.out.println(ANSI_RED + "============================================================================================================================================" + ANSI_RESET);
        // Informe les données sauvegarde lors de la ou des session(s) de jeu
        System.out.println("Vous avez eu " + saveFile.getInt("totalCoins") + " " + COIN_IMG + " lors de vos sessions de jeu");
        System.out.println("Vous avez jouée " + saveFile.getInt("howManyPlay") + " fois");
        System.out.println("Le niveau max que vous avez atteint est le niveau " + saveFile.getInt("maxLvl"));

        SaveFile.write(saveFile, "res/");
    }

    public synchronized void draws()
    {
        clearConsole();

        // Quand le joueur na plus de vie, un ecran de Game Over s'affiche et interrompt la boucle et les Threads en fonctionnement
        if (player.getHealth() <= 0) {
            gameOverScreen();

            refreshAndDisplayThread.terminate();
            keyboardInputThread.terminate();
        } else {
            // Information relative au nombre de piece nécessaire pour gagner le niveau
            System.out.println("\t" + ANSI_RED + "NIVEAU: " + mapsEngine.getMapLvl() + ANSI_RESET);
            System.out.println(ANSI_GREEN + playerName + " ! Vous devez avoir " + mapsEngine.getDeterminateCoins() + " " + COIN_IMG + ANSI_GREEN + " pour pouvoir gagner le niveau" + ANSI_RESET);
            mapsEngine.draw();

            // Affiche les pieces du joueur obtenu et son nombre de point de vie total (Nombre de <3)
            StringBuilder msgHud = new StringBuilder().append(COIN_IMG).append(": ").append(player.getCoins()).append("   ");
            for (int h = 1; h <= player.getHealth(); h++)
                msgHud.append(HEART_IMG).append(" ");
            msgHud.append("   ").append(KEY_IMG).append(": ").append((player.getHaveAKey()) ? "oui" : "non").append("   ");
            msgHud.append(SWORD_IMG).append(": ").append((player.getHaveSword()) ? "oui" : "non");
            System.out.print("\t" + msgHud);
            System.out.println();
        }
    }

    public synchronized void updates()
    {
        // Quit le jeu
        if (keyboardInput.getQuitAction()) {
            SaveFile.write(saveFile, "res/");
            refreshAndDisplayThread.terminate();
            keyboardInputThread.terminate();
        }
        else {             // les déplacements du joueur
            if (!player.getCollideUp() && keyboardInput.getMoveUp())
                player.moveUp();
            if (!player.getCollideDown() && keyboardInput.getMoveDown())
                player.moveDown();
            if (!player.getCollideLeft() && keyboardInput.getMoveLeft())
                player.moveLeft();
            if (!player.getCollideRight() && keyboardInput.getMoveRight())
                player.moveRight();
        }

        // Si toute les pieces on était récolté par le joueur, change de niveau
        if (player.getCoins() == mapsEngine.getDeterminateCoins())
            mapsEngine.addMapLvl();

        // Génère une nouvelle map si on change de niveau
        if (mapsEngine.getMapLvl() > 1 && !mapsEngine.getIsGenerate()) {
            mapsEngine.setHeight((int) (20+Math.random()*15));
            mapsEngine.setWidth((int) (20+Math.random()*15));
            mapsEngine.generateMap();
            mapsEngine.generateObstacles();
            spawnEntity();

            // Active le moment où il doit être invulnerable
            timerInvulnerability = 0;
            player.enableInvulnerability();

            // Range dans un fichier, le niveau maximum atteint s'il dépasse le precedent
            if (mapsEngine.getMapLvl() > saveFile.getInt("maxLvl"))
                saveFile.put("maxLvl", mapsEngine.getMapLvl());
        }

        for (Entity sprite : allSprites)
        {
            // Actualise l'affichage sur l'écran et supprime la dernière frame.
            sprite.checkCollision(mapsEngine.getCalqueCollide());
            boolean collide = sprite.getDataImg() != COIN && sprite.getDataImg() != KEY && sprite.getDataImg() != LASER_VERTICAL && sprite.getDataImg() != LASER_HORIZONTAL && sprite.getDataImg() != SWORD;
            mapsEngine.updateEntity(sprite, collide);


            /*
                    Update player
                Lorsqu'il apparait sur un niveau, il va etres invincible pendant 3s
                Lorsqu'il possede une épée dans sont inventaire, il va lancée une nouvelle épée
             */
            if (sprite instanceof Player)
            {
                if (player.getHaveInvulnerability())
                    if (timerInvulnerability != TIME_INVULNERABILITY)
                        timerInvulnerability++;
                    else player.disableInvulnerability();

                if (keyboardInput.getLaunch())
                    if (player.getHaveSword()) {
                        Sword sword;
                        // Determine sur quel sens on doit lancer l'épée
                        if (keyboardInput.getMoveUp())
                            sword = new Sword(allSprites, player.getXPosition(), player.getYPosition()+1);
                        else if (keyboardInput.getMoveDown())
                            sword = new Sword(allSprites, player.getXPosition(), player.getYPosition()-1);
                        else if (keyboardInput.getMoveRight())
                            sword = new Sword(allSprites, player.getXPosition()+1, player.getYPosition());
                        else if (keyboardInput.getMoveLeft())
                            sword = new Sword(allSprites, player.getXPosition()-1, player.getYPosition());
                        else    // Au cas où si il a pas de input
                            sword = new Sword(allSprites, player.getXPosition(), player.getYPosition());
                        sword.launch();
                        player.haventSword();
                    }
            }


            /*      Update all Monsters
                Cette partie du code permet juste de faire tirer des lasers au monstre. */
            if (sprite instanceof Monster) {
                Monster monster = (Monster) sprite;
                monster.shoot(player, mapsEngine.getMap()[0].length, mapsEngine.getMap().length);
            }


            /*      Update all coins
                 Cette partie du code permet de faire disparaitre et d'ajouter une pièce à l'inventaire imaginaire du joueur lorsqu'il est dessus.
                 Joue un bruitage lorsqu'il la pièce disparaît qui informe au joueur qu'il la bien prirent cette pièce. */
            if (sprite instanceof Coin) {
                Coin coin = (Coin) sprite;
                if (sprite.getXPosition() == player.getXPosition() && sprite.getYPosition() == player.getYPosition()) {
                    player.addCoin();
                    mapsEngine.setElementMap(coin.getXPosition(), coin.getYPosition(), EMPTY, false);
                    allSprites.remove(coin);

                    saveFile.put("totalCoins", saveFile.getInt("totalCoins")+1);
                    Sound.play("pickupCoin.wav", 0);
                }
            }


            if ((sprite.getXPosition() == player.getXPosition()-1 && sprite.getYPosition() == player.getYPosition())
                    || (sprite.getXPosition() == player.getXPosition()+1 && sprite.getYPosition() == player.getYPosition())
                    || (sprite.getYPosition() == player.getYPosition()-1 && sprite.getXPosition() == player.getXPosition())
                    || (sprite.getYPosition() == player.getYPosition()+1 && sprite.getXPosition() == player.getXPosition())) {
                /*      Update all chest
                    Cette partie du code permet lorsque "a" est entrée dans l'input au clavier dans le terminal et que le joueur et autours du coffre (Max un block), le coffre s'ouvre.
                    Selon le contenu du coffre, il va se passer différentes choses.
                    Il peut avoir une vie, une pièce ou rien.
                    Bien entendu, un bruitage sera joué selon le contenu.
                    Le coffre s'ouvre uniquement si le joueur a une clé ! */
                if (sprite instanceof Chest) {
                    Chest chest = (Chest) sprite;
                    if (keyboardInput.getSelect())
                        if (player.getHaveAKey()) {
                            // Quand le contenu du coffre est une piece.
                            if (chest.getWhatInside().equalsIgnoreCase("coin")) {
                                player.addCoin();

                                saveFile.put("totalCoins", saveFile.getInt("totalCoins")+1);
                                Sound.play("pickupCoin.wav", 0);
                            }
                            // Quand le contenu du coffre est une vie en plus pour le joueur.
                            else if (chest.getWhatInside().equalsIgnoreCase("health")) {
                                player.setHealth(player.getHealth()+1);

                                Sound.play("powerUp.wav", 0);
                            }
                            chest.hit();
                            player.haventKey();

                            Sound.play("openChest.wav", 0);
                        }
                }

                /*      Update all keys
                    Cette partie du code permet lorsque "a" est entrée dans l'input au clavier dans le terminal et que le joueur et autours de la clé (Max un block), la clé est dans l'inventaire du joueur.
                    Une fois que le joueur obtient la clé dans l'inventaire, l'état de la variable "haveKey" change en "true", uniquement si le joueur na pas de clé present dans l'inventaire.
                    Ensuite, la clé disparait de la carte et joue un bruitage pour informer au joueur qu'il a bien obtenue la clé */
                if (sprite instanceof Key) {
                    Key key = (Key) sprite;
                    if (keyboardInput.getSelect())
                        if (!player.getHaveAKey()) {
                            player.haveKey();
                            mapsEngine.setElementMap(key.getXPosition(), key.getYPosition(), EMPTY, false);
                            allSprites.remove(key);

                            Sound.play("getKeys.wav", 0);
                        }
                }


                /*
                        Update all swords
                    Cette partie du code permet que lors que le joueur n'a pas d'épée dans son inventaire et que l'épée se trouve autours du joueur.
                    Range l'épée dans l'inventaire et supprimer physiquement sur la carte.
                 */
                if (sprite instanceof Sword) {
                    Sword sword = (Sword) sprite;
                    if (keyboardInput.getSelect())
                        if (!player.getHaveSword()) {
                            player.haveSword();
                            mapsEngine.setElementMap(sword.getXPosition(), sword.getYPosition(), EMPTY, false);
                            allSprites.remove(sprite);
                        }
                }
            }


            /*
                    Update all swords
                Gère les déplacements de l'épée lorsque le joueur actionne le bouton 'e'.
                Supprime le monstre et l'épée lors d'une collision avec le monstre.
            */
            if (sprite instanceof Sword) {
                Sword sword = (Sword) sprite;
                if (sword.getIsThrow()) {
                    boolean yMovement = sword.getMonsterAtTrack().getYPosition() > sword.getYPosition();
                    boolean xMovement = sword.getMonsterAtTrack().getXPosition() > sword.getXPosition();

                    if (sword.getYPosition() != sword.getMonsterAtTrack().getYPosition() && sword.getXPosition() != sword.getMonsterAtTrack().getXPosition()) {
                            if (yMovement)
                                sword.moveDown();
                            else sword.moveUp();

                            if (xMovement)
                                sword.moveRight();
                            else sword.moveLeft();
                    } else if (sword.getYPosition() == sword.getMonsterAtTrack().getYPosition() && sword.getXPosition() == sword.getMonsterAtTrack().getXPosition()) {
                        mapsEngine.setElementMap(sword.getXPosition(), sword.getYPosition(), EMPTY, false);
                        mapsEngine.setElementMap(sword.getMonsterAtTrack().getXPosition(), sword.getMonsterAtTrack().getYPosition(), EMPTY, false);

                        allSprites.remove(sword.getMonsterAtTrack());
                        allSprites.remove(sword);
                    } else if (sword.getYPosition() == sword.getMonsterAtTrack().getYPosition() && sword.getXPosition() != sword.getMonsterAtTrack().getXPosition()) {
                        if (yMovement)
                            sword.moveDown();
                        else sword.moveUp();
                    } else if (sword.getYPosition() != sword.getMonsterAtTrack().getYPosition() && sword.getXPosition() == sword.getMonsterAtTrack().getXPosition()) {
                        if (xMovement)
                            sword.moveRight();
                        else sword.moveLeft();
                    }
                }
            }


            /*      Update all lasers
                 Cette partie du code permet de gérer tous les projectiles qui sont lancés depuis les monstres.
                  Si le laser touche le joueur, cela va engendrer une perte de vie au joueur.
                  Sinon, s'il est en collision avec rien... Les lasers continus sont trajets en ligne droite.
                  Joue un son si le laser rentre en collision avec le joueur pour l'informer d'un dégât physique.
                 Le projectile est supprimé de la carte s'il rencontre une collision lors de son trajet. */
            if (sprite instanceof Laser) {
                Laser laser = (Laser) sprite;
                // Si il touche un joueur.
                if (laser.getDirection() == 0 && (
                        (laser.getYPosition()-1 == player.getYPosition() || laser.getYPosition() == player.getYPosition())
                                && (player.getXPosition() == laser.getXPosition())
                )) {
                    if (!player.getHaveInvulnerability())
                        player.hit();
                    mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                    allSprites.remove(laser);

                    Sound.play("hitHurt.wav", 1);
                } else if (laser.getDirection() == 1 && (
                        (laser.getXPosition()+1 == player.getXPosition() || laser.getXPosition() == player.getXPosition())
                                && (player.getYPosition() == laser.getYPosition())
                )) {
                    if (!player.getHaveInvulnerability())
                        player.hit();
                    mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                    allSprites.remove(laser);

                    Sound.play("hitHurt.wav", 0);
                } else if (laser.getDirection() == 3 && (
                        (laser.getYPosition()+1 == player.getYPosition() || laser.getYPosition() == player.getYPosition())
                                && (player.getXPosition() == laser.getXPosition())
                )) {
                    if (!player.getHaveInvulnerability())
                        player.hit();
                    mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                    allSprites.remove(laser);

                    Sound.play("hitHurt.wav", 0);
                } else if (laser.getDirection() == 2 && (
                        (laser.getXPosition()-1 == player.getXPosition() || laser.getXPosition() == player.getXPosition())
                                && (player.getYPosition() == laser.getYPosition())
                )) {
                    if (!player.getHaveInvulnerability())
                        player.hit();
                    mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                    allSprites.remove(laser);

                    Sound.play("hitHurt.wav", 0);
                } else {
                    // Regarde si il rentre pas en collision et regarde dans quelle direction il va.
                    if (!laser.getCollideUp() && laser.getDirection() == 0)
                        laser.moveUp();
                    else if (!laser.getCollideDown() && laser.getDirection() == 3)
                        laser.moveDown();
                    else if (!laser.getCollideRight() && laser.getDirection() == 1)
                        laser.moveRight();
                    else if (!laser.getCollideLeft() && laser.getDirection() == 2)
                        laser.moveLeft();
                    else {
                        mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                        allSprites.remove(laser);
                    }
                }
            }

            // Refresh les sprites (Entités)
            sprite.updates();
        }
    }
}

