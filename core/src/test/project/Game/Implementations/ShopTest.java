package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Drawer;
import project.CommonInterfaces.TextDrawer;
import project.CommonInterfaces.Utils;
import project.Game.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShopTest {
    public static abstract class MockManager implements Manager.ObjectManager<Clickable> {}

    @Test
    void testDrawNoShown() {
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        Manager.ObjectManager<Clickable> clickableManager = mock(MockManager.class);
        Shop shop = new Shop(clickableManager, drawer, mock(ItemsManager.class));
        shop.draw(drawer, textDrawer);
        verify(drawer, times(1)).draw(any());
        verify(clickableManager, times(7)).addNewObject(any());
    }

    @Test
    void testDrawShown(){
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        Utils utils = mock(Utils.class);
        when(utils.isButtonJustPressed(anyInt())).thenReturn(true);
        when(utils.getMouseX()).thenReturn(1362);
        when(utils.getMouseY()).thenReturn(833);
        ClickableManager clickableManager = new ClickableManager(utils);
        Shop shop = new Shop(clickableManager,drawer, mock(ItemsManager.class));
        clickableManager.update();
        shop.draw(drawer,textDrawer);
        verify(drawer, times(8)).draw(any());
        verify(textDrawer, times(6)).writeTinted(any(), anyFloat(), anyFloat(),anyFloat());
    }


}