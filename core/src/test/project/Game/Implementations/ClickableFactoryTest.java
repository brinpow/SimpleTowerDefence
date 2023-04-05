package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Drawer;
import project.Game.Clickable;
import project.Game.Manager;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ClickableFactoryTest {
    @Test
    void testProduce() {
        Clickable.ClickFunction function = mock(Clickable.ClickFunction.class);
        Drawer drawer = mock(Drawer.class);
        Manager.ObjectManager<Clickable> clickableManager = mock(Manager.ObjectManager.class);
        Clickable clickable = ClickableFactory.produce(new Point(0,0), "", function, clickableManager, drawer);
        assertFalse(clickable.getRectangles().isEmpty());
        assertTrue(clickable.getClickFunctions().hasNext());
        assertEquals(clickable.getClickFunctions().next(), function);
        verify(clickableManager).addNewObject(clickable);
    }

}