package palabrasfugaces;

import gamesInterface.GameFunction;
import gamesInterface.Stat;
import palabrasfugaces.Settings;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

public class Start extends JInternalFrame implements GameFunction {
    private DataSingleton data;
    private SoundPlayer soundPlayer;

    private JInternalFrame mainFrame;
    private JPanel mainPanel;

    private Settings settingsPanel;
    private Game gamePanel;

    public Start() {
        soundPlayer = SoundPlayer.getInstance();
        data = DataSingleton.getInstance();


        try {
            soundPlayer.playStartApp();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        mainFrame = this;
        mainFrame.setTitle("Palabras Fugaces");
        mainFrame.setSize(500, 500);
        mainFrame.setClosable(true);
        mainFrame.setResizable(true);

        this.settingsPanel = new Settings();
        this.gamePanel = new Game();

        addStartGameAction(settingsPanel.getStartGameBtn());
        goToStartAction(gamePanel.getGoStartBtn());


        getContentPane().add(settingsPanel.getSettingsPanel());

        pack();
    }

    private void addStartGameAction(JButton button){

        button.addActionListener(event -> {

            try {
                soundPlayer.playStartGame();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }

            getContentPane().removeAll();
            getContentPane().add(gamePanel.getGamePanel());
            pack();
        });

    }

    private void goToStartAction(JButton button){
        button.addActionListener(e -> {
            getContentPane().removeAll();
            getContentPane().add(settingsPanel.getSettingsPanel());
            pack();
        });
    }




    @Override
    public Stat getStats() {
        return new Stat(data.getClave(), data.getNombre(), data.getValor());
    }

    public static void main(String[] args) {
        System.out.println("Iniciando Palabras Fugaces");
    }

}
