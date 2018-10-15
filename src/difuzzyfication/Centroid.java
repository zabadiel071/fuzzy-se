package difuzzyfication;

import files.Constants;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.LabelRange;
import fuzzyfication.models.Line;

import java.util.ArrayList;

/**
 * Utiliza la variable de salida para el calculo del centroide
 */
public class Centroid {

    private ArrayList<Float> results;
    float x = 0;
    float area = 0;
    float muAcum = 0;

    public Centroid(ArrayList<Float> results) {
        this.results = results;
    }

    public float getCentroid(){
        if (results.size() != Constants.OUTPUT_VARIABLE.getLabels().size()){
            throw new IllegalArgumentException("Deben ser del mismo tamaño");
        }
        return calculate();
    }

    private float calculate() {
        FuzzyVariable fuzzyVariable= Constants.OUTPUT_VARIABLE;

        while (x <= 100){
            for (Float f: results){
                evaluate(f, fuzzyVariable);
            }
        }

        return area / muAcum;
    }

    private float evaluate(Float f, FuzzyVariable fuzzyVariable) {
        for (Label l : fuzzyVariable.getLabels()){
            x = l.getRange().getxMin();
            if (isLabelValuable(x, l.getRange())){
                ArrayList<Line> lines = l.getLines();
                for (Line line : lines){
                    while (isLineValuable(x, line)){
                        float auxMu =  line.getMuValue(x);
                        if (auxMu > f){
                            auxMu = f;
                        }
                        area += x * auxMu;
                        muAcum += auxMu;
                        x += 0.01;
                    }
                }
            }
        }
        return 0;
    }


    /**
     * Verifica si un valor X se puede valuar en un rango de etiqueta
     * @param x : Valor a probar
     * @param range : LabelRange en el que se busca valuar
     * @return boolean : Si entra o no
     */
    private boolean isLabelValuable(float x, LabelRange range){
        return range.getxMin() <= x && x <= range.getxMax();
    }

    /**
     * Verifica si se puede valuar X en una recta
     * @param x : Valor a probar
     * @param line : Recta en la que busca valuar
     * @return boolean : Si se puede o no valuar
     */
    private boolean isLineValuable(float x , Line line){
        return line.getP1().getX() <= x && x <= line.getP2().getX();
    }

}
