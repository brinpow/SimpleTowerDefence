package project.Game.Implementations;

import project.CommonInterfaces.Counter;
import project.Game.Player;
import project.Game.GameException;

public class PlayerImpl implements Player {
    private int hp;
    private int gold;

    PlayerImpl(int hp, int gold) {
        this.hp = hp;
        this.gold = gold;
        Counter.increase(Counter.AchievementType.GOLD, gold); // here?
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void changeHp(int amount) {
        hp += amount;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public void changeGold(int val) {
        if(gold + val < 0)
            throw new GameException("Player has insufficient funds");
        gold += val;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public boolean canAfford(int val) {return gold>=val;}
}
