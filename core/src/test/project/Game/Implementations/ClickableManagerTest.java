package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Utils;
import project.Game.Clickable;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClickableManagerTest {
    private Clickable prepareClickable(boolean contains){
        Clickable clickable = mock(Clickable.class);
        when(clickable.contains(90,100)).thenReturn(contains);
        return clickable;
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
        ClickableManager clickableManager = new ClickableManager(mock(Utils.class));
        Clickable clickable = mock(Clickable.class);
        clickableManager.addNewObject(clickable);

        assertThat(clickableManager.clickableList).containsExactly(clickable);
    }

    @Test
    void testRemoveObject(){
        ClickableManager clickableManager = new ClickableManager(mock(Utils.class));
        Clickable clickable = mock(Clickable.class);
        clickableManager.addNewObject(clickable);
        clickableManager.removeObject(clickable);

        assertThat(clickableManager.clickableList).doesNotContain(clickable);
    }

    @Test
    void testClear(){
        ClickableManager clickableManager = new ClickableManager(mock(Utils.class));
        Clickable clickable1 = mock(Clickable.class);
        Clickable clickable2 = mock(Clickable.class);
        clickableManager.addNewObject(clickable1);
        clickableManager.addNewObject(clickable2);
        clickableManager.clear();

        assertEquals(clickableManager.clickableList.size(), 0);

    }

    @Test
    void testClickableWhenLeftClickOutside(){
        Utils utils = prepareUtils(true);
        ClickableManager clickableManager = new ClickableManager(utils);
        Clickable clickable = prepareClickable(false);
        clickableManager.addNewObject(clickable);
        clickableManager.update();
        verify(clickable).contains(90,100);
        verifyNoMoreInteractions(clickable);
    }

    @Test
    void testClickableWhenLeftClickInside(){
        Utils utils = prepareUtils(true);
        ClickableManager clickableManager = new ClickableManager(utils);
        Clickable clickable = prepareClickable(true);
        Iterator<Clickable.ClickFunction> mockIterator = mock(Iterator.class);
        Clickable.ClickFunction function = mock(Clickable.ClickFunction.class);
        when(clickable.getClickFunctions()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true).thenReturn(false);
        when(mockIterator.next()).thenReturn(function);
        clickableManager.addNewObject(clickable);
        clickableManager.update();
        verify(clickable).contains(90,100);
        verify(clickable).getClickFunctions();
        verify(function).click();
        verify(mockIterator).next();
        verify(mockIterator, times(2)).hasNext();
        verifyNoMoreInteractions(clickable,function,mockIterator);
    }

    @Test
    void testClickableWhenNoLeftClickInside(){
        Utils utils = prepareUtils(false);
        ClickableManager clickableManager = new ClickableManager(utils);
        Clickable clickable = prepareClickable(true);
        clickableManager.addNewObject(clickable);
        clickableManager.update();
        verify(clickable).contains(90,100);
        verifyNoMoreInteractions(clickable);
    }
}