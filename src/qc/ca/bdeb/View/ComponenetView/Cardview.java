package qc.ca.bdeb.View.ComponenetView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe de vue de la carte
 */
public class Cardview extends JButton {
    private ImageIcon image_front,image_back;
    private String image_front_path;
    private JLabel label;
    private Integer angle=0;
    private Timer timer;
    public Cardview(Integer width, Integer height, String image_front_path){
        intialise(width,height,image_front_path);
    }

    /**
     * Fonction qui intialise les variable de la vue de carte
     * @param width
     * @param height
     * @param image_front_path
     */
    private void intialise(Integer width, Integer height, String image_front_path) {
        setPreferredSize(new Dimension(width,height));
        this.image_front_path=image_front_path;
        this.image_front=new ImageIcon(image_front_path);
        this.image_back=new ImageIcon("src/qc/ca/bdeb/Icones/back09.png");
        resizeiconeimage(this.image_back,width,height);
        resizeiconeimage(this.image_front,width,height);
        label=new JLabel();
        label.setIcon(image_back);
        add(label);
        setBorder(null);
    }

    /**
     * Méthode pour redimensionner l'image selon la taille du bouton
     * @param imageicone
     * @param width
     * @param height
     */
    private void resizeiconeimage(ImageIcon imageicone, Integer width, Integer height) {
        Image newimage =imageicone.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        imageicone.setImage(newimage);
    }

    /**
     * Methode pour faire tourner la carte
     */
    public void tourner(){
        int intialx=getX();
        int intialy=getY();
        timer =new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle=angle+10;
                if(angle<180){
                    setLocation(intialx,getY()-2);
                }
                else {
                    setLocation(intialx,intialy);
                    label.setIcon(image_front);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Méthode pour retourner la carte
     */
    public void retourner(){
        int initialx=getX();
        int initialy=getY();
        timer=new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle=angle-10;
                if (angle>0){
                    setLocation(initialx,getY()+2);
                }
                else {
                    setLocation(initialx,initialy);
                    label.setIcon(image_back);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    /**
     * Méthode pour retourner l'imageIcone image_front
     * @return
     */
    public ImageIcon getImage_front() {
        return image_front;
    }

    /**
     * Méthode qui retourne le path du fichier d'image de la face front
     * @return
     */
    public String getImage_front_path() {
        return image_front_path;
    }
}
