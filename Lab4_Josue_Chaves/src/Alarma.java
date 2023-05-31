import java.util.List;

public class Alarma {
    public String NombreEdificio;
    public String Direccion;

    public int Identificador;

    public CentralAlarma centralAlarma;

    public List<Sensor> sensores;

    public Alarma(String nombreEdificio, String direccion, int identificador){
        this.NombreEdificio = nombreEdificio;
        this.Direccion = direccion;
        this.Identificador = identificador;
    }

    public void setCentralAlarma(CentralAlarma centralAlarma) {
        this.centralAlarma = centralAlarma;
    }

    public void agregarSensor(Sensor sensor){
        this.sensores.add(sensor);
        sensor.setAlarma(this);
    }

    public void avizarCentral( Sensor sensor ){
        System.out.println("*Clase Alarma*");
        System.out.println("*** Se está avizando a la central ***");

        switch (sensor.tipoSensor){
            case "Fuego":
                centralAlarma.alarmaDeFuego(this, sensor.localizacion);
                break;
            case "Robo":
                centralAlarma.alarmaDeRobo(this, sensor.localizacion);
                break;
            default:
                System.out.println("No se reconoce el tipo de sensor");
        }
    }

    public void activarAlarma(Sensor sensor){
        System.out.println("*Clase Alarma*");
        System.out.println("*** Se Acaba de Activar una Alarma ***");
        System.out.println("Nombre del Edificio: " + NombreEdificio);
        System.out.println("Direccion: " + Direccion);
        System.out.println("Sensor: " + sensor.nombreSensor);
        System.out.println("Localizacion Sensor: " + sensor.localizacion);
        System.out.println("Tipo de Sensor: " + sensor.tipoSensor);
        System.out.println("****************************************");

        avizarCentral(sensor);
    }


}
