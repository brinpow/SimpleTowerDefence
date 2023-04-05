package project.Game;

import project.CommonInterfaces.Drawable;

public interface Villain extends Drawable, LogicalShape {
    void dealDamage(int amount);
    boolean isAlive();
    void advance();
    void advance(int times);
    int timeAlive();
    int remainingPath();
    int getPathIndex();
    boolean isOut();
    int getHp();
    int getGoldValue();
    VillainType getType();
    Path.direction getDirection();

    enum VillainType {
        b1, b2, b3, b4,
        b5, b6, b7, b8;
    }

    interface VillainFactory {
        Villain produce(VillainType type, Path path);
    }
}
