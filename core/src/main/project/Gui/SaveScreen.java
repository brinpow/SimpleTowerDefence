package project.Gui;

import project.Constants;
import project.Game.GameException;
import project.Game.Implementations.*;

import project.CommonInterfaces.*;
import project.Point;
import project.Game.Implementations.UtilsImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveScreen implements Screen{
    private final ScreenManager manager;
    private final LevelManager levelManager;
    private final List<DrawableText> texts;
    private final List<Drawable> drawableList;
    private final List<String> strings;

    public SaveScreen(ScreenManager manager, LevelManager levelManager) {
        this.manager = manager;
        this.levelManager = levelManager;
        drawableList = new ArrayList<>();
        texts = new ArrayList<>();
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
            DrawableText text = new DrawableTextImpl(strings.get(i) + date, posX, curPosY,2.5f);
            texts.add(text);
            Button save = ButtonFactory.produce(new Point(posX + 550, curPosY - 60), "Ok",
                    ()->{String curDate = new UtilsImpl().getCurrentTime();
                        manager.getDB().updateSaveDate(curIndex, curDate);
                        text.setText(strings.get(curIndex) + curDate);
                        File file = new File("saves/save" + curIndex + ".game");
                        OutputStream os;
                        try {
                            os = new FileOutputStream(file);
                        }
                        catch (FileNotFoundException e) {
                            throw new GameException("Fatal error during save");
                        }
                        DataOutputStream stream = new DataOutputStream(os);
                        SavesManager savesManager = new SavesManagerImpl();
                        savesManager.save(levelManager,stream);

                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    },manager.getButtonManager(), manager.getGuiDrawer());
            drawableList.add(save);
        }

        float buttonPosX = Constants.screenWidth / 2f - manager.getGuiDrawer().getImgWidth(
                "menu/buttonBeforeExit.png") / 2f;
        Button resume = ButtonFactory.produce(new Point(buttonPosX,100),"Resume",
                ()-> manager.setScreen(Screen.ScreenType.GAME),
                manager.getButtonManager(), manager.getGuiDrawer());
        drawableList.add(resume);
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
