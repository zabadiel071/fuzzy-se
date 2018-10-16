import difuzzyfication.Centroid;
import fuzzyfication.Fuzzyficator;
import fuzzyfication.models.FuzzyResult;
import inference.InferenceEngine;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //byte [] values1 = new byte[]{40,65,30,67,23,30,40,50};
        byte [] values2 = new byte[]{70,65,55,39,40,25,70,73};

        ArrayList<Byte> valuesList = new ArrayList<>();

        for (int i = 0; i < values2.length; i++) {
            valuesList.add(values2[i]);
        }

        Fuzzyficator fuzzyficator = new Fuzzyficator();
        ArrayList<FuzzyResult> l = fuzzyficator.bulkFuzzyfication(valuesList);
        System.out.println(l);

        InferenceEngine inferenceEngine = new InferenceEngine();
        inferenceEngine.inference(l);

        float results [] = new float[] {
                inferenceEngine.bajo.getWeight(),
                inferenceEngine.medio.getWeight(),
                inferenceEngine.alto.getWeight()
        };


        Centroid centroid = new Centroid(results);
        float centroidV = centroid.getCentroid();
        System.out.println("El centroide es : " + centroidV);
    }
}
