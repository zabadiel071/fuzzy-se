package fuzzyfication.models;
import java.util.ArrayList;

/**
 * Representa una variable difusa
 */
public class FuzzyVariable {

    /**
     * Nombre de la variable difusa
     */
    private String name;

    /**
     * Etiquetas que contiene la variable difusa
     */
    private ArrayList<Label> labels;

    public FuzzyVariable(String name, ArrayList<Label> labels) {
        this.name = name;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "FuzzyVariable{" +
                "name='" + name + '\'' +
                ", labels=" + labels +
                '}';
    }
}
