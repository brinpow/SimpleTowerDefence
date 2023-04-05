package project.Game.Implementations;

import com.badlogic.gdx.Input;
import project.Game.*;
import project.CommonInterfaces.*;
import project.CommonInterfaces.Utils;

public class DragManager implements Manager.DrawableManager {
    private Tower selectedItem = null;
    private int lastMouseX;
    private int lastMouseY;
    private final GameState gameState;
    private final ObjectManager<Clickable> clickableManager;
    private boolean readyToStand;
    private final Utils utils;

    DragManager(GameState gameState, ObjectManager<Clickable> clickableManager, Utils utils) {
        this.gameState = gameState;
        this.clickableManager = clickableManager;
        this.utils = utils;
    }

    private boolean testIntersection(LogicalShape newTower, ListInterface<? extends LogicalShape> shapeList) {
        for(LogicalShape shape: shapeList)
            if(shape.intersects(newTower))
                return false;
        return true;
    }

    private boolean canStandTower(Tower newTower) {
        return testIntersection(newTower, gameState.getTowerList())
                && testIntersection(newTower, gameState.getMiscShapes());
    }

    public void update() {
        int posX = utils.getMouseX();
        int posY = utils.getMouseY();

        if(selectedItem != null) {
            float deltaX = posX - lastMouseX;
            float deltaY = posY - lastMouseY;

            lastMouseX = posX;
            lastMouseY = posY;

            selectedItem.setX(selectedItem.getX() + deltaX);
            selectedItem.setY(selectedItem.getY() + deltaY);

            if(utils.isButtonJustPressed(Input.Buttons.LEFT) && readyToStand) {
                boolean canStand = canStandTower(selectedItem);

                if(canStand && TransactionChecker.checkBuy(gameState.getPlayer(),selectedItem.getType())) {
                    gameState.getTowerList().add(selectedItem);
                }
                else {
                    clickableManager.removeObject(selectedItem);
                    TowerBarImpl.setActiveTowerBar(null);
                }
                selectedItem = null;
            }
            else {
                readyToStand = true;
            }
        }
    }

    public void newObject(Tower newTower) {
        selectedItem = newTower;
        lastMouseX = utils.getMouseX();
        lastMouseY = utils.getMouseY();
        readyToStand = false;
    }

    public void draw(Drawer drawer, TextDrawer textDrawer) {
        if(selectedItem != null) {
            if(canStandTower(selectedItem))
                drawer.drawTinted(selectedItem,0,1,0); // green color
            else
                drawer.drawTinted(selectedItem,1,0,0); // red color
        }
    }
}