package project.Gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import project.Constants;
import project.Game.Implementations.DrawableShape;
import project.CommonInterfaces.Button;
import project.CommonInterfaces.Drawable;
import project.CommonInterfaces.ScreenManager;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen implements Screen {
    private final ScreenManager manager;
    List<Drawable> drawableList = new ArrayList<>();

    public MenuScreen(ScreenManager manager) {
        this.manager = manager;
    }

    @Override
    public void show() {
        drawableList.add(new DrawableShape(new Point(0,0),"menu/backgroundToMenu.png"));

        float buttonPosX = Constants.screenWidth / 2f - manager.getGuiDrawer().getImgWidth(
                "menu/buttonBeforeExit.png")/2;

        Button exit = ButtonFactory.produce(new Point(buttonPosX,100),"Exit",
                ()-> Gdx.app.exit(),manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(exit);

        Button achievements = ButtonFactory.produce(new Point(buttonPosX, 250), "Achievements",
                () -> manager.setScreen(Screen.ScreenType.ACHIEVEMENTS),
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(achievements);

        Button load = ButtonFactory.produce(new Point(buttonPosX, 400), "LoadGame",
                ()-> manager.setScreen(Screen.ScreenType.LOAD),
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(load);


        Button newGame = ButtonFactory.produce(new Point(buttonPosX, 550), "NewGame",
                ()-> manager.setScreen(Screen.ScreenType.GAME),
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(newGame);
    }

    @Override
    public void hide() {
        drawableList.clear();
        manager.getButtonManager().clear();
    }

    @Override
    public void render() {
        manager.getButtonManager().update();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
