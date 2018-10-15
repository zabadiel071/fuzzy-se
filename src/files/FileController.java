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
    protected RandomAccessFile randomAccessFile;

    /**
     * Longitud del registro
     */
    protected int registerLength;

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    protected FileController(String fileName) {
        try {
            randomAccessFile = new RandomAccessFile(fileName,"rw");
            //Mantener la información permanente en el archivo
            //randomAccessFile.setLength(0);
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
        String values = String.valueOf(result);
        values.replaceAll("\u0000", " ");
        return values.trim();
    }

    /**
     * Pone toda la información en el registro como 0
     * @param position : posición del registro que se desea eliminar
     * @throws IOException : En caso de que haya un error en la escritura.
     */
    protected void clearRegister(long position) throws IOException {
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
    protected String formatString(String s, int length){
        StringBuilder format = new StringBuilder(s.trim());
        while (format.length()<length)
            format.append(Character.MIN_VALUE);
        return format.substring(0,length);
    }

    /**
     * Devuelve el número de registros que se han insertado en el archivo
     * @return Long : numero de registros
     * @throws IOException : En caso de algún error en la lectura del archivo, o que no exista
     */
    public long registersCount() throws IOException {
        return randomAccessFile.length() / registerLength;
    }
}
