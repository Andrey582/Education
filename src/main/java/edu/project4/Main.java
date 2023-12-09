package edu.project4;

import edu.project4.Transformations.Diamond;
import edu.project4.Transformations.Disk;
import edu.project4.Transformations.Linear;
import edu.project4.Transformations.Sinusoid;
import edu.project4.Transformations.Spherical;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Fractal fractal = new Fractal(1920, 1080, 4);
            long start = System.nanoTime();
            fractal.generate(50000, 1000, 2,
                List.of(
                    Diamond::doTransform,
                    Disk::doTransform
                ));
            long end = System.nanoTime();

            System.out.println(i + " -- " + (end - start));
        }

        for (int i = 0; i < 5; i++) {
            Fractal fractal = new Fractal(1920, 1080, 4);
            long start = System.nanoTime();
            fractal.createFractal(50000, 1000, 2,
                List.of(
                    Diamond::doTransform,
                    Disk::doTransform
                ), 4);
            long end = System.nanoTime();

            System.out.println(i + " -- " + (end - start));
        }
    }

}
