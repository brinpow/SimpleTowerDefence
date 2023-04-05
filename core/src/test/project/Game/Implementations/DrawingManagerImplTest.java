package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import project.Game.*;
import project.CommonInterfaces.*;

import static org.mockito.Mockito.*;

class DrawingManagerImplTest {
    @Test
    void testDrawerIsHandledCorrectly() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        InOrder inOrder = Mockito.inOrder(drawer);
        inOrder.verify(drawer).begin();
        inOrder.verify(drawer).end();
    }
    @Test
    void testBackgroundIsDrawn() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        Drawable background = mock(Drawable.class);
        dm.update(0, background);
        verify(drawer).draw(background);
    }

    @Test
    void testEveryVillainIsDrawn() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        Villain villain1 = mock(Villain.class);
        Villain villain2 = mock(Villain.class);
        Villain villain3 = mock(Villain.class);
        gameState.getVillainList().add(villain1);
        gameState.getVillainList().add(villain2);
        gameState.getVillainList().add(villain3);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        verify(drawer).draw(villain1);
        verify(drawer).draw(villain2);
        verify(drawer).draw(villain3);
    }

    @Test
    void testEveryTowerIsDrawn() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        Tower tower1 = mock(Tower.class);
        Tower tower2 = mock(Tower.class);
        Tower tower3 = mock(Tower.class);
        gameState.getTowerList().add(tower1);
        gameState.getTowerList().add(tower2);
        gameState.getTowerList().add(tower3);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        verify(drawer).draw(tower1);
        verify(drawer).draw(tower2);
        verify(drawer).draw(tower3);
    }

    @Test
    void testEveryProjectileIsDrawnRotated() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        Projectile projectile1 = mock(Projectile.class);
        Projectile projectile2 = mock(Projectile.class);
        Projectile projectile3 = mock(Projectile.class);
        gameState.getProjectileList().add(projectile1);
        gameState.getProjectileList().add(projectile2);
        gameState.getProjectileList().add(projectile3);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        verify(drawer).drawRotated(projectile1);
        verify(drawer).drawRotated(projectile2);
        verify(drawer).drawRotated(projectile3);
    }

    @Test
    void testManagersAreDrawn() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        verify(dragManager).draw(drawer, textDrawer);
    }

    @Test
    void testShopIsDrawn() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.update(0, mock(Drawable.class));
        verify(shop).draw(drawer, textDrawer);
    }

    @Test
    void testDispose() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        Drawer drawer = mock(Drawer.class);
        TextDrawer textDrawer = mock(TextDrawer.class);
        DragManager dragManager = mock(DragManager.class);
        Shop shop = mock(Shop.class);
        DrawingManager dm = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
        dm.dispose();
        verify(drawer).dispose();
        verify(textDrawer).dispose();
        verifyNoMoreInteractions(textDrawer, drawer);
    }
}