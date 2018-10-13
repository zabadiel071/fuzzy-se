package fuzzyfication.models;

import java.util.ArrayList;

/**
 * Representación de una etiqueta en la variable difusa
 */
public class Label {

    /**
     * Nombre de la etiqueta
     */
    private String labelName;

    /**
     * Puntos que conforman la etiqueta
     */
    private ArrayList<Point> points;

    public Label(String labelName,ArrayList<Point> points) {
        this.labelName = labelName;
        this.points = points;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelName='" + labelName + '\'' +
                ", points=" + points +
                '}';
    }
}
