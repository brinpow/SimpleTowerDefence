package project.Game;

import project.CommonInterfaces.Drawable;
import project.Point;

public interface Tower extends Drawable, LogicalShape, Draggable, Clickable {
    Projectile shoot(ListInterface<Villain> villainList);
    TowerType getType();
    int getPower();
    int getLevel();
    float getRange();
    boolean isFinal();
    void upgrade();
    void delete();
    enum TowerType {
        a1("Simple Tower", 100, 1), a2("Simple Tower", 100, 2),
        a3("Simple Tower", 100, 3), b1("Simple Tower", 100, 1),
        b2("Simple Tower", 100, 2), b3("Simple Tower", 100, 3),
        c1("Simple Tower", 100, 1), c2("Simple Tower", 100, 2),
        c3("Simple Tower", 100, 3), d1("Simple Tower", 100, 1),
        d2("Simple Tower", 100, 2), d3("Simple Tower", 100, 3);
        private final String nameOfTower;
        private final int cost;
        private final int level;
        TowerType(String name, int cost, int level) {
            this.nameOfTower = name;
            this.cost = cost;
            this.level = level;
        }
        public String getNameOfTower() {
            return nameOfTower;
        }
        public int getCostOfUpgrade(){
            return cost;
        }
        public boolean isFinal() { return level == 3; }
        public int getLevel() { return level; }
    }

    interface TowerFactory {
        Tower produce(TowerType type, Point place);
        Tower upgrade(Tower tower);
    }

    interface TowerManager {
        void notifyUpgrade(Tower tower);
        void notifyDelete(Tower tower);
        boolean isToDelete(Tower tower);
        boolean isToUpgrade(Tower tower);
        void reset();
    }
}

