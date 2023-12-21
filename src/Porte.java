package src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
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
    this.id = id;
    this.origen = origen;
    this.nave = nave;
    this.muelleOrigen = muelleOrigen;
    this.salida = salida;
    this.destino = destino;
    this.muelleDestino = muelleDestino;
    this.llegada = llegada;
    this.precio = precio;
    huecos = new boolean[nave.getFilas()][nave.getColumnas()];
    listaEnvios = new ListaEnvios(nave.getFilas() * nave.getColumnas());
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
        int huecosLibres = 0;

        for (int i = 0; i < huecos.length; i++) {
            for (int j = 0; j < huecos[i].length; j++) {
                if (!huecos[i][j]) {
                    huecosLibres++;
                }
            }
        }
        return huecosLibres;
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
        //Si hay hueco(true), no esta ocupado(false)
        return !huecos[fila][columna]; //HEREhuecos Quitar lo contrario (!)
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
        if (huecos[fila][columna] || fila > huecos.length || columna > huecos.length) {
            return null;
        } else {
            boolean salir = false;
            int i = 0;
            while (i<listaEnvios.getLength() && !salir){
                if (listaEnvios.getEnvio(i).getFila() == fila && listaEnvios.getEnvio(i).getColumna() == columna){
                    salir = true;
                } else {
                    i++;
                }
            }
            return listaEnvios.getEnvio(i);
        }
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
            if (!huecos[envio.getFila() - 1][envio.getColumna() - 1] && listaEnvios.insertarEnvio(envio)) {
                huecos[envio.getFila() - 1][envio.getColumna() - 1] = true;
                ocupar = true;
                //HERE hay que poner numHuecosLibres++ de alguna forma;
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
        return "Porte "+getID()+" de "+getOrigen().toStringSimple()+" M"+getMuelleOrigen()+" "+getSalida()+" a "
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
        boolean coincidente = false;
        if (getOrigen().getCodigo().equalsIgnoreCase(codigoOrigen) && getDestino().getCodigo().equalsIgnoreCase(codigoDestino)
        && getSalida().coincide(fecha)){
            coincidente = true;
        }
        return coincidente;
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
        System.out.print(" ");
        for (int i = 0; i < nave.getColumnas(); i++) {
            char letraComienzo = 'A';
            System.out.print("  " + (char) (letraComienzo + i));
        }
        System.out.println();

        //Huecos
        for (int i = 0; i < (nave.getFilas()); i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < (nave.getColumnas()); j++) {
                if (!huecoOcupado(i, j)) {
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
            pw = new PrintWriter(fichero);
            pw.println("-------------------------------------------------");
            pw.println("-------- Lista de envíos del porte "+getID()+" --------");
            pw.println("-------------------------------------------------");
            pw.println("Hueco\tCliente");
            for (int i = 0 ; i < nave.getFilas(); i++){
                for (int j = 0; j < nave.getColumnas(); j++){
                    if (!huecos[i][j]){
                        pw.print((i+1));
                        char letraComienzo = 'A';
                        char columna = (char) (letraComienzo + j);
                        pw.println(columna);
                    } else {
                        pw.print((i+1));
                        char letraComienzo = 'A';
                        char columna = (char) (letraComienzo + j);
                        pw.print(columna+"\t\t");
                        pw.println(listaEnvios.getEnvio(i).getCliente().toString());
                    }
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } finally {
            if (pw != null){
                pw.close();
            }
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

            String codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
            while (puertosEspaciales.buscarPuertoEspacial(codigoOrigen)==null){
                System.out.println("\t Código de puerto no encontrado.");
                codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
            }
            PuertoEspacial puertoEspacialOrigen = puertosEspaciales.buscarPuertoEspacial(codigoOrigen);
            int muelleOrigen = Utilidades.leerNumero(teclado, "Ingrese el muelle de Origen (1 - "+puertoEspacialOrigen.getMuelles()+"): ", 1,puertoEspacialOrigen.getMuelles());

            String codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
            while (puertosEspaciales.buscarPuertoEspacial(codigoDestino)==null){
                System.out.println("\t Código de puerto no encontrado.");
                codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
            }
            PuertoEspacial puertoEspacialDestino = puertosEspaciales.buscarPuertoEspacial(codigoDestino);
            int terminalDestino = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - "+puertoEspacialDestino.getMuelles()+"): ", 1, puertoEspacialDestino.getMuelles());

            naves.mostrarNaves();

            Nave nave = naves.seleccionarNave(teclado, "Ingrese matrícula de la nave: ", puertoEspacialOrigen.distancia(puertoEspacialDestino));
            if (nave != null) {
                Fecha fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida:");
                Fecha fechaLlegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada:");
                while (fechaLlegada.anterior(fechaSalida)) {
                    System.out.println("Llegada debe ser posterior a salida");
                    fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida:");
                    fechaLlegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada:");
                }
                double precio = Utilidades.leerNumero(teclado, "Ingrese precio de pasaje: ", 0);
                String id = generarID(rand);

                Porte porte = new Porte(id, nave, puertoEspacialOrigen, muelleOrigen, fechaSalida, puertoEspacialDestino, terminalDestino, fechaLlegada, precio);
                portes.insertarPorte(porte);
                System.out.println("Porte " + porte.getID() + " creado correctamente");
                return porte; //HERE Aquí aparece el error con un .toString (aparece usando el debugger)
            } else {
                return null;
            }
    }
}
