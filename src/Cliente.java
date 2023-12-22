package src;

import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Taller de Programación
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

    /**
     * Obtiene el nombre del cliente
     *
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos del cliente
     *
     * @return Apellidos del cliente
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene el email del cliente
     *
     * @return Email del cliente
     */
    public String getEmail() {
        return email;
    }

    // TODO: Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
    /**
     * @return Cadena que representa al cliente
     */
    public String toString() {
    return getNombre()+" "+getApellidos()+", "+getEmail();
    }

    // TODO: Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
    /**
     * @return true si el número máximo de envíos se ha alcanzado, false en caso contrario
     */
    public boolean maxEnviosAlcanzado() {
    return listaEnvios.estaLlena();
    }

    // TODO: Devuelve un envío en función de su posición
    /**
     * @param i Índice de la posición del envío
     * @return Objeto Envio correspondiente a la posición especificada
     */
    public Envio getEnvio(int i) {
    return listaEnvios.getEnvio(i);
    }

    /**
     * Obtiene la lista de envíos asociada al cliente
     *
     * @return Lista de envíos del cliente
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    // TODO: Añade un envío al cliente
    /**
     * @param envio Objeto Envio a añadir a la lista
     * @return true si se añadió correctamente, false si la lista está llena
     */
    public boolean aniadirEnvio(Envio envio) {
        return listaEnvios.insertarEnvio(envio);
    }

    /**
     * Busca y devuelve un envío de la lista del cliente dado su localizador
     *
     * @param localizador Localizador del envío a buscar
     * @return Objeto Envio correspondiente al localizador especificado o null si no se encuentra
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    // TODO: Elimina el envío de la lista de envíos del pasajero
    /**
     * @param localizador Localizador del envío a eliminar
     * @return true si se eliminó correctamente, false si el envío no se encontró en la lista
     */
    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     * Muestra por pantalla los envíos de la lista, con el formato especificado en el enunciado
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    /**
     * Permite seleccionar un envío existente a partir de su localizador
     *
     * @param teclado Scanner para la entrada de texto
     * @param mensaje Mensaje de solicitud para el localizador
     * @return El envío seleccionado o null si se cancela la operación
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }

    /**
     * TODO: Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado Scanner para entrada estándar
     * @param clientes Lista de clientes existentes
     * @param maxEnvios Número máximo de envíos permitidos para el cliente
     * @return src.Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        String nombre = "", apellidos = "", email = "";
        Cliente cliente;
        do{
            nombre = Utilidades.leerCadena(teclado,"Nombre: ");
        } while(!correctoNomApellidos(nombre, "Por favor, introduzca un nombre correcto") && !nombre.equalsIgnoreCase("cancelar"));

        if(!nombre.equalsIgnoreCase("cancelar")){
            do{
                apellidos = Utilidades.leerCadena(teclado, "Apellidos: ");
            } while(!correctoNomApellidos(apellidos, "Por favor, introduzca apellidos correcto") && !apellidos.equalsIgnoreCase("cancelar"));
        }

        if(!nombre.equalsIgnoreCase("cancelar") && !apellidos.equalsIgnoreCase("cancelar")){
            do{
                email = Utilidades.leerCadena(teclado, "Email: ");
            } while(!correctoEmail(email) && !email.equalsIgnoreCase("cancelar"));
        }

        if(!nombre.equalsIgnoreCase("cancelar") && !apellidos.equalsIgnoreCase("cancelar") && !email.equalsIgnoreCase("cancelar")){
            cliente = new Cliente(nombre,apellidos,email,maxEnvios);
            if (clientes.insertarCliente(cliente)){
                System.out.println("Cliente con " + cliente.getEmail() + " creado correctamente");
            }
        } else {
            cliente = null;
        }
        return cliente;
    }


    /**
     * TODO: Metodo para comprobar que el formato de email introducido sea correcto
     * @param email Email a verificar
     * @return true si el formato es correcto, false de lo contrario
     */
    public static boolean correctoEmail(String email) {
        boolean correcto = true;
        if(email.contains("@")){
            String[] partes = email.split("@");
            String parteNombre = partes[0];
            String parteEmail = partes[1];

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

            if(!correcto){
                System.out.println("El email " + email + " es incorrecto.");
            }
        } else if(email.equalsIgnoreCase("cancelar")){

        } else {
            correcto = false;
            System.out.println("El email " + email + " es incorrecto.");
        }


        return correcto;
    }

    //MÉTODOS AÑADIDOS
    /**
     * Comprobación de que las letras en la cadena son válidas (solo a-z y '.')
     *
     * @param parte Cadena a comprobar
     * @return true si las letras son válidas, false de lo contrario
     */
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

    /**
     * Comprobación de que la cadena no empiece ni termine por '.'
     *
     * @param parte Cadena a comprobar
     * @return true si la cadena es válida, false de lo contrario
     */
    private static boolean comprobacionComienzoTerminacion(String parte){
        boolean correcto = true;
        if(parte.endsWith(".") || parte.startsWith(".")){
            correcto = false;
        }
        return correcto;
    }

    /**
     * Comprobación de que la cadena termine en "alumnos.upm.es" o "upm.es"
     *
     * @param parte Cadena a comprobar
     * @return true si la cadena es válida, false de lo contrario
     */
    private static boolean comprobacionExtension(String parte){
        String alumnos = "alumnos.upm.es", upm = "upm.es";
        boolean correcto = true;
        if(!(parte.equals(alumnos) || parte.equals(upm))){
            correcto = false;
        }
        return correcto;
    }

    /**
     * Comprueba que el formato de nombres y apellidos sea correcto
     *
     * @param palabras  Cadena a comprobar (nombres o apellidos)
     * @param mensaje   Mensaje a mostrar si hay un error
     * @return true si el formato es correcto, false de lo contrario
     */
    public static boolean correctoNomApellidos(String palabras, String mensaje){
        int primeraLetra = 'A', ultimaLetra = 'z';
        boolean correcto = true;
        int i = 0;
        char caracter = ' ';
        char caracterAnterior = ' ';
        if(palabras == null || palabras.isEmpty()){
            correcto = false;
        }
        else{
            do {
                caracter = palabras.charAt(i);
                if(i > 0){
                    caracterAnterior = palabras.charAt(i-1);
                }
                if(!(caracter >= primeraLetra && caracter <= ultimaLetra) && caracter != ' '){
                    correcto = false;
                    if(!palabras.equalsIgnoreCase("cancelar")){
                        System.out.println("Error: solo puede contener letras y espacios");
                    }
                } else{
                    if(caracterAnterior == ' ' && !(caracter >= primeraLetra && caracter <= 'Z')){
                        correcto = false;
                        if(!palabras.equalsIgnoreCase("cancelar")){
                            System.out.println("Error: debe existir una mayúscula después de un espacio");
                        }

                    } else{
                        if(caracterAnterior != ' ' && (caracter >= primeraLetra && caracter <= 'Z')){
                            correcto = false;
                            if(!palabras.equalsIgnoreCase("cancelar")){
                                System.out.println("Error: las mayúsculas solo pueden estar después de los espacios");
                            }

                        }
                    }
                }
                i++;
            } while(correcto == true && i < palabras.length());
        }
        if(correcto == false && !palabras.equalsIgnoreCase("cancelar")){
            System.out.println(mensaje);
        }
        return correcto;
    }

}
