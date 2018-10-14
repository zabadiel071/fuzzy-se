package fuzzyfication;

import fuzzyfication.models.*;

import java.util.ArrayList;

/**
 * Realiza las operaciones necesarias para convertir los valores de entrada en valores difusos
 */
public class Fuzzyficator {

    /**
     * Realiza la fusificacion con todas las variables difusas que se encuentran en el archivo maestro
     * @param values : Arraylist<Byte> del mismo tamaño que el numero de variables difusas, cada elemento en values
     *               se compara con su correspondiente variable difusa
     * @return ArrayList<FuzzyResult> : Contiene todos los resultados obtenidos para cada variable
     */
    public ArrayList<FuzzyResult> bulkFuzzyfication(ArrayList<Byte> values){
        ArrayList<FuzzyVariable> variables = MasterVariables.getInstance().getAll();
        ArrayList<FuzzyResult> results = new ArrayList<>();

        if (values.size() == variables.size()){
            for (int i = 0; i < variables.size() - 1; i++) {
                results.add(fuzzyfy(values.get(i) , variables.get(i)));
            }
            return results;
        }else {
            return null;
        }
    }

    /**
     * Obtiene la fusificación para un valor en x en una variable definida
     * @param x : Valor a fusificar
     * @param fuzzyVariable : Variable difusa
     * @return FuzzyResult : Resultado difuso
     */
    public FuzzyResult fuzzyfy(byte x, FuzzyVariable fuzzyVariable){
        ArrayList<LabelResult> results = new ArrayList<>();
        for (Label l : fuzzyVariable.getLabels() ) {
            if (isLabelValuable(x,l.getRange())){
                //System.out.println("\n" + l);
                ArrayList<Line> lines = l.getLines();
                for (Line line : lines ) {
                    if (isLineValuable(x,line)){
                        //System.out.printf("%s Es valuable en %s , con Mu = %s%n", x, line, line.getMuValue(x));
                        results.add(new LabelResult(l.getLabelName(), line.getMuValue(x)));
                        break;
                    }
                }
            }
        }
        return new FuzzyResult(fuzzyVariable.getName(), results);
    }

    /**
     * Verifica si un valor X se puede valuar en un rango de etiqueta
     * @param x : Valor a probar
     * @param range : LabelRange en el que se busca valuar
     * @return boolean : Si entra o no
     */
    private boolean isLabelValuable(byte x, LabelRange range){
        return range.getxMin() <= x && x <= range.getxMax();
    }

    /**
     * Verifica si se puede valuar X en una recta
     * @param x : Valor a probar
     * @param line : Recta en la que busca valuar
     * @return boolean : Si se puede o no valuar
     */
    private boolean isLineValuable(byte x , Line line){
        return line.getP1().getX() <= x && x <= line.getP2().getX();
    }
}
