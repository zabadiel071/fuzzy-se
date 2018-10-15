package inference;

import fuzzyfication.MasterVariables;
import fuzzyfication.models.FuzzyVariable;
import inference.models.FamEntry;
import inference.models.SimpleVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FamService {
    static FamService INSTANCE = new FamService();

    public void FillFile(){
        List<FamEntry> entries = FamService.getINSTANCE().generateFAM();
        for (FamEntry entry : entries){
            MasterFam.getINSTANCE().insert(entry);
        }
    }

    /**
     * Genera la matriz fam
     */
    public List<FamEntry> generateFAM(){
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

    private String calculateResult(HashMap<String,Integer> hashMap, List<SimpleVariable> list) {
        int result = 0;
        for (SimpleVariable variable: list) {
            result += hashMap.get(variable.getLabel());
        }
        if (result >= 5) return "Alto";
        if (result > 2) return "Medio";
        else return "Bajo";
    }


    private FamService() {
    }

    public static FamService getINSTANCE() {
        return INSTANCE;
    }

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
