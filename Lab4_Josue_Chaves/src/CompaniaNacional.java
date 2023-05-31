public class CompaniaNacional extends CompaniaSeguridad{

    public CompaniaNacional(String localidad){
        this.Localidad = localidad;
    }

    public void agregarCentral(CentralAlarma central){
        this.centralesAlarmas.add(central);
    }

    public void avisarPolicia(String direccionEdificio, String localidadSensor){
        System.out.println(
                "Avizando a la CENTRAL NACIONAL DE POLICIAS de "
                        + this.Localidad
                        + " que hay un incendio en el edificio ubicado en "
                        + direccionEdificio
                        + " en en sensor de "
                        + localidadSensor
        );
    }

    public void  avizarBomberos( String direccionEdificio, String localidadSensor ){

        System.out.println(
                "Avizando a la CENTRAL NACIONAL DE BOMBEROS de "
                        + this.Localidad
                        + " que hay un incendio en el edificio ubicado en "
                        + direccionEdificio
                        + " en en sensor de "
                        + localidadSensor
        );

    }

    public void robo( String direccionEdificio, String localidadSensor){
        avisarPolicia(direccionEdificio, localidadSensor);
    }
    public void fuego(String direccionEdificio, String localidadSensor ){
        avizarBomberos(direccionEdificio, localidadSensor);
        avisarPolicia(direccionEdificio, localidadSensor);
    }


}
