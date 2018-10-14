package fuzzyfication.models;

import java.util.ArrayList;

/**
 * Representa el resultado de la fusificación de una variable
 */
public class FuzzyResult {

    /**
     * Nombre de la variable para la cual se realizo la fusificación
     */
    private String variableName;

    /**
     * Resultados obtenidos por etiqueta probada
     * Pueden ser 2 o más etiquetas en las que se encuentra
     */
    private ArrayList<LabelResult> results;

    public FuzzyResult(String variableName, ArrayList<LabelResult> results) {
        this.variableName = variableName;
        this.results = results;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public ArrayList<LabelResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<LabelResult> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "FuzzyResult{" +
                "variableName='" + variableName + '\'' +
                ", results=" + results +
                '}';
    }
}
