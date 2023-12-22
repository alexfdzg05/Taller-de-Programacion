package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * La clase ListaPortes gestiona una colección de portes con funcionalidades como la inserción,
 * búsqueda, listado y escritura/lectura en formato CSV. Cada instancia de la clase contiene una lista de
 * portes con una capacidad máxima, ofreciendo métodos para acceder a la ocupación, verificar si está llena
 * y realizar operaciones clave en la gestión de portes.
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad inicial de la lista
     */
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
    }

    // TODO: Devuelve el número de portes que hay en la lista
    /**
     * @return Número de portes ocupados en la lista
     */
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i< portes.length; i++){
            if (portes[i]!= null){
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista?
    /**
     * @return true si la lista está llena, false en caso contrario
     */
    public boolean estaLlena() {
        boolean llena = true;
        int i = 0;
        while (llena && (i < portes.length)) {
            if (portes[i] == null) {
                llena = false;
            } else {
                i++;
            }
        }
        return llena;
    }

    //TODO: devuelve un porte dado un indice
    /**
     * @param i Índice del porte en la lista
     * @return Objeto Porte en la posición i de la lista
     */
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte Porte a insertar
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean esPosible = false;
        int i = 0;
        if (!estaLlena()){
            while (i<portes.length && portes[i]!=null){
                i++;
            }
            portes[i] = porte;
            esPosible = true;
        }
        return esPosible;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id Identificador del porte a buscar
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
       Porte porte = null;
       for (int i = 0; i < portes.length; i++){
           if (portes[i]!=null){
               if (portes[i].getID().equalsIgnoreCase(id)){
                   porte = portes[i];
               }
           }
       }
       return porte;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen  Código del puerto espacial de origen
     * @param codigoDestino Código del puerto espacial de destino
     * @param fecha Fecha de los portes.
     * @return Lista de portes que cumplen con los parámetros especificados
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaPortes listaPortes = new ListaPortes(portes.length);
        for (int i = 0; i < portes.length; i++){
            if (portes[i]!= null){
                if (portes[i].coincide(codigoOrigen,codigoDestino,fecha)) {
                    listaPortes.insertarPorte(portes[i]);
                }
            }
        }
        if (listaPortes.getOcupacion() == 0){
            System.out.println("No hay portes con esos parámetros");
            listaPortes = null;
        }
        return listaPortes;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (int i = 0; i < portes.length; i++) {
            if (portes[i] != null) {
                System.out.println(portes[i].toString());
            }
        }
    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado  Scanner para entrada de usuario
     * @param mensaje  Mensaje de solicitud al usuario
     * @param cancelar Cadena que indica la opción de cancelar
     * @return Porte seleccionado o null si se cancela la operación
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte = null;
        String id;
        if (portes != null) {
            do {
                id = Utilidades.leerCadena(teclado, mensaje);
                if (!id.equalsIgnoreCase("cancelar")) {
                    porte = buscarPorte(id);
                    if (porte == null) {
                        System.out.println("\t Porte no encontrado.");
                    }
                }
            } while (porte == null && (!id.equalsIgnoreCase(cancelar)));
        }
        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero Ruta y nombre del fichero
     * @return true si se escribió correctamente, false en caso de error
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for(int i = 0; i < getOcupacion(); i++){
                Porte porte = getPorte(i);
                pw.print(porte.getID() + ";");
                pw.print(porte.getNave().getMatricula() + ";");
                pw.print(porte.getOrigen().getCodigo() + ";");
                pw.print(porte.getMuelleOrigen() + ";");
                pw.print(porte.getSalida().toString() + ";");
                pw.print(porte.getDestino().getCodigo() + ";");
                pw.print(porte.getMuelleDestino() + ";");
                pw.print(porte.getLlegada().toString() + ";");
                pw.println(porte.getPrecio());
            }
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero           Ruta y nombre del fichero
     * @param capacidad         Capacidad de la lista de portes
     * @param puertosEspaciales Lista de puertos espaciales para buscar origen y destino de los portes
     * @param naves             Lista de naves para buscar la nave asociada al porte
     * @return Lista de portes generada a partir del archivo CSV o null si hay un error
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        Porte porte = null;
        Scanner sc = null;
        boolean escrito = true;
        try {
            sc = new Scanner(new FileReader(fichero));
            String linea;

            while(sc.hasNextLine() && escrito){
                linea = sc.nextLine();
                String[] datos = linea.split(";");
                String ID = datos[0];
                Nave nave = naves.buscarNave(datos[1]);
                PuertoEspacial origen = puertosEspaciales.buscarPuertoEspacial(datos[2]);
                int muelleOrigen = Integer.parseInt(datos[3]);
                Fecha salida = Fecha.fromString(datos[4]);;
                PuertoEspacial destino = puertosEspaciales.buscarPuertoEspacial(datos[5]);
                int muelleDestino = Integer.parseInt(datos[6]);
                Fecha llegada = Fecha.fromString(datos[7]);
                double precio = Double.parseDouble(datos[8]);
                porte = new Porte(ID,nave,origen,muelleOrigen,salida,destino,muelleDestino,llegada,precio);
                escrito = listaPortes.insertarPorte(porte);
            }
        } catch (Exception e) {
            System.out.println("Fichero Portes no encontrado.");
            return null;
        }
        return listaPortes;
    }
}

