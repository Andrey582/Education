package edu.project4;

import edu.project4.Transformations.Diamond;
import edu.project4.Transformations.Disk;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
    public static void main(String[] args) {

        for (int i = 1; i <= 14; i++) {
            Fractal fractal = new Fractal(1920, 1080, 4);
            long start = System.nanoTime();
            fractal.createFractal(100000, 1000, 2,
                List.of(
                    Diamond::doTransform,
                    Disk::doTransform
                ), i);
            long end = System.nanoTime();
            LOGGER.info("thread count " + i + ": complete in " + (end - start) / 1000000000 + " sec.");
        }
    }

    private Main() {
    }
}
