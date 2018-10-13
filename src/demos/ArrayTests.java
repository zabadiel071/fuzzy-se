package demos;


import fuzzyfication.models.Point;

import java.util.ArrayList;

public class ArrayTests {

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point((byte)1,(byte)20));
        points.add(new Point((byte)1,(byte)20));
        points.add(new Point((byte)1,(byte)20));
        points.add(new Point((byte)1,(byte)20));
        try{
            points.get(4);
        }catch (IndexOutOfBoundsException e){
            System.out.println(":)");
        }
    }
}
