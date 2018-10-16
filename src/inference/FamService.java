package inference;

import fuzzyfication.MasterVariables;
import fuzzyfication.models.FuzzyVariable;
import inference.models.FamEntry;
import inference.models.SimpleVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Genera la matriz fam
 */
class FamService {
    private static FamService INSTANCE = new FamService();

    /**
     * Llena el archivo FAM
     */
    void FillFile(){
        List<FamEntry> entries = FamService.getINSTANCE().generateFAM();
        for (FamEntry entry : entries){
            MasterFam.getINSTANCE().insert(entry);
        }
    }

    /**
     * Devuelve la matriz fam, como una lista de entradas
     *
     * @return List<FamEntry> : Lista con las entradas de la matriz fam
     */
    private List<FamEntry> generateFAM(){
        List<FamEntry> result = new ArrayList<>();

        HashMap hashMap = getLabelDictionary();
        ArrayList<List<SimpleVariable>> cartesian = getCartesian();

        FamEntry famEntry;
        ArrayList<String> labels;
        ArrayList<SimpleVariable> variables;

        for (int i = 0; i < cartesian.size(); i++) {
            famEntry = new FamEntry();
            famEntry.setId(i);
            famEntry.setResult(calculateResult(hashMap, cartesian.get(i)));

            labels = new ArrayList<>();
            variables = (ArrayList<SimpleVariable>) cartesian.get(i);

            for (SimpleVariable variable : variables) {
                labels.add(variable.getLabel());
            }

            famEntry.setLabelList(labels);
            result.add(famEntry);
        }
        return result;
    }

    /**
     * Calcula el resultado que tiene cada combinación de la matriz fam
     * Utiliza un hashmap para obtener el valor de cada etiqueta, el hashmap se genera en getLabelDictionary
     * @param hashMap : Arreglo {Clave,Valor} con el valor numérico asociado a cada etiqueta {"alto", "medio", "bajo", etc}
     * @param list : Lista con las variables a medir
     * @return String : Valor de la variable de salida que toma el la combinación deseada
     */
    private String calculateResult(HashMap<String,Integer> hashMap, List<SimpleVariable> list) {
        int result = 0;
        for (SimpleVariable variable: list) {
            result += hashMap.get(variable.getLabel());
        }
        if (result >= 5) return "Alto";
        if (result > 2) return "Medio";
        else return "Bajo";
    }

    /**
     * Constructor
     */
    private FamService() {}

    /**
     * Unica Instancia que se utiliza en todo el código
     * @return
     */
    static FamService getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Arreglo que funciona como diccionario
     * se genera un arreglo {"String", "entero"} en donde la clave es "String", y se asocia con un número
     * consecutivo de acuerdo al orden en que fue declarada la variable de entrada.
     * Por ejemplo  para la variable Solución de problemas, se generan los siguientes valores
     * {"Bajo", 0} , {"Medio", 1 } , {"Alto", 2}
     *
     * @return
     */
    private HashMap<String, Integer> getLabelDictionary(){
        HashMap<String, Integer> hashMap = new HashMap();
        ArrayList<FuzzyVariable> variables = MasterVariables.getInstance().getAll();
        for (FuzzyVariable v:variables ) {
            for (int i = 0; i < v.getLabels().size() ; i++) {
                hashMap.put( v.getLabels().get(i).getLabelName() , i );
            }
        }
        return hashMap;
    }

    /**
     * Devuelve el producto cartesiano de lals variables de entrada definidas
     * @return ArrayList : Lista con el producto cartesiano
     */
    private ArrayList<List<SimpleVariable>> getCartesian(){
        Cartesian cartesian = new Cartesian();
        ArrayList<FuzzyVariable> variables = MasterVariables.getInstance().getAll();

        ArrayList<List> lists = new ArrayList<>();
        for ( FuzzyVariable v: variables ) {
            List<SimpleVariable> list = cartesian.combinations(v);
            lists.add(list);
        }
        List list = cartesian.product(lists);
        //System.out.println(list);

        return cartesian.resultProduct(list);
    }

}
