package qc.ca.bdeb.Control.Main;

import qc.ca.bdeb.View.GeneralView.Configview;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe qui controle la vue de configuration
 */
public class Controller_config {
    private Configview configview;
    private ArrayList<String> imagelist_bd = new ArrayList<String>();
    private ArrayList<String> imagelist_utility = new ArrayList<String>();

    /**
     * Constructeur pour un controller de configuration
     */
    public Controller_config() {
        loadimage_bd();
        this.configview = new Configview();
        loadimage_utility();
    }

    /**
     * méthode pour charger les images contenus dans le fichiers ressource
     */
    private void loadimage_bd() {
        File folder = new File("src/qc/ca/bdeb/ressources");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String filename = file.getName().toUpperCase();
                String extention = filename.substring(filename.lastIndexOf("."), filename.length());
                if (extention.equals(".PNG") || extention.equals(".GIF") || extention.equals(".JPEG") ||
                        extention.equals(".BTM") || extention.equals("JPG")) {
                    imagelist_bd.add(file.getPath());
                }
            }
        }
    }

    /**
     * méthode pour charger les images utilisés dans le jeu
     */
    private void loadimage_utility() {
        configview.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer numberofpair = Integer.parseInt(configview.getTextField().getText());
                    if (numberofpair > imagelist_bd.size()) {
                        configview.afficherErreur("Le nombre d'image contenus dans le dossier ressources doit "+
                                "etre \n inférieur a " + imagelist_bd.size() + " sinon tu doit mettre plus d'images " +
                                        "dans ce dossier",
                                "Erreur de saisie");
                        configview.getTextField().setText("");
                    } else {
                        Collections.shuffle(imagelist_bd); //Mélanger les images
                        for (int i = 0; i < numberofpair; i++) {
                            imagelist_utility.add(imagelist_bd.get(i));
                        }
                        configview.loading();
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    Controller_play controller_play = new Controller_play(imagelist_utility);
                                } catch (LineUnavailableException ex) {
                                    throw new RuntimeException(ex);
                                }
                                configview.dispose();
                            }
                        }, 6000);
                    }
                } catch (Exception exception) {
                    configview.afficherErreur("La valeur saisie n'est pas un entier. ", "Erreur de saisie");
                    configview.getTextField().setText("");
                }
            }
        });
    }

    public ArrayList<String> getImagelist_utility() {
        return imagelist_utility;
    }

    public Configview getConfigview() {
        return configview;
    }
}
