package project.Game;

import project.CommonInterfaces.Drawable;

import java.util.Iterator;

public interface Clickable extends Drawable, LogicalShape {
    interface ClickFunction {
        void click();
    }

    void addClickFunction(ClickFunction function);
    void removeClickFunction(ClickFunction function);
    Iterator<ClickFunction> getClickFunctions();
}
