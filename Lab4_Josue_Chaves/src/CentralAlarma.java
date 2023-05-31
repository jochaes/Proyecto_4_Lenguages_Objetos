import java.util.List;

public class CentralAlarma {

    public String direccionCentral;
    CompaniaSeguridad companiaSeguridad;

    public List<Alarma> alarmas;


    public void agregarAlarma(Alarma alarma){
        this.alarmas.add(alarma);
        alarma.setCentralAlarma(this);
    }


    public void alarmaDeFuego(Alarma alarma, String localizacionSensor){
        System.out.println("*Clase Central Alarma*");

        System.out.println("*** Se Acaba de Activar una Alarma de FUEGO ***");
        System.out.println("Direccion de la CENTRAL: " + direccionCentral);
        System.out.println("ID de Alarma: " + alarma.Identificador);
        System.out.println("*** Se está avizando a la Compañia de Seguridad ***");

        companiaSeguridad.fuego(alarma.Direccion, localizacionSensor);
    }

    public void alarmaDeRobo(Alarma alarma, String localizacionSensor){

        System.out.println("*Clase Central Alarma*");

        System.out.println("*** Se Acaba de Activar una Alarma de ROBO ***");
        System.out.println("Direccion de la CENTRAL: " + direccionCentral);
        System.out.println("ID de Alarma: " + alarma.Identificador);
        System.out.println("*** Se está avizando a la Compañia de Seguridad ***");
        companiaSeguridad.robo(alarma.Direccion, localizacionSensor);
    }
}
