package game;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LineTest {

    @Test
    void returnedPointsCannotMutateLine() {
        Line line = new Line(0, 0, 2, 0);

        List<Point> exposedPoints = line.getPoints();
        exposedPoints.get(0).x = 99;

        assertEquals(0, line.getPoints().get(0).x);
        assertThrows(
            UnsupportedOperationException.class,
            exposedPoints::clear);
    }

    @Test
    void iteratorCannotMutateLinePoints() {
        Line line = new Line(0, 0, 2, 0);

        Point exposedPoint = line.iterator().next();
        exposedPoint.x = 99;

        assertEquals(0, line.iterator().next().x);
    }
}
