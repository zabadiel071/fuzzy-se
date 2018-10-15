package inference.models;

import java.util.ArrayList;

public class InferenceResult {

    /**
     *
     */
    private FamEntry famEntry;

    /**
     *
     */
    private ArrayList<Float> results;

    private float weight;

    public InferenceResult(FamEntry famEntry, ArrayList<Float> results, float weight) {
        this.famEntry = famEntry;
        this.results = results;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public FamEntry getFamEntry() {
        return famEntry;
    }

    public void setFamEntry(FamEntry famEntry) {
        this.famEntry = famEntry;
    }

    public ArrayList<Float> getResults() {
        return results;
    }

    public void setResults(ArrayList<Float> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "InferenceResult{" +
                "famEntry=" + famEntry +
                ", results=" + results +
                ", weight=" + weight +
                '}';
    }
}
