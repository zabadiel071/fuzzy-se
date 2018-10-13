package fuzzyfication;

import files.Constants;
import files.FileController;

/**
 * Representa un archivo indice
 * Registro conformado por : [Id (Byte) | Position (Long)]
 */
public class IndexFuzzyVariable extends FileController {

    /**
     * Singleton
     */
    private static final IndexFuzzyVariable INSTANCE = new IndexFuzzyVariable("data/variables_index");

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    private IndexFuzzyVariable(String fileName) {
        super(fileName);
        // La suma de los bytes que ocupa la clave y los que ocupa una dirección lógica
        registerLength = Constants.VARIABLE_ID_LENGTH + Long.BYTES;
    }

    /**
     * Inserta un nuevo registro en el final del archivo
     * @param key : clave del registro que existe en el archivo maestro
     * @param position: posicion en bytes del registro en el archivo maestro
     * @return boolean
     */
    public boolean insertRegister(byte key, long position){
        boolean insert = false;
        if (getPosition(key) == -1 ){
            try {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.writeByte(key);
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
    public long getPosition(byte key){
        long position = -1;
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i < nRegisters; i++){
                randomAccessFile.seek(i*registerLength);
                byte foundKey = randomAccessFile.readByte();
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
     * Elimina el registro en la llave ingresada
     * @param key : Llave de la cual se quiere borrar el registro
     */
    public void delete(byte key){
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i< nRegisters ; i++){
                randomAccessFile.seek(i*registerLength);
                byte foundKey = randomAccessFile.readByte();
                if (foundKey == key)
                    clearRegister(randomAccessFile.getFilePointer());
            }
            randomAccessFile.seek(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton
     * @return IndexFuzzyVariable Instance
     */
    public static IndexFuzzyVariable getINSTANCE() {
        return INSTANCE;
    }
}
