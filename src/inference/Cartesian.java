package inference;

import fuzzyfication.MasterVariables;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import inference.models.SimpleVariable;

import java.util.*;

import static java.util.Collections.emptyList;


public class Cartesian  {



    /**
     * Obtiene las combinaciones por etiqueta que tiene una variable
     * @param variable : Variable difusa sobre la cual se busca obtener sus combinaciones
     * @return List : Combinaciones
     */
    public List<SimpleVariable> combinations(FuzzyVariable variable){
        List<SimpleVariable> simpleVariables = new ArrayList<>();
        for (Label label : variable.getLabels())
            simpleVariables.add(new SimpleVariable(variable.getId(),label.getLabelName()));
        return simpleVariables;
    }

    /**
     * Entrada para el producto cartesiano de N variables
     * @param a : Lista con las combinaciones posibles
     * @return : List : Lista con el producto cartesiano
     */
    public List<?> product(List<List> a) {
        if (a.size() >= 2) {
            List<?> product = a.get(0);
            for (int i = 1; i < a.size() ; i++) {
                product = product(product, a.get(i));
            }
            return product;
        }

        return emptyList();
    }

    /**
     * Realiza el producto cartesiano entre dos conjuntos
     * @param a : Lista con los elementos A
     * @param b : Lista con los elementos B
     * @param <A> : Tipo genérico del conjunto A
     * @param <B> : Tipo Genérico del conjunto B
     * @return List<?> : Lista con el producto cartesiano entre ambas
     */
    private <A, B> List<?> product(List<A> a, List<B> b) {
        List<List<Object>> result = new ArrayList<>();
        List<Object> aux;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                aux = new ArrayList<>();
                aux.add(a.get(i));
                aux.add(b.get(j));
                result.add(aux);
            }
        }
        return result;
    }

    /**
     * De manera que el producto cartesiano genera un resultado Anidado, se requiere un metodo para realizar el formato correcto en
     * los resultados obtenidos del producto cartesiano
     * @param list : Lista resultado del producto cartesiano
     * @param <A> : Tipo genérico de los elementos del producto cartesiano
     * @return ArrayList<List<A>> : Continene el resultado del producto cartesiano en formato , 1 por item de la lista
     */
    public <A> ArrayList<List<A>> resultProduct(List<List<A>> list){
        ArrayList<List<A>> result = new ArrayList<>();
        for ( List l : list) {
            List<A> aux = explore(l);
            for (int i = 0; i < aux.size(); i++) {
                if (aux.get(i) instanceof ArrayList){
                    aux.remove(i);
                }
            }
            result.add(aux);
        }
        return result;
    }

    /**
     * Explora una fila del producto cartesiano, para desanidar las listas
     * @param l : Lista con listas anidadas
     * @param <A> : Tipo genérico contenido en las listas
     * @return List : Lista de un solo tipo.
     */
    private <A> List explore(List<A> l) {
        List<A> list = new ArrayList<>();
        for (A a : l ) {
            if (a instanceof ArrayList){
                list.addAll(explore((List<A>) a));
            }else {
                list.addAll( l );
                return list;
            }
        }
        return list;
    }

}
