package edu.project4.Transformations;

import edu.project4.Model.Point;

public class Linear {

    public static Point doTransform(Point point) {
        return new Point(
            point.x(),
            point.y()
        );
    }

    private Linear() {
    }
}
