package palabrasfugaces;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;

public class Game {
    private JPanel gamePanel;
    private JButton exit_btn;
    private JTextPane panelPalabra_tp;
    private JTextField inputJugador_tf;
    private JToolBar statsJugador_tb;

    public Game(){

        gamePanel.setPreferredSize(new Dimension(300, 300));

        statsJugador_tb.setFloatable(false);
        panelPalabra_tp.setEditable(false);
        centerTextPane();

        setToolbarIcons(3, 3);
    }



    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getGoStartBtn() {
        return exit_btn;
    }

    private void setToolbarIcons(int hearts, int stamina){
        ImageIcon heartIcon = new ImageIcon("src/Resources/Imgs/heart-sm.png");
        ImageIcon staminaIcon = new ImageIcon("src/Resources/Imgs/stamina-sm.png");

        statsJugador_tb.add(new JLabel(heartIcon));
        statsJugador_tb.add(new JLabel(staminaIcon));
    }

    private void centerTextPane(){
        StyledDocument doc = panelPalabra_tp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 30);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panelPalabra_tp.setFocusable(false);
        panelPalabra_tp.setText("Escriba la Palabra: PALABRA");
    }



}
