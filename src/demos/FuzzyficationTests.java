package demos;

import fuzzyfication.Fuzzyficator;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class FuzzyficationTests {

    void basicFuzzyfication(){
        // Listas de ejemplo
        ArrayList<Point> pointsLabel1 = new ArrayList<>();
        ArrayList<Point> pointsLabel2 = new ArrayList<>();

        // Inicialización con puntos de ejemplo
        // se le pueden insertar los puntos que quiera, pero solo tomara en cuenta los que están establecidos en la clase Point
        for (byte i = 0; i < 4; i++){
            pointsLabel1.add(new Point( (byte) (i * 10), (byte)(8*i)));
            pointsLabel2.add(new Point( (byte) (i * 10), (byte)(3*i)));
        }

        // Creacion de etiquetas
        Label label1 = new Label("Alto",pointsLabel1);
        Label label2 = new Label("Bajo",pointsLabel2);

        // Arreglo con etiquetas
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(label1);
        labels.add(label2);

        // Creación de las variables difusas
        FuzzyVariable fuzzyVariable = new FuzzyVariable((byte)1, "Var1", labels);
        FuzzyVariable fuzzyVariable2 = new FuzzyVariable((byte)2, "Var2", labels);

        System.out.println(fuzzyVariable);
        System.out.println(fuzzyVariable2);

        Fuzzyficator fuzzyficator = new Fuzzyficator();
        System.out.println("\n" + fuzzyficator.fuzzyfy((byte) 20, fuzzyVariable));
    }

    public static void main(String[] args) {
        FuzzyficationTests fuzzyficationTests = new FuzzyficationTests();
        fuzzyficationTests.basicFuzzyfication();

    }
}
