package project.Gui;

import project.Constants;
import project.Game.Implementations.DrawableShape;
import project.Game.Implementations.DrawableTextImpl;
import project.Game.Implementations.SavesManagerImpl;

import project.CommonInterfaces.*;
import project.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadScreen implements Screen {
    private final ScreenManager manager;
    private final List<DrawableText> texts;
    private final List<Drawable> drawableList;
    private final List<String> strings;

    public LoadScreen(ScreenManager manager){
        this.manager = manager;
        texts = new ArrayList<>();
        drawableList = new ArrayList<>();
        strings = new ArrayList<>();
        strings.add("First save: ");
        strings.add("Second save: ");
        strings.add("Third save: ");
    }

    @Override
    public void show() {
        drawableList.add(new DrawableShape(new Point(0,0),"menu/backgroundToMenu.png"));
        //saves
        int posX = 380;
        int posY = 680;
        for(int i = 0; i < 3; i++) {
            int curIndex = i;
            int curPosY = posY - 150 * i;
            String date = manager.getDB().readSaveDate(0);
            DrawableText text = new DrawableTextImpl(strings.get(i)+date,posX, curPosY,2.5f);
            texts.add(text);
            Button load = ButtonFactory.produce(new Point(posX + 550, curPosY-60), "Ok",
                    ()->{
                        File file = new File("saves/save"+curIndex+".game");
                        InputStream is;
                        try {
                            is = new FileInputStream(file);
                        }
                        catch (FileNotFoundException e) {
                            return;
                        }
                        DataInputStream stream = new DataInputStream(is);
                        SavesManager savesManager = new SavesManagerImpl();
                        LevelManager levelManager = savesManager.load(stream, manager);
                        manager.createGameFromSave(levelManager);
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        manager.setScreen(Screen.ScreenType.GAME);
                    },manager.getButtonManager(), manager.getGuiDrawer());
            drawableList.add(load);
        }
        //MenuButton
        float buttonPosX = Constants.screenWidth / 2f - manager.getGuiDrawer().getImgWidth(
                "menu/buttonBeforeExit.png") / 2f;
        Button menu = ButtonFactory.produce(new Point(buttonPosX,100),"Menu",
                ()-> manager.setScreen(Screen.ScreenType.MENU),
                manager.getButtonManager(), manager.getGuiDrawer());
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
        for(DrawableText drawableText: texts)
            manager.getTextDrawer().writeTinted(drawableText,0,0,0);

        manager.getTextDrawer().end();
    }

    @Override
    public void dispose() {
        manager.getGuiDrawer().dispose();
        manager.getTextDrawer().dispose();
    }
}
