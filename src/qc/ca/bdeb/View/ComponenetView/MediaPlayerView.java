package qc.ca.bdeb.View.ComponenetView;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de vue de MediaPlayer
 */
public class MediaPlayerView extends JPanel {
    private JSlider volumeslider;
    private JButton btn_StopStart,btn_previous,btn_next;
    private JLabel volumelabel;
    private JPanel flow_panel1,flow_panel2;

    /**
     *  Constructeur de la classe
     */
    public MediaPlayerView() {
        loadwidgets();
    }

    /**
     * Méthode qui charge tout les widgets
     */
    private void loadwidgets() {
        //** Menu du mediaPlayer **//
        volumeslider=new JSlider(JSlider.HORIZONTAL,0,100,50);
        volumeslider.setBackground(null);
        volumeslider.setPreferredSize(new Dimension(200,40));
        volumeslider.setPaintTicks(true);
        volumeslider.setPaintTrack(true);
        volumeslider.setMinorTickSpacing(10);
        volumeslider.setMajorTickSpacing(25);
        volumeslider.setPaintLabels(true);
        volumeslider.setFont(new Font("Times New Roman", Font.PLAIN, 8));
        volumelabel=new JLabel(new ImageIcon
                ("src/qc/ca/bdeb/Icones/MusicPlayer/Custom-Icon-Design-Pretty-Office-8-Sound-on.16.png"));
        btn_next=new JButton();
        btn_next.setPreferredSize(new Dimension(20,20));
        btn_next.setIcon(new ImageIcon
                ("src/qc/ca/bdeb/Icones/MusicPlayer/Custom-Icon-Design-Pretty-Office-8-Skip-forward.16.png"));
        btn_next.setBorder(BorderFactory.createLineBorder(Color.blue,1,true));
        btn_previous=new JButton();
        btn_previous.setPreferredSize(new Dimension(20,20));
        btn_previous.setIcon(new ImageIcon
                ("src/qc/ca/bdeb/Icones/MusicPlayer/Custom-Icon-Design-Pretty-Office-8-Skip-backward.16.png"));
        btn_previous.setBorder(BorderFactory.createLineBorder(Color.blue,1,true));
        btn_StopStart=new JButton();
        btn_StopStart.setPreferredSize(new Dimension(20,20));
        btn_StopStart.setIcon(new ImageIcon
                ("src/qc/ca/bdeb/Icones/MusicPlayer/Custom-Icon-Design-Pretty-Office-8-Play.16.png"));
        btn_StopStart.setBorder(BorderFactory.createLineBorder(Color.blue,1,true));
        flow_panel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow_panel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow_panel1.add(volumelabel);
        flow_panel1.add(volumeslider);
        flow_panel1.setAlignmentY(BOTTOM_ALIGNMENT);
        flow_panel2.add(btn_previous);
        flow_panel2.add(btn_StopStart);
        flow_panel2.add(btn_next);
        flow_panel2.setAlignmentY(this.getAlignmentY());
        flow_panel1.setPreferredSize(new Dimension(250,48));
        flow_panel2.setPreferredSize(new Dimension(250,10));
        setLayout(new GridLayout(1,2,0,5));
        add(flow_panel1);
        add(flow_panel2);
        setBackground(null);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setVisible(true);

    }

    public JSlider getVolumeslider() {
        return volumeslider;
    }

    public JButton getBtn_StopStart() {
        return btn_StopStart;
    }

    public JButton getBtn_previous() {
        return btn_previous;
    }

    public JButton getBtn_next() {
        return btn_next;
    }
}
