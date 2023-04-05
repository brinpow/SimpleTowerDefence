package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Utils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KeyboardManagerTest {
    @Test
    void testAddNewObject(){
        KeyboardManager keyboardManager = new KeyboardManager(mock(Utils.class));
        KeyboardManager.PressFunction function = mock(KeyboardManager.PressFunction.class);
        keyboardManager.addNewObject(1, function);

        assertThat(keyboardManager.pressFunctionMap).containsExactly(Map.entry(1,function));
    }

    @Test
    void testRemoveObject(){
        KeyboardManager keyboardManager = new KeyboardManager(mock(Utils.class));
        KeyboardManager.PressFunction function = mock(KeyboardManager.PressFunction.class);
        keyboardManager.addNewObject(1, function);
        keyboardManager.removeObject(1);

        assertThat(keyboardManager.pressFunctionMap).doesNotContain(Map.entry(1,function));
    }

    @Test
    void testClear(){
        KeyboardManager keyboardManager = new KeyboardManager(mock(Utils.class));
        KeyboardManager.PressFunction function = mock(KeyboardManager.PressFunction.class);
        keyboardManager.addNewObject(1, function);
        keyboardManager.addNewObject(2, function);
        keyboardManager.clear();

        assertTrue(keyboardManager.pressFunctionMap.isEmpty());
    }

    @Test
    void testUpdate(){
        Utils utils = mock(Utils.class);
        KeyboardManager keyboardManager = new KeyboardManager(utils);
        KeyboardManager.PressFunction function = mock(KeyboardManager.PressFunction.class);
        keyboardManager.addNewObject(1, function);
        when(utils.isKeyJustPressed(1)).thenReturn(true);
        keyboardManager.update();
        verify(function).press();
    }
}