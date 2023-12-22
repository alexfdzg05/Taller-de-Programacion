package src;

/**
 * Clase que representa una nave espacial (Nave)
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
 */
public class Nave {
    private String marca;
    private String modelo;
    private String matricula;
    private int columnas;
    private int filas;
    private double alcance;


    /**
     * Constructor of the class
     *
     * @param marca     Marca de la nave espacial
     * @param modelo    Modelo de la nave espacial
     * @param matricula Número de matrícula de la nave espacial
     * @param columnas  Número de columnas en la nave espacial
     * @param filas     Número de filas en la nave espacial
     * @param alcance   Alcance o rango de la nave espacial
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * Método getter para obtener la marca de la nave espacial
     *
     * @return La marca de la nave espacial
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método getter para obtener el modelo de la nave espacial
     *
     * @return El modelo de la nave espacial
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Método getter para obtener la matrícula de la nave espacial
     *
     * @return El número de matrícula de la nave espacial
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Método getter para obtener el número de columnas en la nave espacial
     *
     * @return El número de columnas en la nave espacial
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Método getter para obtener el número de filas en la nave espacial
     *
     * @return El número de filas en la nave espacial
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Método getter para obtener el alcance o rango de la nave espacial.
     *
     * @return El alcance o rango de la nave espacial.
     */
    public double getAlcance() {
        return alcance;
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return marca+" "+modelo+" ("+matricula+"): "+(filas*columnas)+" contenedores, hasta "+alcance+" UA";
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return marca+" ("+modelo+"-"+matricula+")";
    }
}

