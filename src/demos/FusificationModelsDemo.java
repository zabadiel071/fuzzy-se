package demos;

import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.Point;

import java.util.ArrayList;

/**
 * Demo básico de la implementación de puntos, etiquetas y variables difusas
 */
public class FusificationModelsDemo {
    public static void main(String[] args) {
        ArrayList<Point> pointsLabel1 = new ArrayList<>();
        ArrayList<Point> pointsLabel2 = new ArrayList<>();

        for (byte i = 0; i < 10; i++){
            pointsLabel1.add(new Point( i, (byte)(8*i)));
            pointsLabel2.add(new Point( i, (byte)(3*i)));
        }

        Label label1 = new Label("Alto",pointsLabel1);
        Label label2 = new Label("Bajo",pointsLabel2);

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(label1);
        labels.add(label2);

        FuzzyVariable fuzzyVariable = new FuzzyVariable((byte)1, "Var1", labels);
        System.out.println(fuzzyVariable);
    }
}
