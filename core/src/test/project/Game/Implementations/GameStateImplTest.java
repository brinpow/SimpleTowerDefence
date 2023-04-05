package project.Game.Implementations;

import org.junit.jupiter.api.Test;

import project.Game.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameStateImplTest {
    private GameStateImpl createSampleGameState() {
        Player player = mock(Player.class);
        return new GameStateImpl(player);
    }

    @Test
    void  testGetVillainList() {
        GameStateImpl gameState = createSampleGameState();
        ListInterface<Villain> villainList = gameState.getVillainList();
        assertNotNull(villainList);
        assertSame(villainList, gameState.getVillainList());
    }

    @Test
    void  testGetTowerList() {
        GameStateImpl gameState = createSampleGameState();
        ListInterface<Tower> towerList = gameState.getTowerList();
        assertNotNull(towerList);
        assertSame(towerList, gameState.getTowerList());
    }

    @Test
    void testGetProjectileList() {
        GameStateImpl gameState = createSampleGameState();
        ListInterface<Projectile> projectileList = gameState.getProjectileList();
        assertNotNull(projectileList);
        assertSame(projectileList, gameState.getProjectileList());
    }

    @Test
    void testGetMiscShapes() {
        GameStateImpl gameState = createSampleGameState();
        ListInterface<LogicalShape> miscShapes = gameState.getMiscShapes();
        assertNotNull(miscShapes);
        assertSame(miscShapes, gameState.getMiscShapes());
    }

    @Test
    void  testGetPlayer() {
        Player player = mock(Player.class);
        GameStateImpl gameState = new GameStateImpl(player);
        assertSame(player, gameState.getPlayer());
    }

    @Test
    void testGetPause() {
        GameStateImpl gameState = createSampleGameState();
        assertFalse(gameState.isGamePaused());
    }

    @Test
    void testSetPauseTrue() {
        GameStateImpl gameState = createSampleGameState();
        gameState.setPause(true);
        assertTrue(gameState.isGamePaused());
    }

    @Test
    void testSetPauseFalse() {
        GameStateImpl gameState = createSampleGameState();
        gameState.setPause(true);
        gameState.setPause(false);
        assertFalse(gameState.isGamePaused());
    }

    @Test
    void testGetStatus() {
        GameStateImpl gameState = createSampleGameState();
        assertEquals(GameState.GameStatus.IN_GAME,gameState.getStatus());
    }

    @Test
    void testSetStatus() {
        GameStateImpl gameState = createSampleGameState();
        gameState.setStatus(GameState.GameStatus.GAME_OVER);
        assertEquals(GameState.GameStatus.GAME_OVER, gameState.getStatus());
        gameState.setStatus(GameState.GameStatus.GAME_FINISHED);
        assertEquals(GameState.GameStatus.GAME_FINISHED, gameState.getStatus());
        gameState.setStatus(GameState.GameStatus.IN_GAME);
        assertEquals(GameState.GameStatus.IN_GAME, gameState.getStatus());
    }
}