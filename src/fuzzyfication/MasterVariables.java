package fuzzyfication;

import files.FileController;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import fuzzyfication.models.Point;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Accesos y operaciones sobre el archivo que almacena las variables difusas
 */
public class MasterVariables extends FileController{

    /**
     * Singleton
     */
    private static final MasterVariables INSTANCE = new MasterVariables("data/master_variables");

    /**
     * Constructor por defecto
     *
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    private MasterVariables(String fileName) {
        super(fileName);
        //Set registerLength
        registerLength = FuzzyVariable.BYTES;
    }

    /**
     * Singleton
     * Obtiene el objeto para hacer uso de las variables que se encuentran en el archivo
     */
    public static MasterVariables getInstance(){
        return INSTANCE;
    }

    /**
     * Inserta la variable difusa, inicializada con sus etiquetas, a su vez inicializadas con la lista  de puntos
     * @param fuzzyVariable : Variable difusa a insertar
     * @return : boolean indicando si lo insertó o no
     */
    public boolean insert(FuzzyVariable fuzzyVariable){
        if (getPosition(fuzzyVariable.getId()) == -1 ){
            try {
                randomAccessFile.seek(randomAccessFile.length());
                long position = randomAccessFile.getFilePointer();

                randomAccessFile.writeByte(fuzzyVariable.getId()); //id
                randomAccessFile.writeChars(formatString(fuzzyVariable.getName() , FuzzyVariable.NAME_LENGTH)); // Nombre

                writeLabels(fuzzyVariable.getLabels());

                //Guardando posición y clave en archivo indice
                IndexFuzzyVariable.getINSTANCE().insertRegister(fuzzyVariable.getId(),position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
            return false;

        return true;
    }

    /**
     * Obtiene un objeto FuzzyVariable con un id ingresado
     * @param id : Identificador de la variable
     * @return FuzzyVariable
     */
    public FuzzyVariable get(byte id){
        long position = getPosition(id);        //Obtener su posición del archivo indice
        FuzzyVariable variable = null;
        if (position != -1){
            try {
                randomAccessFile.seek(position);

                variable = readVariable();
/*                variable = new FuzzyVariable();
                variable.setId(randomAccessFile.readByte());
                variable.setName(readString(FuzzyVariable.NAME_LENGTH));
                variable.setLabels(readLabels());
             */
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return variable;
    }

    /**
     * Obtiene un ArrayList con todas las variables que existen en el archivo maestro
     * @return ArrayList
     */
    public ArrayList<FuzzyVariable> getAll(){
        ArrayList<FuzzyVariable> variables = new ArrayList<>();
        try {
            long registers = registersCount();
            FuzzyVariable fuzzyVariable;
            for (int i = 0; i < registers; i++) {
                randomAccessFile.seek(i*registerLength);
                 fuzzyVariable = readVariable();
                if (fuzzyVariable != null)
                    variables.add(fuzzyVariable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return variables;
    }

    /**
     * Comienza la lectura de una variable en la posición en la que se encuentre el puntero del archivo
     * @return FuzzyVariable : La variable recuperada
     */
    private FuzzyVariable readVariable() {
        FuzzyVariable fuzzyVariable = new FuzzyVariable();
        try {
            fuzzyVariable.setId(randomAccessFile.readByte());
            fuzzyVariable.setName(readString(FuzzyVariable.NAME_LENGTH));
            fuzzyVariable.setLabels(readLabels());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fuzzyVariable;
    }

    /**
     * Lee las etiquetas de la FuzzyVariable que se está intentando leer.
     * No usar este método fuera del contexto de lectura de una variable difusa
     * @return ArrayList : Lista con las etiquetas que pertenecen a la variable difusa
     * @throws IOException : En caso de que haya un error en los archivos
     */
    private ArrayList<Label> readLabels() throws IOException {
        int i = 0;
        ArrayList<Label> labels = new ArrayList<>();
            while (i < FuzzyVariable.LABELS_COUNT){
                labels.add(parseLabel());
                i++;
            }
        return labels;
    }

    /**
     * Lee los puntos que se encuentran en la etiqueta actual, de la variable actual que se intenta leer.
     * No usar este método fuera del contexto de lectura de una etiqueta
     * @return ArrayList<Point> : Con los puntos que pertenecen a la etiqueta
     * @throws IOException : En caso de que haya un error en la lectura del archivo
     */
    private ArrayList<Point> readPoints() throws IOException {
        int i = 0;
        ArrayList<Point> points = new ArrayList<>();
            byte x,mu;
            while (i < Label.POINTS_COUNT){
                x = randomAccessFile.readByte();
                mu = randomAccessFile.readByte();
                if (x != -1 && mu != -1){
                    points.add(new Point(x, mu));
                }
                i++;
            }
        return points;
    }

    /**
     * Lee una etiqueta en la posición actual
     * @return Label : Etiqueta leida
     * @throws IOException : En caso de algún error con los archivos
     */
    private Label parseLabel() throws IOException {

        String label = readString(Label.LABEL_NAME_LENGHT);

        if (label.equals(formatString("",Label.LABEL_NAME_LENGHT)))
            return null;

        ArrayList<Point> points = readPoints();

        return new Label(label,points);
    }

    /**
     * Escribe las etiquetas que se pueden insertar en un registro
     * @param labels : ArrayList con las etiquetas que se van a insertar
     * @return long
     */
    private long writeLabels(ArrayList<Label> labels) throws IOException {
        int i = 0;
        try {
            if (labels == null) throw new IndexOutOfBoundsException();

            while (i < FuzzyVariable.LABELS_COUNT){
                randomAccessFile.writeChars(formatString(labels.get(i).getLabelName(),Label.LABEL_NAME_LENGHT));
                writePoints(labels.get(i).getPoints());
                i++;
            }
        }
        // No se encontraron suficientes etiquetas, por lo tanto hay que llenar con datos genéricos
        catch (IndexOutOfBoundsException e){
            while (i < FuzzyVariable.LABELS_COUNT){
                randomAccessFile.writeChars(formatString("", Label.LABEL_NAME_LENGHT));
                writePoints(null);
                i++;
            }
        }
        return randomAccessFile.getFilePointer();
    }

    /**
     * Escribe la lista de puntos sobre el registro actual de la etiqueta.
     * @param points : ArrayList con los puntos que se van a insertar en la etiqueta
     * @throws IOException : En caso de que haya un error en la escritura de archivos
     */
    private void writePoints(ArrayList<Point> points) throws IOException {
        int i = 0;
        try {
            // En caso de que no se hayan enviado puntos, automaticamente se llena de datos genéricos
            if (points == null) throw new IndexOutOfBoundsException();
            byte x, mu;
            while (i<Label.POINTS_COUNT){
                x = points.get(i).getX();
                mu = points.get(i).getMu();
                randomAccessFile.writeByte(x);
                randomAccessFile.writeByte(mu);
                i++;
            }
        }
        // No se encontraron suficientes puntos, por lo tanto hay que llenar con datos genéricos
        catch (IndexOutOfBoundsException e){
            while (i < Label.POINTS_COUNT){
                randomAccessFile.writeByte((byte) -1); //X
                randomAccessFile.writeByte((byte) -1); //MU
                i++;
            }
        }
    }

    /**
     * Obtiene la posicion en bytes del id
     * @param id : Identificador de la variable
     * @return long
     */
    private long getPosition(byte id) {
        return IndexFuzzyVariable.getINSTANCE().getPosition(id);
    }

}