package edu.project4.Transformations;

import edu.project4.Point;

public class Sinusoid {

    public static Point doTransform(Point point) {
        return new Point(
          Math.sin(point.x()),
          Math.sin(point.y())
        );
    }
}
