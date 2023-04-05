package project.Game.Implementations;

import project.Constants;
import project.Game.*;

import project.CommonInterfaces.*;
import project.Gui.Screen;

import java.io.*;
import java.util.ArrayList;

public class LevelManagerImpl implements LevelManager {
    private final GameState gameState;
    private final ArrayList<Path> paths = new ArrayList<>();
    private final Manager.ManagerHolder managerHolder;
    private final Villain.VillainFactory villainFactory;
    private WaveManager waveManager;
    private final DrawingManager drawingManager;
    private final ScreenManager screenManager;

    private int timePassed;
    private int countDown = Constants.fps * 3;
    private final String levelName;
    private Drawable background;

    LevelManagerImpl(DataInput levelData, String levelName, int timePassed, GameState gameState,
                     Manager.ManagerHolder managerHolder, DrawingManager drawingManager,
                     Villain.VillainFactory villainFactory, ScreenManager screenManager) {
        this.gameState = gameState;
        this.levelName = levelName;
        this.timePassed = timePassed;
        this.managerHolder = managerHolder;
        this.villainFactory = villainFactory;
        this.drawingManager = drawingManager;
        this.screenManager = screenManager;

        // read from file
        LevelLoader loader = new LevelLoaderImpl(this);
        try {
            loader.load(levelData,timePassed);
        } catch (IOException e) {
            throw new GameException("Incorrect level data file: " + levelName);
        }
    }

    private void endGame() {
        if(!gameState.getStatus().equals(GameState.GameStatus.IN_GAME))
            countDown--;

        if(countDown<0) {
            screenManager.newGameNotify();
            screenManager.setScreen(Screen.ScreenType.MENU);
        }

    }

    @Override
    public void update() {
        if(!gameState.isGamePaused() && gameState.getStatus().equals(GameState.GameStatus.IN_GAME)) {
            if(!waveManager.finished())
                waveManager.update(gameState.getVillainList(), paths, villainFactory);
            else {
                if(gameState.getVillainList().isEmpty())
                    gameState.setStatus(GameState.GameStatus.GAME_FINISHED);
            }
            timePassed++;
            managerHolder.getGameLogicManager().update();
            managerHolder.getDragManager().update();
            managerHolder.getClickableManager().update();
        }

        managerHolder.getKeyboardManager().update();
        managerHolder.getButtonManager().update();
        drawingManager.update(waveManager.getWaveNr(), background);

        endGame();
    }

    @Override
    public void dispose() {
        drawingManager.dispose();
    }

    @Override
    public void setBackground(Drawable background) {
        this.background = background;
    }

    @Override
    public void addPath(Path path) {
        paths.add(path);
    }

    @Override
    public Path getPath(int index) {
        for(Path p : paths)
            if(p.getIndex() == index)
                return p;
        throw new GameException("Path with given index does not exist");
    }

    @Override
    public void setWaveManager(WaveManager waveManager) {
        this.waveManager = waveManager;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public String getLevelName() {
        return levelName;
    }

    @Override
    public int getTimePassed() {
        return timePassed;
    }
}
