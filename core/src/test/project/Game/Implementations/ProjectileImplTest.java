package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.Projectile;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileImplTest {
    private static final float delta = 1e-6f;
    @Test
    public void testGetPower() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(0f, 0f),
                15f, 5, "testString.png");
        assertEquals(5, p.getPower());
    }

    @Test
    public void testGetImgName() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(0f, 0f),
                15f, 5, "testString.png");
        assertEquals("testString.png", p.getImgName());
    }

    @Test
    public void testGetSpeed()
    {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(0f, 0f),
                15f, 5, "testString.png");
        assertEquals(15f, p.getSpeed());
        
    }

    @Test
    public void testRotationFirstQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(2f, 2f),
                15f, 5, "");
        assertEquals(Math.PI / 4f, p.getRotation(), delta);
    }

    @Test
    public void testRotationSecondQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(-2f, 2f),
                15f, 5, "");
        assertEquals(Math.PI - Math.PI / 4f, p.getRotation(), delta);
    }

    @Test
    public void testRotationThirdQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(-2f, -2f),
                15f, 5, "");
        assertEquals(Math.PI + Math.PI / 4f, p.getRotation(), delta);
    }

    @Test
    public void testRotationFourthQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(2f, -2f),
                15f, 5, "");
        assertEquals(2f * Math.PI - Math.PI / 4f, p.getRotation(), delta);
    }

    @Test
    public void testAdvanceFirstQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(6f, 8f),
                5f, 5, "");
        p.advance();
        assertEquals(3f, p.getX(), delta);
        assertEquals(4f, p.getY(), delta);
    }

    @Test
    public void testAdvanceSecondQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(-6f, 8f),
                5f, 5, "");
        p.advance();
        assertEquals(-3f, p.getX(), delta);
        assertEquals(4f, p.getY(), delta);
    }

    @Test
    public void testAdvanceThirdQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(-6f, -8f),
                5f, 5, "");
        p.advance();
        assertEquals(-3f, p.getX(), delta);
        assertEquals(-4f, p.getY(), delta);
    }

    @Test
    public void testAdvanceFourthQuarter() {
        Projectile p = new ProjectileImpl(new Point(0f, 0f), new Point(6f, -8f),
                5f, 5, "");
        p.advance();
        assertEquals(3f, p.getX(), delta);
        assertEquals(-4f, p.getY(), delta);
    }

    @Test
    public void testSecondConstructorFirstQuarter() {
        Projectile p = new ProjectileImpl(new Point(0, 0), (float)Math.PI / 4f,
                (float)Math.sqrt(2), 5, "");
        p.advance();
        assertEquals(1f, p.getX(), delta);
        assertEquals(1f, p.getY(), delta);
    }

    @Test
    public void testSecondConstructorSecondQuarter() {
        Projectile p = new ProjectileImpl(new Point(0, 0), (float)Math.PI / 2f + (float)Math.PI / 4f,
                (float)Math.sqrt(2), 5, "");
        p.advance();
        assertEquals(-1f, p.getX(), delta);
        assertEquals(1f, p.getY(), delta);
    }

    @Test
    public void testSecondConstructorThirdQuarter() {
        Projectile p = new ProjectileImpl(new Point(0, 0), (float)Math.PI + (float)Math.PI / 4f,
                (float)Math.sqrt(2), 5, "");
        p.advance();
        assertEquals(-1f, p.getX(), delta);
        assertEquals(-1f, p.getY(), delta);
    }

    @Test
    public void testSecondConstructorFourthQuarter() {
        Projectile p = new ProjectileImpl(new Point(0, 0), 2 * (float)Math.PI - (float)Math.PI / 4f,
                (float)Math.sqrt(2), 5, "");
        p.advance();
        assertEquals(1f, p.getX(), delta);
        assertEquals(-1f, p.getY(), delta);
    }
}