package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.Clickable;
import project.Game.Manager;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

class MastermindManagerTest {
    @Test
    void testGetters() {
        Manager.ObjectManager<Clickable> objectManager = mock(ClickableManager.class);
        DragManager dragManager = mock(DragManager.class);
        ButtonManager buttonManager = mock(ButtonManager.class);
        KeyboardManager keyboardManager = mock(KeyboardManager.class);
        GameLogicManager gameLogicManager = mock(GameLogicManager.class);
        Manager.ManagerHolder m = new MastermindManager(objectManager, dragManager, buttonManager,
                keyboardManager, gameLogicManager);
        assertSame(objectManager, m.getClickableManager());
        assertSame(dragManager, m.getDragManager());
        assertSame(buttonManager, m.getButtonManager());
        assertSame(keyboardManager, m.getKeyboardManager());
        assertSame(gameLogicManager, m.getGameLogicManager());
    }
}