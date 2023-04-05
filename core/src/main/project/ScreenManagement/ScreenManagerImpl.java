package project.ScreenManagement;

import com.badlogic.gdx.ApplicationAdapter;
import project.DataBase.DbInterface;
import project.DataBase.SqlDbFactory;
import project.Game.Implementations.ButtonManager;
import project.Drawing.DrawersFactory;
import project.Drawing.GdxDrawer;
import project.Drawing.GdxTextDrawer;
import project.Game.Implementations.LevelManagerFactoryImpl;
import project.Game.Manager;
import project.Gui.*;
import project.CommonInterfaces.*;
import project.Game.Implementations.UtilsImpl;

import java.util.HashMap;
import java.util.Map;

public class ScreenManagerImpl extends ApplicationAdapter implements ScreenManager {
    private final Screen.ScreenFactory screenFactory = new ScreenFactoryImpl(this);
    private final Map<Screen.ScreenType, Screen> screenMap = new HashMap<>();
    private Screen curScreen;
    private Drawer drawer;
    private TextDrawer textDrawer;
    private Manager.ObjectManager<Button> buttonManager;
    private LevelManagerFactory levelManagerFactory;
    private DbInterface db;

    @Override
    public void create() {
        drawer = new GdxDrawer();
        textDrawer = new GdxTextDrawer();
        db = SqlDbFactory.getDb();
        buttonManager = new ButtonManager(new UtilsImpl());
        drawer = DrawersFactory.getGuiDrawer();
        levelManagerFactory = new LevelManagerFactoryImpl();
        setScreen(Screen.ScreenType.MENU);
    }

    @Override
    public void render()
    {
        curScreen.render();
    }

    @Override
    public void dispose() {
        curScreen.dispose();
    }

    @Override
    public void setScreen(Screen.ScreenType screen) {
        if(curScreen!=null)
            curScreen.hide();

        switch (screen) {
            case MENU -> {
                if(!screenMap.containsKey(Screen.ScreenType.MENU))
                    screenMap.put(Screen.ScreenType.MENU,new MenuScreen(this));
                curScreen = screenMap.get(Screen.ScreenType.MENU);
            }
            case GAME -> {
                if(!screenMap.containsKey(Screen.ScreenType.GAME))
                    screenMap.put(Screen.ScreenType.GAME,new GameScreen(this, levelManagerFactory));
                curScreen = screenMap.get(Screen.ScreenType.GAME);
            }

            case LOAD -> {
                if (!screenMap.containsKey(Screen.ScreenType.LOAD))
                    screenMap.put(Screen.ScreenType.LOAD,new LoadScreen(this));
                curScreen = screenMap.get(Screen.ScreenType.LOAD);
            }

            case ACHIEVEMENTS -> {
                if (!screenMap.containsKey(Screen.ScreenType.ACHIEVEMENTS))
                    screenMap.put(Screen.ScreenType.ACHIEVEMENTS, new AchievementScreen(this));
                curScreen = screenMap.get(Screen.ScreenType.ACHIEVEMENTS);
            }
            case ESC -> {
                if(!screenMap.containsKey(Screen.ScreenType.ESC))
                    throw new RuntimeException("ESC Screen is not created");
                curScreen = screenMap.get(Screen.ScreenType.ESC);
            }
            case SAVE -> {
                if(!screenMap.containsKey(Screen.ScreenType.SAVE))
                    throw new RuntimeException("Save Screen is not created");
                curScreen = screenMap.get(Screen.ScreenType.SAVE);
            }
        }
        curScreen.show();
    }

    @Override
    public void newGameNotify() {
        screenMap.get(Screen.ScreenType.GAME).dispose();
        screenMap.remove(Screen.ScreenType.GAME);
        screenMap.remove(Screen.ScreenType.ESC);
        screenMap.remove(Screen.ScreenType.SAVE);
    }

    @Override
    public void specialScreenCreation(Screen.ScreenType type, LevelManager levelManager) {
        switch (type) {
            case ESC -> {
                if(!screenMap.containsKey(Screen.ScreenType.ESC))
                    screenMap.put(Screen.ScreenType.ESC, new ESCScreen(this, levelManager));
            }
            case SAVE -> {
                if(!screenMap.containsKey(Screen.ScreenType.SAVE))
                    screenMap.put(Screen.ScreenType.SAVE, new SaveScreen(this, levelManager));
            }
            default -> throw new RuntimeException("Wrong type Screen creation");
        }
    }

    @Override
    public void createGameFromSave(LevelManager levelManager) {
        screenMap.put(Screen.ScreenType.GAME, new GameScreen(this, levelManager));
    }

    @Override
    public Drawer getGuiDrawer() {
        return drawer;
    }

    @Override
    public TextDrawer getTextDrawer() {
        return textDrawer;
    }

    @Override
    public DbInterface getDB() {
        return db;
    }

    @Override
    public LevelManagerFactory getFactory() { return levelManagerFactory; }

    @Override
    public Manager.ObjectManager<Button> getButtonManager() {
        return buttonManager;
    }

}
