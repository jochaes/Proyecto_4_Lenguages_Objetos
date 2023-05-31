package palabrasfugaces;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
    private JPanel gamePanel;
    private JButton exit_btn;
    private JTextPane panelPalabra_tp;
    private JTextField inputJugador_tf;
    private JToolBar statsJugador_tb;

    private ImageIcon heartIcon;
    private ImageIcon staminaIcon;

    private ScheduledExecutorService executorService;
    private SoundPlayer soundPlayer;
    private DataSingleton data;

//    La lista "faciles" tiene 41 palabras.
//    La lista "medias" tiene 60 palabras.
//    La lista "dificiles" tiene 52 palabras.


    public Game(){
        soundPlayer = SoundPlayer.getInstance();
        data = DataSingleton.getInstance();

        exit_btn.setText("Salir");
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

        setPlayerInput();
    }

    public void startGame(String pNombreJugaor){

        data.setStamina(3);
        data.setVidas(3);
        data.setValor(0);
        data.setNombre(pNombreJugaor);
        data.setClave("Puntos");

        setToolbarIcons(data.getVidas(), data.getStamina());
        runTimerNextWord();
    }


    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getGoStartBtn() {
        return exit_btn;
    }

    private void setToolbarIcons(int hearts, int stamina){
        statsJugador_tb.removeAll();

        if (hearts > 0 ){
            for (int i = 0; i < hearts; i++) {
                statsJugador_tb.add(new JLabel(heartIcon));
            }
        }

        if(stamina > 0){
            for (int i = 0; i < stamina; i++) {
                statsJugador_tb.add(new JLabel(staminaIcon));
            }
        }

    }

    private void removeHeartToolbar(int totalHearts){

        for (int i = 0; i < 3-totalHearts; i++) {
            statsJugador_tb.getComponent(i).setVisible(false);
        }

        statsJugador_tb.repaint();
    }

    private void removeStaminaToolbar(int totalStamina){

        for (int i = 3; i < 6-totalStamina; i++) {
            statsJugador_tb.getComponent(i).setVisible(false);
        }

        statsJugador_tb.repaint();
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

    private void setPlayerInput(){

        this.inputJugador_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

//                System.out.println("typed");
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("pressed");

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    inputJugador_tf.setText(inputJugador_tf.getText().substring(0, inputJugador_tf.getText().length() - 1));
//                    System.out.println("Espacio");
                    if(data.getStamina() > 0){
                        data.pierdeStamina();

                        try {
                            soundPlayer.playVerPalabra();
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                            ioException.printStackTrace();
                        }
                        removeStaminaToolbar(data.getStamina());
                        verPalabra();
                    }
                }else{
                    if( data.getPalabraActual().equals(inputJugador_tf.getText()) ){
                        System.out.println("Palabra Correcta ");
                        data.agregarPuntos();
                        inputJugador_tf.setText("");
                        runTimerNextWord();
                    }
                    else{
                        if(data.getPalabraActual().startsWith(inputJugador_tf.getText())) {
                            try {
                                soundPlayer.playLetraOk();
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            data.pierdeVida();


                            removeHeartToolbar(data.getVidas());
                            inputJugador_tf.setText(inputJugador_tf.getText().substring(0, inputJugador_tf.getText().length() - 1));
                            try {
                                soundPlayer.playLetraNo();
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                                ioException.printStackTrace();
                            }

                            if (data.getVidas() == 0){
                                terminarJuego();
                            }
                        }

                    }
                }


            }
        });


    }

    private void verPalabra(){
        executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            int ms = 500;
            @Override
            public void run() {

                panelPalabra_tp.setText("Escribe la palabra\n" + data.getPalabraActual());
                ms = ms - 100 ;


                if (ms < 0){
                    panelPalabra_tp.setText("Escribe la palabra\n");
                    executorService.shutdown();
                }
            }
        };
        executorService.scheduleAtFixedRate(task, 10, 100, TimeUnit.MILLISECONDS);
    }

    private void runTimerNextWord(){
        executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            int segundos = 3;
            @Override
            public void run() {
                if (segundos >=0) {
                    panelPalabra_tp.setText(String.valueOf(segundos));
                    inputJugador_tf.setEditable(false);
                    try {
                        soundPlayer.playContador();
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    segundos--;
                }else {

                    data.setPalabraActual();
                    executorService.shutdown();
                    inputJugador_tf.setEditable(true);
                    inputJugador_tf.requestFocus();
                    verPalabra();
                }
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }


    public void terminarJuego(){
        exit_btn.doClick();
    }


}
