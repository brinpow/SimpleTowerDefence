package project.Game;

import project.CommonInterfaces.Drawer;
import project.CommonInterfaces.TextDrawer;

public interface TowerBar {
    void draw(Drawer drawer, TextDrawer textDrawer);
    void clear();
    void fillClickables();
}
