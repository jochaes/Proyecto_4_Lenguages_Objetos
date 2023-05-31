package palabrasfugaces;


import java.util.Random;

//Clase que contiene los datos del juego
public class DataSingleton {
    private static DataSingleton instance = new DataSingleton();

    private String clave = "";
    private String nombre = "";
    private int valor = 0;

    private String[] palabras = {"sol","luz","flor","rio", "casa", "paz", "mano", "bueno", "pan", "amor", "arte", "mar", "onda",
            "dios", "lago", "reir", "azul", "perro", "gato", "sano", "agua", "vida", "arbol", "hola", "jugar", "luna",
            "pez", "nube", "dulce", "pala", "tela", "pelo", "pato", "pino", "masa", "amasa","aventura", "elefante",
            "enciclopedia","relajante","misterioso","chocolate","dinosaurio",
            "melancolico","fantasma","musica","maravilloso","imaginacion","telescopio","romantico","exquisito",
            "galaxia","arqueologia","estadistica","fotografia","tecnologia","eclipse","equilibrio","independencia",
            "sabiduria","contemplar","valentia","verdadero","naturaleza","curiosidad","magico","creativo","legendario",
            "emocionante","complejidad","profundidad","inigualable","magnifico","sobresaliente","adrenalina","armonia",
            "resplandor","perspicacia","singularidad","esperanza","incomparable","esplendor","grandioso","alegria",
            "precioso","encantador","fascinante","impresionante","resplandeciente","glorioso","sensacional","exuberante",
            "majestuoso","captivador","extraterrestre","paralelepipedo","inconmensurable","desconocido","microscopico","revolucionario",
            "incomprensible","espectacular","extraordinario","incalculable","complicadisimo","formidable","intergalactico",
            "inconcebible","inexplicable","monumental","asombroso","insuperable","indescriptible","inconquistable",
            "inabarcable","indomable","inextinguible","inimitable","invencible","irrepetible","sorprendente","inigualado",
            "inagotable","inmarcesible","imperdible","irresistible","increible","incomparable","incomprehensible",
            "impresionante","indecifrable","desmesurado","descomunal","indescifrable","desconcertante","inexpugnable",
            "indestructible","desorbitado","ilimitado","inconexo","desbordante","indefinible","indivisible","indispensable",
            "inexpresable","descomposicion","impredecible","incontestable","desafiante"};

    private String palabraActual;

    private int vidas;

    private int stamina ;

    private boolean acabaDePerderStamina = false;

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

    public void agregarPuntos(){
        if(acabaDePerderStamina){
            setValor(getValor()+1);
            acabaDePerderStamina = false;
        }else {
            setValor(getValor() + 3);
        }
    }
    public String getPalabraActual() {
        return palabraActual;
    }

    public void setPalabraActual() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(palabras.length-1);
        this.palabraActual = palabras[randomIndex];
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void pierdeVida(){
        this.vidas--;
        if(this.vidas < 0){
            this.vidas = 0;
        }
    }

    public int getStamina() {
        return stamina;
    }
    public void pierdeStamina(){
        this.stamina--;
        if (this.stamina < 0){
            this.stamina = 0;
        }

    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
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
