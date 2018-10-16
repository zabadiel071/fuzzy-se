package inference;

import fuzzyfication.models.FuzzyResult;
import fuzzyfication.models.LabelResult;
import inference.models.FamEntry;
import inference.models.InferenceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Recibe un conjunto de valores de membresía para cada variable difusa
 * y devuelve valores en Alto , Medio y Bajo para la variable de salida
 */
public class InferenceEngine {

    /**
     * El motor de inferencia se inicializa llenando la matriz fam con la que se va a trabajar
     */
    public InferenceEngine() {
        FamService.getINSTANCE().FillFile();
    }

    /**
     * Conjuntos de resultados de los que se obtienen los máximos
     */
    private List<InferenceResult> altos,medios ,bajos;

    /**
     * Conjunto de resultados máximos
     */
    public InferenceResult alto, medio, bajo;

    /**
     * Método principal para iniciar la inferencia por min- max
     * @param fuzzyResults : Resultados difusos que se obtienen de la fusificación
     */
    public void inference(ArrayList<FuzzyResult> fuzzyResults){

        List<InferenceResult> results = min(fuzzyResults);  //Se obtiene el minimo

        //Se separan los valores en alto, medio, bajo
        splitRanges(results);

        //Se aplica máximo para cada uno
        alto = max(altos);
        medio = max(medios);
        bajo = max (bajos);

        //Salida de las reglas seleccionadas
        System.out.println(alto);
        System.out.println(medio);
        System.out.println(bajo);
    }
    public InferenceResult getAlto(){ return alto;}
    public InferenceResult getMedio(){return medio;}
    public InferenceResult getBajo(){return bajo;}
    /**
     * Aplicación del máximo de los pesos de regla (weight) para cada lista de resultados inferidos
     * @param list : Lista con los resultados de la inferencia
     * @return InferenceResult : Resultado máximo de la lista de valores
     */
    private InferenceResult max(List<InferenceResult> list) {
        float max = 0;
        InferenceResult inferenceResult = null;
        for (InferenceResult i : list){
            if ( max <= i.getWeight()){
                inferenceResult = i;
                max = inferenceResult.getWeight();
            }
        }
        return inferenceResult;
    }

    /**
     * Separa los resultados en altos, medios, bajos
     * @param results : conjunto de valors que contiene todas las combinaciones obtenidas
     */
    private void splitRanges(List<InferenceResult> results){
        altos = new ArrayList<>();
        medios = new ArrayList<>();
        bajos = new ArrayList<>();
        String result = "";
        for (InferenceResult i : results){
            result = i.getFamEntry().getResult();
            if (result.equals("Alto"))
                altos.add(i);
            if (result.equals("Medio"))
                medios.add(i);
            if (result.equals("Bajo"))
                bajos.add(i);
        }
    }

    /**
     * Obtiene los valores minimos entre los resultados difusos encontrados, es decir , el mínimo para
     * el conjunto con todas las variables lingüistica definidas.
     * @param fuzzyResults : ArrayList con los resultados de cada variable lingüistica
     * @return List<InferenceResult> : Lista con los mínimos obtenidos
     */
    private List<InferenceResult> min(ArrayList<FuzzyResult> fuzzyResults){
        List<FamEntry> entries = MasterFam.getINSTANCE().getAll();
        List<InferenceResult> results = new ArrayList<>();

        List<Float> muResults;

        for (FamEntry entry: entries) {
            muResults = new ArrayList<>();
            for (int i = 0; i < fuzzyResults.size(); i++) {
                String label = entry.getLabelList().get(i);
                FuzzyResult fuzzyResult = fuzzyResults.get(i);
                float mu = calculateResult(label,fuzzyResult);
                muResults.add(mu);
            }
            results.add(new InferenceResult(entry, (ArrayList<Float>) muResults, getWeight(muResults)));
        }
        return results;
    }

    /**
     * Obtiene el peso de la regla, buscando el valor mínimo de los resultados de cada variable difusa
     * @param muResults : Lista con los valores difusos
     * @return float : Valor mínimo, que representa el peso de la regla
     */
    private float getWeight(List<Float> muResults) {
        float weigth = 1.0f;
        for (int i = 0; i < muResults.size(); i++) {
            if (muResults.get(i)< weigth){
                weigth = muResults.get(i);
            }
        }
        return weigth;
    }

    /**
     * Obtiene la membresia para un resultado y una regla insertada
     * @param label : Etiqueta a la que se pertenece
     * @param fuzzyResult : Resultado difuso
     * @return float : Nivel de membresia para el resultado difuso
     */
    private float calculateResult(String label, FuzzyResult fuzzyResult) {
        List<LabelResult> labelResults = fuzzyResult.getResults();
        for (LabelResult l : labelResults){
            if (l.getLabelName().equals(label)){
                return l.getMu();
            }
        }
        return 0;
    }

}
