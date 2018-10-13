package files;

/**
 * Representa un archivo indice
 */
public class Index extends FileController{

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    public Index(String fileName) {
        super(fileName);
        // La suma de los bytes que ocupa la clave y los que ocupa una dirección lógica
        registerLength = Constants.ID_LENGTH + Long.BYTES;
    }

    /**
     * Inserta un nuevo registro en el final del archivo
     * @param key : clave del registro que existe en el archivo maestro
     * @param position: posicion en bytes del registro en el archivo maestro
     * @return boolean
     */
    public boolean insertRegister(int key, long position){
        boolean insert = false;
        if (position == -1 ){
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
    public long getPosition(int key){
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
     * Elimina el registro en la llave ingresada
     * @param key : Llave de la cual se quiere borrar el registro
     */
    public void delete(int key){
        try {
            long nRegisters = randomAccessFile.length() / registerLength;
            for (int i = 0; i< nRegisters ; i++){
                randomAccessFile.seek(i*registerLength);
                int foundKey = randomAccessFile.readInt();
                if (foundKey == key)
                    clearRegister(randomAccessFile.getFilePointer());
            }
            randomAccessFile.seek(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
