package qc.ca.bdeb.View.ComponenetView;

import javax.swing.*;

/**
 * Classe de boite de dialogue de save
 */
public class Savedialog extends JDialog {
    private JLabel label=new JLabel("Username");
    private JTextField usernametextfield=new JTextField(30);
    private JButton submitbtn=new JButton("Ok");

    /**
     * constructuer de la classe de boite de dialogue
     */
    public Savedialog() {
        JPanel panel=new JPanel();
        panel.add(label);
        panel.add(usernametextfield);
        panel.add(submitbtn);
        setModal(true);
        add(panel);
        pack();
        setTitle("Enregitrement");
        setVisible(true);
    }

    public JButton getSubmitbtn() {
        return submitbtn;
    }

    public JTextField getUsernametextfield() {
        return usernametextfield;
    }
}
