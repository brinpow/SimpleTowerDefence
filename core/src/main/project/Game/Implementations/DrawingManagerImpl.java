package project.Game.Implementations;

import project.Constants;
import project.Game.*;
import project.CommonInterfaces.*;

public class DrawingManagerImpl implements DrawingManager {
    private final GameState gameState;
    private final Drawer drawer;
    private final TextDrawer textDrawer;
    private final Shop shop;
    private final Manager.DrawableManager[] managers;

    DrawingManagerImpl(GameState gameState, Drawer drawer, TextDrawer textDrawer, Shop shop,
                       DragManager dragManager) {
        this.gameState = gameState;
        this.drawer = drawer;
        this.textDrawer = textDrawer;
        this.shop = shop;
        managers = new Manager.DrawableManager[1];
        managers[0] = dragManager;
    }

    @Override
    public void update(int waveNumber, Drawable background) {
        drawer.begin();

        drawer.draw(background);

        for(Tower tower : gameState.getTowerList())
            drawer.draw(tower);

        for (Projectile p : gameState.getProjectileList())
            drawer.drawRotated(p);

        for (Villain v : gameState.getVillainList())
            drawer.draw(v);

        textDrawer.begin();

        shop.draw(drawer, textDrawer);
        for(Manager.DrawableManager m : managers)
            m.draw(drawer, textDrawer);

        TowerBarImpl.drawTowerBar(drawer, textDrawer);

        drawer.end();

        if(gameState.isGamePaused() && gameState.getStatus().equals(GameState.GameStatus.IN_GAME)) {
            DrawableText pause = new DrawableTextImpl("PAUSE",
                    Constants.screenWidth / 2f - 250, Constants.screenHeight / 2f+75,10);
            textDrawer.writeTinted(pause,1,0,0);
        }

        if(gameState.getStatus().equals(GameState.GameStatus.GAME_OVER)) {
            DrawableText pause = new DrawableTextImpl("GAME OVER",
                    Constants.screenWidth / 2f - 350, Constants.screenHeight / 2f+75,10);
            textDrawer.writeTinted(pause,1,0,0);
        }

        if(gameState.getStatus().equals(GameState.GameStatus.GAME_FINISHED)) {
            DrawableText pause = new DrawableTextImpl("CONGRATULATIONS",
                    Constants.screenWidth / 2f - 700, Constants.screenHeight / 2f+75,10);
            textDrawer.writeTinted(pause,0,1,0);
        }

        DrawableText hp = new DrawableTextImpl("Hp: " + gameState.getPlayer().getHp(), 15,Constants.screenHeight - 10, 2f); //maybe crete  observer??
        DrawableText gold = new DrawableTextImpl("Gold: " + gameState.getPlayer().getGold(),15,Constants.screenHeight - 40, 2f);
        DrawableText wave = new DrawableTextImpl("Wave: " + waveNumber, 15,Constants.screenHeight - 70, 2f);
        textDrawer.writeTinted(hp, 1, 0, 0);
        textDrawer.writeTinted(gold, 1,0,0);
        textDrawer.writeTinted(wave, 1, 0, 0);
        textDrawer.end();
    }

    @Override
    public void dispose() {
        drawer.dispose();
        textDrawer.dispose();
    }
}
