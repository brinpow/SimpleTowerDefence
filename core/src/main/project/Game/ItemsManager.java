package project.Game;

public interface ItemsManager {
    void notifyHealUsed();
    void notifyMagicUsed();
    void notifyTowerUsed(Tower.TowerType type);
    boolean isHealUsed();
    boolean isMagicUsed();
    Tower.TowerType getTowerUsed();
    void reset();
    int healCost = 200;
    int magicCost = 500;
}
