package qc.ca.bdeb.View.GeneralView;

import qc.ca.bdeb.Utilities.InterfacePlay;
import qc.ca.bdeb.Utilities.Temps;
import qc.ca.bdeb.View.ComponenetView.Barmenuview;
import qc.ca.bdeb.View.ComponenetView.Cardview;
import qc.ca.bdeb.View.ComponenetView.MediaPlayerView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class de vue de plateau de jeu
 */
public class Playview extends JFrame implements InterfacePlay {
    private JPanel plateau_panel,main_panel;
    private ArrayList<Cardview> list_cardview;
    private ArrayList<String> imagelist_utilities;
    private Barmenuview barre_menu;
    private MediaPlayerView mediaPlayerView;

    /**
     * Constructuer de la vue du jeu
     * @param imagelist_utilities
     * @throws HeadlessException
     */
    public Playview(ArrayList<String> imagelist_utilities) throws HeadlessException {
        this.imagelist_utilities=imagelist_utilities;
        loadwidgets();
        loadplateux();
    }

    /**
     * Méthode pour charger le plateau de jeu
     */
    @Override
    public void loadplateux() {
        Integer taille=imagelist_utilities.size();
        Integer nrow,ncol=5,hgab=10,vgab=10;
        nrow=1+(2*taille)/6;
        Integer width,height;
        width=(ncol-1)*hgab+ncol*120;
        height=(nrow-1)*vgab+nrow*140;
        list_cardview=new ArrayList<Cardview>();
        plateau_panel=new JPanel();
        GridLayout gridLayout=new GridLayout(nrow,ncol,hgab,vgab);
        plateau_panel.setLayout(gridLayout);
        for (int i=1;i<=2;i++) {
            Collections.shuffle(imagelist_utilities);
            for (String path : imagelist_utilities) {
                Cardview cardview=new Cardview(120,140,path);
                list_cardview.add(cardview);
                plateau_panel.add(cardview);
                plateau_panel.repaint();
            }
        }
        main_panel=new JPanel();
        main_panel.add(plateau_panel);
        setContentPane(main_panel);
        pack();
    }

    /**
     * Méthode pour charger les widgets
     */
    @Override
    public void loadwidgets() {


        //Ajout a la frame//
        ImageIcon game_icone=new ImageIcon("src/qc/ca/bdeb/Icones/Games.png");
        barre_menu=new Barmenuview();
        mediaPlayerView =new MediaPlayerView();
        setJMenuBar(barre_menu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Jeu de mémoire");
        setIconImage(game_icone.getImage());
        setVisible(false);

    }

    public Barmenuview getBarre_menu() {
        return barre_menu;
    }

    public ArrayList<Cardview> getList_cardview() {
        return list_cardview;
    }
}
