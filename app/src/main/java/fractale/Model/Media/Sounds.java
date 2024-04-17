package fractale.Model.Media;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sounds {
    private static Clip mainSong;
    public static boolean musicOn = true;

    // Lance la musique
    public static void playMainSong() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (mainSong == null) {
            File f = new File("src/main/java/fractale/Model/Media/2021-12-20-18_22_49.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            mainSong = AudioSystem.getClip();
            mainSong.open(audioIn);
        }
        mainSong.loop(100);
        musicOn = true;
    }
    // Arrête la musique
    public static void stopMainSong() {
        if (mainSong != null) mainSong.stop();
        musicOn = false;
    }

    
}

