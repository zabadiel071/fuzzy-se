package fuzzyfication.models;

/**
 * Resultado que se encontró en la etiqueta
 */
public class LabelResult {
    /**
     * Nombre de la etiqueta probada
     */
    private String labelName;

    /**
     * Valor en MU que se obtuvo para la etiqueta
     */
    private float mu;

    public LabelResult(String labelName, float mu) {
        this.labelName = labelName;
        this.mu = mu;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public float getMu() {
        return mu;
    }

    public void setMu(float mu) {
        this.mu = mu;
    }

    @Override
    public String toString() {
        return "LabelResult{" +
                "labelName='" + labelName + '\'' +
                ", mu=" + mu +
                '}';
    }
}
