package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.Game.GameState;
import project.Game.Player;
import project.Game.Tower;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionCheckerTest {
    @Test
    void testCheckBuyTrue(){
        Player player = mock(Player.class);
        when(player.canAfford(anyInt())).thenReturn(true);
        boolean value = TransactionChecker.checkBuy(player, Tower.TowerType.a1);
        verify(player).changeGold(anyInt());
        assertTrue(value);
    }

    @Test
    void testCheckBuyFalse(){
        Player player = mock(Player.class);
        when(player.canAfford(anyInt())).thenReturn(false);
        boolean value = TransactionChecker.checkBuy(player, Tower.TowerType.a1);
        verify(player).canAfford(anyInt());
        verifyNoMoreInteractions(player);
        assertFalse(value);
    }

    @Test
    void testUpgradeHealMagicTrue(){
        Player player = mock(Player.class);
        when(player.canAfford(anyInt())).thenReturn(true);
        boolean value = TransactionChecker.checkUpgrade(player, Tower.TowerType.a1);
        verify(player).canAfford(anyInt());
        assertTrue(value);
    }

    @Test
    void testUpgradeHealMagicFalse(){
        Player player = mock(Player.class);
        when(player.canAfford(anyInt())).thenReturn(false);
        boolean value = TransactionChecker.checkHealing(player);
        verify(player).canAfford(anyInt());
        verifyNoMoreInteractions(player);
        assertFalse(value);
    }
}