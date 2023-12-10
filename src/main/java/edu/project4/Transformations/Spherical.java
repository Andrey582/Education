package edu.project4.Transformations;

import edu.project4.Model.Point;

public class Spherical {

    public static Point doTransform(Point point) {
        double r = 1.0 / (point.x() * point.x() + point.y() + point.y());
        return new Point(
            r * point.x(),
            r * point.y()
        );
    }

    private Spherical() {
    }
}
