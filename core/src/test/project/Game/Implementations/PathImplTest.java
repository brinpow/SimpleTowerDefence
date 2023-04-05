package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.LogicalShape;
import project.Point;
import project.Game.GameException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathImplTest {
    @Test
    public void constructorThrowsWhenTooLittlePoints() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(1, 2));
        assertThrows(GameException.class, () -> new PathImpl(pointList, 1, 0));
    }

    @Test
    public void constructorThrowsWhenWidthIsNotPositive() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(1, 2));
        pointList.add(new Point(3, 2));
        assertThrows(GameException.class, () -> new PathImpl(pointList, 0f, 0));
    }

    @Test
    void testGetPointThrows() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(3, 0));
        pointList.add(new Point(3, 5));
        PathImpl path = new PathImpl(pointList, 1, 0);
        assertThrows(GameException.class, () -> path.getPoint(9));
    }

    @Test
    void testGetIndex() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(3, 0));
        pointList.add(new Point(3, 5));
        PathImpl path = new PathImpl(pointList, 1, 2);
        assertEquals(2, path.getIndex());
    }

    @Test
    void testGetPoint() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(3, 0));
        pointList.add(new Point(3, 5));
        PathImpl path = new PathImpl(pointList, 1, 0);
        Point p2 = path.getPoint(2);
        Point p5 = path.getPoint(5);
        assertEquals(2, p2.x());
        assertEquals(0, p2.y());
        assertEquals(3, p5.x());
        assertEquals(2, p5.y());
    }

    @Test
    void testLength() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(3, 0));
        pointList.add(new Point(3, 5));
        PathImpl path = new PathImpl(pointList, 1, 0);
        assertEquals(8, path.length());
    }

    @Test
    void testGetShape() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(3, 0));
        pointList.add(new Point(3, 5));
        PathImpl path = new PathImpl(pointList, 2f, 0);
        LogicalShape s = path.getShape();
        Shape s1 = new Shape(1.5f, 1.5f);
        s1.addRectangle(0, 0, 1.0f, 0.02f);
        Shape s2 = new Shape(0, 1.25f);
        s2.addRectangle(0, 0, 0.25f, 0.25f);
        assertTrue(s.intersects(s1));
        assertFalse(s.intersects(s2));
    }
}
