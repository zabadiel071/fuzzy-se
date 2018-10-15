package inference;

import files.FileController;

import java.io.IOException;

/**
 * Representa un archivo indice
 * Registro conformado por : [ Id(Int) | Position (Long) ]
 */
class IndexFam extends FileController{

    /**
     * Singleton
     */
    private static final IndexFam INSTANCE = new IndexFam("data/fam_index");

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    private IndexFam(String fileName) {
        super(fileName);
        registerLength = Integer.BYTES + Long.BYTES;
        try {
            randomAccessFile.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta un nuevo registro en el final del archivo
     * @param key : clave del registro que existe en el archivo maestro
     * @param position: posicion en bytes del registro en el archivo maestro
     * @return boolean
     */
    public boolean insertRegister(int key, long position){
        boolean insert = false;
        if (getPosition(key) == -1 ){
            try {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeInt(key);
                randomAccessFile.writeLong(position);
                randomAccessFile.seek(0);
                insert = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  insert;
    }

    /**
     * Obtiene la dirección lógica ( Posición en bytes del archivo Maestro ) para la llave ingresada
     * @param key : Clave del registro
     * @return long
     */
    public long getPosition(int key) {
        long position = -1;
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i < nRegisters; i++){
                randomAccessFile.seek(i*registerLength);
                int foundKey = randomAccessFile.readInt();
                if (foundKey == key)
                    position = randomAccessFile.readLong();
            }
            randomAccessFile.seek(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    /**
     * Singleton
     * @return IndexFam Instance
     */
    public static IndexFam getINSTANCE() {
        return INSTANCE;
    }
}
