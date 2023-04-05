package project.Game.Implementations;

import project.Drawing.DrawersFactory;
import project.Game.*;

import project.CommonInterfaces.*;
import project.CommonInterfaces.LevelManagerFactory;
import project.CommonInterfaces.ScreenManager;
import project.CommonInterfaces.Utils;

import java.io.*;

public class LevelManagerFactoryImpl implements LevelManagerFactory {
    @Override
    public LevelManager defaultLevelManager(String levelName, ScreenManager screenManager) {
        File file = new File(levelName);
        InputStream is;
        DataInputStream stream;
        try {
            is = new FileInputStream(file);
            stream = new DataInputStream(is);
        } catch (FileNotFoundException e) {
            throw new GameException("File with level data not found");
        }
        Utils utils = new UtilsImpl();
        GameState gameState = new GameStateImpl(new PlayerImpl(10, 100));
        Drawer drawer = DrawersFactory.getDefaultDrawer();
        TextDrawer textDrawer = DrawersFactory.getDefaultTextDrawer();
        Villain.VillainFactory villainFactory = new VillainFactoryImpl(drawer);
        Projectile.ProjectileFactory projectileFactory = new ProjectileFactoryImpl(drawer);
        Manager.ObjectManager<Clickable> clickableManager = new ClickableManager(utils);
        Tower.TowerManager towerManager = new TowerManagerImpl();
        Tower.TowerFactory towerFactory = new TowerFactoryImpl(drawer, projectileFactory,
                clickableManager, towerManager);
        DragManager dragManager = new DragManager(gameState, clickableManager,utils);
        ItemsManager itemsManager = new ItemsManagerImpl();
        Shop shop = new Shop(clickableManager, drawer, itemsManager);
        ButtonManager buttonManager = new ButtonManager(utils);
        KeyboardManager keyboardManager = new KeyboardManager(utils);
        GameLogicManager gameLogicManager = new GameLogicManager(gameState, towerManager, itemsManager,
                dragManager,towerFactory, utils, clickableManager);
        DrawingManagerImpl drawingManagerImpl = new DrawingManagerImpl(gameState, drawer, textDrawer,
                shop, dragManager);
        MastermindManager managerHolder = new MastermindManager(clickableManager, dragManager, buttonManager,
                keyboardManager, gameLogicManager);
        LevelManager result = new LevelManagerImpl(stream, levelName, 0, gameState,
                managerHolder, drawingManagerImpl, villainFactory, screenManager);
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyboardManager.addDefaultButtons(keyboardManager, gameState, screenManager, result);

        return result;
    }
}
