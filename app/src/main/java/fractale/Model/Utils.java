package fractale.Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Utils {
    public static final String usage=
    "usage:\r\n java Contoller/main  --version -> --version=terminal ou --version=gui. OBLIGATOIRE\n --set  -> --set=Julia ou --set=Mand. OBLIGATOIRE\n --constant -> Choisir la constante de julia. --constant=double+double.\n Pour les complexes de partie imaginaire négative, il faut les introduire en respectant ce format: double+-double\n--help -> affiche ce message";

    public static void displayHelpAndExit(){
        System.out.println();
        System.out.println("Usage : java Controller/main");
        System.out.println("Without argument the graphical interface is launch");
        System.out.println("Otherwise to generate an image \"fractal.png\" you can do :");
        System.out.println("--set=Julia | --set=Mand");
        System.out.println("Then the number of iterations you want :");
        System.out.println("--iteration=int");
        System.out.println("And finally if you choose Julia you have to specify the constant C :");
        System.out.println("--constant=double+double");
        System.out.println();
        System.out.println("You can try with these examples :");
        System.out.println("java Controller/main --set=Julia --iteration=800 --constant=0.285+0.01");
        System.out.println("java Controller/main --set=Mand --iteration=900");
        System.exit(2);

    }
    public static void draw(BufferedImage img, int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                img.setRGB(i, j, matrix[i][j]);
            }
        }
    }
    
    public static void save(BufferedImage img){
        File f;
        try {
            f = new File("Fractale.png");
            ImageIO.write(img, "PNG", f);

        } catch (Exception e) {
            System.out.println("Not found");
        }
    }

    private static boolean knownParameter(String param) {
        switch (param) {
            case "help":
            case "set":
            case "constante":
            case "iteration":
                return true;
            default:
                return false;
        }
    }
    public static Map<String, String> parseParameters(final String[] args) {
        if (args.length == 0){
            Utils.displayHelpAndExit();
        }
        final Map<String, String> parameters = new HashMap<>();
        for (final String arg : args) {
            if (!arg.startsWith("--")) {
                System.err.println("Bad parameter: " + arg);
                System.exit(2);
            }
            if (arg.equals("--help")){
                Utils.displayHelpAndExit();
            }
            else{
            final String[] tab = arg.substring(2).split("=");
            if(!knownParameter(tab[0])){
                System.out.println("Unknown parametre:"+tab[0]);
                System.exit(2);
            }
            parameters.put(tab[0], tab[1]);
            }
        }
    
        return parameters;
    }

}
