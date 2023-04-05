package project.Gui;

import project.Constants;
import project.Game.Implementations.DrawableShape;
import project.CommonInterfaces.Button;
import project.CommonInterfaces.Drawable;
import project.CommonInterfaces.LevelManager;
import project.CommonInterfaces.ScreenManager;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class ESCScreen implements Screen {
    private final ScreenManager manager;
    private final LevelManager levelManager;
    private final List<Drawable> drawableList;

    public ESCScreen(ScreenManager manager, LevelManager levelManager) {
        this.manager = manager;
        this.levelManager = levelManager;
        drawableList = new ArrayList<>();
    }

    @Override
    public void show() {
        drawableList.add(new DrawableShape(new Point(0,0),"menu/backgroundToMenu.png"));

        float buttonPosX = Constants.screenWidth / 2f - manager.getGuiDrawer().getImgWidth(
                "menu/buttonBeforeExit.png")/2;
        Button menu = ButtonFactory.produce(new Point(buttonPosX,200),"Menu",
                () -> {
                    manager.newGameNotify();
                    manager.setScreen(Screen.ScreenType.MENU);
                    },
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(menu);

        Button save = ButtonFactory.produce(new Point(buttonPosX,350),"SaveGame",
                () -> {
                        manager.specialScreenCreation(Screen.ScreenType.SAVE, levelManager);
                        manager.setScreen(Screen.ScreenType.SAVE);
                        },
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(save);

        Button resume = ButtonFactory.produce(new Point(buttonPosX,500),"Resume",
                ()-> manager.setScreen(Screen.ScreenType.GAME),
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(resume);
    }

    @Override
    public void hide() {
        drawableList.clear();
        manager.getButtonManager().clear();
    }

    @Override
    public void render() {
        manager.getButtonManager().update();
        manager.getGuiDrawer().begin();
        for(Drawable drawable: drawableList)
            manager.getGuiDrawer().draw(drawable);
        manager.getGuiDrawer().end();
    }

    @Override
    public void dispose() {
        manager.getGuiDrawer().dispose();
    }
}
