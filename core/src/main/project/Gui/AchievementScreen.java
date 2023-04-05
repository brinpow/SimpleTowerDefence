package project.Gui;

import project.Constants;
import project.Game.Implementations.DrawableShape;
import project.Game.Implementations.DrawableTextImpl;
import project.CommonInterfaces.*;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class AchievementScreen implements Screen{
    private final ScreenManager manager;
    private final List<DrawableText> texts;
    private final List<Drawable> drawableList;
    private final List<String> imageNames;

    public AchievementScreen(ScreenManager manager) {
        this.manager = manager;
        texts = new ArrayList<>();
        drawableList = new ArrayList<>();

        imageNames = new ArrayList<>();
        imageNames.add("Towers/coins.png");
        imageNames.add("Towers/heal.png");
        imageNames.add("Towers/a1.png");
        imageNames.add("Balloons/balloon8.png");
        imageNames.add("Towers/games.png");
    }

    @Override
    public void show() {
        drawableList.add(new DrawableShape(new Point(0,0),"menu/backgroundToMenu.png"));

        float posX = Constants.screenWidth / 2f - 250;
        float posY = 325;
        int i=0;
        for(Counter.AchievementType type: Counter.AchievementType.values()) {
            int value = manager.getDB().readAchievements(type.toString());
            float curY = posY+90*i;
            String text = type.getAchievementText(value);
            texts.add(new DrawableTextImpl(text, posX, curY,3));
            drawableList.add(new DrawableShape(new Point(posX - 100, curY-60), imageNames.get(i)));
            i++;
        }

        float buttonPosX = Constants.screenWidth / 2f - manager.getGuiDrawer().getImgWidth("menu/buttonBeforeExit.png")/2;
        Button menu = ButtonFactory.produce(new Point(buttonPosX,100),"Menu",
                ()-> manager.setScreen(Screen.ScreenType.MENU),manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(menu);
    }

    @Override
    public void hide() {
        texts.clear();
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
        manager.getTextDrawer().begin();
        for(DrawableText text: texts)
            manager.getTextDrawer().writeTinted(text, 0,1,0);
        manager.getTextDrawer().end();
    }

    @Override
    public void dispose() {
        manager.getGuiDrawer().dispose();
        manager.getTextDrawer().dispose();
    }
}

