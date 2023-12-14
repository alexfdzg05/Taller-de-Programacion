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
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];
    }

    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        int ocupacion = 0;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                ocupacion++;
            }
        }
        return ocupacion;
    }

    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean llena = true;
        int i = 0;
        while (llena && (i < lista.length)) {
            if (lista[i] == null) {
                llena = false;
            } else {
                i++;
            }
        }
        return llena;
    }

    // TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     *
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean esPosible = false;
        int i = 0;
        if (!estaLlena()) {
            while (i < lista.length && lista[i] != null) {
                i++;
            }
            lista[i] = puertoEspacial;
            esPosible = true;
        }
        return esPosible;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     *
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        int i = 0;
        while (!lista[i].getCodigo().equalsIgnoreCase(codigo) && i < lista.length) {
            i++;
        }
        if (lista[i].getCodigo().equalsIgnoreCase(codigo)) {
            return lista[i];
        } else {
            return null;
        }

    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     *
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        String codigo;
        do {
            System.out.println(mensaje);
            codigo = teclado.nextLine();
            if (!codigo.equalsIgnoreCase("cancelar")) {
                puertoEspacial = buscarPuertoEspacial(codigo);
            }
        } while (puertoEspacial == null && !codigo.equalsIgnoreCase("cancelar"));
        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     *
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nombre);
            for (int i = 0; i < getOcupacion(); i++) {
                PuertoEspacial puertoEspacial = getPuertoEspacial(i);
                pw.print(puertoEspacial.getNombre() + ";");
                pw.print(puertoEspacial.getCodigo() + ";");
                pw.print(puertoEspacial.getRadio() + ";");
                pw.print(puertoEspacial.getAzimut() + ";");
                pw.print(puertoEspacial.getPolar() + ";");
                pw.print(puertoEspacial.getNombre());
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error de escritura en fichero PuertosEspaciales.");
            return false;
        } finally {
            pw.close();
        }
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     *
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        boolean escrito = true;
        try {
            sc = new Scanner(new FileReader(fichero));
            String linea;

            while (sc.hasNextLine() && escrito) {
                linea = sc.nextLine();
                String[] datos = linea.split(";");
                String nombre = datos[0];
                String codigo = datos[1];
                double radio = Double.parseDouble(datos[2]);
                double azimut = Double.parseDouble(datos[3]);
                double polar = Double.parseDouble(datos[4]);
                int numMuelles = Integer.parseInt(datos[5]);

                PuertoEspacial puertoEspacial = new PuertoEspacial(nombre, codigo, radio, azimut, polar, numMuelles);

                escrito = listaPuertosEspaciales.insertarPuertoEspacial(puertoEspacial);

            }
        } catch (Exception e) {
            System.out.println("Fichero PuertosEspaciales no encontrado.");
            return null;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return listaPuertosEspaciales;
    }
}
