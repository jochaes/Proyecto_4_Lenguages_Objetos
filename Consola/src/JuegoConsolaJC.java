import javax.swing.JInternalFrame;
import gamesInterface.GameFunction;
import gamesInterface.Stat;

public class JuegoConsolaJC {

    private JInternalFrame frameJuego;
    private String nombreJuego;


    public JuegoConsolaJC(JInternalFrame frameJuego, String nombreJuego) {
        this.frameJuego = frameJuego;
        this.nombreJuego = nombreJuego;
    }

    public int getPuntuacion(){
        Stat puntuacion = ((GameFunction) this.frameJuego).getStats();
        return puntuacion.getValor();
    }

    public String getNombreJugador(){
        Stat nombreJugador = ((GameFunction) this.frameJuego).getStats();
        return nombreJugador.getNombre();
    }

    public String getClavePuntuacion(){
        Stat claveJugador = ((GameFunction) this.frameJuego).getStats();
        return claveJugador.getClave();
    }



    public JInternalFrame getFrameJuego() {
        return this.frameJuego;
    }

    public String getNombreJuego() {
        return this.nombreJuego;
    }

}
