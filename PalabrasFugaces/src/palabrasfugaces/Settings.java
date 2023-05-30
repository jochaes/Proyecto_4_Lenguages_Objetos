package palabrasfugaces;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;


public class Settings {
    private JPanel settingsPanel;
    private JButton StartGame_btn;
    private JTextField playerName_tf;

    public Settings(){
        settingsPanel.setPreferredSize(new Dimension(300, 300));
        playerName_tf.setText("Player");
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JButton getStartGameBtn() {
        return StartGame_btn;
    }

    public String getPlayerName() {
        return playerName_tf.getText();
    }
}
