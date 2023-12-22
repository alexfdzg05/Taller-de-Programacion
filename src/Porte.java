package src;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * La clase Porte contiene un porte con información como identificador, nave, puertos de origen y destino,
 * fechas de salida y llegada, precio y una matriz de huecos. Contiene métodos para gestionar la ocupación de los
 * huecos, mostrar información detallada y abreviada del porte, y generar un ID aleatorio, entre otros.
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
     * @param id Identificador del porte
     * @param nave Nave asociada al porte
     * @param origen Puerto espacial de origen
     * @param muelleOrigen Número de muelle de origen
     * @param salida Fechade salida
     * @param destino Puerto espacial de destino
     * @param muelleDestino Número de muelle de destino
     * @param llegada Fecha de llegada
     * @param precio Precio del coste
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

    /**
     * Obtiene el identificador del porte
     *
     * @return Identificador del porte
     */
    public String getID() {
        return id;
    }

    /**
     * Obtiene la nave del porte
     *
     * @return Nave del porte
     */
    public Nave getNave(){
        return nave;
    }

    /**
     * Obtiene el puerto espacial de origen del porte
     *
     * @return Puerto espacial de origen del porte
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }

    /**
     * Obtiene el muelle de origen del porte
     *
     * @return Muelle de origen del porte
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    /**
     * Obtiene la fecha de salida del porte
     *
     * @return Fecha de salida del porte
     */
    public Fecha getSalida(){
        return salida;
    }

    /**
     * Obtiene el puerto espacial de destino del porte
     *
     * @return Puerto espacial de destino del porte
     */
    public PuertoEspacial getDestino() {
        return destino;
    }

    /**
     * Obtiene el muelle de destino del porte
     *
     * @return Muelle de destino del porte
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }

    /**
     * Obtiene la fecha de llegada del porte
     *
     * @return Fecha de llegada del porte
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     * Obtiene el precio del porte
     *
     * @return Precio del porte
     */
    public double getPrecio() {
        return precio;
    }

    // TODO: Devuelve el número de huecos libres que hay en el porte
    /**
     * @return Numero de huecos libres
     */
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
    /**
     * @return Si el porte está o no completo
     */
    public boolean porteLleno() {
        boolean lleno = false;
        if(numHuecosLibres()==0){
            lleno = true;
        }
        return lleno;
    }

    // TODO: ¿Está ocupado el hueco consultado?  //true si está ocupado, false si no
    /**
     * @return Si el hueco (introducido por fila y columna) del porte está o no ocupado
     */
    public boolean huecoOcupado(int fila, int columna) {
        //Si hay hueco(true), no esta ocupado(false)
        return !huecos[fila][columna]; //HEREhuecos Quitar lo contrario (!)
    }

    /**
     * Busca si un envío está en la lista de envíoss
     *
     * @return Envio encontrado
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }


    /**
     * TODO: Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila Fila del envío
     * @param columna Columna del envío
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
     * @param envio Envío a ocupar
     * @return true si se encontró un hueco, false al contrario
     */
    public boolean ocuparHueco(Envio envio) {
        boolean ocupar = false;
        if(!porteLleno()) {
            if (!huecos[envio.getFila() - 1][envio.getColumna() - 1] && listaEnvios.insertarEnvio(envio)) {
                huecos[envio.getFila() - 1][envio.getColumna() - 1] = true;
                ocupar = true;
            }
        }
        return ocupar;

    }


    /**
     * TODO: A través del localizador del envio, se desocupará el hueco
     * @param localizador Localizador del envío a desocupar
     * @return true si se pudo desocupar el hueco, false en caso contrario
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
     * @param codigoOrigen Código puerto de origen
     * @param codigoDestino Código puerto de destino
     * @param fecha Fecha
     * @return si coinciden el código de origen y salida, además de la fecha
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
     * @param fichero Nombre del fichero donde se escribirá la lista de envíos
     * @return true si se pudo escribir en el fichero, false en caso contrario
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
     * @param rand Objeto Random utilizado para generar números aleatorios
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
     * @param teclado           Scanner para la entrada de usuario
     * @param rand              Generador de números aleatorios
     * @param puertosEspaciales Lista de puertos espaciales disponibles
     * @param naves             Lista de naves disponibles
     * @param portes            Lista de portes existentes
     * @return Objeto Porte creado por el usuario
     */
    public static Porte altaPorte(Scanner teclado, Random rand,
                                  ListaPuertosEspaciales puertosEspaciales,
                                  ListaNaves naves,
                                  ListaPortes portes) {
        String codigoOrigen = "", codigoDestino = "";
        int muelleOrigen = 0, terminalDestino = 0;
        boolean cancelar = false;
        PuertoEspacial puertoEspacialOrigen = null, puertoEspacialDestino = null;
        Porte porte;

        //codOrigen
        while(puertosEspaciales.buscarPuertoEspacial(codigoOrigen)==null && !codigoOrigen.equalsIgnoreCase("cancelar")){
            codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
            if(codigoOrigen.equalsIgnoreCase("cancelar")){
                cancelar = true;
            } else if(puertosEspaciales.buscarPuertoEspacial(codigoOrigen)==null){
                System.out.println("\t Código de puerto no encontrado.");
            }
        }
        if(!cancelar){
            puertoEspacialOrigen = puertosEspaciales.buscarPuertoEspacial(codigoOrigen);
            muelleOrigen = Utilidades.leerNumero(teclado, "Ingrese el muelle de Origen (1 - "+puertoEspacialOrigen.getMuelles()+"): ", 1,puertoEspacialOrigen.getMuelles());

        }

        //codDestino
        while(puertosEspaciales.buscarPuertoEspacial(codigoDestino)==null && !cancelar){
            codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen:");
            if(codigoDestino.equalsIgnoreCase("cancelar")){
                cancelar = true;
            } else if(puertosEspaciales.buscarPuertoEspacial(codigoDestino)==null){
                System.out.println("\t Código de puerto no encontrado.");
            }
        }
        if(!cancelar){
            puertoEspacialDestino = puertosEspaciales.buscarPuertoEspacial(codigoDestino);
            terminalDestino = Utilidades.leerNumero(teclado, "Ingrese Terminal Destino (1 - "+puertoEspacialDestino.getMuelles()+"): ", 1, puertoEspacialDestino.getMuelles());
        }

        if(!cancelar){
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

                porte = new Porte(id, nave, puertoEspacialOrigen, muelleOrigen, fechaSalida, puertoEspacialDestino, terminalDestino, fechaLlegada, precio);
                portes.insertarPorte(porte);
                System.out.println("Porte " + porte.getID() + " creado correctamente");
                return porte;
            } else {
                porte = null;
            }
        }
        else{
            porte = null;
        }
        return porte;
    }
}
