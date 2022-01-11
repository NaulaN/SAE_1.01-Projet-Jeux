package fr.chrzdevelopment.game;
// https://r12a.github.io/app-conversion/   Java char compatibility

import static fr.chrzdevelopment.game.Const.*;
import fr.chrzdevelopment.game.entities.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Ce Thread représente un processus parallel qui se charge des inputs au clavier
 */
class KeyboardInputThread extends Thread
{
    private final KeyboardInput keyboardInput;
    private final Game game;


    public KeyboardInputThread(Game game, KeyboardInput keyboardInput)
    {
        this.keyboardInput = keyboardInput;
        this.game = game;
    }

    @Override
    public void run()
    {
        synchronized (this) {
            while (game.getRunning() && !this.isInterrupted()) {
                try {
                    this.wait(100);
                } catch (InterruptedException ignored) { return; }

                keyboardInput.getInput();
            }
        }
    }
}


/**
 * Ce Thread représente un processus parallel qui se charge de l'actualisation et l'affichage du jeu
 */
class RefreshAndDisplayThread extends Thread
{
    private final Game game;


    RefreshAndDisplayThread(Game game) { this.game = game; }

    @Override
    public void run()
    {
        synchronized (this) {
            while (game.getRunning() && !this.isInterrupted()) {
                try {
                    this.wait(100);
                } catch (InterruptedException ignored) { return; }

                game.updates();
                game.draws();
            }
        }
    }
}


/**
 * <p>Permet le bon fonctionnement du jeu</p>
 * <p>Rassemble tous !</p>
 */
public class Game
{
    private JSONObject saveFile;

    // Entities
    private final List<Entity> allSprites = new CopyOnWriteArrayList<>();
    private Player player;

    // TODO
    private String playerName;
    private int totalCoins;
    private int maxLvl;

