package palabrasfugaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;

public class Game {
    private JPanel gamePanel;
    private JButton goStart_btn;

    public Game(){
        gamePanel.setPreferredSize(new Dimension(300, 300));
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getGoStartBtn() {
        return goStart_btn;
    }



}
