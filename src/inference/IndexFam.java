package inference;

import files.FileController;

public class IndexFam extends FileController{

    private static final IndexFam INSTANCE = new IndexFam("data/fam_index");

    /**
     * Constructor por defecto
     *
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    protected IndexFam(String fileName) {
        super(fileName);
    }


}
