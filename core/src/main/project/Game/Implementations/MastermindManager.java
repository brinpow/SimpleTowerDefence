package project.Game.Implementations;

import project.Game.Manager;
import project.Game.Clickable;
import project.CommonInterfaces.Button;

public class MastermindManager implements Manager.ManagerHolder {
    private final Manager.ObjectManager<Clickable> clickableManager;
    private final DragManager dragManager;
    private final Manager.ObjectManager<Button> buttonManager;
    private final KeyboardManager keyboardManager;
    private final GameLogicManager gameLogicManager;

    MastermindManager(Manager.ObjectManager<Clickable> objectManager, DragManager dragManager, Manager.ObjectManager<Button> buttonManager,
                      KeyboardManager keyboardManager, GameLogicManager gameLogicManager) {
        this.clickableManager = objectManager;
        this.buttonManager = buttonManager;
        this.dragManager = dragManager;
        this.keyboardManager = keyboardManager;
        this.gameLogicManager = gameLogicManager;
    }

    @Override
    public Manager.ObjectManager<Clickable> getClickableManager() {
        return clickableManager;
    }

    @Override
    public Manager.DrawableManager getDragManager() {
        return dragManager;
    }

    @Override
    public Manager.ObjectManager<Button> getButtonManager() {
        return buttonManager;
    }

    @Override
    public Manager getKeyboardManager() {
        return keyboardManager;
    }

    @Override
    public Manager getGameLogicManager() {
        return gameLogicManager;
    }
}
