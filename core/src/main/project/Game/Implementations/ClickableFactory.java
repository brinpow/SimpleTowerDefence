package project.Game.Implementations;

import project.Game.Clickable;
import project.CommonInterfaces.Drawer;
import project.Game.Manager;
import project.Point;

public class ClickableFactory {
    public static Clickable produce(Point pos, String imgName, Clickable.ClickFunction function,
                                    Manager.ObjectManager<Clickable> clickableManager, Drawer drawer) {
        Clickable clickable = new ClickableImpl(pos,imgName);
        clickable.addClickFunction(function);
        clickable.addRectangle(0,0,drawer.getImgWidth(imgName), drawer.getImgHeight(imgName));
        clickableManager.addNewObject(clickable);
        return clickable;
    }
}
