package files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileController {
    /**
     *
     */
    private RandomAccessFile randomAccessFile;

    /**
     *
     */
    private int registerLength;

    /**
     * Constructor por defecto
     * @param fileName : Nombre del archivo en la estructura de archivos
     */
    public FileController(String fileName) {
        try {
            randomAccessFile = new RandomAccessFile(fileName,"rw");
            randomAccessFile.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a string for the size specified
     * @param length : expected length for the string
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
     * Sets all data in row equals to 0
     * @param position :
     * @throws IOException : En caso de que haya un error en la escritura.
     */
    public void clearRegister(long position) throws IOException {
        randomAccessFile.seek(position);
        for (int i = 0; i < registerLength;i++){
            randomAccessFile.writeByte(0);
        }
    }

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
