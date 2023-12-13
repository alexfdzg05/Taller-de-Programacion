package src;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author      Taller de Programación
 * @version     1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;
    //HERE Falta envíos


    /**
     * TODO: Rellene el constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
        //Planet Express
        this.listaPuertosEspaciales = new ListaPuertosEspaciales(maxPuertosEspaciales);
        this.listaNaves = new ListaNaves(maxNaves);
        this.listaClientes = new ListaClientes(maxClientes);
        this.listaPortes = new ListaPortes(maxPortes);
        // HERE Falta envíos
    }


    /**
     * TODO: Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
        listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos, maxPuertosEspaciales);
        listaNaves = ListaNaves.leerNavesCsv(ficheroNaves, maxNaves);
        listaPortes = ListaPortes.leerPortesCsv(ficheroPortes, maxPortes,listaPuertosEspaciales,listaNaves);
        listaClientes = ListaClientes.leerClientesCsv(ficheroClientes,maxClientes, maxEnviosPorCliente);
        ListaEnvios.leerEnviosCsv(ficheroEnvios, listaPortes, listaClientes); //HERE Falta envíos
    }


    /**
     * TODO: Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * @param ficheroPuertos
     * @param ficheroNaves
     * @param ficheroPortes
     * @param ficheroClientes
     * @param ficheroEnvios
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
    listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
    listaNaves.escribirNavesCsv(ficheroNaves);
    listaPortes.escribirPortesCsv(ficheroPortes);
    listaClientes.escribirClientesCsv(ficheroClientes);
    ListaEnvios listaEnvios = new ListaEnvios(maxClientes*maxEnviosPorCliente);
    listaEnvios.aniadirEnviosCsv(ficheroEnvios);
    // HERE FaltaEnvíos

    }
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * TODO: Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado
     * @return
     */
    public ListaPortes buscarPorte(Scanner teclado) {
    String codigoOrigen, codigoDestino;
    Fecha fecha;
    codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese codigo de puerto Origen:");
    codigoDestino = Utilidades.leerCadena(teclado, "Ingrese codigo de puerto Destino:");
    fecha = Utilidades.leerFecha(teclado, "Fecha de salida:");
        return listaPortes.buscarPortes(codigoOrigen, codigoDestino, fecha);
    }


    /**
     * TODO: Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado
     * @param rand
     * @param porte
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        if (porte != null) {
            Cliente cliente;
            char opcion;
            opcion = Utilidades.leerEleccion(teclado,  "(1) Contratar el Envío como un Cliente ya registrado +" +
                    "\n (2) contratar el Envío como un nuevo Cliente", '1','2');

            if (opcion == '1'){
                cliente = listaClientes.seleccionarCliente(teclado, "Email del cliente: ");
            } else {
                cliente = Cliente.altaCliente(teclado,listaClientes,maxEnviosPorCliente);
            }

            porte.imprimirMatrizHuecos();
            if (cliente != null) {
                Envio.altaEnvio(teclado, rand, porte, cliente);
            }
        }
    }


    /**
     * TODO Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("1. Alta de Porte\n2. Alta de Cliente\n3.Buscar Porte\n4. Mostrar envíos de un cliente\n5. Generar lista de envíos\n0. Salir");
        opcion = Utilidades.leerNumero(teclado, "Seleccione opción:", 0, 5);
        return opcion;
    }

    private void mensajeBienvenida(){
        System.out.println("Bienvenido a Planet Express");
    }

    /**
     * TODO: Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String[] args) {
        PlanetExpress planetExpress = new PlanetExpress(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        planetExpress.cargarDatos(args[5], args[6], args[7], args[8], args[9]);

        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }

        Random rand = new Random();
        Scanner teclado  = new Scanner(System.in);

        int opcion;
        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 1:     // TODO: Alta de Porte
                    if(!planetExpress.maxPortesAlcanzado()){
                        Porte.altaPorte(teclado, rand, planetExpress.listaPuertosEspaciales, planetExpress.listaNaves, planetExpress.listaPortes);
                    } else System.out.println("No se pueden dar de alta más portes");

                    break;
                case 2:     // TODO: Alta de Cliente
                    if(!planetExpress.maxPortesAlcanzado()){
                        Cliente cliente = Cliente.altaCliente(teclado, planetExpress.listaClientes, planetExpress.maxEnviosPorCliente); //here
                        if(planetExpress.insertarCliente(cliente)){
                            System.out.println("Cliente con " + cliente.getEmail() + " creado correctamente");
                        }
                    } else System.out.println("No se pueden dar de alta más clientes");

                    break;
                case 3:     // TODO: Buscar Porte
                    ListaPortes portes = planetExpress.buscarPorte(teclado);
                    portes.listarPortes();
                    Porte porte = portes.seleccionarPorte(teclado, "Seleccione un porte", "cancelar");
                    if (porte == null){
                        break;
                    } else {
                        //Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)? El valor de entrada debe ser 'n' o 'e'
                        char comprarBillete = Utilidades.leerEleccion(teclado, "¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?", 'n' , 'e');
                        if (comprarBillete == 'n'){
                            if(!planetExpress.maxPortesAlcanzado()){
                                Cliente cliente = Cliente.altaCliente(teclado, planetExpress.listaClientes, planetExpress.maxEnviosPorCliente); //here
                                if(planetExpress.insertarCliente(cliente)){
                                    System.out.println("Cliente con " + cliente.getEmail() + " creado correctamente");
                                    //Hasta aquí es igual que el altaCliente
                                    cliente.getListaEnvios().insertarEnvio(Envio.altaEnvio(teclado,rand,porte,cliente));
                                    //A partir de aquí es igual al altaCliente
                                }
                            } else System.out.println("No se pueden dar de alta más clientes");
                        } else {
                            Cliente cliente = planetExpress.listaClientes.seleccionarCliente(teclado, "Email del cliente:" );
                            //
                            cliente.getListaEnvios().insertarEnvio(Envio.altaEnvio(teclado,rand,porte,cliente));
                            //colocar lo mismo que lo que está entre comentarios
                        }
                    }

                    break;
                case 4:     // TODO: Listado de envíos de un cliente
                    Cliente cliente = planetExpress.listaClientes.seleccionarCliente(teclado, "Email del cliente: ");
                    Envio envio = cliente.seleccionarEnvio(teclado, "Seleccione un envío: ");
                    char accion = Utilidades.leerEleccion(teclado, "¿Cancelar envío (c), o generar factura (f)?", 'c', 'f');
                    if (accion == 'c'){
                        break;
                    } else {
                        String nombreFichero = Utilidades.leerCadena(teclado, "Nombre del fichero: ");
                        envio.generarFactura(nombreFichero);
                        System.out.println("\n \t Factura generada correctamente");
                    }
                    break;
                case 5:     // TODO: Lista de envíos de un porte
                    planetExpress.listaPortes.listarPortes();
                    Porte porte1 = planetExpress.listaPortes.seleccionarPorte(teclado, "Seleccione un porte: ", "cancelar");
                    String nombreFichero = Utilidades.leerCadena(teclado, "Nombre del fichero: ");
                    porte1.generarListaEnvios(nombreFichero);
                    System.out.println("\n\t Fichero creado correctamente");
                    break;
            }
        } while (opcion != 0);


    }
}
