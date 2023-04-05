package project.CommonInterfaces;

import project.DataBase.DbInterface;

import java.util.HashMap;
import java.util.Map;

public interface Counter {
    enum AchievementType {
        GOLD("You have earned ", " gold coin(s)."), HEAL("You have healed ", " health point(s)"),
        TOWER("You have placed ", " tower(s)"), VILLAIN("You have slain ", " balloon(s)"),
        GAMES("You have played ", " game(s)");
        private final String first;
        private final String end;
        AchievementType(String first, String end) {
            this.first = first;
            this.end = end;
        }

        public String getAchievementText(int amount)
        {
            return first + amount + end;
        }
    }

    Map<AchievementType, Integer> counter = new HashMap<>();
    static void increase(AchievementType type, int amount) {
        int value = counter.getOrDefault(type, 0);
        value += amount;
        counter.put(type, value);
    }

    static void update(DbInterface db) {
        for(AchievementType type:counter.keySet()) {
            int oldValue = db.readAchievements(type.toString());
            db.updateAchievements(type.toString(), oldValue + counter.get(type));
        }
        counter.clear();
    }
}
