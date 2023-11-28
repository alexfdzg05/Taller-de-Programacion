package src;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaNaves(int capacidad) {
    naves = new Nave[capacidad];
    }
    // TODO: Devuelve el número de naves que hay en la lista
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i< naves.length; i++){
            if (naves[i]!= null){
                ocupacion++;
            }
        }
        return ocupacion;
    }
    // TODO: ¿Está llena la lista de naves?
    public boolean estaLlena() {
        boolean llena = true;
        int i = 0;
        while (llena && (i < naves.length)) {
            if (naves[i] == null) {
                llena = false;
            }
            i++;
        }
        return llena;
    }
    // TODO: Devuelve nave dado un indice
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * TODO: insertamos una nueva nave en la lista
     * @param nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean esPosible = false;
        int i = 0;
        if (!estaLlena()){
            while (i<naves.length && naves[i]!=null){
                i++;
            }
            naves[i] = nave;
        }
        return esPosible;
    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        int i = 0;
        while (!naves[i].getMatricula().equalsIgnoreCase(matricula)&&i< naves.length){
            i++;
        }
        if (naves[i].getMatricula().equalsIgnoreCase(matricula)){
            return naves[i];
        } else {
            return null;
        }
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
        int i = 0;
    while (naves[i]!=null && i < naves.length){
        System.out.print(naves[i].getMarca()+";"+naves[i].getModelo()+";"+naves[i].getMatricula()
        +";"+naves[i].getFilas()+";"+naves[i].getColumnas());
        i++;
    }
    }



    /**
     * TODO: Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado
     * @param mensaje
     * @param alcance
     * @return
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave nave = null;


        return nave;
    }


    /**
     * TODO: Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre
     * @return
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }


    /**
     * TODO: Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {

        } catch (Exception e) {
            return null;
        } finally {

        }
        return listaNaves;
    }
}

