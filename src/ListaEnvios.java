package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaEnvios {
    private Envio[] envios;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaEnvios(int capacidad) {
        envios = new Envio[capacidad];
    }

    // TODO: Devuelve el número de envíos que hay en la lista
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i < envios.length; i++) {
            if (envios[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista de envíos?
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
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * TODO: insertamos un nuevo envío en la lista
     *
     * @param envio
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
        }
        return esPosible;
    }

    /**
     * TODO: Buscamos el envio a partir del localizador pasado como parámetro
     *
     * @param localizador
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        int i = 0;
        while ((!envios[i].getLocalizador().equalsIgnoreCase(localizador)) && i < envios.length) {
            i++;
        }
        if (envios[i].getLocalizador().equalsIgnoreCase(localizador)) {
            return envios[i];
        } else {
            return null;
        }
    }

    /**
     * TODO: Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     *
     * @param idPorte
     * @param fila
     * @param columna
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {


        return null;
    }

    /**
     * TODO: Eliminamos un envio a través del localizador pasado por parámetro
     *
     * @param localizador
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio(String localizador) {
    boolean cancelado = false;
        int i = 0;
        while ((!envios[i].getLocalizador().equalsIgnoreCase(localizador)) && i < envios.length) {
            i++;
        }
        if (envios[i].getLocalizador().equalsIgnoreCase(localizador)) {
            envios[i] = null;
            cancelado = true;
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
                System.out.print(envios[i].getLocalizador() + ";" + envios[i].getPorte().getID() + ";" + envios[i].getCliente().getEmail()
                        + ";" + envios[i].getFila() + ";" + envios[i].getColumna() + ";" + envios[i].getPrecio());
            }
        }
    }

        /**
         * TODO: Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
         *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
         *  La función solicita repetidamente hasta que se introduzca un localizador correcto
         * @param teclado
         * @param mensaje
         * @return
         */
        public Envio seleccionarEnvio (Scanner teclado, String mensaje){
            Envio envio = null;


            return envio;
        }


        /**
         * TODO: Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
         * @param fichero
         * @return
         */
        public boolean aniadirEnviosCsv (String fichero){
            PrintWriter pw = null;
            try {

                return true;
            } catch (Exception e) {
                return false;
            } finally {

            }
        }

        /**
         * TODO: Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
         * @param ficheroEnvios
         * @param portes
         * @param clientes
         */
        public static void leerEnviosCsv (String ficheroEnvios, ListaPortes portes, ListaClientes clientes){
            Scanner sc = null;
            try {

            } catch (FileNotFoundException e) {
                System.out.println("No se ha encontrado el fichero de envíos");
            } finally {

            }
        }
    }
}


