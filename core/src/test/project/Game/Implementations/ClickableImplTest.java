package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.Clickable;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ClickableImplTest {
    @Test
    void testAddFunction(){
        Point point = new Point(0,0);
        ClickableImpl clickable = new ClickableImpl(point, "test.png");
        clickable.addClickFunction(mock(Clickable.ClickFunction.class));

        assertTrue(clickable.getClickFunctions().hasNext());
    }

    @Test
    void testRemoveFunction(){
        Point point = new Point(0,0);
        ClickableImpl clickable = new ClickableImpl(point, "test.png");
        Clickable.ClickFunction clickFunction = mock(Clickable.ClickFunction.class);
        clickable.addClickFunction(clickFunction);
        clickable.removeClickFunction(clickFunction);

        assertFalse(clickable.getClickFunctions().hasNext());
    }
}
