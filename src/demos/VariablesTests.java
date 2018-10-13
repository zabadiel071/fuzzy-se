package demos;

import fuzzyfication.MasterVariables;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.Point;

import java.util.ArrayList;

public class VariablesTests {

    void IOTest(){
        // Listas de ejemplo
        ArrayList<Point> pointsLabel1 = new ArrayList<>();
        ArrayList<Point> pointsLabel2 = new ArrayList<>();

        // Inicialización con puntos de ejemplo
        // se le pueden insertar los puntos que quiera, pero solo tomara en cuenta los que están establecidos en la clase Point
        for (byte i = 0; i < 10; i++){
            pointsLabel1.add(new Point( i, (byte)(8*i)));
            pointsLabel2.add(new Point( i, (byte)(3*i)));
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

        //System.out.println(fuzzyVariable);
        //System.out.println(fuzzyVariable2);

        // Inserción de las variables en el archivo maestro
        MasterVariables.getInstance().insert(fuzzyVariable);
        MasterVariables.getInstance().insert(fuzzyVariable2);

        // Obtención de la variable 1 del archivo
        fuzzyVariable = MasterVariables.getInstance().get((byte)1);
        System.out.println(fuzzyVariable);

        // Obtención de la variable 2 del archivo
        fuzzyVariable2 = MasterVariables.getInstance().get((byte)2);
        System.out.println(fuzzyVariable2);
    }

    public static void main(String[] args) {
        VariablesTests variablesTests = new VariablesTests();
        variablesTests.IOTest();
    }
}
