package difuzzyfication;

import files.Constants;
import fuzzyfication.models.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *  Clase para el cálculo del centroide
 *
 */
public class Centroid {

    /**
     * Valores de corte obtenidos como el máximo {bajo, medio, alto}
     */
    private float[] muCuts;

    /**
     *
     * @param muCuts : Valores en mu que se utilizan de "corte"
     */
    public Centroid(float[] muCuts) {
        this.muCuts = muCuts;
    }

    /**
     * Puerta de entrada al cálculo del centroide, en caso de que estén incorrectos los valores,
     * devuelve error
     * @return float : Valor del centroide calculado
     */
    public float getCentroid(){
        if (muCuts.length != Constants.OUTPUT_VARIABLE.getLabels().size())
            throw new IllegalArgumentException("Insuficientes valores de membresia para las etiquetas existentes");
        return calculate();
    }

    /**
     * Calculo principal del centroide
     * @return float : Valor encontrado para el centroide
     */
    private float calculate() {
        FuzzyVariable fuzzyVariable = Constants.OUTPUT_VARIABLE;
        ArrayList<Label> labels = fuzzyVariable.getLabels();

        Iterator<Label> labelIterator = labels.iterator();
        Label label = labelIterator.next();

        int indexCut = 0;
        float muVal = 0;
        float muAccumulator = 0;

        float muCalculated;
        float area = 0;

        float x = 0;
        while (x<100) {
            if (isLabelValuable(x,label.getRange())){
                for (Line l: label.getLines() ) {
                    if (isLineValuable(x, l)){
                        muCalculated = l.getMuValue(x);
                        if (muCalculated < muCuts[indexCut])
                            muVal = muCalculated;
                        else
                            muVal = muCuts[indexCut];

                        area += muVal * x;
                        muAccumulator += muVal;
                        break;
                    }
                }
            }else {
                label = labelIterator.next();
                indexCut++;
            }
            //System.out.println(area);
            x += 0.1;
        }

        return area / muAccumulator;
    }


    /**
     * Devuelve si se puede valuar un valor de x en una etiqueta
     * @param x : Valor a probar
     * @param range : Rango de valores que utiliza la etiqueta
     * @return boolean : Si es valuable o no
     */
    private boolean isLabelValuable(float x, LabelRange range) {
        return range.getxMin() <= x && x <= range.getxMax();
    }

    /**
     * Devuelve si se puede valuar un valor de x en una linea
     * @param x : Valor a probar
     * @param line : Linea en la que se quiere evaluar
     * @return boolean : Si es valuable o no
     */
    private boolean isLineValuable(float x , Line line){
        return line.getP1().getX() <= x && x <= line.getP2().getX();
    }
}
