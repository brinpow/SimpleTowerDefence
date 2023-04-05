package project.Game.Implementations;

import project.Game.Tower;

public class TowerManagerImpl implements Tower.TowerManager {
    private Tower toUpgrade = null;
    private Tower toDelete = null;

    @Override
    public void notifyUpgrade(Tower tower) {
        toUpgrade = tower;
    }

    @Override
    public void notifyDelete(Tower tower) {
        toDelete = tower;
    }

    @Override
    public boolean isToDelete(Tower tower) {
        return tower == toDelete;
    }

    @Override
    public boolean isToUpgrade(Tower tower) {
        return tower == toUpgrade;
    }

    @Override
    public void reset() {
        toUpgrade = null;
        toDelete = null;
    }
}
