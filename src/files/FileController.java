package files;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase base para el manejo de archivos
 */
public class FileController {

    /**
     * Objeto para el acceso aleatorio al archivo
     */
    RandomAccessFile randomAccessFile;

    /**
     * Longitud del registro
     */
    int registerLength;

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    FileController(String fileName) {
        try {
            randomAccessFile = new RandomAccessFile(fileName,"rw");
            randomAccessFile.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee un String del tamaño que se especifique
     * @param length : el tamaño que se espera que tenga el string
     * @return String
     */
    protected String readString(int length) throws IOException {
        char [] result = new char[length];
        for (int i = 0; i < length ; i++){
            result[i] = randomAccessFile.readChar();
        }
        return String.valueOf(result);
    }

    /**
     * Pone toda la información en el registro como 0
     * @param position : posición del registro que se desea eliminar
     * @throws IOException : En caso de que haya un error en la escritura.
     */
    public void clearRegister(long position) throws IOException {
        randomAccessFile.seek(position);
        for (int i = 0; i < registerLength;i++){
            randomAccessFile.writeByte(0);
        }
    }

    /**
     * Convierte un String en el formato que se requiere para su inserción en el archivo,
     * debido a que se requiere longitud de tamaño fijo
     * @param s : Cadena que se desea formatear
     * @param length : Longitud que se desea que tenga la cadena
     * @return String
     */
    public String formatString(String s, int length){
        StringBuilder format = new StringBuilder(s.trim());
        while (format.length()<length)
            format.append(Character.MIN_VALUE);
        return format.substring(0,length);
    }

    public int getRegLength() {
        return registerLength;
    }

    public void setRegLength(int regLength) {
        registerLength = regLength;
    }
}
