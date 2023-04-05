package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.Tower;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TowerManagerImplTest {
    @Test
    void testIsDeleteEqual(){
        TowerManagerImpl towerManager = new TowerManagerImpl();
        Tower tower1 = mock(Tower.class);
        towerManager.notifyDelete(tower1);
        assertTrue(towerManager.isToDelete(tower1));
    }
    @Test
    void testIsDeleteNotEqual(){
        TowerManagerImpl towerManager = new TowerManagerImpl();
        Tower tower1 = mock(Tower.class);
        Tower tower2 = mock(Tower.class);
        towerManager.notifyDelete(tower1);
        assertFalse(towerManager.isToDelete(tower2));
    }
    @Test
    void testIsToUpgradeEqual(){
        TowerManagerImpl towerManager = new TowerManagerImpl();
        Tower tower1 = mock(Tower.class);
        towerManager.notifyUpgrade(tower1);
        assertTrue(towerManager.isToUpgrade(tower1));
    }
    @Test
    void testIsUpgradeNotEqual(){
        TowerManagerImpl towerManager = new TowerManagerImpl();
        Tower tower1 = mock(Tower.class);
        Tower tower2 = mock(Tower.class);
        towerManager.notifyUpgrade(tower1);
        assertFalse(towerManager.isToUpgrade(tower2));
    }

    @Test
    void testReset() {
        TowerManagerImpl towerManager = new TowerManagerImpl();
        Tower tower1 = mock(Tower.class);
        Tower tower2 = mock(Tower.class);
        towerManager.notifyDelete(tower1);
        towerManager.notifyUpgrade(tower2);
        towerManager.reset();
        assertFalse(towerManager.isToDelete(tower1));
        assertFalse(towerManager.isToUpgrade(tower2));
    }
}