package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Drawer;
import project.CommonInterfaces.TextDrawer;
import project.Game.*;
import project.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TowerBarImplTest {
    public static abstract class MockManager implements Manager.ObjectManager<Clickable> {}

    @Test
    void testFillClickablesAndConstruction(){
        Drawer drawer = mock(Drawer.class);
        Manager.ObjectManager<Clickable> clickableManager = mock(MockManager.class);
        Tower tower = new TowerImpl(new Point(0,0), 0,0,mock(Tower.TowerManager.class),
                "", Tower.TowerType.a1,mock(ProjectileSupplier.class));
        TowerBarImpl towerBar = new TowerBarImpl(tower, clickableManager, drawer);
        towerBar.fillClickables();
        verify(clickableManager, times(4)).addNewObject(any());
    }

    @Test
    void testDraw(){
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        Tower tower = new TowerImpl(new Point(0,0), 0,0,mock(Tower.TowerManager.class),
                "", Tower.TowerType.a1,mock(ProjectileSupplier.class));
        TowerBarImpl towerBar = new TowerBarImpl(tower, mock(MockManager.class), drawer);
        towerBar.draw(drawer,textDrawer);
        verify(drawer, times(3)).draw(any());
        verify(textDrawer, times(5)).write(any());
    }

    @Test
    void testFillClear(){
        Drawer drawer = mock(Drawer.class);
        Manager.ObjectManager<Clickable> clickableManager = mock(MockManager.class);
        Tower tower = new TowerImpl(new Point(0,0), 0,0,mock(Tower.TowerManager.class),
                "", Tower.TowerType.a1,mock(ProjectileSupplier.class));
        TowerBarImpl towerBar = new TowerBarImpl(tower, clickableManager, drawer);
        towerBar.clear();
        verify(clickableManager, times(2)).removeObject(any());
    }

    @Test
    void testFillGetActiveTowerBar(){
        TowerBarImpl.setActiveTowerBar(null);
        assertNull(TowerBarImpl.getActiveTowerBar());
    }

    @Test
    void testFillSetActiveTowerBar(){
        TowerBar towerBar = mock(TowerBar.class);
        TowerBarImpl.setActiveTowerBar(towerBar);
        assertEquals(towerBar, TowerBarImpl.getActiveTowerBar());
    }

    @Test
    void testFillDrawTowerBar(){
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        TowerBar towerBar = mock(TowerBar.class);
        TowerBarImpl.setActiveTowerBar(towerBar);
        TowerBarImpl.drawTowerBar(drawer, textDrawer);
        verify(towerBar).draw(drawer,textDrawer);
    }

}