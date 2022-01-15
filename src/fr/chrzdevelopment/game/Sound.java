package fr.chrzdevelopment.game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;


public class Sound
{
    public static final String DEFAULT_RESOURCE_PATH = "res/";


    /** Joue une music, un bruitage, ... */
    public static void play(final String fileName, int loopCount)
    {
        Clip clip;
        File wavFile = new File(DEFAULT_RESOURCE_PATH + fileName);
        if (!wavFile.canRead()) {   // Si le code est compilé en .jar
            BufferedInputStream in = new BufferedInputStream(Sound.class.getResourceAsStream("/" + fileName));
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(in));
                clip.loop(loopCount);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) { e.printStackTrace(); }
        } else {    // Si le code est ouvert en tant que projet dans un IDE
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(wavFile));
                clip.loop(loopCount);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) { e.printStackTrace(); }
        }
    }
}
