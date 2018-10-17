package demos;

import difuzzyfication.Centroid;

public class CentroidTest {
    public static void main(String[] args) {
        Centroid centroid = new Centroid(new float[]{0.0f,0.7f,0.0f});
        System.out.println(centroid.getCentroid());
    }
}
