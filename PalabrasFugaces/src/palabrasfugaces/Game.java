package palabrasfugaces;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;

public class Game {
    private JPanel gamePanel;
    private JButton exit_btn;
    private JTextPane panelPalabra_tp;
    private JTextField inputJugador_tf;
    private JToolBar statsJugador_tb;

    private ImageIcon heartIcon;
    private ImageIcon staminaIcon;

    public Game(){

        gamePanel.setPreferredSize(new Dimension(300, 300));

        statsJugador_tb.setFloatable(false);
        panelPalabra_tp.setEditable(false);
        centerTextPane();

        try{
            InputStream heartIS = new BufferedInputStream(getClass().getResourceAsStream("/Imgs/heart-sm.png"));
            Image heartImage = ImageIO.read(heartIS);

            InputStream staminaIS = new BufferedInputStream(getClass().getResourceAsStream("/Imgs/stamina-sm.png"));
            Image staminaImage = ImageIO.read(staminaIS);

            this.heartIcon = new ImageIcon(heartImage);
            this.staminaIcon = new ImageIcon(staminaImage);
        }catch (IOException e) {
            e.printStackTrace();
        }

        setToolbarIcons(3, 3);
    }



    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getGoStartBtn() {
        return exit_btn;
    }

    private void setToolbarIcons(int hearts, int stamina){

        for (int i = 0; i < hearts; i++) {
            statsJugador_tb.add(new JLabel(heartIcon));
        }

        for (int i = 0; i < stamina; i++) {
            statsJugador_tb.add(new JLabel(staminaIcon));
        }
    }

    private void centerTextPane(){
        StyledDocument doc = panelPalabra_tp.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center, 30);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        panelPalabra_tp.setFocusable(false);
        panelPalabra_tp.setText("Escriba la Palabra:\n PALABRA");
    }



}
