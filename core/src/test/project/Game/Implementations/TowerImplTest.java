package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.*;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TowerImplTest {

    @Test
    void testGetType() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);

        assertEquals(Tower.TowerType.b1, t.getType());

    }

    @Test
    void testGetRange() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm, "",Tower.TowerType.b1, ps);

        assertEquals(100, t.getRange());

    }

    @Test
    void testGetPower() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        when(ps.getPower()).thenReturn(5);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);

        assertEquals(ps.getPower(),t.getPower());

    }

    @Test
    void testSetX() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);
        float posX = 121;
        t.setX(posX);

        assertEquals(posX,t.getX());
    }

    @Test
    void testSetY() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);
        float posY = 121;
        t.setY(posY);

        assertEquals(posY,t.getY());

    }

    @Test
    void testShootingOnEmptyListReturnsNull() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 1, 10,
                tm,"",Tower.TowerType.b1, ps);

        assertNull(t.shoot(new GdxList<>()));
    }

    @Test
    void testWhenNoVillainsInRangeShootReturnsNull() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 1, 10, tm,"",Tower.TowerType.b1, ps);
        Villain villain = mock(Villain.class);
        when(villain.getX()).thenReturn(1f);
        when(villain.getY()).thenReturn(1f);
        when(villain.getDirection()).thenReturn(Path.direction.down);
        when(ps.get(new Point(0f, 0f), new Point(1, 1))).thenReturn(mock(Projectile.class));
        ListInterface<Villain> villains = new GdxList<>();
        villains.add(villain);

        assertNull(t.shoot(villains));
    }

    @Test
    void testWhenVillainInRangeThenShoots() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 2, 10, tm,"",Tower.TowerType.b1, ps);
        Villain villain = mock(Villain.class);
        when(villain.getX()).thenReturn(1f);
        when(villain.getY()).thenReturn(1f);
        when(villain.getDirection()).thenReturn(Path.direction.down);
        when(ps.get(new Point(0f, 0f), new Point(1, 1))).thenReturn(mock(Projectile.class));
        ListInterface<Villain> villains = new GdxList<>();
        villains.add(villain);

        assertNotNull(t.shoot(villains));
    }

    @Test
    void testWhenMultipleInRangeTheClosestToFinishIsChosen() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 2, 10, tm,"",Tower.TowerType.b1, ps);
        Villain villain1 = mock(Villain.class);
        when(villain1.getX()).thenReturn(-1f);
        when(villain1.getY()).thenReturn(1f);
        when(villain1.getDirection()).thenReturn(Path.direction.down);
        Villain villain2 = mock(Villain.class);
        when(villain2.getX()).thenReturn(1f);
        when(villain2.getY()).thenReturn(1f);
        when(villain2.getDirection()).thenReturn(Path.direction.down);
        Villain villain3 = mock(Villain.class);
        when(villain3.getX()).thenReturn(1f);
        when(villain3.getY()).thenReturn(-1f);
        when(villain3.getDirection()).thenReturn(Path.direction.down);
        when(villain1.remainingPath()).thenReturn(3);
        when(villain2.remainingPath()).thenReturn(1);
        when(villain3.remainingPath()).thenReturn(2);
        Projectile projectile1 = mock(Projectile.class);
        Projectile projectile2 = mock(Projectile.class);
        Projectile projectile3 = mock(Projectile.class);
        when(ps.get(new Point(0f, 0f), new Point(-1, 1))).thenReturn(projectile1);
        when(ps.get(new Point(0f, 0f), new Point(1, 1))).thenReturn(projectile2);
        when(ps.get(new Point(0f, 0f), new Point(-1, -1))).thenReturn(projectile3);
        ListInterface<Villain> villains = new GdxList<>();
        villains.add(villain1);
        villains.add(villain2);
        villains.add(villain3);

        assertSame(projectile2, t.shoot(villains));
    }

    @Test
    void testShootingStartsCooldown() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 2, 2, tm,"",Tower.TowerType.b1, ps);
        Villain villain = mock(Villain.class);
        when(villain.getX()).thenReturn(1f);
        when(villain.getY()).thenReturn(1f);
        when(villain.getDirection()).thenReturn(Path.direction.down);
        when(ps.get(new Point(0f, 0f), new Point(1, 1))).thenReturn(mock(Projectile.class));
        ListInterface<Villain> villains = new GdxList<>();
        villains.add(villain);

        assertNotNull(t.shoot(villains));
        assertNull(t.shoot(villains));
        assertNull(t.shoot(villains));
        assertNotNull(t.shoot(villains));
    }

    @Test
    void testWhenShootReturnsNullItDoesNotStartCooldown() {
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 2, 10, tm,"",Tower.TowerType.b1, ps);
        Villain villain = mock(Villain.class);
        when(villain.getX()).thenReturn(1f);
        when(villain.getY()).thenReturn(1f);
        when(villain.getDirection()).thenReturn(Path.direction.down);
        when(ps.get(new Point(0f, 0f), new Point(1, 1))).thenReturn(mock(Projectile.class));
        ListInterface<Villain> villains = new GdxList<>();

        assertNull(t.shoot(villains));
        villains.add(villain);
        assertNotNull(t.shoot(villains));
    }

    @Test
    void testGetLevel(){
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);

        assertEquals(t.getType().getLevel(),t.getLevel());
    }

    @Test
    void testisFinal(){
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);

        assertEquals(t.getType().isFinal(),t.isFinal());
    }

    @Test
    void testUpgrade(){
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);
        t.upgrade();
        verify(tm).notifyUpgrade(t);
    }

    @Test
    void testDelete(){
        ProjectileSupplier ps = mock(ProjectileSupplier.class);
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower t = new TowerImpl(new Point(0,0), 100, 10,
                tm,"",Tower.TowerType.b1, ps);
        t.delete();
        verify(tm).notifyDelete(t);
    }
}