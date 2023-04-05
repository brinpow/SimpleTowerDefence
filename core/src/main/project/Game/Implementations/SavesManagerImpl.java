package project.Game.Implementations;

import project.Drawing.DrawersFactory;
import project.Game.*;

import project.CommonInterfaces.*;
import project.CommonInterfaces.SavesManager;
import project.CommonInterfaces.ScreenManager;
import project.Point;
import project.CommonInterfaces.Utils;

import java.io.*;

public class SavesManagerImpl implements SavesManager {
    @Override
    public void save(LevelManager levelManager, DataOutputStream outputData) {
        try {
            outputData.writeUTF(levelManager.getLevelName());
            outputData.writeInt(levelManager.getTimePassed());

            GameState gameState = levelManager.getGameState();
            outputData.writeInt(gameState.getPlayer().getHp());
            outputData.writeInt(gameState.getPlayer().getGold());

            outputData.writeInt(gameState.getTowerList().getSize());
            for(Tower tower: gameState.getTowerList()) {
                outputData.writeUTF(tower.getType().toString());
                outputData.writeFloat(tower.getX());
                outputData.writeFloat(tower.getY());
            }

            outputData.writeInt(gameState.getVillainList().getSize());
            for (Villain villain : gameState.getVillainList()) {
                outputData.writeUTF(villain.getType().toString());
                outputData.writeInt(villain.getPathIndex());
                outputData.writeInt(villain.timeAlive());
                outputData.writeInt(villain.getHp());
            }

            outputData.writeInt(gameState.getProjectileList().getSize());
            for(Projectile projectile: gameState.getProjectileList()) {
                outputData.writeFloat(projectile.getX());
                outputData.writeFloat(projectile.getY());
                outputData.writeFloat(projectile.getRotation());
                outputData.writeFloat(projectile.getSpeed());
                outputData.writeInt(projectile.getPower());
                outputData.writeUTF(projectile.getImgName());
            }
        } catch (IOException e) {
            throw new GameException("Fatal error during save");
        }

    }

    @Override
    public LevelManager load(DataInputStream inputData, ScreenManager screenManagerImpl) {
        LevelManager levelManager;
        String lvlName;
        int timePassed;
        GameState gameState;
        Villain.VillainFactory villainFactory;
        Manager.ManagerHolder managerHolder;
        DrawingManager drawingManager;
        try {
            lvlName = inputData.readUTF();
            timePassed = inputData.readInt();

            assert timePassed >= 0;

            int hp = inputData.readInt();
            int gold = inputData.readInt();

            assert hp>=0;
            assert gold>=0;

            Utils utils = new UtilsImpl();

            Player player = new PlayerImpl(hp, gold);
            gameState = new GameStateImpl(player);
            Drawer drawer = DrawersFactory.getDefaultDrawer();
            TextDrawer textDrawer = DrawersFactory.getDefaultTextDrawer();
            villainFactory = new VillainFactoryImpl(drawer);
            Projectile.ProjectileFactory projectileFactory = new ProjectileFactoryImpl(drawer);
            ClickableManager clickableManager = new ClickableManager(utils);
            Tower.TowerManager towerManager = new TowerManagerImpl();
            Tower.TowerFactory towerFactory = new TowerFactoryImpl(drawer, projectileFactory, clickableManager,
                    towerManager);
            DragManager dragManager = new DragManager(gameState, clickableManager, utils);
            ItemsManager itemsManager = new ItemsManagerImpl();
            Shop shop = new Shop(clickableManager, drawer, itemsManager);
            ButtonManager buttonManager = new ButtonManager(utils);
            KeyboardManager keyboardManager = new KeyboardManager(utils);
            GameLogicManager gameLogicManager = new GameLogicManager(gameState, towerManager, itemsManager,
                    dragManager,towerFactory, utils, clickableManager);
            drawingManager = new DrawingManagerImpl(gameState, drawer, textDrawer, shop, dragManager);
            managerHolder = new MastermindManager(clickableManager, dragManager, buttonManager,
                    keyboardManager, gameLogicManager);
            File file = new File(lvlName);
            InputStream is;
            DataInputStream stream;
            try {
                is = new FileInputStream(file);
                stream = new DataInputStream(is);
            } catch (FileNotFoundException e) {
                throw new GameException("File with level data not found: " + lvlName);
            }
            levelManager = new LevelManagerImpl(stream, lvlName, timePassed, gameState,
                    managerHolder, drawingManager, villainFactory, screenManagerImpl);
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            KeyboardManager.addDefaultButtons(keyboardManager, gameState, screenManagerImpl, levelManager);

            int towerListSize = inputData.readInt();
            assert towerListSize>=0;
            for(int i=0;i<towerListSize; i++) {
                String type = inputData.readUTF();
                float posX = inputData.readFloat();
                float posY = inputData.readFloat();
                Tower tower = towerFactory.produce(Tower.TowerType.valueOf(type),new Point(posX,posY));
                gameState.getTowerList().add(tower);
            }

            int villainListSize = inputData.readInt();
            assert villainListSize>=0;
            for(int i=0;i<villainListSize; i++) {
                String type = inputData.readUTF();
                int pathIndex = inputData.readInt();
                int times = inputData.readInt();
                int vHp = inputData.readInt();

                assert times>=0;
                assert vHp>=0;
                assert pathIndex >= 0;

                Villain villain = villainFactory.produce(Villain.VillainType.valueOf(type),levelManager.getPath(pathIndex));
                villain.advance(times);
                villain.dealDamage(villain.getHp()-vHp);
                gameState.getVillainList().add(villain);
            }

            int projectileListSize = inputData.readInt();
            assert projectileListSize>=0;
            for(int i=0;i<projectileListSize; i++) {
                float posX = inputData.readFloat();
                float posY = inputData.readFloat();
                float rotation = inputData.readFloat();
                float speed = inputData.readFloat();
                int power = inputData.readInt();
                String imgName = inputData.readUTF();

                Projectile projectile = projectileFactory.produce(new Point(posX,posY),rotation,speed,power,imgName);

                gameState.getProjectileList().add(projectile);
            }
        } catch(IOException e) {
            throw new GameException("Fatal error during load");
        } catch (AssertionError e) {
            throw new GameException("Save corrupted");
        }
        return levelManager;
    }
}
