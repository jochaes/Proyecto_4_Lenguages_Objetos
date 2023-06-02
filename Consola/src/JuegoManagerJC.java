/**
 * Instituto Tecnológico de Costa Rica
 * Escuela de Computación
 * Curso de Lenguajes
 *
 * Proyecto 4: Consola de Juegos
 *
 * Josué Chaves Araya  - 2015094068
 */

//Clase que se encarga de manejar los juegos


import gamesInterface.Stat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JuegoManagerJC {

    //Lista de las instancias de los juegos
    List<JuegoConsolaJC> juegos;
    //Lista de los nombres de los juegos
    List<String> nombresJuegos;


    public JuegoManagerJC(){
        this.juegos = new ArrayList<>();
        this.nombresJuegos = new ArrayList<>();
    }

    //Añade un nuevo juego a la lista de juegos
    public void addJuego(JuegoConsolaJC juego){
        this.juegos.add(juego);
        if( !this.nombresJuegos.contains(juego.getNombreJuego()) ){
            this.nombresJuegos.add(juego.getNombreJuego());
        }
    }

    //Devuelve un alista con las estadisticas más altas por juego
    //Por cada juego obtiene la estadistica con el valor más alto (Actualiza el puntaje si el nuevo es mayor)
    public List<String[]> getEstadisticas(){
        List<String[]> mejoresPuntuaciones = new ArrayList<String[]>();

        for (JuegoConsolaJC juego: this.juegos) {
            Stat gameStat  = juego.getPuntuacion();

            String [] mejorPuntuacion = {juego.getNombreJuego(), gameStat.getNombre(), gameStat.getClave(), String.valueOf(gameStat.getValor())};
            mejoresPuntuaciones.add(mejorPuntuacion);
        }
        return mejoresPuntuaciones;
    }

    //Se encarag de verificar que no se cargue un juego que ya esta cargado
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
