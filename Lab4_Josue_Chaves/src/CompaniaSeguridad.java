import java.util.List;

public abstract class CompaniaSeguridad {

    public String Localidad;
    public List<CentralAlarma> centralesAlarmas;

    public abstract void avisarPolicia(String direccionEdificio, String localidadSensor);
    public abstract void  avizarBomberos( String direccionEdificio, String localidadSensor );
    public abstract void robo( String direccionEdificio, String localidadSensor);
    public abstract void fuego(String direccionEdificio, String localidadSensor );
}
