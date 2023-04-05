package project.Game.Implementations;

import org.junit.jupiter.api.Test;

import project.Game.*;
import project.CommonInterfaces.*;

import java.io.DataInput;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LevelManagerImplTest {
    private static Manager.ManagerHolder createMastermindManager() {
        Manager.ObjectManager<Clickable> objectManager = mock(ClickableManager.class);
        DragManager dragManager = mock(DragManager.class);
        ButtonManager buttonManager = mock(ButtonManager.class);
        KeyboardManager keyboardManager = mock(KeyboardManager.class);
        GameLogicManager gameLogicManager = mock(GameLogicManager.class);
        return new MastermindManager(objectManager, dragManager, buttonManager,
                keyboardManager, gameLogicManager);
    }

    @Test
    void testConstructor() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Manager.ManagerHolder managerHolder = mock(Manager.ManagerHolder.class);
        DrawingManager drawingManager = mock(DrawingManager.class);
        LevelManager lm = new LevelManagerImpl(mock(DataInput.class), "level_name", 0, gameState,
                managerHolder, drawingManager, mock(Villain.VillainFactory.class), mock(ScreenManager.class));
        assertSame(gameState, lm.getGameState());
        assertEquals("level_name", lm.getLevelName());
    }

    @Test
    void testGetTimePassed() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        DrawingManager drawingManager = mock(DrawingManager.class);
        Manager.ManagerHolder managerHolder = createMastermindManager();
        LevelManager lm = new LevelManagerImpl(mock(DataInput.class), "level_name", 0, gameState,
                managerHolder, drawingManager, mock(Villain.VillainFactory.class), mock(ScreenManager.class));
        lm.setWaveManager(mock(WaveManager.class));
        lm.update();
        lm.update();
        assertEquals(2, lm.getTimePassed());
    }

    @Test
    void testWhenGameNotPausedEveryoneUpdatedOnce() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        DrawingManager drawingManager = mock(DrawingManager.class);
        WaveManager waveManager = mock(WaveManager.class);
        Drawable background = mock(Drawable.class);
        Manager.ManagerHolder managerHolder = createMastermindManager();
        Villain.VillainFactory villainFactory = mock(Villain.VillainFactory.class);
        LevelManager lm = new LevelManagerImpl(mock(DataInput.class), "level_name", 0, gameState,
                managerHolder, drawingManager, villainFactory, mock(ScreenManager.class));
        lm.setWaveManager(waveManager);
        lm.setBackground(background);
        gameState.setPause(false);
        when(waveManager.getWaveNr()).thenReturn(1);
        lm.update();
        verify(managerHolder.getClickableManager(), times(1)).update();
        verify((managerHolder.getDragManager()), times(1)).update();
        verify(managerHolder.getButtonManager(), times(1)).update();
        verify(managerHolder.getKeyboardManager(), times(1)).update();
        verify(managerHolder.getGameLogicManager(), times(1)).update();
        verify(waveManager, times(1)).update(any(), any(), any());
        verify(drawingManager, times(1)).update(1, background);
    }

    @Test
    void testWhenGamePausedThenOnlySomeAreUpdated() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        DrawingManager drawingManager = mock(DrawingManager.class);
        WaveManager waveManager = mock(WaveManager.class);
        Drawable background = mock(Drawable.class);
        Manager.ManagerHolder managerHolder = createMastermindManager();
        Villain.VillainFactory villainFactory = mock(Villain.VillainFactory.class);
        LevelManager lm = new LevelManagerImpl(mock(DataInput.class), "level_name", 0, gameState,
                managerHolder, drawingManager, villainFactory, mock(ScreenManager.class));
        lm.setWaveManager(waveManager);
        lm.setBackground(background);
        gameState.setPause(true);
        when(waveManager.getWaveNr()).thenReturn(1);
        lm.update();
        verify(managerHolder.getClickableManager(), never()).update();
        verify((managerHolder.getDragManager()), never()).update();
        verify(managerHolder.getButtonManager(), times(1)).update();
        verify(managerHolder.getKeyboardManager(), times(1)).update();
        verify(managerHolder.getGameLogicManager(), never()).update();
        verify(waveManager, never()).update(any(), any(), any());
        verify(drawingManager, times(1)).update(1, background);
    }

    @Test
    void testWhenGameStatusIsNotInGameOnlySomeAreUpdated() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        DrawingManager drawingManager = mock(DrawingManager.class);
        WaveManager waveManager = mock(WaveManager.class);
        Drawable background = mock(Drawable.class);
        Manager.ManagerHolder managerHolder = createMastermindManager();
        Villain.VillainFactory villainFactory = mock(Villain.VillainFactory.class);
        LevelManager lm = new LevelManagerImpl(mock(DataInput.class), "level_name", 0, gameState,
                managerHolder, drawingManager, villainFactory, mock(ScreenManager.class));
        lm.setWaveManager(waveManager);
        lm.setBackground(background);
        gameState.setPause(false);
        gameState.setStatus(GameState.GameStatus.GAME_FINISHED);
        when(waveManager.getWaveNr()).thenReturn(1);
        lm.update();
        verify(managerHolder.getClickableManager(), never()).update();
        verify((managerHolder.getDragManager()), never()).update();
        verify(managerHolder.getButtonManager(), times(1)).update();
        verify(managerHolder.getKeyboardManager(), times(1)).update();
        verify(managerHolder.getGameLogicManager(), never()).update();
        verify(waveManager, never()).update(any(), any(), any());
        verify(drawingManager, times(1)).update(1, background);
    }
}