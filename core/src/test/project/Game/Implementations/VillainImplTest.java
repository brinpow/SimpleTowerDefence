package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.Villain;
import project.Point;
import project.Game.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VillainImplTest {
    private static Path createPath(int l) {
        Path p = mock(Path.class);
        when(p.length()).thenReturn(l);
        when(p.getIndex()).thenReturn(0);
        when(p.getDirection(0)).thenReturn(Path.direction.up);
        for(int i = 0; i < 5; i++)
            when(p.getPoint(i)).thenReturn(new Point(i, i));
        return p;
    }

    @Test
    void testVillainAlive() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(1, 1, path, "", Villain.VillainType.b1,0);

        assertTrue(villain.isAlive());
    }

    @Test
    void testVillainNotAlive() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(0, 1, path, "",Villain.VillainType.b1,0);

        assertFalse(villain.isAlive());
    }

    @Test
    void testDealDamageAlive() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(3, 1, path, "",Villain.VillainType.b1,0);

        villain.dealDamage(2);

        assertTrue(villain.isAlive());
    }

    @Test
    void testDealDamageNotAlive() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        villain.dealDamage(3);

        assertFalse(villain.isAlive());
    }

    @Test
    void testGetCorrectPosition() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        assertEquals(0, villain.getX());
        assertEquals(0, villain.getY());

    }

    @Test
    void testAdvance() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        villain.advance();

        assertEquals(1, villain.getX());
        assertEquals(1, villain.getY());
    }

    @Test
    void testAdvanceTimes() {
        Path path = createPath(5);
        Villain villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        villain.advance(3);

        assertEquals(3, villain.getX());
        assertEquals(3, villain.getY());
    }

    @Test
    void testTimeAlive() {
        Path path = createPath(5);
        Villain villain = new VillainImpl(1, 2, path, "",Villain.VillainType.b1,0);

        villain.advance();
        villain.advance();

        assertEquals(2, villain.timeAlive());
    }

    @Test
    void testRemainingPath() {
        Path path = createPath(5);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        villain.advance();
        villain.advance();

        assertEquals(3, villain.remainingPath());
    }

    @Test
    void testIsOutFalse() {
        Path path = createPath(3);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);
        villain.advance();
        villain.advance();

        assertFalse(villain.isOut());
    }

    @Test
    void testIsOutTrue() {
        Path path = createPath(3);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);
        villain.advance();
        villain.advance();
        villain.advance();

        assertTrue(villain.isOut());
    }

    @Test
    void testGetPathIndex() {
        Path path = createPath(3);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        assertEquals(path.getIndex(),villain.getPathIndex());
    }

    @Test
    void testGetHp() {
        Path path = createPath(3);
        int hp = 10;
        VillainImpl villain = new VillainImpl(hp, 1, path, "",Villain.VillainType.b1,0);

        assertEquals(hp,villain.getHp());
    }

    @Test
    void testGetType() {
        Path path = createPath(3);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        assertEquals(Villain.VillainType.b1,villain.getType());
    }

    @Test
    void testGetGoldValue() {
        Path path = createPath(3);
        int goldValue = 10;
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,goldValue);

        assertEquals(goldValue, villain.getGoldValue());
    }

    @Test
    void testGetPathDirection() {
        Path path = createPath(3);
        VillainImpl villain = new VillainImpl(1, 1, path, "",Villain.VillainType.b1,0);

        assertEquals(path.getDirection(0),villain.getDirection());
    }
}
