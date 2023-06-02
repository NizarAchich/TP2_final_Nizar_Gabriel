package qc.ca.bdeb.View.ComponenetView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe de Boite de dialogue A propos
 */
public class Aboutview extends JDialog {
    private String texte="";
    private String path="";
    private JTextArea textArea=new JTextArea();

    /**
     * Constructeur de la classe
     */
    public Aboutview() {
        path="src/qc/ca/bdeb/Texte/a propos.txt";
        uploadfile();
    }

    /**
     * Méthode qui upload un file
     */
    private void uploadfile() {
        File file=new File(path);
        try{
            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                texte=texte+scanner.nextLine()+"\n";
            }
            textArea.setRows(12);
            textArea.setText(texte);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            Font f=new Font("Time New Romain",Font.PLAIN,14);
            textArea.setFont(f);
            add(textArea,BorderLayout.CENTER);
            setVisible(false);
            setLocationRelativeTo(null);
            setModal(true);
            setSize(1000,600);
        }
        catch(FileNotFoundException e){
            JOptionPane.showConfirmDialog(this,"fichier introuvable!!!","Erreur Fatale"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
