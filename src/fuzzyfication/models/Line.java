package fuzzyfication.models;

/**
 * Representación de una recta
 */
public class Line {
    /**
     * Puntos que definen a la recta
     */
    private Point p1;
    private Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Calculo de la pendiente
     * @return double : Valor de la pendiente
     */
    private float slope(){
        return (p2.getNormalizedMu() - p1.getNormalizedMu()) / (p2.getX() - p1.getX());
    }

    /**
     * Obtiene el valor de Mu para el valor de X insertado
     * @param x : Valor en X del que se desea conocer su membresía
     * @return float : Valor de Mu para X
     */
    public float getMuValue(byte x){
        return slope() * (x - p1.getX()) + p1.getNormalizedMu();
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "Line{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    public float getMuValue(float x) {
        return slope() * (x - p1.getX()) + p1.getNormalizedMu();
    }
}
