package src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     * TODO: Completa el constructo de la clase
     *
     * @param id
     * @param nave
     * @param origen
     * @param muelleOrigen
     * @param salida
     * @param destino
     * @param muelleDestino
     * @param llegada
     * @param precio
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {

    }
    public String getID() {
        return id;
    }
    public Nave getNave(){
        return nave;
    }
    public PuertoEspacial getOrigen() {
        return origen;
    }
    public int getMuelleOrigen() {
        return muelleOrigen;
    }
    public Fecha getSalida(){
        return salida;
    }
    public PuertoEspacial getDestino() {
        return destino;
    }
    public int getMuelleDestino() {
        return muelleDestino;
    }
    public Fecha getLlegada() {
        return llegada;
    }
    public double getPrecio() {
        return precio;
    }
    // TODO: Devuelve el número de huecos libres que hay en el porte

    public int numHuecosLibres() {

    }
    // TODO: ¿Están llenos todos los huecos?
    public boolean porteLleno() {
        boolean lleno = false;
        if(numHuecosLibres()==0){
            lleno = true;
        }
        return lleno;
    }

    // TODO: ¿Está ocupado el hueco consultado?  //true si está ocupado, false si no
    public boolean huecoOcupado(int fila, int columna) {
        boolean ocupado = true;
        if (huecos [fila-1][columna-1] = false){
            ocupado = false;
        }
        return ocupado;


    }
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila
     * @param columna
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {

        return null;
    }


    /**
     * TODO: Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio
     * @return
     */
    public boolean ocuparHueco(Envio envio) {
        boolean ocupar = false;
        if(!porteLleno()) {
            if (!huecos[envio.getFila() - 1][envio.getColumna() - 1]) {
                huecos[envio.getFila() - 1][envio.getColumna() - 1] = true;
                listaEnvios.insertarEnvio(envio);
                //HERE hay que poner numHuecosLibres++ de alguna forma;
                ocupar = true;
            }
        }
        return ocupar;

    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador
     * @return
     */
    public boolean desocuparHueco(String localizador) {
        boolean desocupar = false;
        Envio envio = listaEnvios.buscarEnvio(localizador);
        if(envio != null){
            huecos[envio.getFila() - 1][envio.getColumna() - 1] = true;
            listaEnvios.eliminarEnvio(localizador);
            //HERE hay que poner numHuecosLibres++ de alguna forma
            desocupar = true;
        }
        return desocupar;
    }

    /**
     * TODO: Devuelve una cadena con información completa del porte
     * @return ejemplo del formato -> "Porte PM0066 de Gaia Galactic Terminal(GGT) M5 (01/01/2023 08:15:00) a
     *  Cidonia(CID) M1 (01/01/2024 11:00:05) en Planet Express One(EP-245732X) por 13424,56 SSD, huecos libres: 10"
     */
    public String toString() {
        // le falta el M% antes de la fecha
        return "Porte "+getID()+" de "+getOrigen().toStringSimple()+" "+getMuelleOrigen()+" "+getSalida()+" a "
                +getDestino().toStringSimple()+" en "+getNave().toStringSimple()+" por "+getPrecio()+" SSD, huecos libres:"+numHuecosLibres();
    }


    /**
     * TODO: Devuelve una cadena con información abreviada del vuelo
     * @return ejemplo del formato -> "Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05)"
     */
    public String toStringSimple() {
        return "Porte "+getID()+" de "+getOrigen().toStringSimple()+" "+getMuelleOrigen()+" "+getSalida()+" a "
        +getDestino().toStringSimple();
    }


    /**
     * TODO: Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return ;
    }


    /**
     * TODO: Muestra la matriz de huecos del porte, ejemplo:
     *        A  B  C
     *      1[ ][ ][ ]
     *      2[X][X][X]
     *      3[ ][ ][ ]
     *      4[ ][X][ ]
     *     10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        //Primera fila, letras: 		A  B  C  D  E F
        for (int i = 0; i < nave.getColumnas(); i++) {
            char letraComienzo = 'A';
            System.out.print(" " + (char) (letraComienzo + i) + " ");
        }
        System.out.println();

        //Huecos
        for (int i = 1; i <= (nave.getFilas()); i++) {
            for (int j = 0; j <= (nave.getColumnas()); j++) {
                if (i < 10) {
                    System.out.print(" " + (i));
                } else {
                    System.out.print(i);
                }

                if (huecoOcupado(i, j)) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }

            }
            System.out.println();
        }
    }



    /**
     * TODO: Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     *  del enunciado
     * @param fichero
     * @return
     */
    public boolean generarListaEnvios(String fichero) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }


    /**
     * TODO: Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        int numAleatorio = rand.nextInt(10000);
        return String.format("PM%04d", numAleatorio);
    }

    /**
     * TODO: Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     *  y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     *  del enunciado.
     *  La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param puertosEspaciales
     * @param naves
     * @param portes
     * @return
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {

        return null;
    }
}
