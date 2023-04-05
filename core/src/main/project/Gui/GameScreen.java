package project.Gui;

import project.Game.Implementations.TowerBarImpl;
import project.CommonInterfaces.Counter;
import project.CommonInterfaces.LevelManager;
import project.CommonInterfaces.LevelManagerFactory;
import project.CommonInterfaces.ScreenManager;

public class GameScreen implements Screen {
    private final LevelManager lvlManager;
    private final ScreenManager manager;

    public GameScreen(ScreenManager manager, LevelManagerFactory factory) {
        Counter.increase(Counter.AchievementType.GAMES, 1);
        this.manager = manager;
        lvlManager = factory.defaultLevelManager("Levels/Level1", manager);
    }

    public GameScreen(ScreenManager manager, LevelManager levelManager) {
        this.manager = manager;
        this.lvlManager = levelManager;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        Counter.update(manager.getDB());
        TowerBarImpl.setActiveTowerBar(null);
    }

    @Override
    public void render() {
        lvlManager.update();
    }

    @Override
    public void dispose() {
        Counter.update(manager.getDB());
        lvlManager.dispose();
    }
}
