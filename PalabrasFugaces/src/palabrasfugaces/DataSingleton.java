package palabrasfugaces;


//Clase que contiene los datos del juego
public class DataSingleton {
    private static DataSingleton instance = new DataSingleton();

    private String clave;
    private String nombre;
    private int valor;

    public static DataSingleton getInstance(){
        return instance;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "DataSingleton{" +
                "clave='" + clave + '\'' +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
