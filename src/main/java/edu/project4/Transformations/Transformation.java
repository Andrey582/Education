package edu.project4.Transformations;

import edu.project4.Model.Point;
import java.util.function.Function;

@FunctionalInterface
public interface Transformation extends Function<Point, Point> {

}
