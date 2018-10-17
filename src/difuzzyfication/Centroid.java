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

        int indexCut = 0;
        float muVal = 0;
        float muAccumulator = 0;

        float muCalculated;
        float area = 0;
        float x = 0;

        int indexLabel = 0;
        Label label = labels.get(indexLabel);
        while (x<100) {
            if (isLabelValuable(x,label.getRange())){
                for (Line l: label.getLines() ) {
                    if (isLineValuable(x, l)){
                        muCalculated = l.getMuValue(x);
                        if (muCalculated < muCuts[indexCut])
                            muVal = muCalculated;
                        else
                            muVal = muCuts[indexCut];

                        //En caso de que el valor de la etiqueta siguiente sea mayor, es decir,
                        // el nuevo corte haya sido más grande
                        try {
                            float nextMu = testNextLabel(x,labels.get(indexLabel+1), indexCut);
                            if ( nextMu > muVal){
                                indexLabel++;
                                indexCut++;
                                label = labels.get(indexLabel);
                                break;
                            }
                        }catch (IndexOutOfBoundsException e){  }

                        area += muVal * x;
                        muAccumulator += muVal;
                        x += 0.1;
                        break;
                    }
                }

            }else {
                indexLabel++;
                indexCut++;
                label = labels.get(indexLabel);
            }
            //System.out.println(area);
        }

        return area / muAccumulator;
    }

    private float testNextLabel(float x, Label label, int indexCut) throws IndexOutOfBoundsException {
        float muCalculated;
        if (isLabelValuable(x, label.getRange())){
            for (Line l : label.getLines()){
                if (isLineValuable(x, l)){
                    muCalculated = l.getMuValue(x);
                    if (muCalculated < muCuts[indexCut + 1])
                        return muCalculated;
                    else
                        return muCuts[indexCut + 1];

                }
            }
        }
            return -1;
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
