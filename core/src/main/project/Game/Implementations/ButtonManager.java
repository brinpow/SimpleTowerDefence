package project.Game.Implementations;

import com.badlogic.gdx.Input;
import project.Game.Manager;
import project.CommonInterfaces.Button;
import project.CommonInterfaces.Utils;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager implements Manager.ObjectManager<Button> {
    private interface SelectedFunction {
        void function();
    }
    List<Button> buttonList = new ArrayList<>();
    private final Utils utils;

    public ButtonManager(Utils utils) { this.utils = utils;}

    @Override
    public void update() {
        int posX = utils.getMouseX();
        int posY = utils.getMouseY();

        SelectedFunction buttonFunction = null;

        for(Button button : buttonList) {
            if(button.isActive()) {
                if(button.isPressed()) {
                    if(button.contains(posX, posY) && utils.isButtonJustPressed(Input.Buttons.LEFT)) {
                        button.changeState(Button.State.HOVERED);
                        buttonFunction = button::hover;
                    }
                }
                else {
                    if(button.contains(posX, posY) && utils.isButtonJustPressed(Input.Buttons.LEFT)) {
                        button.changeState(Button.State.PRESSED);
                        buttonFunction = button::click;
                        break;
                    }
                    else if(button.contains(posX, posY)) {
                        button.changeState(Button.State.HOVERED);
                        buttonFunction = button::hover;
                    }
                    else {
                        button.changeState(Button.State.UNPRESSED);
                    }
                }
            }
        }

        if(buttonFunction != null)
            buttonFunction.function();
    }

    @Override
    public void addNewObject(Button button)
    {
        buttonList.add(button);
    }

    @Override
    public void removeObject(Button button) {buttonList.remove(button);}

    @Override
    public void clear()
    {
        buttonList.clear();
    }
}
