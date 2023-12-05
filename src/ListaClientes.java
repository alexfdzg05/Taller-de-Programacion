package src;

import java.io.*;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaClientes(int capacidad) {
    clientes = new Cliente[capacidad];
    }
    // TODO: Devuelve el número de clientes que hay en la lista de clientes
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
    public Cliente getCliente(int i) {
        return clientes[i];
    }
    // TODO: Inserta el cliente en la lista de clientes
    public boolean insertarCliente(Cliente cliente) {
    int i = 0;
    boolean insertado = false;
        while ((clientes[i]!=null)&&i< clientes.length){
            i++;
        }
        if (clientes[i]== null){
            clientes[i] = cliente;
            insertado = true;
        }
        return insertado;
    }
    // TODO: Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
    public Cliente buscarClienteEmail(String email) {
        int i = 0;
        while ((!clientes[i].getEmail().equalsIgnoreCase(email)) && i < clientes.length) {
            i++;
        }
        if (clientes[i].getEmail().equalsIgnoreCase(email)) {
            return clientes[i];
        } else {
            return null;
        }
    }

    /**
     * TODO: Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        do {
            System.out.println(mensaje);
            cliente = buscarClienteEmail(teclado.nextLine());
        }while (cliente == null);
        return cliente;
    }

    /**
     * TODO: Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero
     * @return
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
     * @param fichero
     * @param capacidad
     * @param maxEnviosPorCliente
     * @return lista de clientes
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
