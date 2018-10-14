package fuzzyfication;

import com.google.gson.Gson;
import fuzzyfication.models.FuzzyVariable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase para carga de los datos desde variables.json
 * Es necesario añadir la libreria Gson
 * Se pueden utilizar los archivos master_variables y variables_index que vienen por defecto en el repositorio
 * en caso de que no se quiera instalar la libreria.
 *
 * Comentar esta clase si está causando conflictos al compilar.
 *
 * Para añadir Gson en IntelliJ :
 * ***** https://www.jetbrains.com/help/idea/library.html#download-libraries-from-maven
 * ***** Utilizando : com.google.code.gson:gson:2.8.5 como repositorio de Maven
 */
public class VariableService {

    private static String readJsonFile(){
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new FileReader("variables.json"));
            String line;
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String jsonString = readJsonFile();

        Gson gson = new Gson();
        FuzzyVariable[] variables = gson.fromJson(jsonString, FuzzyVariable[].class );

        ArrayList<FuzzyVariable> variableArrayList = new ArrayList<>(Arrays.asList(variables));

        for (FuzzyVariable fuzzyVariable: variableArrayList ) {
            MasterVariables.getInstance().insert(fuzzyVariable);
        }
        System.out.println("Carga en data/master_variables");
    }
}
