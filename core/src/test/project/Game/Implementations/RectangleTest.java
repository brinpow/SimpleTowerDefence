package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.LogicalShape;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RectangleTest {
    private static LogicalShape createOwner() {
        LogicalShape ls = mock(LogicalShape.class);
        when(ls.getX()).thenReturn(0f);
        when(ls.getY()).thenReturn(0f);
        return ls;
    }

    @Test
    void containsTest() {
        LogicalShape.Rectangle rec = new LogicalShape.Rectangle(createOwner(), 0, 0, 10, 5);
        assertTrue(rec.contains(8, 4));
    }

    @Test
    void notContainsTest() {
        LogicalShape.Rectangle rec = new LogicalShape.Rectangle(createOwner(), 0, 0, 10, 5);
        assertFalse(rec.contains(11, 4));
    }

    @Test
    void intersectsTest() {
        LogicalShape.Rectangle rec1 = new LogicalShape.Rectangle(createOwner(), 0, 0, 10, 5);
        LogicalShape.Rectangle rec2 = new LogicalShape.Rectangle(createOwner(), 5, 3, 10, 5);
        assertTrue(rec1.intersects(rec2));
    }

    @Test
    void notIntersectsTest() {
        LogicalShape.Rectangle rec1 = new LogicalShape.Rectangle(createOwner(), 0, 0, 10, 5);
        LogicalShape.Rectangle rec2 = new LogicalShape.Rectangle(createOwner(), 15, 6, 10, 5);
        assertFalse(rec1.intersects(rec2));
    }
}
