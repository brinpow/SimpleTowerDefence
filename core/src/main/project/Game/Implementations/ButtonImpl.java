package project.Game.Implementations;

import project.CommonInterfaces.Button;
import project.Point;

public abstract class ButtonImpl extends DrawableShape implements Button {
    private boolean active = true;
    private final String buttonName;
    private Button.State state;

    public ButtonImpl(Point p, String name) {
        super(p, "menu/buttonBefore"+name+".png");
        buttonName = name;
        state = State.UNPRESSED;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isPressed() {
        return state.equals(State.PRESSED);
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void changeState(Button.State state)
    {
        this.state = state;

        switch (this.state) {
            case HOVERED, PRESSED -> imgName = "menu/buttonAfter" + buttonName+ ".png";
            case UNPRESSED -> imgName = "menu/buttonBefore" + buttonName + ".png";
        }
    }

    @Override
    public void hover() {}

}
