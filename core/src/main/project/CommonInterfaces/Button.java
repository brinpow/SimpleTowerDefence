package project.CommonInterfaces;

import project.Game.LogicalShape;

public interface Button extends Drawable, LogicalShape {
    enum State {
        PRESSED, HOVERED, UNPRESSED
    }
    boolean isActive();
    boolean isPressed();
    void setActive(boolean active);
    void click();
    void hover();
    void changeState(Button.State state);
}
