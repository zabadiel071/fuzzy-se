package demos;

import fuzzyfication.MasterVariables;
import fuzzyfication.models.FuzzyVariable;
import inference.Cartesian;
import inference.models.SimpleVariable;

import java.util.ArrayList;
import java.util.List;

public class CartesianTests {

    public static void main(String[] args) {
        Cartesian cartesian = new Cartesian();
        ArrayList<FuzzyVariable> variables = MasterVariables.getInstance().getAll();

        ArrayList<List> lists = new ArrayList<>();
        for ( FuzzyVariable v: variables ) {
            List<SimpleVariable> list = cartesian.combinations(v);
            lists.add(list);
        }

        List list = cartesian.product(lists);
        System.out.println(list);

        ArrayList arrayList = cartesian.resultProduct(list);

        System.out.println(arrayList);
    }
}
