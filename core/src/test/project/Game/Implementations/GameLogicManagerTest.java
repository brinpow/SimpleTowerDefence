package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Constants;

import project.Game.*;
import project.CommonInterfaces.Utils;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameLogicManagerTest {
    private static abstract class MockManager implements Manager.ObjectManager<Clickable> {}
    
    @Test
    void testTowersShoot() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Tower tower1 = mock(Tower.class);
        Tower tower2 = mock(Tower.class);
        Tower tower3 = mock(Tower.class);
        Projectile projectile1 = mock(Projectile.class);
        Projectile projectile2 = mock(Projectile.class);
        when(tower1.shoot(gameState.getVillainList())).thenReturn(projectile1);
        when(tower2.shoot(gameState.getVillainList())).thenReturn(projectile2);
        gameState.getTowerList().add(tower1);
        gameState.getTowerList().add(tower2);
        gameState.getTowerList().add(tower3);
        glm.update();
        assertThat(gameState.getProjectileList()).containsExactly(projectile1, projectile2);
    }

    @Test
    void testVillainsAdvance() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Villain villain1 = mock(Villain.class);
        Villain villain2 = mock(Villain.class);
        when(villain1.isAlive()).thenReturn(true);
        when(villain2.isAlive()).thenReturn(true);
        gameState.getVillainList().add(villain1);
        gameState.getVillainList().add(villain2);
        glm.update();
        verify(villain1).advance();
        verify(villain2).advance();
    }

    @Test
    void testDeadVillainsDisappear() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Villain villain1 = mock(Villain.class);
        Villain villain2 = mock(Villain.class);
        when(villain1.isAlive()).thenReturn(true);
        when(villain2.isAlive()).thenReturn(false);
        when(villain2.getGoldValue()).thenReturn(5);
        gameState.getVillainList().add(villain1);
        gameState.getVillainList().add(villain2);
        glm.update();
        assertThat(gameState.getVillainList()).containsOnly(villain1);
        verify(gameState.getPlayer()).changeGold(5);
    }

    @Test
    void testVillainsOutDisappear() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Villain villain1 = mock(Villain.class);
        Villain villain2 = mock(Villain.class);
        when(villain1.isAlive()).thenReturn(true);
        when(villain2.isAlive()).thenReturn(true);
        when(villain1.isOut()).thenReturn(false);
        when(villain2.isOut()).thenReturn(true);
        when(villain2.getGoldValue()).thenReturn(5);
        gameState.getVillainList().add(villain1);
        gameState.getVillainList().add(villain2);
        glm.update();
        assertThat(gameState.getVillainList()).containsOnly(villain1);
        verify(gameState.getPlayer()).changeHp(-1);
    }

    @Test
    void testProjectilesAdvance() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Projectile projectile1 = mock(Projectile.class);
        Projectile projectile2 = mock(Projectile.class);
        gameState.getProjectileList().add(projectile1);
        gameState.getProjectileList().add(projectile2);
        glm.update();
        verify(projectile1).advance();
        verify(projectile2).advance();
    }

    @Test
    void testProjectilesOutDisappear() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Projectile projectile1 = mock(Projectile.class);
        Projectile projectile2 = mock(Projectile.class);
        Projectile projectile3 = mock(Projectile.class);
        when(projectile1.getX()).thenReturn((float) Constants.screenWidth + 5f);
        when(projectile2.getY()).thenReturn(-2f);
        gameState.getProjectileList().add(projectile1);
        gameState.getProjectileList().add(projectile2);
        gameState.getProjectileList().add(projectile3);
        glm.update();
        assertThat(gameState.getProjectileList()).containsOnly(projectile3);
    }

    @Test
    void testProjectilesAndVillainsCollide() {
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        Projectile projectile = mock(Projectile.class);
        when(projectile.getPower()).thenReturn(5);
        Villain villain = mock(Villain.class);
        when(villain.isAlive()).thenReturn(true);
        when(projectile.intersects(villain)).thenReturn(true);
        when(villain.intersects(projectile)).thenReturn(true);
        gameState.getProjectileList().add(projectile);
        gameState.getVillainList().add(villain);
        glm.update();
        verify(villain).dealDamage(5);
        assertThat(gameState.getProjectileList()).doesNotContain(projectile);
    }

    @Test
    void whenHealUsedAndPlayerCanAffordItPlayerHeals() {
        ItemsManager im = mock(ItemsManager.class);
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), im,
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        when(im.isHealUsed()).thenReturn(true);
        when(gameState.getPlayer().canAfford(ItemsManager.healCost)).thenReturn(true);
        glm.update();
        verify(gameState.getPlayer()).changeHp(1);
        verify(gameState.getPlayer()).changeGold(-ItemsManager.healCost);
    }

    @Test
    void testWhenMagicUsedAndPlayerCanAffordItVillainsAreDamaged() {
        ItemsManager im = mock(ItemsManager.class);
        GameState gameState = new GameStateImpl(mock(Player.class));
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), im,
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), mock(MockManager.class));
        when(im.isMagicUsed()).thenReturn(true);
        when(gameState.getPlayer().canAfford(ItemsManager.magicCost)).thenReturn(true);
        Villain villain1 = mock(Villain.class);
        Villain villain2 = mock(Villain.class);
        when(villain1.isAlive()).thenReturn(true);
        when(villain2.isAlive()).thenReturn(true);
        gameState.getVillainList().add(villain1);
        gameState.getVillainList().add(villain2);
        glm.update();
        verify(villain1).dealDamage(2);
        verify(villain2).dealDamage(2);
        verify(gameState.getPlayer()).changeGold(-ItemsManager.magicCost);
    }

    @Test
    void testNewTowerSelected(){
        ItemsManager im = mock(ItemsManager.class);
        GameState gameState = new GameStateImpl(mock(Player.class));
        DragManager dragManager = mock(DragManager.class);
        Tower.TowerFactory tf = mock(Tower.TowerFactory.class);
        Tower tower = mock(Tower.class);
        Iterator<Clickable.ClickFunction> mockIterator = mock(Iterator.class);
        Clickable.ClickFunction function = mock(Clickable.ClickFunction.class);
        GameLogicManager glm = new GameLogicManager(gameState, mock(Tower.TowerManager.class), im,
                dragManager, tf, mock(Utils.class), mock(MockManager.class));
        when(im.getTowerUsed()).thenReturn(Tower.TowerType.a1);
        when(tf.produce(any(),any())).thenReturn(tower);
        when(tower.getClickFunctions()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn(function);
        glm.update();
        verify(tf).produce(any(),any());
        verify(dragManager).newObject(any());
        verify(mockIterator).next();
        verify(function).click();
    }

    @Test
    void testIfTowerIsDeletedThenItDisappears() {
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        GameState gameState = new GameStateImpl(mock(Player.class));
        Manager.ObjectManager<Clickable> clickableManager = mock(MockManager.class);
        GameLogicManager glm = new GameLogicManager(gameState, tm, mock(ItemsManager.class),
                mock(DragManager.class), mock(Tower.TowerFactory.class), mock(Utils.class), clickableManager);
        Tower tower = mock(Tower.class);
        gameState.getTowerList().add(tower);
        when(tm.isToDelete(tower)).thenReturn(true);
        glm.update();
        assertThat(gameState.getTowerList()).doesNotContain(tower);
        verify(tm).reset();
        verify(clickableManager).removeObject(tower);
    }

    @Test
    void testIfTowerIsUpgradedAndPlayerCanAffordItThenItHappens() {
        Tower.TowerManager tm = mock(Tower.TowerManager.class);
        Tower.TowerFactory tf = mock(Tower.TowerFactory.class);
        GameState gameState = new GameStateImpl(mock(Player.class));
        Manager.ObjectManager<Clickable> clickableManager = mock(MockManager.class);
        GameLogicManager glm = new GameLogicManager(gameState, tm, mock(ItemsManager.class),
                mock(DragManager.class), tf, mock(Utils.class), clickableManager);
        Tower tower = mock(Tower.class);
        Tower upgraded = mock(Tower.class);
        when(tower.getType()).thenReturn(Tower.TowerType.a1);
        when(gameState.getPlayer().canAfford(Tower.TowerType.a1.getCostOfUpgrade())).thenReturn(true);
        when(tf.upgrade(tower)).thenReturn(upgraded);
        when(tm.isToUpgrade(tower)).thenReturn(true);
        gameState.getTowerList().add(tower);
        glm.update();
        assertThat(gameState.getTowerList()).containsExactly(upgraded);
        verify(gameState.getPlayer()).changeGold(-Tower.TowerType.a1.getCostOfUpgrade());
        verify(tm).reset();
        verify(clickableManager).removeObject(tower);
    }
}