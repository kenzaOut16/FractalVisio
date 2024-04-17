package fractale.View;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import fractale.Model.*;
import fractale.Model.Media.Images;
import fractale.Model.Media.Sounds;

/*
* Contient un JPanel qui est en bas de la fenêtre et contient essentiellement les boutons
*/
public final class ButtonPanel extends JPanel {

    private JButton restoreParameters;
    private JButton changeColors;
    private JButton nextFractal;
    private JButton musique;
    private JButton zoomIn;
    private JButton zoomOut;
    private JButton zooming;
    private JComboBox<Integer> comboBoxIterations;
    private JLabel labelIterations;
    private JLabel EnterAComplexLabel;
    private JTextField EnterAComplex;
    private JLabel EnterXcordinatesLabel;
    private JTextField EnterXcordinates;
    private JLabel EnterYcordinatesLabel;
    private JTextField EnterYcordinates;

    // Initialement il n'y a pas de musique par defaut
    private boolean is_music = false;
    // Initialement on dessine julia en premier
    private boolean is_mandelbrot = false;
    // Initialement le zoom automatique n'est pas démarré
    private boolean is_zooming = false;
    private FractalPanel panel;

    ButtonPanel(FractalPanel panel) {
        this.panel = panel;
        restoreParameters = new JButton();
        changeColors = new JButton();
        nextFractal = new JButton();
        musique = new JButton();
        zooming = new JButton();
        zoomIn = new JButton();
        zoomOut = new JButton();
        EnterAComplexLabel = new JLabel("Complexe");
        EnterAComplexLabel.setForeground(Color.BLACK);
        EnterAComplexLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        EnterAComplexLabel.setBounds(200, 200, 100, 30);
        EnterAComplex = new JTextField();
        EnterXcordinatesLabel = new JLabel(" Abscisses");
        EnterXcordinatesLabel.setBounds(200, 200, 100, 30);
        EnterXcordinatesLabel.setForeground(Color.BLACK);
        EnterXcordinatesLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        EnterXcordinates = new JTextField();
        EnterYcordinatesLabel = new JLabel(" Ordonnes");
        EnterYcordinatesLabel.setBounds(200, 200, 100, 30);
        EnterYcordinatesLabel.setForeground(Color.BLACK);
        EnterYcordinatesLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
        EnterYcordinates = new JTextField();

        // Initialisation de tous les composants
        initButton(restoreParameters);
        initButton(changeColors);
        initButton(nextFractal);
        initButton(musique);
        initButton(zoomIn);
        initButton(zoomOut);
        initButton(zooming);
        initRestoreButton();
        initChangeColorButton();
        initNextFractal();
        initMusique();
        initJComboBox();
        initZoomInButton();
        initZoomOutButton();
        initZoomingButton();
        initEnterAComplex();
        initEnterXcordinates();
        initEnterYcordinates();

        setPreferredSize(new Dimension(200, 150));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
        setBackground(new Color(0xBA55D3));

        // Ajout des composants
        labelIterations.add(comboBoxIterations);
        add(restoreParameters);
        add(changeColors);
        add(nextFractal);
        add(musique);
        add(labelIterations);
        add(comboBoxIterations);
        add(zoomIn);
        add(zoomOut);
        add(zooming);
        add(EnterAComplexLabel);
        add(EnterAComplex);
        add(EnterXcordinatesLabel);
        add(EnterXcordinates);
        add(EnterYcordinatesLabel);
        add(EnterYcordinates);

    }
    public JTextField getEnterAComplex(){
        return EnterAComplex;
    }

    /**
     * @param button
     *               Donne le visuel général des boutons
     */
    public static void initButton(JButton button) {
        button.setOpaque(false);
        button.setFont(new Font("Calibri", Font.PLAIN, 14));
        button.setBackground(new Color(0x2D98CE));
        button.setForeground(Color.white);
        button.setUI(new DesignButtonUI());

    }

    /**
     * Initialise le button pour réintialiser le fractale
     */
    public void initRestoreButton() {
        restoreParameters.setText("Restaurer les paramètres par défaut !");
        restoreParameters.setIcon(Images.getIconImage(20, 20, "resetButton.png"));
        restoreParameters.addActionListener(e -> {
            this.panel.reset();
            this.panel.validate();
            this.panel.repaint();
        });
    }

