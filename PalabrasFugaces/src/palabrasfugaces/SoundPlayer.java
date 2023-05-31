package palabrasfugaces;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.print.DocFlavor;
import javax.sound.sampled.*;

public class SoundPlayer {

    private String sword = "/Sounds/Master_Sword_Get.wav";
    private String timer = "/Sounds/Korog_Timer.flac";

    //Sonidos del Juego
    private String contador = "/Sounds/contador.wav";
    private String endGame = "/Sounds/endGame.wav";
    private String letraNo = "/Sounds/letraNo.wav";

    private String letraOk = "/Sounds/letraOk.wav";
    private String popUpMenu = "/Sounds/popUpMenu.wav";
    private String startGame = "/Sounds/startGame2.wav";

    private String verPalabra = "/Sounds/verPalabra.wav";

    private File soundFile;

    private AudioInputStream audioIn;

    private Clip clip;
    InputStream audioSrc;
    InputStream bufferedIn;

    private static SoundPlayer instance = new SoundPlayer();

    public static SoundPlayer getInstance(){
        return instance;
    }


    private void playSound( String soundName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        audioSrc = getClass().getResourceAsStream(soundName);
        //add buffer for mark/reset support
        assert audioSrc != null;
        bufferedIn = new BufferedInputStream(audioSrc);
        audioIn= AudioSystem.getAudioInputStream(bufferedIn);

        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }



    public void stopSound() {
        clip.stop();
        clip.close();
    }

    public void playContador() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        playSound(contador);

    }

    public void playEndGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        playSound(endGame);
    }

    public void playLetraNo() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(letraNo);
    }

    public void playLetraOk() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        playSound(letraOk);

    }

    public void playPopUpMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(popUpMenu);
    }

    public void playStartGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {


        playSound(startGame);

    }

    public void playStartApp() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(sword);
    }

    public void playVerPalabra() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playSound(verPalabra);
    }


}
