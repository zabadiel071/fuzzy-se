package fuzzyfication.models;

public class Point {

    /**
     * x -> byte, mu -> byte
     */
    static final int BYTES = 2 * Byte.BYTES;

    /**
     * Valor en X del punto
     */
    private byte x;

    /**
     * Valor en MU (Nivel de memebresía)
     * Está en tipo byte, pero en el cálculo debe hacerse el ajuste  (mu * 0.01) Para manejar el valor de 0 a 1
     */
    private byte mu;

    public Point(byte x, byte mu) {
        this.x = x;
        this.mu = mu;
    }

    float getNormalizedMu(){
        return (float) (mu * 0.01);
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getMu() {
        return mu;
    }

    public void setMu(byte mu) {
        this.mu = mu;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", mu=" + mu +
                '}';
    }
}
