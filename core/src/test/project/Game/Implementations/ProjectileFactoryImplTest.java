package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Drawer;
import project.Game.Projectile;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProjectileFactoryImplTest {
    // the purpose of this test is to check if the factory creates appropriate LogicalShape
    // behind the projectile
    private static final Point source = new Point(0f, 0f);
    private static final float res = (float)(Math.sqrt(2) + Math.sqrt(2) / 2);
    private static final float delta = 1e-6f;

    @Test
    public void testShapeSizesFirstQuarter() {
        Drawer drawer = mock(Drawer.class);
        when(drawer.getImgWidth("")).thenReturn(2f);
        when(drawer.getImgHeight("")).thenReturn(1f);
        Projectile.ProjectileFactory factory = new ProjectileFactoryImpl(drawer);
        Projectile p = factory.produce(source, new Point(5, 5), 0, 0, "");
        assertEquals(res, p.getWidth(), delta);
        assertEquals(res, p.getHeight(), delta);
    }

    @Test
    public void testShapeSizesSecondQuarter() {
        Drawer drawer = mock(Drawer.class);
        when(drawer.getImgWidth("")).thenReturn(2f);
        when(drawer.getImgHeight("")).thenReturn(1f);
        Projectile.ProjectileFactory factory = new ProjectileFactoryImpl(drawer);
        Projectile p = factory.produce(source, new Point(-5, 5), 0, 0, "");
        assertEquals(res, p.getWidth(), delta);
        assertEquals(res, p.getHeight(), delta);
    }

    @Test
    public void testShapeSizesThirdQuarter() {
        Drawer drawer = mock(Drawer.class);
        when(drawer.getImgWidth("")).thenReturn(2f);
        when(drawer.getImgHeight("")).thenReturn(1f);
        Projectile.ProjectileFactory factory = new ProjectileFactoryImpl(drawer);
        Projectile p = factory.produce(source, new Point(-5, -5), 0, 0, "");
        assertEquals(res, p.getWidth(), delta);
        assertEquals(res, p.getHeight(), delta);
    }

    @Test
    public void testShapeSizesForthQuarter() {
        Drawer drawer = mock(Drawer.class);
        when(drawer.getImgWidth("")).thenReturn(2f);
        when(drawer.getImgHeight("")).thenReturn(1f);
        Projectile.ProjectileFactory factory = new ProjectileFactoryImpl(drawer);
        Projectile p = factory.produce(source, new Point(5, -5), 0, 0, "");
        assertEquals(res, p.getWidth(), delta);
        assertEquals(res, p.getHeight(), delta);
    }

}