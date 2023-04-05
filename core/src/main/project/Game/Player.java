package project.Game;

import project.Game.GameException;

public interface Player {
    boolean isAlive();
    void changeHp(int amount);
    int getGold();
    void changeGold(int val) throws GameException;
    boolean canAfford(int val);
    int getHp();
}
