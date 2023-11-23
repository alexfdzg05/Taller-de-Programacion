package src;

import java.util.Scanner;

/**
 * Description of the class
 *
 * @author Paula Cabrero
 * @author
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


}

