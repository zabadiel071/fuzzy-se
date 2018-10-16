package fuzzyfication.models;

import java.util.ArrayList;

/**
 * Representación de una etiqueta en la variable difusa
 */
public class Label {

    /**
     * Longitud máxima para el nombre de una etiqueta
     */
    public static final byte LABEL_NAME_LENGHT = 50;

    /**
     * Numero máximo de puntos que puede tener una etiqueta
     */
    public static final byte POINTS_COUNT = 5;

    /**
     * 50 caracteres y 5 puntos
     */
    static final int BYTES = LABEL_NAME_LENGHT * Character.BYTES + 5*Point.BYTES;

    /**
     * Nombre de la etiqueta
     * Máximo 50 caracteres
     */
    private String labelName;

    /**
     * Puntos que conforman la etiqueta
     * Maximo se deben de tener 5
     */
    private ArrayList<Point> points;

    public Label() {

    }

    /**
     * Devuelve el rango de valores en los que se puede valuar para esta etiqueta
     * @return LabelRange : Rango de valores
     */
    public LabelRange getRange(){
        Point first = points.get(0);
        Point last = points.get(points.size() - 1);
        return new LabelRange(first.getX(), last.getX());
    }

    /**
     *
     * @return
     */
    public ArrayList<Line> getLines(){
        ArrayList<Line> lines = new ArrayList<>();
        if (points.size() >= 2){
            try {
                Point p1, p2;
                for (int i = 0; i < points.size() - 1; i++) {
                    p1 = points.get(i);
                    p2 = points.get(i+1);
                    lines.add(new Line(p1,p2));
                }
            }catch (IndexOutOfBoundsException ex){
                return lines;
            }
        }else {
            return null;
        }
        return lines;
    }

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
