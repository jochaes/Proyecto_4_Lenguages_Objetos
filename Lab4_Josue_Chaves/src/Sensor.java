public class Sensor {

    String nombreSensor;
    String localizacion;

    String tipoSensor;

    Alarma alarma;

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public void activarSensor(){

        this.alarma.avizarCentral(this);
        System.out.println("Activando sensor " + nombreSensor + " en " + localizacion);
    }

}
