package edu.project4.Transformations;

import edu.project4.Point;

public class Disk {

    public static Point doTransform(Point point) {
        double r = Math.sqrt(point.x() * point.x() + point.y() * point.y()) * Math.PI;
        double theta = Math.atan2(point.y(), point.x());
        return new Point(
            theta * Math.sin(r),
            theta * Math.cos(r)
        );
    }
}
