package difuzzyfication;

import files.Constants;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Line;

import java.util.ArrayList;

/**
 * Utiliza la variable de salida para el calculo del centroide
 */
public class Centroid {

    private ArrayList<Float> results;

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
        ArrayList<Line> lines;

        return 0;
    }


}
