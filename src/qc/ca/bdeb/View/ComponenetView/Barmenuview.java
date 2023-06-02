package qc.ca.bdeb.View.ComponenetView;

import qc.ca.bdeb.Utilities.Temps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe de barredemenu
 */
public class Barmenuview extends JMenuBar {
    private JMenu menujeu, menuaide;
    private JMenuItem newparty_item, pauseparty_item, quitter_item,score_item, instruction_item, apropos_item;
    private JLabel datelabel, heurelabel;
    private MediaPlayerView mediaPlayerView =new MediaPlayerView();
    private Timer temps_horloge,tempsecoulé;
    private Temps temps_ecoulé=new Temps();

    /**
     * Constructeur de la barre de Menu
     */
    public Barmenuview() {
        loading();
        Updatetemps();
        incrémentetempsecoulé();
    }

    /**
     * Méthode qui charge les composants de la barres de Menu
     */
    private void loading() {
        ///**** Création de la barre de menu ****//
        //*** menu Jeu ***//
        ImageIcon play_icon=new ImageIcon("src/qc/ca/bdeb/Icones/play.png");
        ImageIcon new_icon = new ImageIcon("src/qc/ca/bdeb/Icones/new.png");
        ImageIcon pause_icone=new ImageIcon("src/qc/ca/bdeb/Icones/pause.png");
        ImageIcon score_icone=new ImageIcon("src/qc/ca/bdeb/Icones/record.png");
        ImageIcon exit_icone=new ImageIcon("src/qc/ca/bdeb/Icones/exit.png");
        newparty_item = new JMenuItem("Nouvelle Partie",new_icon);
        pauseparty_item = new JMenuItem("Mettre en Pause",pause_icone);
        score_item=new JMenuItem("Tableau de Score",score_icone);
        quitter_item = new JMenuItem("Quitter le jeu",exit_icone);
        menujeu = new JMenu("Jeu");
        menujeu.setIcon(play_icon);
        menujeu.add(newparty_item);
        menujeu.add(pauseparty_item);
        menujeu.add(score_item);
        menujeu.add(quitter_item);

        //*** menu aide ***//
        ImageIcon aide_icone=new ImageIcon("src/qc/ca/bdeb/Icones/aide.png");
        ImageIcon instructions_icone=new ImageIcon("src/qc/ca/bdeb/Icones/instructions.png");
        ImageIcon about_icone=new ImageIcon("src/qc/ca/bdeb/Icones/about.png");
        instruction_item=new JMenuItem("Instruction",instructions_icone);
        apropos_item=new JMenuItem("A Propos",about_icone);
        menuaide = new JMenu("Aide");
        menuaide.setIcon(aide_icone);
        menuaide.add(instruction_item);
        menuaide.add(apropos_item);

        //** Définition des variables temps et date **//
        ImageIcon time_icone=new ImageIcon("src/qc/ca/bdeb/Icones/time.png");
        ImageIcon date_icon=new ImageIcon("src/qc/ca/bdeb/Icones/date.png");
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("EEE dd MMM yyyy", Locale.FRENCH);
        heurelabel=new JLabel(LocalTime.now().format(timeFormatter));
        heurelabel.setIcon(time_icone);
        datelabel=new JLabel(LocalDate.now().format(dateFormatter));
        datelabel.setIcon(date_icon);


        //*** Ajout des composant de la barre de menu ***//
        this.add(Box.createHorizontalStrut(2));
        this.add(menujeu);
        this.add(Box.createHorizontalStrut(5));
        this.add(menuaide);
        this.add(Box.createHorizontalStrut(2));
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(mediaPlayerView,BorderLayout.CENTER);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(Box.createHorizontalStrut(2));
        this.add(heurelabel);
        this.add(datelabel);
        this.add(Box.createHorizontalStrut(20));
    }

    /**
     * Méthode qui metAjour le temps affiché dans la barre de menu
     */
    public void Updatetemps(){
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        temps_horloge=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heurelabel.setText(LocalTime.now().format(timeFormatter));
                heurelabel.repaint();
            }
        });
        temps_horloge.start();
    }

    /**
     * Méthode qui permet d'ajouter une seconde au temps écoulé
     */
    public void incrémentetempsecoulé(){
        tempsecoulé=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                temps_ecoulé.Ajout_une_seconde();
            }
        });
        tempsecoulé.start();
    }

    public JMenuItem getNewparty_item() {
        return newparty_item;
    }

    public JMenuItem getInstruction_item() {
        return instruction_item;
    }

    public JMenuItem getApropos_item() {
        return apropos_item;
    }

    public MediaPlayerView getMediaPlayerView() {
        return mediaPlayerView;
    }

    public JMenuItem getScore_item() {
        return score_item;
    }

    public Timer getTempsecoulé() {
        return tempsecoulé;
    }

    public JMenuItem getPauseparty_item() {
        return pauseparty_item;
    }

    public JMenuItem getQuitter_item() {
        return quitter_item;
    }

    public Temps getTemps_ecoulé() {
        return temps_ecoulé;
    }

    public JLabel getDatelabel() {
        return datelabel;
    }
}