    private final KeyboardInput keyboardInput = new KeyboardInput();
    private final MapsEngine mapsEngine;
    private final String OS = System.getProperty("os.name");
    // clear terminal commands
    private final ProcessBuilder processBuilder = (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10")) ? new ProcessBuilder("cmd", "/c", "cls") : new ProcessBuilder("clear");

    private boolean running = true;

    private final Thread refreshAndDisplayThread = new RefreshAndDisplayThread(this);
    private final Thread keyboardInputThread = new KeyboardInputThread(this, keyboardInput);


    public Game()
    {
        // Load JSON file
        try {
            // In an IDE
            saveFile = new JSONObject(new JSONTokener( new FileReader("res/save.json")));
        } catch (FileNotFoundException e) {
            // In a JAR
            InputStream in = getClass().getResourceAsStream("/save.json");
            if (in != null)
                saveFile = new JSONObject(new JSONTokener( new BufferedReader(new InputStreamReader(in))));
        }
        playerName = saveFile.getString("playerName");
        totalCoins = saveFile.getInt("totalCoins");
        maxLvl = saveFile.getInt("maxLvl");


        // Load graphics
        if (OS.equalsIgnoreCase("windows") || OS.equalsIgnoreCase("windows 10"))
            windowsGraphics();
        else linuxGraphics();

        // Crée la taille de la carte
        mapsEngine = new MapsEngine(30, 20);

        // La generation.
        mapsEngine.generateMap();
        spawnEntity();
    }

    public boolean getRunning() { return running; }

    /** Démarre la boucle principale du jeu */
    public void loop()
    {
        Sound.play("music.wav", -1);
        clearConsole();

        System.out.println();
        // Title screen
        System.out.println("\t" + ANSI_RED + "                 ▄       ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄       ▄                 ");
        System.out.println("\t" + "                ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌                ");
        System.out.println("\t" + "               ▐░▌       ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀▀█░█▀▀▀       ▐░▌               ");
        System.out.println("\t" + "              ▐░▌            ▐░▌     ▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌           ▐░▌           ▐░▌              ");
        System.out.println("\t" + " ▄▄▄▄▄▄▄▄▄▄▄ ▐░▌             ▐░▌     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌           ▐░▌            ▐░▌ ▄▄▄▄▄▄▄▄▄▄▄ ");
        System.out.println("\t" + "▐░░░░░░░░░░░▌▐░▌             ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌           ▐░▌            ▐░▌▐░░░░░░░░░░░▌");
        System.out.println("\t" + " ▀▀▀▀▀▀▀▀▀▀▀ ▐░▌             ▐░▌     ▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀      ▐░▌           ▐░▌            ▐░▌ ▀▀▀▀▀▀▀▀▀▀▀ ");
        System.out.println("\t" + "              ▐░▌            ▐░▌     ▐░▌     ▐░▌  ▐░▌               ▐░▌           ▐░▌           ▐░▌              ");
        System.out.println("\t" + "               ▐░▌       ▄▄▄▄█░█▄▄▄▄ ▐░▌      ▐░▌ ▐░▌           ▄▄▄▄█░█▄▄▄▄  ▄▄▄▄▄█░▌          ▐░▌               ");
        System.out.println("\t" + "                ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░▌         ▐░▌                ");
        System.out.println("\t" + "                  ▀      ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀            ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀           ▀                 " + ANSI_RESET);
        System.out.println();

        System.out.println();
        System.out.println("Bienvenue chèr(e) aventurièr(e) ! Ici, multiple aventure vous attend, entre les monstres, les coffres, les obstacles... Il a de quoi faire !");
        System.out.println(ANSI_RED + "============================================================================================================================================" + ANSI_RESET);
        System.out.print("\t" + ANSI_GREEN + "Entrez votre nom > ");
        playerName = keyboardInput.getStringInput();

        // Game loops
        refreshAndDisplayThread.start();
        keyboardInputThread.setPriority(Thread.MAX_PRIORITY);
        keyboardInputThread.start();
    }

    /**
     * Nettoie tout ce qui est present et afficher sur le terminal.
     */
    private void clearConsole()
    {
        // TODO: Demander a l'enseignant si cela est correct. J'ai essayé de bien faire pour qu'il est deux essaie au cas où
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

    private void spawnEntity()
    {
        allSprites.clear();
        player = mapsEngine.spawnPlayer(allSprites);
        mapsEngine.spawnMonster(allSprites);
        mapsEngine.spawnCoin(allSprites);
        mapsEngine.spawnChest(allSprites);
        mapsEngine.spawnKey(allSprites);
    }

    /** Dessine les elements qui nécessite à voir sur la console */
    public synchronized void draws()
    {
        clearConsole();

        if (player.getHealth() <= 0) {
            // Game over
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

            running = false;
            refreshAndDisplayThread.interrupt();
            keyboardInputThread.interrupt();
        } else {
            // Information relative au nombre de piece necessaire pour gagner le niveau
            System.out.println("\t" + ANSI_RED + "NIVEAUX: " + mapsEngine.getMapLvl() + ANSI_RESET);
            System.out.println(ANSI_GREEN + playerName + " ! Vous devez avoir " + mapsEngine.getDeterminateCoins() + " " + COIN_IMG + ANSI_GREEN + " pour pouvoir gagner le niveau" + ANSI_RESET);
            mapsEngine.draw();

            // Affiche les pieces du joueur obtenu et son nombre de point de vie total (Nombre de <3)
            StringBuilder msgHud = new StringBuilder().append(COIN_IMG).append(": ").append(player.getCoins()).append("   ");
            for (int h = 1; h <= player.getHealth(); h++)
                msgHud.append(HEART_IMG).append(" ");
            msgHud.append("   ").append(KEY_IMG).append(": ").append((player.getHaveAKey()) ? "oui" : "non");
            System.out.print("\t" + msgHud.toString());
            System.out.println();
        }
    }

    /** Actualise les valeurs qui ont besoin d'etre actualisé à chaque passage de la boucle */
    public synchronized void updates()
    {
        // Quit le jeu
        if (keyboardInput.getQuitAction()) {
            running = false;
            refreshAndDisplayThread.interrupt();
            keyboardInputThread.interrupt();
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
            mapsEngine.setHeight(RANDOM.nextInt(20, 35));
            mapsEngine.setWidth(RANDOM.nextInt(20, 35));
            mapsEngine.generateMap();
            mapsEngine.generateObstacles();
            spawnEntity();
        }

        for (Entity sprite : allSprites)
        {
            sprite.checkCollision(mapsEngine.getCalqueCollide());
            boolean collide = sprite.getDataImg() != COIN && sprite.getDataImg() != KEY && sprite.getDataImg() != LASER_VERTICAL && sprite.getDataImg() != LASER_HORIZONTAL;
            mapsEngine.updateEntity(sprite, collide);

            // Update all coins
            if (sprite instanceof Coin coin)
                if (sprite.getXPosition() == player.getXPosition() && sprite.getYPosition() == player.getYPosition()) {
                    player.addCoin();
                    mapsEngine.setElementMap(coin.getXPosition(), coin.getYPosition(), EMPTY, false);
                    allSprites.remove(coin);
                }

            // Update all chest
            if (sprite instanceof Chest chest)
                if (keyboardInput.getSelect())
                    if ((sprite.getXPosition() == player.getXPosition()-1 || sprite.getXPosition() == player.getXPosition()+1)
                            || (sprite.getYPosition() == player.getYPosition()-1 || sprite.getYPosition() == player.getYPosition()+1))
                        if (player.getHaveAKey()) {
                            if (chest.getWhatInside().equalsIgnoreCase("coin"))
                                player.addCoin();
                            else if (chest.getWhatInside().equalsIgnoreCase("health"))
                                player.setHealth(player.getHealth()+1);

                            chest.hit();
                            player.haventKey();
                        }

            // Update all keys
            if (sprite instanceof Key key)
                if (keyboardInput.getSelect())
                    if ((sprite.getXPosition() == player.getXPosition()-1 || sprite.getXPosition() == player.getXPosition()+1)
                            || (sprite.getYPosition() == player.getYPosition()-1 || sprite.getYPosition() == player.getYPosition()+1))
                        if (!player.getHaveAKey()) {
                            player.haveKey();

                            mapsEngine.setElementMap(key.getXPosition(), key.getYPosition(), EMPTY, false);
                            allSprites.remove(key);
                        }

            // Update all lasers
            if (sprite instanceof Laser laser) {
                if (laser.getXPosition() == player.getXPosition() && laser.getYPosition() == player.getYPosition()) {
                    player.hit();

                    mapsEngine.setElementMap(laser.getXPosition(), laser.getYPosition(), EMPTY, false);
                    allSprites.remove(laser);
                } else {
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

            sprite.updates();
        }
    }
}

