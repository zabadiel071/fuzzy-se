package inference.models;

import fuzzyfication.models.Label;

import java.util.ArrayList;

/**
 * Representa una entrada en el archivo maestro de la matriz fam
 */
public class FamEntry {

    /**
     * Variables con las que se puede enlazar la entrada
     */
    public static final byte VARIABLES_COUNT = 10;

    /**
     *
     */
    public static final byte RESULT_LENGTH = 60;

    /**
     * Id , variables , resultado
     */
    public static final int BYTES = Integer.BYTES + VARIABLES_COUNT * Character.BYTES *Label.LABEL_NAME_LENGHT + Character.BYTES * RESULT_LENGTH;

    /**
     * Identificador de la entrada
     */
    private int id;

    /**
     * Etiqueta resultado de la combinación
     */
    private String result;

    /**
     * Arreglo de variables que componen la entrada
     */
    private ArrayList<String> labelList;

    public FamEntry(int id, String result, ArrayList<String> simpleVariable) {
        this.id = id;
        this.result = result;
        this.labelList = simpleVariable;
    }

    public FamEntry() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(ArrayList<String> labelList) {
        this.labelList = labelList;
    }

    @Override
    public String toString() {
        return "FamEntry{" +
                "id=" + id +
                ", result='" + result + '\'' +
                ", labelList=" + labelList +
                '}';
    }
}
