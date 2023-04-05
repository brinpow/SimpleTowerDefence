package project.CommonInterfaces;

import project.DataBase.DbInterface;
import project.Game.Manager;
import project.Gui.Screen;

public interface ScreenManager {
    void setScreen(Screen.ScreenType screen);
    void newGameNotify();
    void specialScreenCreation(Screen.ScreenType type, LevelManager levelManager);
    void createGameFromSave(LevelManager levelManager);
    Drawer getGuiDrawer();
    TextDrawer getTextDrawer();
    DbInterface getDB();
    LevelManagerFactory getFactory();
    Manager.ObjectManager<Button> getButtonManager();
}
