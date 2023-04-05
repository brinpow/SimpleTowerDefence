package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.GameException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerImplTest {

    @Test
    void testPlayerAlive() {
        PlayerImpl player = new PlayerImpl(1, 10);

        assertTrue(player.isAlive());
    }

    @Test
    void testPlayerNotAlive() {
        PlayerImpl player = new PlayerImpl(-1, 10);

        assertFalse(player.isAlive());
    }

    @Test
    void testDealDamageAlive() {
        PlayerImpl player = new PlayerImpl(3, 10);

        player.changeHp(-2);

        assertTrue(player.isAlive());
    }

    @Test
    void testDealDamageNotAlive() {
        PlayerImpl player = new PlayerImpl(3, 10);

        player.changeHp(-3);

        assertFalse(player.isAlive());
    }

    @Test
    void testGetGold() {
        PlayerImpl player = new PlayerImpl(3, 10);

        assertEquals(10, player.getGold());
    }

    @Test
    void testEarnGold() {
        PlayerImpl player = new PlayerImpl(3, 10);

        player.changeGold(10);

        assertEquals(20, player.getGold());
    }

    @Test
    void testSpendCorrectlyGold() {
        PlayerImpl player = new PlayerImpl(3, 10);

        player.changeGold(-5);

        assertEquals(5, player.getGold());
    }

    @Test
    void testSpendIncorrectlyGold() {
        PlayerImpl player = new PlayerImpl(3, 10);

        assertThrows(GameException.class, ()->player.changeGold(-15));
    }
    @Test
    void testGetHp(){
        PlayerImpl player = new PlayerImpl(10,10);

        assertEquals(10, player.getHp());
    }
    @Test
    void testAddHpPositive(){
        PlayerImpl player = new PlayerImpl(10, 10);

        player.changeHp(20);

        assertEquals(30, player.getHp());
    }
    @Test
    void testAddHpNegative(){
        PlayerImpl player = new PlayerImpl(10,10);
        player.changeHp(-5);

        assertEquals(5, player.getHp());
    }

    @Test
    void testCanAffordTrue(){
        PlayerImpl player = new PlayerImpl(10,10);

        assertTrue(player.canAfford(5));
    }

    @Test
    void testCantAffordFalse(){
        PlayerImpl player = new PlayerImpl(10,10);

        assertFalse(player.canAfford(20));
    }

}