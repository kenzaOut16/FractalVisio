package fractale.Model;

import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import java.awt.Color;
import java.awt.Point;

/**
 * Builder Pattern
 */
public class Fractal {

    private double MinX;
    private double MinY;
    private double MaxX;
    private double MaxY;
    private int MaxIteration;
    private  final int defaultMaxIterations = 1000;
    private String fileName;
    private Complex constant;

    public static class FractalBuilder {
        private double MinX = -2;
        private double MinY = -2;
        private double MaxX = 2;
        private double MaxY = 2;
        private int MaxIteration = 1000;
        private String fileName = "My fractal.png";
        private Complex constant = new Complex(-0.7269, 0.1889);

        public FractalBuilder withMinX(double MinX) {
            this.MinX = MinX;
            return this;
        }

        public FractalBuilder withMaxX(double MaxX) {
            this.MaxX = MaxX;
            return this;
        }

        public FractalBuilder withMinY(double MinY) {
            this.MinY = MinY;
            return this;
        }

        public FractalBuilder withMaxY(double MaxY) {
            this.MaxY = MaxY;
            return this;
        }

        public FractalBuilder withMaxIteration(int MaxIteration) {
            this.MaxIteration = MaxIteration;
            return this;
        }

        public FractalBuilder withFileName(String fileNmae) {
            this.fileName = fileNmae;
            return this;
        }

        public FractalBuilder withConstant(double real, double img) {
            this.constant = new Complex(real, img);
            return this;
        }

        public Fractal build() {
            Fractal result = new Fractal();
            result.MaxX = MaxX;
            result.MinX = MinX;
            result.MaxY = MaxY;
            result.MinY = MinY;
            result.MaxIteration = MaxIteration;
            result.constant = constant;
            result.fileName = fileName;
            return result;
        }

    }

    /**
     * @return FractalBuilder
     */
    public static FractalBuilder FractalBuilder() {
        return new FractalBuilder();
    }

    /**
     * @return int
     */
    public int getDefaultMaxIterations() {
        return defaultMaxIterations;
    }

    /**
     * @param width
     * @param height
     * @param zoom
     * @param imageCorner
     * @param colorMethod
     * @return BufferedImage
     * Calcule l'ensemble de Julia, et renvoie l'image correspondante de taille width*height
     */
    public BufferedImage generateJulia(int width, int height, double zoom, Point imageCorner, int colorMethod) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        IntStream.range(0, width).parallel().forEach(x -> {
            IntStream.range(0, height).parallel().forEach(y -> {
                // Calcule les coordonnées du pixel correspondant à x et y
                double zy = ((((this.MinY) + (y * (this.MaxY - this.MinY)) / img.getHeight())) / zoom)
                        + (imageCorner.getY() / 100);
                double zx = ((((this.MinX) + (x * (this.MaxX - this.MinX)) / img.getWidth())) / zoom)
                        + (imageCorner.getX() / 100);

                // Initialisation du complexe de départ
                Complex zZero = new Complex(zx, zy);
                int count = 0;
                do {
                    // Julia : ajoute la constante c
                    zZero = Complex.add(this.constant,
                            Complex.sqrt(zZero));
                    count++;
                } while ((count < this.MaxIteration) && (Complex.magnitude(zZero) < 2));
                changeColor(img, x, y, count, colorMethod);
            });

        });
        Utils.save(img);
        return img;
    }

    /**
     * @param w
     * @param h
     * @param zoom
     * @param imageCorner
     * @param colorMethod
     * @return BufferedImage
     * Calcule l'ensemble de Mandelbrot, et renvoie l'image correspondante de taille width*height
     */
    public BufferedImage generateMandelbrot(int w, int h, double zoom, Point imageCorner, int colorMethod) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        IntStream.range(0, w).parallel().forEach(x -> {
            IntStream.range(0, h).parallel().forEach(y -> {
                // Calcule les coordonnées du pixel correspondant à x et y
                double zy = ((((this.MinY) + (y * (this.MaxY - this.MinY)) / img.getHeight())) / zoom)
                        + (imageCorner.getY() / 100);
                double zx = ((((this.MinX) + (x * (this.MaxX - this.MinX)) / img.getWidth())) / zoom)
                        + (imageCorner.getX() / 100);

                // Initialisation du complexe de départ
                Complex zZero = new Complex(zx, zy);
                // Calcule du complexe à ajouter
                Complex cToAdd = new Complex(zx, zy);
                int count = 0;
                do {
                    // Mandelbrot : à chaque fois on aj
                    zZero = Complex.add(cToAdd,
                            Complex.sqrt(zZero));
                    count++;
                } while ((count < this.MaxIteration) && (Complex.magnitude(zZero) < 2));
                changeColor(img, x, y, count, colorMethod);
            });

        });
        Utils.save(img);
        return img;
    }

    /**
     * @param img
     * @param x
     * @param y
     * @param count
     * @param colorMethod
     * La méthode permet de changer les couleurs
     * Nous avons 3 methode de couleurs indiquées par des int,
     * elle en prend une, et l'applique au pixel (x,y) de l'image
     * passée en paramètres
     */
    public void changeColor(BufferedImage img, int x, int y, int count, int colorMethod) {
        if (colorMethod == 1) {
            img.setRGB(x, y, Color.HSBtoRGB(count / 256f, 1, count / (count + 8f)));

            return;
        }
        if (colorMethod == 2) {
            img.setRGB(x, y, count | (count << 20));

            return;
        }
        if (colorMethod == 3) {
            img.setRGB(x, y, count | (count << 40));

            return;
        }
    } 
    public double getMinX(){
        return this.MinX;
    }

    public double getMaxX(){
        return this.MaxX;
    }

    public double getMinY(){
        return this.MinY;
    }

    public double getMaxY(){
        return this.MaxY;
    }
    public Complex getConstant(){
        return this.constant;
    }


}