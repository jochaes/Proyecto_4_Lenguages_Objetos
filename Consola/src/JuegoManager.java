import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JuegoManager {

    List<Juego> juegos;
    List<String> nombresJuegos;


    public JuegoManager(){
        this.juegos = new ArrayList<>();
        this.nombresJuegos = new ArrayList<>();
    }

    public void addJuego(Juego juego){
        this.juegos.add(juego);
        if( !this.nombresJuegos.contains(juego.getNombreJuego()) ){
            this.nombresJuegos.add(juego.getNombreJuego());
        }
    }

    //Devuelve un alista con las estadisticas más altas por juego
    //Por cada juego, hace un filtro de todas las instancias y compara todas estas para obtener la mayor
    public List<String[]> getEstadisticas(){
        List<String[]> mejoresPuntuaciones = new ArrayList<String[]>();

        for (String nombre: this.nombresJuegos) {
            Juego instancia = juegos.stream()
                    .filter(p -> p.getNombreJuego().equals(nombre)).toList()
                    .stream().max(Comparator.comparing(Juego::getPuntuacion)).get();

            String [] mejorPuntuacion = {instancia.getNombreJuego(), instancia.getNombreJugador(), instancia.getClavePuntuacion(), String.valueOf(instancia.getPuntuacion())};
            mejoresPuntuaciones.add(mejorPuntuacion);
        }
        return mejoresPuntuaciones;
    }

    public boolean yaExiste(String nombreJuego){

        if (this.nombresJuegos.isEmpty()) {return false;}

        else if(this.nombresJuegos.contains(nombreJuego)){
            //Si vuelvo a cargar un juego que ya estaba cargado, lo pongo visible
            Juego instancia = this.juegos.stream()
                    .filter(p -> p.getNombreJuego().equals(nombreJuego)).toList().get(0);

            instancia.getFrameJuego().setVisible(true);
            return true;


        }else return false;


    }

}
