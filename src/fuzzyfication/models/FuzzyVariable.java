package fuzzyfication.models;
import java.util.ArrayList;

/**
 * Representa una variable difusa
 */
public class FuzzyVariable {

    /**
     * Número de etiquetas que puede tener una variable
     */
    public static final byte LABELS_COUNT = 2;

    /**
     *  Longitud máxima del nombre de la variable difusa
     */
    public static final byte NAME_LENGTH = 60;

    /**
     * Identificador (Byte) , 100 caracteres -> Nombre , 2 Etiquetas
     */
    public static final int BYTES = Byte.BYTES + NAME_LENGTH * Character.BYTES + LABELS_COUNT * Label.BYTES;

    /**
     * Identificador del registro
     */
    private byte id;

    /**
     * Nombre de la variable difusa
     * Máximo 100 Caracteres
     */
    private String name;

    /**
     * Etiquetas que contiene la variable difusa
     * Máximo 2 etiquetas
     */
    private ArrayList<Label> labels;

    public FuzzyVariable(byte id, String name, ArrayList<Label> labels) {
        this.id = id;
        this.name = name;
        this.labels = labels;
    }

    public FuzzyVariable() {}

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

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }
}
