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


    /** Joue une music, un bruitage, ... */
    public static void play(final String fileName, int loopCount)
    {
        Clip clip;
        File wavFile = new File("res/" + fileName);
        if (!wavFile.canRead()) {
            BufferedInputStream in = new BufferedInputStream(Sound.class.getResourceAsStream("/" + fileName));
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(in));
                clip.loop(loopCount);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) { e.printStackTrace(); }
        } else {
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(wavFile));
                clip.loop(loopCount);
                clip.start();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) { e.printStackTrace(); }
        }
    }
}
