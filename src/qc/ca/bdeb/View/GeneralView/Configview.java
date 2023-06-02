package qc.ca.bdeb.View.GeneralView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class de vue de configuration du jeu
 */
public class Configview extends JFrame {
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JPanel panel;
    private JOptionPane Informationpanel;
    private JProgressBar progressbar;
    private Timer timer;

    /**
     * Constructeur de la class Vue de configuration
     */
    public Configview() {
        initialisewidget();
    }

    /**
     * Méthode qui charge les widgets de la vue de configuration
     */
    private void initialisewidget() {
        label=new JLabel("Veullez entrez le nombre de paire de carte que vous voulez dans le jeu :");
        textField=new JTextField(15);
        button =new JButton("OK");
        progressbar=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
        progressbar.setStringPainted(true);
        progressbar.setVisible(false);
        progressbar.setFont(new Font("MV Boli",Font.BOLD,20));
        progressbar.setForeground(Color.blue);
        progressbar.setValue(0);
        panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label,BorderLayout.NORTH);
        panel.add(textField,BorderLayout.CENTER);
        panel.add(button,BorderLayout.AFTER_LINE_ENDS);
        panel.add(progressbar,BorderLayout.SOUTH);
        setContentPane(panel);
        setSize(500,100);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Méthode qui affiche un message d'erreur
     * @param message
     * @param titre
     */
    public void afficherErreur(String message, String titre){
        JOptionPane error=new JOptionPane();
        error.setVisible(true);
        error.showMessageDialog(this,message,titre,JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Méthode pour lancer la barre de progress
     */
    public void loading(){
        progressbar.setVisible(true);
        repaint();
        timer=new Timer(50, new ActionListener() {
            Integer count=0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count<=100){
                    count=count+1;
                    progressbar.setValue(count);
                }
                else{
                    timer.stop();
                    setVisible(false);
                }
            }
        });
        timer.start();
    }

    public JButton getButton() {
        return button;
    }

    public JTextField getTextField() {
        return textField;
    }

    public Timer getTimer() {
        return timer;
    }

}
