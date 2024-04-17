package fractale.Model.Media;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Images {
    
    // Prends un chemin et retourne l'image de ce chemin
    public static ImageIcon getIconImage(int width, int height, String filename) {
            try {
                Image tmp = ImageIO.read(new File("src/main/java/fractale/Model/Media/"+filename));
                tmp = tmp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(tmp);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("hi");
            }
            return null;
    }
}
    
