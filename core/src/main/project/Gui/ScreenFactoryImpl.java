package project.Gui;

import project.CommonInterfaces.LevelManager;
import project.CommonInterfaces.ScreenManager;

public class ScreenFactoryImpl implements Screen.ScreenFactory {
    private final ScreenManager screenManager;
    public ScreenFactoryImpl(ScreenManager screenManager){
        this.screenManager = screenManager;

}
    @Override
    public Screen produceScreen(Screen.ScreenType type) {
        return null;
    }
    @Override
    public Screen produceSpecialScreen(Screen.ScreenType type, LevelManager levelManager) {
        return null;
    }
}
