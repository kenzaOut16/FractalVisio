package fractale.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import  fractale.Model.Fractal;

// Contient le JPanel sur lequel est affiché le fractale
public final class FractalPanel extends JPanel {

    private static Point imageCorner;
    private static Point previousPoint;
    public Fractal fractal;
    // true when we want to generate a julia set, false otherwise
    private boolean julia = true;
    private double zoom = 1;
    private int colorMethod=1;
    private ButtonPanel panel;


    public void augmente11Zoom(){
        zoom+=0.05;
    }
    
    //Augmente le zoom
    public void augmentZoom() {
       /* int newImageWidth = imageWidth * zoomLevel;
    int newImageHeight = imageHeight * zoomLevel;
    BufferedImage resizedImage = new BufferedImage(newImageWidth , newImageHeight, imageType);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, newImageWidth , newImageHeight , null);
    g.dispose();*/

        this.zoom *= 2;
    }
    //décremente le zoom
    public void diminishZoom() {
        this.zoom /= 2;

    }
    public void setJulia(){
        julia =!julia;
    }
    public void setColorMethod(){
        if(colorMethod == 1){
            colorMethod =2;
            return;
        }
        if(colorMethod == 2){
            colorMethod =3;
            return;
        }
        if(colorMethod == 3){
            colorMethod =1;
            return;
        }
    }
    public void reset(){
        this.zoom =1;
        this.colorMethod = 1;
        fractal = Fractal.FractalBuilder().withMaxIteration(fractal.getDefaultMaxIterations()).build();
        //fractal = Fractal.FractalBuilder().withMaxIteration(1000).build();
        imageCorner = new Point(0,0);
    }

    FractalPanel(ButtonPanel panel) {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.white);
        this.panel = panel;
        initLocations();
        initPoints();
        this.fractal = Fractal.FractalBuilder().build();

    }

    
    /** 
     * @param g
     */
    void drawJuliaSet(Graphics2D g) {
        int w = getWidth();
        int h = getHeight();
        
        if(julia){
            g.drawImage(this.fractal.generateJulia(w, h, zoom, imageCorner, colorMethod),0,0,null);
        }else{
        g.drawImage(this.fractal.generateMandelbrot(w, h, zoom, imageCorner, colorMethod),0,0,null);
        }
    }

    
    /** 
     * @param gg
     */
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawJuliaSet(g);
    }

    static void initLocations() {
        // Voir si on travaille avec une image
    }

    // Permet de se déplacer dans l'image grâce à la souris
    void initPoints() {
        imageCorner = new Point(0, 0);
        addMouseListener(new ClickListener());
        addMouseMotionListener(new DragListener());

    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            previousPoint = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            imageCorner.translate((int) ((previousPoint.getX() - currentPt.getX())/zoom),
                    (int) ((previousPoint.getY() - currentPt.getY())/zoom));
            previousPoint = currentPt;
            repaint();
        }
    }

    
    /** 
     * @param g
     * @param w
     * @param h
     */
    public void generateFractal(Graphics g, int w, int h){
        if(julia){
            g.drawImage(this.fractal.generateJulia(w, h, zoom, imageCorner, colorMethod),0,0,null);
        }else{
        g.drawImage(this.fractal.generateMandelbrot(w, h, zoom, imageCorner, colorMethod),0,0,null);
        }
    }


}
