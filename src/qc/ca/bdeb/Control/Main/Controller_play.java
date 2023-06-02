package qc.ca.bdeb.Control.Main;
import qc.ca.bdeb.Control.MediaPlayer.MediaPlayerController;
import qc.ca.bdeb.Model.Carte;
import qc.ca.bdeb.Model.User;
import qc.ca.bdeb.View.ComponenetView.*;
import qc.ca.bdeb.View.GeneralView.Configview;
import qc.ca.bdeb.View.GeneralView.Playview;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Classe Controlleur
 */
public class Controller_play {
    private ArrayList<String>imagelist_utility;
    private ArrayList<Carte> list_carte=new ArrayList<>();
    private Hashtable<Cardview,Carte> ht=new Hashtable<>();
    private ArrayList<Cardview>list_2cardview=new ArrayList<>();
    private Playview play;
    private Integer count=0,total_trouvée=0;
    private Consignesview consignesview;
    private Aboutview aboutview;
    private Savedialog savedialog;
    private MediaPlayerController mediaPlayerController;
    private String path1,path2;

    /**
     * Constructeur de la classe
     * @param imagelist_utility
     * @throws LineUnavailableException
     */
    public Controller_play(ArrayList<String> imagelist_utility) throws LineUnavailableException {
        this.imagelist_utility = imagelist_utility;
        play = new Playview(imagelist_utility);
        play.setVisible(true);
        this.consignesview = new Consignesview();
        this.aboutview = new Aboutview();
        showconsignes();
        nouvellePartie();
        showabout();
        showScores();
        pause_game();
        quitter_jeu();
        loadcarte();
        mediaPlayerController = new MediaPlayerController(play.getBarre_menu().getMediaPlayerView());
        cliquercarte();
    }

    /**
     * méthode qui controle le comportement quand on clique sur une carte
     */
    private void cliquercarte() {
        for (Cardview cardview:play.getList_cardview()){
            cardview.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(count==0){
                        list_2cardview.add(cardview);
                        list_2cardview.get(0).tourner();
                        path1=list_2cardview.get(0).getImage_front_path();
                        ht.get(cardview).setRetournee(true);
                        count=count+1;
                    }
                    else if (count==1){
                        list_2cardview.add(cardview);
                        list_2cardview.get(1).tourner();
                        path2=list_2cardview.get(1).getImage_front().toString();
                        ht.get(cardview).setRetournee(true);
                        count=count+1;
                    }
                    valider_carte();
                }
            });
        }
    }

    /**
     * Méthode qui permet de vérifier les cartes
     * si ils ne coressepondent pas
     */

    private void valider_carte() {
        if (count > 1) {
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (path1.equals(path2)) {
                        ht.get(list_2cardview.get(0)).setTrouvée(true);
                        ht.get(list_2cardview.get(1)).setTrouvée(true);
                        total_trouvée = total_trouvée + 2;
                        if (total_trouvée==play.getList_cardview().size()){
                            partiefin();
                        }
                    } else {
                        list_2cardview.get(0).retourner();
                        list_2cardview.get(1).retourner();
                    }
                    list_2cardview.clear();
                    count = 0;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    /**
     * Méthode pour mettre fin la partie
     */
    private void partiefin() {
        play.getBarre_menu().getTempsecoulé().stop();
        int respond1=JOptionPane.showConfirmDialog(play,"Bravo vous avez terminer le jeu en "
                        +play.getBarre_menu().getTemps_ecoulé().toString()+
                        ". \n Voulez-vous enrigistrer votre score?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if(respond1==JOptionPane.YES_OPTION){
            savedialog=new Savedialog();
            savedialog.getSubmitbtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(savedialog.getUsernametextfield().getText().equals("")){
                        JOptionPane.showMessageDialog(savedialog,"Veuillez saisir un username");
                    }
                    else {
                        User user=new User(savedialog.getUsernametextfield().getText(),
                                play.getBarre_menu().getTemps_ecoulé(),play.getBarre_menu().getDatelabel().getText(),
                                list_carte.size()/2);
                    }
                }
            });
        }
        int respond2=JOptionPane.showConfirmDialog(play,"Voulez-vous relancer une nouvelle Partie?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (respond2==JOptionPane.YES_OPTION){
            mediaPlayerController.close();
            play.dispose();
            Controller_config controllerConfig=new Controller_config();
        }
    }

    /**
     * méthode qui load les cartes
     */
    private void loadcarte() {
        for (int i=0;i<play.getList_cardview().size();i++){
            list_carte.add(new Carte(play.getList_cardview().get(i).getImage_front_path()));
            ht.put(play.getList_cardview().get(i),list_carte.get(i));
        }
    }

    /**
     * Méthode pour quitter le jeu
     */
    private void quitter_jeu() {
        play.getBarre_menu().getQuitter_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * méthode pour mettre en pause le jeu
     */
    private void pause_game() {
        play.getBarre_menu().getPauseparty_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                play.getBarre_menu().getTempsecoulé().stop();
                JOptionPane info=new JOptionPane();
                info.setVisible(true);
                info.showMessageDialog(play,"la partie est maintenant en pause. Pour reprendre veuillez " +
                        "cliquer sur Ok","Jeu en Pause",JOptionPane.WARNING_MESSAGE);
                play.getBarre_menu().getTempsecoulé().start();
            }
        });
    }

    /**
     * Méthode qui affiche la scoreview
     */
    private void showScores() {
        play.getBarre_menu().getScore_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreView scoreView=new ScoreView();
            }
        });
    }

    /**
     * Méthode qui met une nouvelle partie
     */
    private void nouvellePartie() {
        play.getBarre_menu().getNewparty_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(play,
                        "Etes vous sur de vouloir relancer une nouvelle Partie?",
                        "question", JOptionPane.YES_NO_OPTION);
                if (res==0) {
                    mediaPlayerController.close();
                    play.dispose();
                    Controller_config controllerConfig=new Controller_config();
                }
            }
        });
    }

    /**
     * méthode pour afficher les consignes
     */
    private void showconsignes(){
        play.getBarre_menu().getInstruction_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consignesview.setVisible(true);
            }
        });
    }

    /**
     * méthode qui affiche la page a propos
     */
    private void showabout(){
        play.getBarre_menu().getApropos_item().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutview.setVisible(true);
            }
        });
    }
}
