package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Button;
import project.CommonInterfaces.Utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ButtonManagerTest {
    private Button prepareButton(boolean contains, boolean pressed, boolean active){
        Button button = mock(Button.class);
        when(button.contains(90,100)).thenReturn(contains);
        when(button.isPressed()).thenReturn(pressed);
        when(button.isActive()).thenReturn(active);
        return button;
    }

    private Utils prepareUtils(boolean pressed) {
        Utils utils = mock(Utils.class);
        when(utils.getMouseX()).thenReturn(90);
        when(utils.getMouseY()).thenReturn(100);
        when(utils.isButtonJustPressed(0)).thenReturn(pressed);
        return utils;
    }


    @Test
    void testAddNewObject(){
        ButtonManager buttonManager = new ButtonManager(mock(Utils.class));
        Button button = mock(Button.class);
        buttonManager.addNewObject(button);

        assertThat(buttonManager.buttonList).containsExactly(button);
    }

    @Test
    void testRemoveObject(){
        ButtonManager buttonManager = new ButtonManager(mock(Utils.class));
        Button button = mock(Button.class);
        buttonManager.addNewObject(button);
        buttonManager.removeObject(button);

        assertThat(buttonManager.buttonList).doesNotContain(button);
    }

    @Test
    void testClear(){
        ButtonManager buttonManager = new ButtonManager(mock(Utils.class));
        Button button1 = mock(Button.class);
        Button button2 = mock(Button.class);
        buttonManager.addNewObject(button1);
        buttonManager.addNewObject(button2);
        buttonManager.clear();

        assertEquals(buttonManager.buttonList.size(), 0);


    }

    @Test
    void testNoActiveButton()
    {
        Utils utils = prepareUtils(true);
        ButtonManager buttonManager = new ButtonManager(utils);
        Button button = prepareButton(false,false,false);
        buttonManager.addNewObject(button);
        buttonManager.update();
        verify(button).isActive();
        verifyNoMoreInteractions(button);
    }

    @Test
    void testNoPressedButtonWhenLeftClickOutside(){
        Utils utils = prepareUtils(true);
        ButtonManager buttonManager = new ButtonManager(utils);
        Button button = prepareButton(false,false,true);
        buttonManager.addNewObject(button);
        buttonManager.update();
        verify(button).isActive();
        verify(button).changeState(Button.State.UNPRESSED);
        verify(button, times(2)).contains(90,100);
        verify(button).isPressed();
        verifyNoMoreInteractions(button);
    }

    @Test
    void testNoPressedButtonWhenLeftClickInside(){
        Utils utils = prepareUtils(true);
        ButtonManager buttonManager = new ButtonManager(utils);
        Button button = prepareButton(true,false,true);
        buttonManager.addNewObject(button);
        buttonManager.update();
        verify(button).isActive();
        verify(button).changeState(Button.State.PRESSED);
        verify(button).contains(90,100);
        verify(button).isPressed();
        verify(button).click();
        verifyNoMoreInteractions(button);
    }

    @Test
    void testNoPressedButtonWhenNoLeftClickInside(){
        Utils utils = prepareUtils(false);
        ButtonManager buttonManager = new ButtonManager(utils);
        Button button = prepareButton(true,false,true);
        buttonManager.addNewObject(button);
        buttonManager.update();
        verify(button).isActive();
        verify(button).changeState(Button.State.HOVERED);
        verify(button, times(2)).contains(90,100);
        verify(button).isPressed();
        verify(button).hover();
        verifyNoMoreInteractions(button);
    }

    @Test
    void testPressedButtonWhenLeftClickInside(){
        Utils utils = prepareUtils(true);
        ButtonManager buttonManager = new ButtonManager(utils);
        Button button = prepareButton(true,true,true);
        buttonManager.addNewObject(button);
        buttonManager.update();
        verify(button).isActive();
        verify(button).isPressed();
        verify(button).changeState(Button.State.HOVERED);
        verify(button).contains(90,100);
        verify(button).hover();
        verifyNoMoreInteractions(button);
    }


}
