package inference;

import files.FileController;
import fuzzyfication.models.FuzzyVariable;
import fuzzyfication.models.Label;
import inference.models.FamEntry;

import java.io.IOException;
import java.util.ArrayList;

public class MasterFam extends FileController{

    private static final MasterFam INSTANCE = new MasterFam("data/master_fam");

    /**
     * Constructor por defecto
     *
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    private MasterFam(String fileName) {
        super(fileName);
        try {
            randomAccessFile.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        registerLength = FamEntry.BYTES;
    }

    /**
     *
     * @param famEntry
     * @return
     */
    public boolean insert(FamEntry famEntry){
        if (getPosition(famEntry.getId()) == -1){
            try {
                randomAccessFile.seek(randomAccessFile.length());
                long position = randomAccessFile.getFilePointer();

                randomAccessFile.writeInt(famEntry.getId());
                randomAccessFile.writeChars(formatString(famEntry.getResult(),FamEntry.RESULT_LENGTH));

                writeResults(famEntry.getLabelList());

                IndexFam.getINSTANCE().insertRegister(famEntry.getId(), position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.printf("El id ya existe en la base de conocimiento, no pudo ser agregado %n");
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public ArrayList<FamEntry> getAll(){
       ArrayList<FamEntry> entries = new ArrayList<>();
       try {
           long registers = registersCount();
           FamEntry famEntry;
           for (int i = 0; i < registers; i++) {
               randomAccessFile.seek(i*registerLength);
               famEntry = readEntry();
               entries.add(famEntry);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return entries;
    }

    /**
     *
     * @return
     */
    private FamEntry readEntry() {
        FamEntry famEntry = new FamEntry();
        try {
            famEntry.setId(randomAccessFile.readInt());
            famEntry.setResult(readString(FamEntry.RESULT_LENGTH));
            famEntry.setLabelList(readLabels());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return famEntry;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private ArrayList<String> readLabels() throws IOException {
        int i = 0;
        ArrayList<String> labels = new ArrayList<>();
        String s;
        while (i < FamEntry.VARIABLES_COUNT){
            s = parseLabel();
            if (s != null)
                labels.add(s);
            i++;
        }
        return labels;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private String parseLabel() throws IOException {
        String label = readString(Label.LABEL_NAME_LENGHT);
        if (label.equals(""))
            return null;
        return label;
    }

    /**
     *      * @param labelList
     */
    private void writeResults(ArrayList<String> labelList) throws IOException {
        int i = 0;
        try {
            if (labelList == null) throw new IndexOutOfBoundsException();
            while (i < FamEntry.VARIABLES_COUNT){
                randomAccessFile.writeChars(formatString(
                        labelList.get(i), Label.LABEL_NAME_LENGHT
                ));
                i++;
            }
        }
        catch (IndexOutOfBoundsException e){
            //Llenar con datos genéricos
            while (i < FamEntry.VARIABLES_COUNT){
                randomAccessFile.writeChars(formatString("", Label.LABEL_NAME_LENGHT));
                i++;
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    private long getPosition(int id) {
        return IndexFam.getINSTANCE().getPosition(id);
    }

    /**
     *
     * @return
     */
    public static MasterFam getINSTANCE() {
        return INSTANCE;
    }
}
