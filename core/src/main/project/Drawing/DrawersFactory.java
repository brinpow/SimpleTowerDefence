package project.Drawing;

import project.Constants;
import project.CommonInterfaces.Drawer;
import project.CommonInterfaces.TextDrawer;

public class DrawersFactory {
    public static Drawer getDefaultDrawer() {
        Drawer drawer = new GdxDrawer();
        drawer.load("rectangle.png", 40, 30);
        drawer.load("Towers/a1.png", 277, 409, 0.2f);
        drawer.load("Towers/a2.png", 278, 459, 0.2f);
        drawer.load("Towers/a3.png", 278, 479, 0.2f);
        drawer.load("Towers/b1.png", 293, 466, 0.175f);
        drawer.load("Towers/b2.png", 293, 543, 0.175f);
        drawer.load("Towers/b3.png", 300, 556, 0.175f);
        drawer.load("Towers/c1.png", 291, 690, 0.118f);
        drawer.load("Towers/c2.png", 294, 739, 0.118f);
        drawer.load("Towers/c3.png", 294, 738, 0.118f);
        drawer.load("Towers/d1.png", 288, 686, 0.119f);
        drawer.load("Towers/d2.png", 312, 744, 0.119f);
        drawer.load("Towers/d3.png", 294, 742, 0.119f);
        drawer.load("Towers/shot1.png", 286, 179, 0.1f);
        drawer.load("Balloons/balloon1.png", 49, 63, 0.6f);
        drawer.load("Balloons/balloon8.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon7.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon6.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon5.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon4.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon3.png", 63, 80, 0.6f);
        drawer.load("Balloons/balloon2.png", 63, 80, 0.6f);
        drawer.load("Maps/map.png", Constants.screenWidth, Constants.screenHeight);
        drawer.load("shop/towerInfo.png", 1110, 680, 0.3f);
        drawer.load("shop/eqLogo.png",412,449,0.15f);
        drawer.load("shop/shopEq.png",1544,818,0.25f);
        drawer.load("backgrounds/background.png",1313,783,0.75f);
        drawer.load("Towers/range.png",468,478,1f);
        drawer.load("Towers/oneArrowSpeed.png", 200,200,1f);
        drawer.load("Towers/twoArrowsSpeed.png", 200,200,1f);
        drawer.load("Towers/heal.png", 2400,2400,0.03f);
        drawer.load("Towers/magic.png", 600,597,0.23f);
        drawer.load("Towers/levelUp.png",1077, 1024,0.04f);
        drawer.load("Towers/xButton.png",512, 512,0.08f);
        return drawer;
    }

    public static Drawer getGuiDrawer() {
        Drawer drawer = new GdxDrawer();
        drawer.load("menu/backgroundToMenu.png",1600, 900, 1);
        drawer.load("menu/buttonAfterExit.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeExit.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterNewGame.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeNewGame.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterAchievements.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeAchievements.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterLoadGame.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeLoadGame.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterMenu.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeMenu.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterSaveGame.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeSaveGame.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterResume.png", 1237,400,0.419f);
        drawer.load("menu/buttonBeforeResume.png", 1037,278,0.5f);
        drawer.load("menu/buttonAfterOk.png", 1237,400,0.2425f);
        drawer.load("menu/buttonBeforeOk.png", 1037,278,0.289f);
        drawer.load("Towers/a1.png", 277, 409, 0.2f);
        drawer.load("Balloons/balloon8.png", 63, 80, 1);
        drawer.load("Balloons/balloon7.png", 63, 80, 1);
        drawer.load("Balloons/balloon6.png", 63, 80, 1);
        drawer.load("Balloons/balloon5.png", 63, 80, 1);
        drawer.load("Balloons/balloon4.png", 63, 80, 1);
        drawer.load("Balloons/balloon3.png", 63, 80, 1);
        drawer.load("Balloons/balloon2.png", 63, 80, 1);
        drawer.load("Balloons/balloon1.png", 63, 80, 1);
        drawer.load("Towers/games.png",512, 512,0.15f);
        drawer.load("Towers/coins.png",2420, 1576,0.04f);
        drawer.load("Towers/heal.png", 2400,2400,0.02f);
        return drawer;
    }

    public static TextDrawer getDefaultTextDrawer() {
        return new GdxTextDrawer();
    }
}

