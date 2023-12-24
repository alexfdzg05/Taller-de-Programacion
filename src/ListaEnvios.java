package src;
import java.io.*;
import java.util.Scanner;

/**
 * Clase que representa una lista de envíos. Contiene métodos para gestionar y manipular
 * envíos, como la inserción, búsqueda, eliminación y visualización de los mismos.
 * Además, permite realizar operaciones de lectura y escritura desde y hacia ficheros CSV.
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
 */

public class ListaEnvios {
    private Envio[] envios;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad inicial de la lista de envíos
     */
    public ListaEnvios(int capacidad) {
        envios = new Envio[capacidad];
    }

    // TODO: Devuelve el número de envíos que hay en la lista
    /**
     * @return número de envíos en la lista
     */
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    /**
     * Devuelve la longitud máxima de la lista de envíos
     *
     * @return longitud máxima de la lista de envíos
     */
    public int getLength(){
        return envios.length;
    }

    // TODO: ¿Está llena la lista de envíos?
    /**
     * @return true si la lista está llena, false si hay espacio disponible.
     */
    public boolean estaLlena() {
        boolean llena = true;
        int i = 0;
        while (llena && (i < envios.length)) {
            if (envios[i] == null) {
                llena = false;
            }
            i++;
        }
        return llena;
    }

    //TODO: Devuelve el envio dado un indice
    /**
     * @param i Índice del envío
     * @return Envío en la posición indicada
     */
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     *
     * @param envio Envío a ser insertado
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        boolean esPosible = false;
        int i = 0;
        if (!estaLlena()) {
            while (i < envios.length && envios[i] != null) {
                i++;
            }
            envios[i] = envio;
            esPosible = true;
        }
        return esPosible;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     *
     * @param localizador Localizador del envío a buscar
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio envio = null;
        for (int i = 0; i < envios.length; i++){
            if (envios[i]!=null){
                if (envios[i].getLocalizador().equalsIgnoreCase(localizador)){
                    envio = envios[i];
                }
            }
        }
        return envio;
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     *
     * @param idPorte Identificador del porte asociado al envío
     * @param fila    Fila del envío
     * @param columna Columna del envío
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        Porte porte = null;
        Envio envio = null;
        for (int i = 0; i < envios.length; i++){
            if (envios[i].getPorte() != null){
                if (envios[i].getPorte().getID().equalsIgnoreCase(idPorte)){
                    porte = envios[i].getPorte();
                }
            }
        }
        if (porte != null){
            envio = porte.buscarEnvio(fila, columna);
        }
        return envio;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     *
     * @param localizador Localizador del envío a eliminar
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio(String localizador) {
    boolean cancelado = false;
        for (int i = 0; i < envios.length; i++){
            if (envios[i]!=null){
                if (envios[i].getLocalizador().equalsIgnoreCase(localizador)){
                    envios[i] = null;
                    cancelado = true;
                }
            }
        }

        return cancelado;
    }

    /**
     * TODO: Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null) {
                System.out.println(envios[i].toString());
            }
        }
    }

    /**
     * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     *
     * @param teclado Scanner para la entrada de texto
     * @param mensaje Mensaje de solicitud para el localizador
     * @return El envío seleccionado o null si se cancela la operación
     */
    public Envio seleccionarEnvio (Scanner teclado, String mensaje){
        Envio envio = null;
        String localizador;
        do {
            localizador = Utilidades.leerCadena(teclado, mensaje);
            if (!localizador.equalsIgnoreCase("cancelar")) {
                envio = buscarEnvio(localizador);
                if (envio == null) {
                    System.out.println("Localizador incorrecto: ");
                }
            }
        }while (envio == null && !localizador.equalsIgnoreCase("cancelar"));
        return envio;
    }


    /**
     * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     *
     * @param fichero Nombre del fichero CSV
     * @return True si se realiza la operación correctamente, false en caso contrario
     */
    public boolean aniadirEnviosCsv (String fichero){
        PrintWriter pw = null;
        FileWriter fw;
        try {
            pw = new PrintWriter(fichero);
            pw.println();
            for (int i = 0; i < envios.length; i++){
                if (envios[i]!= null) {
                    pw.print(envios[i].getLocalizador());
                    pw.print(";");
                    pw.print(envios[i].getPorte().getID());
                    pw.print(";");
                    pw.print(envios[i].getCliente().getEmail());
                    pw.print(";");
                    pw.print(envios[i].getFila());
                    pw.print(";");
                    pw.print(envios[i].getColumna());
                    pw.print(";");
                    pw.print(envios[i].getPrecio());
                    pw.println();
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }catch (IOException e){
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios Nombre del fichero CSV que contiene la información de los envíos
     * @param portes        Lista de portes asociada a los envíos
     * @param clientes      Lista de clientes asociada a los envíos
     */
    public static void leerEnviosCsv (String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        ListaEnvios listaEnvios = new ListaEnvios(clientes.getLength());
        Scanner sc = null;
        boolean escrito = true;
        try {
            sc = new Scanner(new FileReader(ficheroEnvios));
            String linea;

            while (sc.hasNextLine() && escrito) {
                linea = sc.nextLine();
                String[] datos = linea.split(";");
                String localizador = datos[0];
                Porte porte = portes.buscarPorte(datos[1]);
                Cliente cliente = clientes.buscarClienteEmail(datos[2]);
                int fila = Integer.parseInt(datos[3]);
                int columna = Integer.parseInt(datos[4]);
                double precio = Double.parseDouble(datos[5]);
                Envio envio = new Envio(localizador, porte, cliente, fila, columna, precio);
                if (porte != null) {
                    porte.ocuparHueco(envio);
                }

                if (cliente != null) {
                    cliente.aniadirEnvio(envio);
                }
                escrito = listaEnvios.insertarEnvio(envio);//HERE revisar el ocuparHueco

            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de envíos");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

}



