package src;

import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Taller de Programación
 * @author Paula Cabrero Carrillo
 * @author Alejandro Fernández Guerrero
 * @version     1.0
 */
public class Cliente {
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;

    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getEmail() {
        return email;
    }
    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    public String toString() {
        return nombre + " " + apellidos + ", " + email;
    }
    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }
    // TODO: Devuelve un envío en función de su posición
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }
    // TODO: Añade un envío al cliente
    public boolean aniadirEnvio(Envio envio) {
        boolean aniadido = false;
        if(!maxEnviosAlcanzado()){
            listaEnvios.insertarEnvio(envio);
            aniadido = true;
        }
        return aniadido;
    }
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }
    // TODO: Elimina el envío de la lista de envíos del pasajero
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }
    // Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado
     * @param clientes
     * @param maxEnvios
     * @return src.Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {




        return new Cliente(nombre, apellidos, email, maxEnvios);
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email
     * @return
     */
    public static boolean correctoEmail(String email) {
        String[] partes = email.split("@");
        String parteNombre = partes[0];
        String parteEmail = partes[1];
        boolean correcto = true;

        //Comprobación de que el correo upm no tiene mayúsculas, y solo tiene valores de a-z o "."
        boolean letraValidas = comprobacionLetrasValidas(parteNombre) && comprobacionLetrasValidas(parteEmail);

        //Comprobación no empiece ni termine por .
        boolean terminacionValida = comprobacionComienzoTerminacion(parteNombre);

        //Comprobación de que termine en alumnos.upm.es o upm.es
        boolean extensionValida = comprobacionExtension(parteEmail);

        //Comprueba las verificaciones como conjunto
        if(!letraValidas || !terminacionValida || !extensionValida){
            correcto = false;
        }
        return correcto;
    }

    //MÉTODOS AÑADIDOS
    private static boolean comprobacionLetrasValidas(String parte){
        int primeraLetra = 'a', ultimaLetra = 'z', punto = '.';
        char caracter = ' ';
        int i = 0;
        boolean correcto = true;
        if (parte == null || parte.isEmpty()){
            correcto = false;
        } else {
            do {
                caracter = parte.charAt(i);
                if(!(caracter >= primeraLetra && caracter <= ultimaLetra) && caracter != punto){
                    correcto = false;
                }
                i++;
            } while(correcto == true && i < parte.length());
        }
        return correcto;
    }

    private static boolean comprobacionComienzoTerminacion(String parte){
        boolean correcto = true;
        if(parte.endsWith(".") || parte.startsWith(".")){
            correcto = false;
        }
        return correcto;
    }

    private static boolean comprobacionExtension(String parte){
        String alumnos = "alumnos.upm.es", upm = "upm.es";
        boolean correcto = true;
        if(!(parte.equals(alumnos) || parte.equals(upm))){
            correcto = false;
        }
        return correcto;
    }
}
