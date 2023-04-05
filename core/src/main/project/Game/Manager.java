package project.Game;

import project.CommonInterfaces.Button;
import project.CommonInterfaces.Drawer;
import project.CommonInterfaces.TextDrawer;

public interface Manager {
    void update();
    interface DrawableManager extends Manager {
        void draw(Drawer drawer, TextDrawer textDrawer);
    }

    interface ManagerHolder {
        ObjectManager<Clickable> getClickableManager();
        DrawableManager getDragManager();
        Manager.ObjectManager<Button> getButtonManager();
        Manager getKeyboardManager();
        Manager getGameLogicManager();
    }
    interface ObjectManager<T> extends Manager
    {
        void addNewObject(T object);
        void removeObject(T object);
        void clear();
    }
}
