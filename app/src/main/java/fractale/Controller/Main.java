package fractale.Controller;

import java.awt.Point;
import java.util.Map;

import javax.swing.SwingUtilities;
import fractale.View.GUI;
import fractale.Model.*;

public class Main {

    public static void main(String[] args) {
        // Sans argument l'interface graphique se lance 
        if (args.length == 0){
            new GUI();
        } else {
        /* S'il y a des arguments, alors il faut sélectionner les différents paramètres
        Et si les arguments sont incorrects on affiche la façon d'utiliser ces arguments */
            try {
                final Map<String, String> parameters = Utils.parseParameters(args); 
                String set = parameters.get("set");
                int iteration = Integer.parseInt(parameters.get("iteration"));

                // Julia ou Mandelbrot
                if (set.equals("Mand")) {
                    Fractal fract = Fractal.FractalBuilder().withMaxIteration(iteration).build();
                    // On lance avec des paramètres par defaut
                    fract.generateMandelbrot(400, 400, 1, new Point(0, 0), 1);
                } else {
                    if (set.equals("Julia")) {
                        String nbrc = parameters.get("constante");
                        double real = 0;
                        double img = 0;
                        try {
                            final String[] tab = nbrc.substring(0).split("\\+");
                            real = Double.parseDouble(tab[0]);
                            img = Double.parseDouble(tab[1]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Mauvaise entrée. Format du complexe est <double+double>");
                            System.exit(2);
                        }
                        if ((real == 0) && (img == 0)) {
                            Fractal f = Fractal.FractalBuilder().withMaxIteration(iteration).build();
                            f.generateJulia(400, 400, 1, new Point(0, 0), 1);
                        } else {
                            Fractal f = Fractal.FractalBuilder().withMaxIteration(iteration).withConstant(real, img).build();
                            f.generateJulia(400, 400, 1, new Point(0, 0), 1);
                        }
                    } else 
                        Utils.displayHelpAndExit();
                    
                }
                
            } catch (Exception e) { 
                Utils.displayHelpAndExit();
            }
        }
    }
}
