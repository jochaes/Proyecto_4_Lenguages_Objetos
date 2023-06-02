import javax.swing.JInternalFrame;
import gamesInterface.GameFunction;
import gamesInterface.Stat;

public class JuegoConsolaJC {

    private JInternalFrame frameJuego;
    private String nombreJuego;

    private Stat ultimaPuntuacion;

    public JuegoConsolaJC(JInternalFrame frameJuego, String nombreJuego) {
        this.frameJuego = frameJuego;
        this.nombreJuego = nombreJuego;
        this.ultimaPuntuacion = new Stat("pts","ply", 0);
    }


    public Stat getPuntuacion(){

        if ( ((GameFunction) this.frameJuego).getStats().getValor() >= this.ultimaPuntuacion.getValor() ){
            this.ultimaPuntuacion = ((GameFunction) this.frameJuego).getStats();
            return this.ultimaPuntuacion;
        }else{
            return this.ultimaPuntuacion;
        }

    }

    public JInternalFrame getFrameJuego() {
        return this.frameJuego;
    }

    public String getNombreJuego() {
        return this.nombreJuego;
    }

}
