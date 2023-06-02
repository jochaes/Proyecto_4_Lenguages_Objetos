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



//Esta clase se encarga de manejar la interfaz gráfica de la pantalla de incio


package palabrasfugaces;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;




public class Settings {
    private JPanel settingsPanel;
    private JButton StartGame_btn;
    private JTextField playerName_tf;
    private JButton showInstructions_btn;
    private JButton showStats_btn;
    private JLabel gameTitle_lbl;
    private JLabel setPlayerName_tf;
    private SoundPlayer soundPlayer;
    private DataSingleton data;

    FocusListener focusListener;

    public Settings(){
        data = DataSingleton.getInstance();
        soundPlayer = SoundPlayer.getInstance();

        gameTitle_lbl.setText("¡Bienvenido a Palabras Fugaces!");

        settingsPanel.setPreferredSize(new Dimension(300, 300));
        setPlayerName_tf.setText("Nombre de Jugador");

        showInstructions_btn.setText("Instrucciones");
        showInstructions_btn.addActionListener(e -> showInstructions());

        showStats_btn.setText("Estadisticas");
        showStats_btn.addActionListener(e -> showStats());

        getStartGameBtn().setText("Iniciar Juego");
        getStartGameBtn().requestFocus();
    }

    //Muestra las instrucciones del juego
    private void showInstructions(){
        try {
            soundPlayer.playPopUpMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String [] message =
        {
            "\t\t\tGeneral",
                "Por cada palabra escrita correctamente se sumaran 3 puntos",
                "Por cada *letra* incorrecta se restará una vida",
                "La palabra se reviza automáticamente cuando se va escribiendo, no hay necesidad de presionar enter u otro botón.",
                " ",
                "\t\t\tVidas",
                "El jugador empieza con 3 vidas",
                "Si el jugador se queda sin vidas, el juego termina",
                " ",
                "\t\t\tControles",
                "La barra espaciadora le permite volver a ver la palabra, si la acierta sólo obtendrá un punto",
                "Sólo puede realizar esta acción 3 veces en total",
                " ",
                " ",
                "ITCR-SLSC * Escuela de Computación * Lenguajes * I Semestre 2023 * Josue Chaves"

        };
        JOptionPane.showMessageDialog(settingsPanel, message,"Instrucciones", JOptionPane.INFORMATION_MESSAGE);
    }


    //Muestra las estadísticas del juego
    private void showStats(){
        try {
            soundPlayer.playPopUpMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String stats = "Última Puntuación\n" +
                "Jugador: " + data.getNombre() + "\n" +
                "Puntaje: " + data.getValor() + "\n";

        JOptionPane.showMessageDialog(settingsPanel, stats,"Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JButton getStartGameBtn() {
        return StartGame_btn;
    }

    //Retorna el nombre del jugador
    public String getPlayerName() {

        if (playerName_tf.getText().equals("Escriba su nombre de jugador") || playerName_tf.getText().isEmpty()){
            return "";
        }else{
            return playerName_tf.getText();
        }
    }
}
