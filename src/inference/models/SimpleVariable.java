package inference.models;

public class SimpleVariable {

    private byte variableId;
    private String label;

    public SimpleVariable(byte variableId, String label) {
        this.variableId = variableId;
        this.label = label;
    }

    public byte getVariableId() {
        return variableId;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SimpleVariable{" +
                "variableId=" + variableId +
                ", label='" + label + '\'' +
                '}';
    }
}
