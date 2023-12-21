package src;

import static java.lang.Math.*;

/**
 * La clase PuertoEspacial representa un puerto espacial con características como nombre, código, coordenadas
 * esféricas (radio, azimut, polar) y número de muelles. Proporciona métodos para obtener y calcular información
 * sobre el puerto, como la distancia a otro puerto espacial y la representación en formato de cadena.
 *
 * @author Paula Cabrero
 * @author Alejandro Fernández
 * @version     21.0
 *  */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;
    private double azimut;
    private double polar;
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre     Nombre del puerto espacial
     * @param codigo     Código identificador del puerto espacial
     * @param radio      Radio del puerto espacial en coordenadas esféricas
     * @param azimut     Ángulo azimutal del puerto espacial en coordenadas esféricas
     * @param polar      Ángulo polar del puerto espacial en coordenadas esféricas
     * @param numMuelles Número de muelles disponibles en el puerto espacial
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    /**
     * Obtiene el nombre del puerto espacial
     *
     * @return Nombre del puerto espacial
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el código identificador del puerto espacial
     *
     * @return Código identificador del puerto espacial
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el radio del puerto espacial en coordenadas esféricas
     *
     * @return Radio del puerto espacial
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Obtiene el ángulo azimutal del puerto espacial en coordenadas esféricas
     *
     * @return Ángulo azimutal del puerto espacial
     */
    public double getAzimut() {
        return azimut;
    }

    /**
     * Obtiene el ángulo polar del puerto espacial en coordenadas esféricas
     *
     * @return Ángulo polar del puerto espacial
     */
    public double getPolar() {
        return polar;
    }

    /**
     * Obtiene el número de muelles disponibles en el puerto espacial
     *
     * @return Número de muelles en el puerto espacial
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino Puerto espacial destino
     * @return Distancia entre los dos puertos espaciales
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        double distancia = 0;
        double x1 = getRadio() * sin(getAzimut()) * cos(getPolar());
        double y1 = getRadio() * sin(getAzimut()) * sin(getPolar());
        double z1 = getRadio() * cos(getAzimut());
        double x2 = destino.getRadio() * sin(destino.getAzimut()) * cos(destino.getPolar());
        double y2 = destino.getRadio() * sin(destino.getAzimut()) * sin(destino.getPolar());
        double z2 = destino.getRadio() * cos(destino.getAzimut());
        distancia = Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        return distancia;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return nombre+" ("+codigo+"), en ("+radio+" "+azimut+" "+polar+"), con "+numMuelles+" muelles (Radio, Azimut, Polar)";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return nombre+" ("+codigo+")";
    }
}

