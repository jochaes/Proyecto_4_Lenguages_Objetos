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
    private SoundPlayer soundPlayer;

    FocusListener focusListener;

    public Settings(){
        soundPlayer = SoundPlayer.getInstance();

        settingsPanel.setPreferredSize(new Dimension(300, 300));
        playerName_tf.setToolTipText("Escriba su nombre de jugador");
        setPlaceHolder();

        showInstructions_btn.setText("Instrucciones");
        showInstructions_btn.addActionListener(e -> showInstructions());

        showStats_btn.setText("Estadisticas");
        showStats_btn.addActionListener(e -> showStats());

        getStartGameBtn().setText("Iniciar Juego");
    }

    private void setPlaceHolder(){

        focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                if (playerName_tf.getText().equals("Escriba su nombre de jugador")){
                    playerName_tf.setText("");
                }else{
                    playerName_tf.setText("Escriba su nombre de jugador");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("focus lost");
                if (playerName_tf.getText().isEmpty()){
                    playerName_tf.setText("Escriba su nombre de jugador");
                }
            }
        };

        playerName_tf.addFocusListener(focusListener);

    }

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
                "Por cada palabra escrita incorrectamente se restara una vida",
                "\t\t\tVidas",
                "El jugador comienza con 3 vidas",
                "Si el jugador se queda sin vidas, el juego termina",
                "\t\t\tControles",
                "Presionar barra espaciadora para volver a ver la palabra, obtiene 1 punto, si acierta la palabra",
                "Solo escriba la palabra en el espacio indicado"
        };
        JOptionPane.showMessageDialog(settingsPanel, message,"Instrucciones", JOptionPane.INFORMATION_MESSAGE);
    }


    private void showStats(){
        try {
            soundPlayer.playPopUpMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String [] stats = {"\t\t\tJugador", "Puntaje", "Vidas"};
        JOptionPane.showMessageDialog(settingsPanel, stats,"Stadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JButton getStartGameBtn() {
        return StartGame_btn;
    }

    public String getPlayerName() {

        if (playerName_tf.getText().equals("Escriba su nombre de jugador") || playerName_tf.getText().isEmpty()){
            return "";
        }else{
            return playerName_tf.getText();
        }
    }
}
