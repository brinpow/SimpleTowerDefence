package project.Game.Implementations;


import project.Game.*;
import project.CommonInterfaces.*;
import project.Point;

import java.util.ArrayList;

class TowerImpl extends ClickableImpl implements Tower {
    private final float range;
    private final int framesPerAttack;
    private final TowerManager towerManager;
    private final TowerType type;
    private final ProjectileSupplier projectileGenerator;
    int attackCooldown = 0;

    public TowerImpl(Point p, float range, int framesPerAttack, TowerManager towerManager,
                     String imgName, TowerType type, ProjectileSupplier projectileGenerator) {
        super(p, imgName);
        this.range = range;
        this.framesPerAttack = framesPerAttack;
        this.towerManager = towerManager;
        this.type = type;
        this.projectileGenerator = projectileGenerator;
    }

    private ArrayList<Villain> getVillainsInRange(ListInterface<Villain> villainList) {
        ArrayList<Villain> result = new ArrayList<>();
        for(Villain villain : villainList) {
            float dx = LogicalShape.getCenterX(this) - LogicalShape.getCenterX(villain);
            float dy = LogicalShape.getCenterY(this) - LogicalShape.getCenterY(villain);
            if(dx * dx + dy * dy <= range * range)
                result.add(villain);
        }
        return result;
    }



    @Override
    public TowerType getType() {
        return type;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public int getPower() {
        return projectileGenerator.getPower();
    }

    @Override
    public int getLevel() { return type.getLevel(); }

    @Override
    public boolean isFinal() { return type.isFinal();}

    @Override
    public void upgrade() {
        towerManager.notifyUpgrade(this);
    }

    @Override
    public void delete() {
        towerManager.notifyDelete(this);
        TowerBarImpl.setActiveTowerBar(null);
    }

    @Override
    public Projectile shoot(ListInterface<Villain> villainList) {
        if(attackCooldown > 0) {
            attackCooldown--;
            return null;
        }
        ArrayList<Villain> villainsInRange = getVillainsInRange(villainList);
        Villain target = null;
        int minRemaining = 2000000000; // effective infinity
        for(Villain villain : villainsInRange) {
            int remaining = villain.remainingPath();
            if(remaining < minRemaining) {
                minRemaining = remaining;
                target = villain;
            }
        }
        Projectile result = null;
        if(target != null) {
            float targetX = switch (target.getDirection()) {
                case left -> target.getX() - target.getWidth() / 2f;
                case right -> target.getX() + target.getWidth() * 1.5f;
                default -> LogicalShape.getCenterX(target);
            };
            float targetY = switch (target.getDirection()) {
                case up -> target.getY() + target.getHeight() * 1.5f;
                case down -> target.getY() - target.getHeight() / 2f;
                default -> LogicalShape.getCenterY(target);
            };
            result = projectileGenerator.get(
                    new Point(LogicalShape.getCenterX(this), LogicalShape.getCenterY(this)),
                    new Point(targetX, targetY));
            attackCooldown = framesPerAttack;
        }
        return result;
    }

    @Override
    public void setX(float x)
    {
        this.x = x;
    }

    @Override
    public void setY(float y)
    {
        this.y = y;
    }

    public static class TowerClickFunction implements Clickable.ClickFunction {
        private final TowerBar ownTowerBar;

        TowerClickFunction(Tower tower, Manager.ObjectManager<Clickable> clickableManager, Drawer drawer) {
            ownTowerBar = new TowerBarImpl(tower, clickableManager, drawer);
        }

        @Override
        public void click() {
            if(TowerBarImpl.getActiveTowerBar() != ownTowerBar)
                TowerBarImpl.setActiveTowerBar(ownTowerBar);
            else
                TowerBarImpl.setActiveTowerBar(null);
        }
    }
}
