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



//Esta clase se encarga de manejar la interfaz gráfica del juego


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
    private JLabel puntos_lb;

    private ImageIcon heartIcon;
    private ImageIcon staminaIcon;

    private ScheduledExecutorService executorService;
    private SoundPlayer soundPlayer;
    private DataSingleton data;

    public Game(){
        soundPlayer = SoundPlayer.getInstance();
        data = DataSingleton.getInstance();

        exit_btn.setText("Salir");
        gamePanel.setPreferredSize(new Dimension(300, 300));

        statsJugador_tb.setFloatable(false);
        panelPalabra_tp.setEditable(false);
        centerTextPane();

        //Carga las imagenes de los iconos de stamina y corazones en memoria
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

    //Constructor, setea la instancia de DataSingleton
    public void startGame(String pNombreJugaor){

        data.setStamina(3);
        data.setVidas(3);
        data.setValor(0);
        data.setNombre(pNombreJugaor);
        data.setClave("Puntos");

        setToolbarIcons(data.getVidas(), data.getStamina());
        inputJugador_tf.setText("");
        runTimerNextWord();
    }


    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getGoStartBtn() {
        return exit_btn;
    }

    //Coloca los iconos de stamina y corazones en la barra de herramientas
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

    //Remueve un corazón de la barra de herramientas
    private void removeHeartToolbar(int totalHearts){

        for (int i = 0; i < 3-totalHearts; i++) {
            statsJugador_tb.getComponent(i).setVisible(false);
        }

        statsJugador_tb.repaint();
    }

    //Remueve un icono de stamina de la barra de herramientas
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

    //Cambia la palabra actual en el panel de texto
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
                //Si es la barra espaciadora, entonces baja la stamina
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
                    //Verifica que la palabra que se está escribiendo sea igual a la palabra actual
                    if( data.getPalabraActual().equals(inputJugador_tf.getText()) ){
//                        System.out.println("Palabra Correcta ");
                        data.agregarPuntos();
                        puntos_lb.setText(String.valueOf("Pts:" + data.getValor()));
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

    //Método que se ejecuta cuando se presiona la barra de espacio
    //Muestra la palabra por 400ms y luego la borra
    private void verPalabra(){
        executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            int ms = 400;
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

    //Ejecuta el timer para mostrar la siguiente palabra
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
