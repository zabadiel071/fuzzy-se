package fuzzyfication.models;

public class Point {

    /**
     * Valor en X del punto
     */
    private byte x;

    /**
     * Valor en MU (Nivel de memebresía)
     */
    private byte mu;

    public Point(byte x, byte mu) {
        this.x = x;
        this.mu = mu;
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
