package project.Gui;

import project.Game.Implementations.ButtonImpl;
import project.CommonInterfaces.Button;
import project.CommonInterfaces.Drawer;
import project.Game.Manager;
import project.Point;

public class ButtonFactory {
    public interface ClickFunction {
        void click();
    }

    public static Button produce(Point pos, String name, ClickFunction function,
                                 Manager.ObjectManager<Button> buttonManager, Drawer drawer) {
        Button button = new ButtonImpl(pos,name) {
            @Override
            public void click() {
                function.click();
            }
        };
        String path = "menu/buttonBefore"+name+".png";
        button.addRectangle(0,0,drawer.getImgWidth(path), drawer.getImgHeight(path));
        buttonManager.addNewObject(button);

        return button;
    }
}
