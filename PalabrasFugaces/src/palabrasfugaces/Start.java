/**
 * Instituto Tecnológico de Costa Rica
 * Escuela de Computación
 *
 * Curso de Lenguajes de Programación
 *
 * Proyecto 4: Juego de Palabras Fugaces
 *
 * Estudiante: Josué Chaves Araya - 2015094068
 *
 * I Semestre 2023
 */



//Esta clase se encarga de manejar la interfaz gráfica de la aplicacion


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


//        try {
//            soundPlayer.playStartApp();
//        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
//            e.printStackTrace();
//        }

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

    //Agrega el evento de iniciar el juego al botón de iniciar juego
    private void addStartGameAction(JButton button){

        button.addActionListener(event -> {
            String nombre = settingsPanel.getPlayerName();

            if (nombre.isEmpty()){
                try {
                    soundPlayer.playLetraNo();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de jugador", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                    soundPlayer.playStartGame();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
                getContentPane().removeAll();
                getContentPane().add(gamePanel.getGamePanel());
                pack();
                gamePanel.startGame(  settingsPanel.getPlayerName() );
            }
        });
    }

    //Agrega el evento de ir a la pantalla de inicio al botón de ir a inicio
    //Se ejecuta cuando el juego termina
    private void goToStartAction(JButton button){
        button.addActionListener(e -> {

            try {
                soundPlayer.playEndGame();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }

            String stats = "Última Puntuación\n" +
                    "Jugador: " + data.getNombre() + "\n" +
                    "Puntaje: " + data.getValor() + "\n";

            JOptionPane.showMessageDialog(this, stats,"Estadísticas", JOptionPane.INFORMATION_MESSAGE);


            getContentPane().removeAll();
            getContentPane().add(settingsPanel.getSettingsPanel());
            pack();
        });
    }

    //Devuelve las estadísticas del juego
    @Override
    public Stat getStats() {
        return new Stat(data.getClave(), data.getNombre(), data.getValor());
    }

    public static void main(String[] args) {
        System.out.println("Iniciando Palabras Fugaces");
    }

}
