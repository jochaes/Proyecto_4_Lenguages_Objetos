import gamesInterface.Stat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JuegoManagerJC {

    List<JuegoConsolaJC> juegos;
    List<String> nombresJuegos;


    public JuegoManagerJC(){
        this.juegos = new ArrayList<>();
        this.nombresJuegos = new ArrayList<>();
    }

    public void addJuego(JuegoConsolaJC juego){
        if( !this.nombresJuegos.contains(juego.getNombreJuego()) ){
            this.nombresJuegos.add(juego.getNombreJuego());
            this.juegos.add(juego);
        }
    }

    //Devuelve un alista con las estadisticas más altas por juego
    //Por cada juego, hace un filtro de todas las instancias y compara todas estas para obtener la mayor
    public List<String[]> getEstadisticas(){
        List<String[]> mejoresPuntuaciones = new ArrayList<String[]>();

        for (JuegoConsolaJC juego: this.juegos) {
            Stat gameStat  = juego.getPuntuacion();

            String [] mejorPuntuacion = {juego.getNombreJuego(), gameStat.getNombre(), gameStat.getClave(), String.valueOf(gameStat.getValor())};
            mejoresPuntuaciones.add(mejorPuntuacion);
        }
        return mejoresPuntuaciones;
    }

    public boolean yaExiste(String nombreJuego){


        if (this.nombresJuegos.isEmpty()) {return false;}

        else if(this.nombresJuegos.contains(nombreJuego)){

            System.out.println("Ya hay una instancia de: " + nombreJuego);
            System.out.println("Mostrando instancia de: " + nombreJuego);


            //Si vuelvo a cargar un juego que ya estaba cargado, lo pongo visible
            JuegoConsolaJC instancia = this.juegos.stream()
                    .filter(p -> p.getNombreJuego().equals(nombreJuego)).toList().get(0);

            instancia.getFrameJuego().setVisible(true);
            return true;

        }else return false;


    }

}
