package project.Game.Implementations;

import com.badlogic.gdx.Input;
import project.CommonInterfaces.Utils;
import project.Game.GameState;
import project.CommonInterfaces.LevelManager;
import project.Game.Manager;
import project.CommonInterfaces.ScreenManager;
import project.Gui.Screen;

import java.util.HashMap;
import java.util.Map;

public class KeyboardManager implements Manager {
    public interface PressFunction {
        void press();
    }

    final Map<Integer, PressFunction> pressFunctionMap;
    private final Utils utils;

    KeyboardManager(Utils utils)
    {
        pressFunctionMap = new HashMap<>();
        this.utils = utils;
    }

    public static void addDefaultButtons(KeyboardManager k, GameState gameState,
                                         ScreenManager screenManager, LevelManager levelManager) {
        k.addNewObject(Input.Keys.P, ()->gameState.setPause(!gameState.isGamePaused()));
        k.addNewObject(Input.Keys.ESCAPE,
                ()->{
                    screenManager.specialScreenCreation(Screen.ScreenType.ESC, levelManager);
                    screenManager.setScreen(Screen.ScreenType.ESC);});
    }

    public void update() {
        for(int key: pressFunctionMap.keySet()) {
            if(utils.isKeyJustPressed(key))
                pressFunctionMap.get(key).press();
        }
    }

    void addNewObject(int key, PressFunction function)
    {
        pressFunctionMap.put(key, function);
    }

    void removeObject(int key) {
        pressFunctionMap.remove(key);
    }
    void clear()
    {
        pressFunctionMap.clear();
    }
}
