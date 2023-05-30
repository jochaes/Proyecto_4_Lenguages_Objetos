package palabrasfugaces;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    private String sword = "src/Resources/Sounds/Master_Sword_Get.wav";
    private String timer = "src/Resources/Sounds/Korog_Timer.flac";

    //Sonidos del Juego
    private String contador = "src/Resources/Sounds/contador.wav";
    private String endGame = "src/Resources/Sounds/endGame.wav";
    private String letraNo = "src/Resources/Sounds/letraNo.wav";

    private String letraOk = "src/Resources/Sounds/letraOk.wav";
    private String popUpMenu = "src/Resources/Sounds/popUpMenu.wav";
    private String startGame = "src/Resources/Sounds/startGame2.wav";

    private String verPalabra = "src/Resources/Sounds/verPalabra.wav";



    private File soundFile;

    private AudioInputStream audioIn;



    private void playSound( AudioInputStream audioIn ) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

    public void playContador() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(contador).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playEndGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(endGame).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playLetraNo() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(letraNo).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playLetraOk() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(letraOk).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playPopUpMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(popUpMenu).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playStartGame() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(startGame).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }

    public void playVerPalabra() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        soundFile = new File(verPalabra).getAbsoluteFile();
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        playSound(audioIn);

    }


}
