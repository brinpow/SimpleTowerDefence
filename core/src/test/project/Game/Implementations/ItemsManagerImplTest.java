package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.ItemsManager;
import project.Game.Tower;

import static org.junit.jupiter.api.Assertions.*;

class ItemsManagerImplTest {
    @Test
    void testAtTheBeginningNothingIsUsed() {
        ItemsManager im = new ItemsManagerImpl();
        assertNull(im.getTowerUsed());
        assertFalse(im.isHealUsed());
        assertFalse(im.isMagicUsed());
    }

    @Test
    void testHealUsedAfterNotify() {
        ItemsManager im = new ItemsManagerImpl();
        im.notifyHealUsed();
        assertTrue(im.isHealUsed());
        // no side effects
        assertFalse(im.isMagicUsed());
        assertNull(im.getTowerUsed());
    }

    @Test
    void testMagicUsedAfterNotify() {
        ItemsManager im = new ItemsManagerImpl();
        im.notifyMagicUsed();
        assertTrue(im.isMagicUsed());

        assertFalse((im.isHealUsed()));
        assertNull(im.getTowerUsed());
    }

    @Test
    void testTowerUsedAfterNotify() {
        ItemsManager im = new ItemsManagerImpl();
        im.notifyTowerUsed(Tower.TowerType.a1);
        assertEquals(Tower.TowerType.a1, im.getTowerUsed());

        assertFalse(im.isHealUsed());
        assertFalse(im.isMagicUsed());
    }

    @Test
    void testNotifiesDoNotEraseEachOther() {
        ItemsManager im = new ItemsManagerImpl();
        im.notifyTowerUsed(Tower.TowerType.a1);
        im.notifyMagicUsed();
        im.notifyHealUsed();
        assertEquals(Tower.TowerType.a1, im.getTowerUsed());
        assertTrue(im.isMagicUsed());
        assertTrue(im.isHealUsed());
    }

    @Test
    void testNothingIsUsedAfterReset() {
        ItemsManager im = new ItemsManagerImpl();
        im.notifyTowerUsed(Tower.TowerType.a1);
        im.notifyMagicUsed();
        im.notifyHealUsed();
        im.reset();
        assertNull(im.getTowerUsed());
        assertFalse(im.isHealUsed());
        assertFalse(im.isMagicUsed());
    }
}