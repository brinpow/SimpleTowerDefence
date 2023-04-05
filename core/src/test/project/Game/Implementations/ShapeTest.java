package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.GameException;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    @Test
    void testGetX() {
        Shape shape = new Shape(10,12);

        assertEquals(10, shape.getX());
    }

    @Test
    void testGetY() {
        Shape shape = new Shape(10,12);

        assertEquals(12, shape.getY());
    }

    @Test
    void testAddRectangle() {
        Shape shape= new Shape(10,10);
        shape.addRectangle(0,0,10,10);

        assertFalse(shape.getRectangles().isEmpty());
    }

    @Test
    void testGetWidth() {
        Shape shape = new Shape(1,2);
        shape.addRectangle(0, 0, 4, 4);
        shape.addRectangle(1, 1, 4, 4);

        assertEquals(5, shape.getWidth());
    }

    @Test
    void testGetInitializeHeight(){
        Shape shape = new Shape(1,2);
        shape.addRectangle(0, 0, 4, 4);
        shape.addRectangle(1, 1, 4, 4);

        assertEquals(5, shape.getHeight());
    }

    @Test
    void testContains() {
        Shape shape= new Shape(10,10);
        shape.addRectangle(0,0,100,100);

        assertTrue(shape.contains(50,60));
    }

    @Test
    void testNotContains() {
        Shape shape= new Shape(10,10);
        shape.addRectangle(0,0,100,100);

        assertFalse(shape.contains(0,0));
    }

    @Test
    void testUninitializedShapeContains() {
        Shape shape= new Shape(10,10);
        assertThrows(GameException.class,()-> shape.contains(0,0));
    }

    @Test
    void testIntersects() {
        Shape shape1 = new Shape(10, 10);
        shape1.addRectangle(0, 0, 5, 5);
        Shape shape2 = new Shape(5, 5);
        shape2.addRectangle(1, 1, 5, 5);
        assertTrue(shape1.intersects(shape2));
    }

    @Test
    void testNotIntersects() {
        Shape shape1 = new Shape(10, 10);
        shape1.addRectangle(0, 0, 5, 5);
        Shape shape2 = new Shape(5, 5);
        shape2.addRectangle(0, 0, 4, 4);
        assertFalse(shape1.intersects(shape2));
    }

    @Test
    void testUninitializedShapeIntersects() {
        Shape shape1 = new Shape(10, 10);
        Shape shape2 = new Shape(5, 5);
        shape2.addRectangle(0, 0, 4, 4);

        assertThrows(GameException.class,()-> shape1.intersects(shape2));
    }
}
