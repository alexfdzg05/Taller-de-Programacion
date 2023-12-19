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
public class Envio {
    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador
     * @param porte
     * @param cliente
     * @param fila
     * @param columna
     * @param precio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }
    public String getLocalizador() {
        return localizador;
    }
    public Porte getPorte() {
        return porte;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    // TODO: Ejemplos: "1A" para el hueco con fila 1 y columna 1, "3D" para el hueco con fila 3 y columna 4
    public String getHueco() {
        char letraComienzo = 'A';
        char columna = (char) (letraComienzo + getColumna() - 1);
        char fila = (char) getFila();
        String hueco = String.valueOf(fila);
        hueco += String.valueOf(columna);
        return hueco;
    }
    public double getPrecio() {
        return precio;
    }
    //TODO: Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
    public String toString() {
    return "Envío "+getPorte()+" para "+getPorte().toStringSimple()+" en hueco "+getFila()+" "+getColumna()+" por "+ getPrecio()+"SSD";
    }
    // TODO: Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
    public boolean cancelar() {
        String id = getLocalizador();
        porte.desocuparHueco(id);
        cliente.cancelarEnvio(id);
        return true;
    }

    /**
     * TODO: Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero
     * @return Devuelve la información con el siguiente formato como ejemplo ->
     *     -----------------------------------------------------
     *     --------- Factura del envío PM1111AAAABBBBC ---------
     *     -----------------------------------------------------
     *     Porte: PM0066
     *     Origen: Gaia Galactic Terminal (GGT) M5
     *     Destino: Cidonia (CID) M1
     *     Salida: 01/01/2023 08:15:00
     *     Llegada: 01/01/2024 11:00:05
     *     Cliente: Zapp Brannigan, zapp.brannigan@dop.gov
     *     Hueco: 6C
     *     Precio: 13424,56 SSD
     */
    public boolean generarFactura(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            pw.println("-----------------------------------------------------");
            pw.println("--------- Factura del envío " + getLocalizador() + " ---------");
            pw.println("-----------------------------------------------------");
            pw.println("Porte: " + getPorte().getID());
            pw.println("Origen: " + getPorte().getOrigen().toString());
            pw.println("Destino: " + getPorte().getDestino().toString());
            pw.println("Salida: " + getPorte().getSalida().toString());
            pw.println("Llegada: " + getPorte().getLlegada().toString());
            pw.println("Cliente: " + getCliente().toString());
            pw.println("Hueco: " + getFila() + getHueco());
            pw.println("Precio: " + getPrecio() + " SSD");
            pw.println();

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
     *	TODO: Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
     *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand
     * @param idPorte
     * @return
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        for(int i = 0; i < 9; i++){
            char randomChar = (char) (rand.nextInt(26) + 'A');
            localizador.append(randomChar);
        }
        return localizador.toString();
    }


    /**
     * TODO: Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param rand
     * @param porte
     * @param cliente
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        int fila = (int) Utilidades.leerNumero(teclado, "Fila del hueco: ", 0);
        int columna = (int) Utilidades.leerNumero(teclado, "Columna del hueco: ", 0);
        double precio = Utilidades.leerNumero(teclado, "Precio: ", 0.0);
        String id = generarLocalizador(rand, porte.getID());
        System.out.println("\t Envío "+id+" creado correctamente");
        Envio envio = new Envio(id, porte, cliente, fila, columna, precio);
        porte.ocuparHueco(envio);
        return envio;
    }
}