    /**
     * Initialise le button changer la couleur du fractale
     */
    public void initChangeColorButton() {
        changeColors.setText(" Changer de couleur ");
        changeColors.setIcon(Images.getIconImage(20, 20, "changecolorButton.png"));
        changeColors.addActionListener(e -> {
            this.panel.setColorMethod();
            this.panel.validate();
            this.panel.repaint();
        });
    }

    /*
     * Initialise le button pour afficher un autre fractale
     */
    void initNextFractal() {
        if (is_mandelbrot) {
            nextFractal.setText(" Passer à Julia ");
            nextFractal.setIcon(Images.getIconImage(20, 20, "julia.png"));

        } else {
            nextFractal.setText(" Passer à Mandelbrot ");
            nextFractal.setIcon(Images.getIconImage(20, 20, "mandelbrot.jpg"));
        }
        nextFractal.addActionListener(e -> {
            if (!is_mandelbrot) {
                nextFractal.setText("Passer à Julia ");
                nextFractal.setIcon(Images.getIconImage(20, 20, "julia.png"));
                panel.reset();
                is_mandelbrot = true;
            } else {
                nextFractal.setText("Passer à Mandelbrot ");
                nextFractal.setIcon(Images.getIconImage(20, 20, "mandelbrot.jpg"));
                panel.reset();
                is_mandelbrot = false;
            }
            this.panel.setJulia();
            this.panel.validate();
            this.panel.repaint();
        });
    }

    /*
     * Initialise le button pour mettre de la musique ou la couper
     */
    void initMusique() {
        musique.setText(" Mettre de la musique ");
        musique.setIcon(Images.getIconImage(20, 20, "musiqueOn.png"));
        musique.addActionListener(action -> {
            if (!is_music) {
                try {
                    Sounds.playMainSong();
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                musique.setText(" Couper la musique ");
                musique.setIcon(Images.getIconImage(20, 20, "musiqueOff.jpg"));

                is_music = true;
            } else {
                Sounds.stopMainSong();
                musique.setText(" Remettre la musique ");
                musique.setIcon(Images.getIconImage(20, 20, "musiqueOn.png"));
                is_music = false;
            }
        });
    }

    /**
     * Initialise le bouton du zoom automatique
     * soit lancer le zoom, soit l'arreter
     */
    void initZoomingButton() {
        zooming.setText(" Zoomer automatiquement ");
        zooming.addActionListener(action -> {
            if (!is_zooming) {
                is_zooming = true;
                zooming.setText(" Arrêter de zoomer ");

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (is_zooming) {
                            panel.augmente11Zoom();
                            panel.validate();
                            panel.repaint();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }).start();

            } else {
                zooming.setText(" Reprendre le zoom ");
                is_zooming = false;
            }
        });

    }

    /*
     * Gère la multibox pour choisir le nombre d'itérations
     */
    void initJComboBox() {
        comboBoxIterations = new JComboBox<>();
        labelIterations = new JLabel();
        labelIterations.setText("Choisir le nombre d'itérations maximum");
        comboBoxIterations.addItem(1);
        comboBoxIterations.addItem(10);
        comboBoxIterations.addItem(25);
        comboBoxIterations.addItem(100);
        comboBoxIterations.addItem(200);
        comboBoxIterations.addItem(500);
        comboBoxIterations.addItem(1000);
        comboBoxIterations.addItem(2000);
        labelIterations.setForeground(Color.BLACK);
        labelIterations.setFont(new Font("Calibri", Font.PLAIN, 16));
        labelIterations.setBounds(200, 200, 100, 30);
        comboBoxIterations.setEditable(true);
        comboBoxIterations.setSelectedItem(1000);
        comboBoxIterations.addActionListener(e -> {
            this.panel.fractal = Fractal.FractalBuilder().withMaxIteration((int) comboBoxIterations.getSelectedItem())
                    .build();
            this.panel.validate();
            this.panel.repaint();
        });

    }

