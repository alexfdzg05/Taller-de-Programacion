package src;

import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo el valor mínimo que se tiene que encontrar el numero
     * @param maximo el valor máximo que se tiene que encontrar el numero
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do{
            System.out.println(mensaje);
            numero=teclado.nextInt();
        }while (!(numero >= minimo && numero <= maximo));
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo  el valor mínimo que se tiene que encontrar el numero
     * @param maximo el valor máximo que se tiene que encontrar el numero
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        System.out.println(mensaje);
        long numero = teclado.nextLong();
        teclado.nextLine();
        while (!(numero >= minimo && numero <= maximo)) {
            System.out.println(mensaje);
            numero = teclado.nextLong();
            teclado.nextLine();
        }
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo el valor mínimo que se tiene que encontrar el numero
     * @param maximo el valor máximo que se tiene que encontrar el numero
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        System.out.println(mensaje);
        double numero = teclado.nextDouble();
        teclado.nextLine();
        while (!(numero >= minimo && numero <= maximo)) {
            System.out.println(mensaje);
            numero = teclado.nextDouble();
            teclado.nextLine();
        }
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo el valor mínimo que se tiene que encontrar la letra
     * @param maximo el valor máximo que se tiene que encontrar la letra
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        do{
            System.out.println(mensaje);
            letra=teclado.next().charAt(0);
        }while (!(letra<=maximo && letra>=minimo));
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia, mes, anio;
        boolean correcto = false;
        Fecha fecha = null;
        System.out.println(mensaje);
        do {
            String mensajeDias = "Ingrese día: ";
            dia = leerNumero(teclado, mensajeDias, 1, Fecha.DIAS_MES);
            String mensajeMes = "Ingrese mes: ";
            mes = leerNumero(teclado, mensajeMes, 1, Fecha.MESES_ANIO);
            String mensajeAnio = "Ingrese año: ";
            anio = leerNumero(teclado, mensajeAnio, Fecha.PRIMER_ANIO, Fecha.ULTIMO_ANIO);
            if(Fecha.comprobarFecha(dia,mes,anio)){
                correcto=true;
            }else {
                System.out.println("Fecha introducida incorrecta.");
            }
        } while (!correcto);
        if (!Fecha.comprobarFecha(fecha.getDia(), fecha.getMes(), fecha.getAnio())) {
            // La fecha es incorrecta, se lanza una excepción o se devuelve null
            throw new IllegalArgumentException("Fecha introducida incorrecta.");
            // O bien: fecha = null;
        }
        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia, mes, anio, hora, minuto, segundo;
        Fecha fecha ;
        boolean correcto = false;
        System.out.println(mensaje);
        do {
            String mensajeDias = "Ingrese día: ";
            dia = leerNumero(teclado, mensajeDias, 1, Fecha.DIAS_MES);
            String mensajeMes = "Ingrese mes: ";
            mes = leerNumero(teclado, mensajeMes, 1, Fecha.MESES_ANIO);
            String mensajeAnio = "Ingrese año: ";
            anio = leerNumero(teclado, mensajeAnio, Fecha.PRIMER_ANIO, Fecha.ULTIMO_ANIO);
            String mensajeHora = "Ingrese hora: ";
            hora = leerNumero(teclado, mensajeHora, 0, Fecha.HORAS_DIA);
            String mensajeMinuto = "Ingrese minuto: ";
            minuto = leerNumero(teclado, mensajeMinuto, 0, Fecha.MINUTOS_HORA);
            String mensajeSegundo = "Ingrese segundo: ";
            segundo = leerNumero(teclado, mensajeSegundo, 0, Fecha.SEGUNDOS_MINUTO);

            if(Fecha.comprobarFecha(dia,mes,anio) && Fecha.comprobarHora(hora,minuto,segundo)){
                correcto=true;
            }else {
                System.out.println("Fecha u hora introducida incorrecta.");
            }

        } while (!correcto);
        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}

