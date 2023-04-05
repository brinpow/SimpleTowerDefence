package project.Game.Implementations;

import project.Game.ItemsManager;
import project.Game.Tower;

public class ItemsManagerImpl implements ItemsManager {
    private boolean healUsed = false;
    private boolean magicUsed = false;
    private Tower.TowerType towerUsed = null;

    @Override
    public void notifyHealUsed() {
        healUsed = true;
    }

    @Override
    public void notifyMagicUsed() {
        magicUsed = true;
    }

    @Override
    public void notifyTowerUsed(Tower.TowerType type) {
        towerUsed = type;
    }

    @Override
    public boolean isHealUsed() {
        return healUsed;
    }

    @Override
    public boolean isMagicUsed() {
        return magicUsed;
    }

    @Override
    public Tower.TowerType getTowerUsed() {
        return towerUsed;
    }

    @Override
    public void reset() {
        healUsed = false;
        magicUsed = false;
        towerUsed = null;
    }
}
