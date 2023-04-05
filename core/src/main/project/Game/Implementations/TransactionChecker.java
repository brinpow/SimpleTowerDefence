package project.Game.Implementations;

import project.CommonInterfaces.Counter;
import project.Game.ItemsManager;
import project.Game.Player;
import project.Game.Tower;

class TransactionChecker {
    private static boolean check(Player player, int cost) {
        if(player.canAfford(cost)) {
            player.changeGold(-cost);
            return true;
        }
        return false;
    }

    public static boolean checkBuy(Player player, Tower.TowerType type) {
        int towerCost = Shop.getTowerCost(type);
        if(player.canAfford(towerCost)) {
            player.changeGold(-towerCost);
            Counter.increase(Counter.AchievementType.TOWER, 1);
            return true;
        }
        return false;
    }

    public static boolean checkUpgrade(Player player, Tower.TowerType type) {
        return check(player, type.getCostOfUpgrade());
    }

    public static boolean checkHealing(Player player) {
        return check(player, ItemsManager.healCost);
    }

    public static boolean checkMagic(Player player) {
        return check(player, ItemsManager.magicCost);
    }
}
