package src;

import java.io.*;
import java.util.Scanner;

/**
 * Clase que representa una lista de clientes. Contiene métodos para gestionar y manipular
 * clientes, como la inserción, búsqueda, eliminación y visualización de los mismos.
 * Además, permite realizar operaciones de lectura y escritura desde y hacia ficheros CSV.
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad Capacidad inicial de la lista de clientes
     */
    public ListaClientes(int capacidad) {
    clientes = new Cliente[capacidad];
    }

    // TODO: Devuelve el número de clientes que hay en la lista de clientes
    /**
     * @return Número de clientes en la lista
     */
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i< clientes.length; i++){
            if (clientes[i]!= null){
                ocupacion++;
            }
        }
    return ocupacion;
    }

    // TODO: ¿Está llena la lista de clientes?
    /**
     * @return True si la lista está llena, false si hay espacio disponible
     */
    public boolean estaLlena() {
        boolean llena = true;
        int i = 0;
        while (llena && (i < clientes.length)) {
            if (clientes[i] == null) {
                llena = false;
            }
            i++;
        }
        return llena;
    }

    // TODO: Devuelve el cliente dada el indice
    /**
     * @param i Índice del cliente
     * @return Cliente en la posición indicada
     */
    public Cliente getCliente(int i) {
        return clientes[i];
    }

    /**
     * Devuelve la longitud máxima de la lista de clientes
     *
     * @return Longitud máxima de la lista de clientes
     */
    public int getLength(){
        return clientes.length;
    }

    // TODO: Inserta el cliente en la lista de clientes
    /**
     * @param cliente Cliente a ser insertado
     * @return True si se añade correctamente, false si la lista está llena o hay un error
     */
    public boolean insertarCliente(Cliente cliente) {
        boolean esPosible = false;
        int i = 0;
        if (!estaLlena()){
            while (i<clientes.length && clientes[i]!=null){
                i++;
            }
            clientes[i] = cliente;
            esPosible = true;
        }
        return esPosible;
    }

    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    /**
     * @param email Email del cliente a buscar
     * @return El cliente encontrado o null si no existe
     */
    public Cliente buscarClienteEmail(String email) {
        Cliente cliente = null;
        for (int i = 0; i < clientes.length; i++){
            if (clientes[i] != null){
                if (clientes[i].getEmail().equalsIgnoreCase(email)){
                    cliente = clientes[i];
                }
            }
        }
        return cliente;
    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     *
     * @param teclado Scanner para la entrada de texto
     * @param mensaje Mensaje de solicitud para el email
     * @return El cliente seleccionado o null si se cancela la operación
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        String email;
        do {
            email = Utilidades.leerCadena(teclado, mensaje);
            if (!email.equalsIgnoreCase("cancelar")) {
                cliente = buscarClienteEmail(email);
                if (cliente == null) {
                    System.out.println("Email no encontrado");
                }
            }
        }while (cliente == null && !email.equalsIgnoreCase("cancelar"));
        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     *
     * @param fichero Nombre del fichero CSV
     * @return True si se realiza la operación correctamente, false en caso contrario
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (int i = 0; i < getOcupacion(); i++){
                Cliente cliente = getCliente(i);
                pw.print(cliente.getNombre() + ";");
                pw.print(cliente.getApellidos() + ";");
                pw.println(cliente.getEmail());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Fichero Clientes no encontrado.");
            return false;
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /**
     * TODO: Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     *
     * @param fichero              Nombre del fichero CSV que contiene la información de los clientes
     * @param capacidad            Capacidad de la lista de clientes
     * @param maxEnviosPorCliente  Número máximo de envíos por cliente
     * @return Lista de clientes generada a partir del fichero CSV
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        BufferedReader in = null;
        boolean escrito = true;
        try {
            in = new BufferedReader(new FileReader(fichero));
            String linea;

            while ((linea = in.readLine()) != null && escrito){
                String[] datos = linea.split(";");
                String nombre = datos[0];
                String apellidos = datos[1];
                String email= datos[2];
                Cliente cliente = new Cliente(nombre, apellidos, email, maxEnviosPorCliente);
                escrito= listaClientes.insertarCliente(cliente);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero Clientes no encontrado.");
            return null;
        } catch (IOException ex) {
            System.out.println("Error de lectura en fichero Clientes.");
            return null;
        } finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("Error de cierre de fichero Cliente.");
            }
        }
        return listaClientes;
    }
}
