package qc.ca.bdeb.View.ComponenetView;

import qc.ca.bdeb.Model.User;
import qc.ca.bdeb.Utilities.Temps;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe de la vue de Score
 */
public class ScoreView extends JDialog {
    private JPanel panel;
    private JTextField textField;
    private JLabel label;
    private JTableHeader tableHeader;
    private ArrayList<User> userlist=new ArrayList<>();
    private String[] colomnname={"UserName","tempsecoulé","dateofscore","numberofpaire","score"};
    private JTable table;

    /**
     * Constructeur de la classe de vue de Score
     */
    public ScoreView() {
        intialise();
    }

    public void setUserlist(ArrayList<User> userlist) {
        this.userlist = userlist;
    }

    public ArrayList<User> getUserlist() {
        return userlist;
    }

    /**
     * Méthode qui intialize les variables de scores
     */
    private void intialise() {
        panel=new JPanel();
        label=new JLabel("Username: ");
        textField=new JTextField(16);
        Object[][] data=new Object[userlist.size()][colomnname.length];
        DefaultTableModel model=new DefaultTableModel(data,colomnname);
        table=new JTable(model);
        tableHeader=table.getTableHeader();
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD,12f));
        tableHeader.setReorderingAllowed(false);
        JScrollBar scrollBar=new JScrollBar();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < colomnname.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(120);
        }
        table.add(scrollBar);
        panel.add(label,BorderLayout.NORTH);
        panel.add(textField,BorderLayout.NORTH);
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(tableHeader);
        panel.add(table);
        add(panel);
        setModal(true);
        setSize(620,400);
        setVisible(true);
        setModal(true);

    }
}
