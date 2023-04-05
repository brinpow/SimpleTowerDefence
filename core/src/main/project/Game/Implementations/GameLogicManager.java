package project.Game.Implementations;

import project.Constants;

import project.Game.*;
import project.CommonInterfaces.*;
import project.Point;
import project.CommonInterfaces.Utils;

import java.util.Iterator;

public class GameLogicManager implements Manager {
    private final GameState gameState;
    private final Tower.TowerManager towerManager;
    private final ItemsManager itemsManager;
    private final Tower.TowerFactory factory;
    private final DragManager dragManager;
    private final Utils utils;
    Manager.ObjectManager<Clickable> clickableManager;

    GameLogicManager(GameState gameState, Tower.TowerManager towerManager, ItemsManager itemsManager,
                     DragManager dragManager, Tower.TowerFactory factory, Utils utils, Manager.ObjectManager<Clickable> clickableManager) {
        this.gameState = gameState;
        this.towerManager = towerManager;
        this.itemsManager = itemsManager;
        this.dragManager = dragManager;
        this.factory = factory;
        this.utils = utils;
        this.clickableManager = clickableManager;
    }

    @Override
    public void update() {
        HandleItemsManager();
        HandleTowers();
        HandleProjectiles();
        HandleVillains();
    }

    private void HandleVillains() {
        for (Iterator<Villain> it = gameState.getVillainList().iterator(); it.hasNext(); ) {
            Villain v = it.next();
            if(v.isOut()){
                gameState.getPlayer().changeHp(-1);
                it.remove();
                if(!gameState.getPlayer().isAlive()) {
                    gameState.setStatus(GameState.GameStatus.GAME_OVER);
                    break;
                }
            }
            else if(!v.isAlive()){
                gameState.getPlayer().changeGold(v.getGoldValue());
                Counter.increase(Counter.AchievementType.GOLD, v.getGoldValue());
                it.remove();
            } else {
                v.advance();
            }
        }
    }

    private void HandleProjectiles() {
        for (Iterator<Projectile> it = gameState.getProjectileList().iterator(); it.hasNext(); ) {
            Projectile p = it.next();
            p.advance();
            if (p.getX() < 0 || p.getX() > Constants.screenWidth || p.getY() < 0 || p.getY() > Constants.screenHeight)
                it.remove();
            else {
                for (Villain v : gameState.getVillainList()) {
                    if (p.intersects(v)) {
                        v.dealDamage(p.getPower());
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    private void HandleTowers() {
        Tower toAdd = null;
        for (Iterator<Tower> it = gameState.getTowerList().iterator(); it.hasNext(); ) {
            Tower tower = it.next();
            if(towerManager.isToDelete(tower)) {
                towerManager.reset();
                clickableManager.removeObject(tower);
                it.remove();
                continue;
            }
            if(towerManager.isToUpgrade(tower)) {
                towerManager.reset();
                if(TransactionChecker.checkUpgrade(gameState.getPlayer(), tower.getType())) {
                    toAdd = factory.upgrade(tower);
                    clickableManager.removeObject(tower);
                    it.remove();
                    continue;
                }
            }
            Projectile p = tower.shoot(gameState.getVillainList());
            if (p != null){
                gameState.getProjectileList().add(p);
            }
        }
        if(toAdd != null) {
            gameState.getTowerList().add(toAdd);
            TowerBarImpl.setActiveTowerBar(null);
        }
    }

    private void HandleItemsManager() {
        Player player = gameState.getPlayer();
        if(itemsManager.isHealUsed() && TransactionChecker.checkHealing(player)) {
            player.changeHp(1);
            Counter.increase(Counter.AchievementType.HEAL, 1);
        }


        if(itemsManager.isMagicUsed() && TransactionChecker.checkMagic(player)) {
            for(Villain villain : gameState.getVillainList())
                villain.dealDamage(2);
        }

        Tower.TowerType itemType = itemsManager.getTowerUsed();
        if(itemType != null) {
            Tower newTower = factory.produce(itemType, new Point(0, 0));
            newTower.setX(utils.getMouseX() - newTower.getWidth() / 2f);
            newTower.setY(utils.getMouseY() - newTower.getHeight() / 2f);
            dragManager.newObject(newTower);
            newTower.getClickFunctions().next().click();
        }

        itemsManager.reset();
    }
}
