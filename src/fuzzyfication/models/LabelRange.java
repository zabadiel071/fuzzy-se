package fuzzyfication.models;

/**
 * Define el rango que puede tener cada etiqueta
 */
public class LabelRange {
    /**
     * Valor mínimo en X
     */
    private byte xMin;

    /**
     * Valor máximo en X
     */
    private byte xMax;

    public LabelRange(byte xMin, byte xMax) {
        this.xMin = xMin;
        this.xMax = xMax;
    }

    public byte getxMin() {
        return xMin;
    }

    public void setxMin(byte xMin) {
        this.xMin = xMin;
    }

    public byte getxMax() {
        return xMax;
    }

    public void setxMax(byte xMax) {
        this.xMax = xMax;
    }
}
