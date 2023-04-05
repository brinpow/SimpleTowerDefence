package project.Gui;

import project.CommonInterfaces.LevelManager;

public interface Screen {
    enum ScreenType {
        MENU, GAME, LOAD, ACHIEVEMENTS, SAVE, ESC
    }

    void show();
    void hide();
    void render();
    void dispose();
    interface ScreenFactory{
        Screen produceScreen(ScreenType type);
        Screen produceSpecialScreen(ScreenType type, LevelManager levelManager);
    }
}
