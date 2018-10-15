package inference;

import difuzzyfication.Centroid;
import fuzzyfication.Fuzzyficator;
import fuzzyfication.models.FuzzyResult;
import fuzzyfication.models.LabelResult;
import fuzzyfication.models.Line;
import inference.models.FamEntry;
import inference.models.InferenceResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Recibe un conjunto de valores de membresía para cada variable difusa
 * y devuelve valores en Alto , Medio y Bajo para la variable de salida
 */
public class InferenceEngine {

    public InferenceEngine() {
        FamService.getINSTANCE().FillFile();
    }

    /**
     *
     */
    List<InferenceResult> altos,medios ,bajos;

    /**
     *
     */
    public InferenceResult alto, medio, bajo;

    public static void main(String[] args) {
        //byte [] values1 = new byte[]{40,65,30,67,23,30,40,50};
        byte [] values2 = new byte[]{90,90,90,90,90,90,90,90};

        ArrayList<Byte> valuesList = new ArrayList<>();

        for (int i = 0; i < values2.length; i++) {
            valuesList.add(values2[i]);
        }

        Fuzzyficator fuzzyficator = new Fuzzyficator();
        ArrayList<FuzzyResult> l = fuzzyficator.bulkFuzzyfication(valuesList);
        System.out.println(l);

        InferenceEngine inferenceEngine = new InferenceEngine();
        inferenceEngine.inference(l);

        ArrayList<Float> linesResults = new ArrayList<>();
        linesResults.add(inferenceEngine.bajo.getWeight());
        linesResults.add(inferenceEngine.medio.getWeight());
        linesResults.add(inferenceEngine.alto.getWeight());

        Centroid centroid = new Centroid(linesResults);
        float centroidV = centroid.getCentroid();
        System.out.println(centroidV);
    }

    /**
     *
     * @param fuzzyResults
     */
    void inference(ArrayList<FuzzyResult> fuzzyResults){

        List<InferenceResult> results = min(fuzzyResults);

        splitRanges(results);

        alto = max(altos);
        medio = max(medios);
        bajo = max (bajos);

        System.out.println(alto);
        System.out.println(medio);
        System.out.println(bajo);
    }

    /**
     *
     * @param list
     * @return
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
     *
     * @param results
     */
    void splitRanges(List<InferenceResult> results){
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
     *
     * @param fuzzyResults
     * @return
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
     *
     * @param muResults
     * @return
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
     *
     * @param label
     * @param fuzzyResult
     * @return
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