    /*
     * Initialise le bouton du zoom avant
     */
    void initZoomInButton() {
        zoomIn.setText("Zoomer");
        zoomIn.addActionListener(e -> {
            this.panel.augmentZoom();
            this.panel.validate();
            this.panel.repaint();
        });
    }

    /*
     * Initialise le bouton du zoom arrière
     */
    void initZoomOutButton() {
        zoomOut.setText("Dezoomer");
        zoomOut.addActionListener(e -> {
            this.panel.diminishZoom();
            this.panel.validate();
            this.panel.repaint();
        });
    }

    /**
     * Initialise la zone text pour entrer le complexe souhaité et son label
     */
    void initEnterAComplex() {
        EnterAComplex.setPreferredSize(new Dimension(100, 20));
        EnterAComplexLabel.setLabelFor(EnterAComplex);
        EnterAComplex.addActionListener(e -> {
            try {
            String userComplex = EnterAComplex.getText();
            final String[] tab = userComplex.substring(0).split("\\+");
            this.panel.fractal = Fractal.FractalBuilder()
                    .withConstant(Double.parseDouble(tab[0]), Double.parseDouble(tab[1])).build();
            this.panel.validate();
            this.panel.repaint();
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Le complexe doit respecter le format\n 'double+double'\npar exemple : 0.4+0.6", "Format du complexe", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Verifiez que le complexe respecte le format: 'double+double' -exemple: 0.4+-0.6 ");
            }
        });
    }

    void initEnterXcordinates() {
        EnterXcordinates.setPreferredSize(new Dimension(50, 20));
        EnterXcordinatesLabel.setLabelFor(EnterXcordinates);
        EnterXcordinates.addActionListener(e -> {
            String userComplex = EnterXcordinates.getText();
            final String[] tab = userComplex.substring(0).split(" ");
            try {
                if (is_mandelbrot) {
                    this.panel.fractal = Fractal.FractalBuilder().withMinX(Double.parseDouble(tab[0]))
                            .withMaxX(Double.parseDouble(tab[1]))
                            .withMaxY(this.panel.fractal.getMaxY())
                            .withMinY(this.panel.fractal.getMinY())
                            .build();
                } else {
                    this.panel.fractal = Fractal.FractalBuilder().withMinX(Double.parseDouble(tab[0]))
                            .withMaxX(Double.parseDouble(tab[1]))
                            .withMaxY(this.panel.fractal.getMaxY())
                            .withMinY(this.panel.fractal.getMinY())
                            .withConstant(this.panel.fractal.getConstant().getReal(),
                                    this.panel.fractal.getConstant().getImaginary())
                            .build();
                }
                this.panel.revalidate();
                this.panel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Les coordonnees d'ordonnées minimal et maximal doivent respecter le format :\n 'double double' avec un espace entre les deux\n ex : -1 1", "Format des coordonnees", JOptionPane.INFORMATION_MESSAGE);


            }
        });

    }

    void initEnterYcordinates() {
        EnterYcordinates.setPreferredSize(new Dimension(50, 20));
        EnterYcordinatesLabel.setLabelFor(EnterXcordinates);
        EnterYcordinates.addActionListener(e -> {
            String entry = EnterYcordinates.getText();
            final String[] tab = entry.substring(0).split(" ");
            try {
                if (is_mandelbrot) {
                    this.panel.fractal = Fractal.FractalBuilder().withMinY(Double.parseDouble(tab[0]))
                            .withMaxY(Double.parseDouble(tab[1]))
                            .withMaxX(this.panel.fractal.getMaxX())
                            .withMinX(this.panel.fractal.getMinX())
                            .build();
                } else {
                    this.panel.fractal = Fractal.FractalBuilder().withMinY(Double.parseDouble(tab[0]))
                            .withMaxY(Double.parseDouble(tab[1]))
                            .withMaxX(this.panel.fractal.getMaxX())
                            .withMinX(this.panel.fractal.getMinX())
                            .withConstant(this.panel.fractal.getConstant().getReal(),
                                    this.panel.fractal.getConstant().getImaginary())
                            .build();
                }
                this.panel.validate();
                this.panel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Les coordonnees d'ordonnées minimal et maximal doivent respecter le format :\n 'double double' avec un espace entre les deux\n ex : -1 1", "Format des coordonnees", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}