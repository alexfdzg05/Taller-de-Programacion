package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            esPosible = true;
        }
        return esPosible;
    }
    /**
     * TODO: Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave nave = null;
        for (int i = 0; i < naves.length; i++){
            if (naves[i]!=null){
                if (naves[i].getMatricula().equalsIgnoreCase(matricula)){
                    nave = naves[i];
                }
            }
        }
        return nave;
    }
    // TODO: Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
    public void mostrarNaves() {
    for (int i = 0; i < naves.length; i++){
        if (naves[i]!=null) {
            System.out.println(naves[i].toString());
        }
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
        String matricula = Utilidades.leerCadena(teclado, mensaje);
        while (!matricula.equalsIgnoreCase("cancelar") && nave == null) {
            nave = buscarNave(matricula);
            if (nave == null){
                System.out.println("Nave no encontrada");
                matricula = Utilidades.leerCadena(teclado, mensaje);
            }else {
                if (nave.getAlcance() >= alcance){
                    System.out.println("Nave seleccionada con alcance suficiente");
                } else {
                    System.out.println("Nave seleccionada con alcance insuficiente");
                    nave = null;
                    matricula = Utilidades.leerCadena(teclado, mensaje);
                }
            }
        }
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
            pw = new PrintWriter(nombre);
            for (int i = 0; i < getOcupacion(); i++){
                Nave nave = getNave(i);
                pw.print(nave.getMarca() + ";");
                pw.print(nave.getModelo() + ";");
                pw.print(nave.getMatricula() + ";");
                pw.print(nave.getFilas() + ";");
                pw.print(nave.getColumnas() + ";");
                pw.print(nave.getAlcance());
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            pw.close();
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
        Nave nave;
        boolean escrito = true;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(fichero));
            String linea;
            while(sc.hasNextLine() && escrito){
                linea = sc.nextLine();
                String[] datos = linea.split(";");
                String marca = datos[0];
                String modelo = datos[1];
                String matricula = datos[2];
                int filas = Integer.parseInt(datos[3]);
                int columnas = Integer.parseInt(datos[4]);
                double alcance = Double.parseDouble(datos[5]);
                nave = new Nave(marca,modelo,matricula,columnas,filas,alcance);
                escrito = listaNaves.insertarNave(nave);
            }
        } catch (Exception e) {
            System.out.println("Fichero Naves no encontrado.");
            return null;
        } finally {
                if(sc != null){
                    sc.close();
                }
        }
        return listaNaves;
    }
}

