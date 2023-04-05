package project.Game.Implementations;


import project.Game.*;
import project.CommonInterfaces.*;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class TowerBarImpl implements TowerBar {
    private String towerType;
    private final Tower tower;
    private final List<DrawableText> texts;
    private final List<Clickable> icons;
    private final Drawable selectedTowerBar;
    private final Point positionTowerBar;
    private final float towerRange;
    private final static String circleImgName = "Towers/range.png";
    private final Manager.ObjectManager<Clickable> clickableManager;

    private static TowerBar activeTowerBar = null;

    TowerBarImpl(Tower tower, Manager.ObjectManager<Clickable> clickableManager, Drawer drawer) {
        this.tower = tower;
        this.clickableManager = clickableManager;
        positionTowerBar = new Point(1200, 500);
        towerType = tower.getType().toString();
        towerRange = tower.getRange();
        texts = new ArrayList<>();
        icons = new ArrayList<>();
        prepareName();
        prepareLevel();
        prepareRange();
        preparePower();
        prepareCost();
        if(!tower.getType().isFinal())
            addLevelUp(drawer);
        addDeleteTower(drawer);
        selectedTowerBar = new DrawableShape(positionTowerBar, "shop/towerInfo.png");
    }
    private void addLevelUp(Drawer drawer) {
        Point itemPos = new Point(1300, 530);
        String imageName = "Towers/levelUp.png";
        Clickable item = ClickableFactory.produce(itemPos, imageName, tower::upgrade,clickableManager, drawer);
        icons.add(item);
    }
    private void addDeleteTower(Drawer drawer) {
        Point itemPos = new Point(1375, 528);
        String imageName = "Towers/xButton.png";
        Clickable item = ClickableFactory.produce(itemPos, imageName, tower::delete,clickableManager, drawer); /* to change asap */
        icons.add(item);
    }
    private void prepareLevel() {
        String level = "Tower level: " + tower.getLevel() + "/3";
        texts.add(new DrawableTextImpl(level, positionTowerBar.x() + 30,positionTowerBar.y() + 150,1.2f));
    }

    private void prepareRange() {
        String towerRange = "Tower range: " + tower.getRange();
        texts.add(new DrawableTextImpl(towerRange, positionTowerBar.x() + 30,positionTowerBar.y() + 130,1.2f));
    }

    private void preparePower() {
        String towerPower = "Tower power: " + tower.getPower();
        texts.add(new DrawableTextImpl(towerPower, positionTowerBar.x() + 30,positionTowerBar.y() + 110,1.2f));
    }

    private void prepareName() {
        towerType += String.valueOf(tower.getType().getNameOfTower());
        towerType = towerType.substring(2); // xD
        texts.add(new DrawableTextImpl(towerType, positionTowerBar.x() + 120,positionTowerBar.y() + 180,1.2f));
    }

    private void prepareCost() {
        String costNewTower;
        if(tower.isFinal())
            costNewTower = "Level Max";
        else
            costNewTower = "Level up cost: " + tower.getType().getCostOfUpgrade();
        texts.add(new DrawableTextImpl(costNewTower, positionTowerBar.x() + 30,positionTowerBar.y() + 90,1.2f));
    }

    public void draw(Drawer drawer, TextDrawer textDrawer){
            float imgR = drawer.getImgHeight(circleImgName) / 2;
            // note: png of the range circle is larger than the actual circle
            // this is visible with larger ranges
            // TO DO: clip the image
            DrawableShape rangeOnSelectedTower = new DrawableShape(new Point(LogicalShape.getCenterX(tower),
                    LogicalShape.getCenterY(tower)), circleImgName);
            drawer.draw(rangeOnSelectedTower, towerRange / imgR, true);
            drawer.draw(selectedTowerBar);

            for(DrawableText text: texts)
                textDrawer.write(text);

            for(Drawable icon:icons)
                drawer.draw(icon);
    }

    public void fillClickables() {
        for(Clickable clickable: icons)
            clickableManager.addNewObject(clickable);
    }

    public void clear() {
        for(Clickable clickable: icons)
            clickableManager.removeObject(clickable);
    }

    public static void drawTowerBar(Drawer drawer, TextDrawer textDrawer) {
        if(activeTowerBar!=null)
            activeTowerBar.draw(drawer,textDrawer);
    }

    public static TowerBar getActiveTowerBar() {
        return activeTowerBar;
    }

    public static void setActiveTowerBar(TowerBar towerBar) {
        if(towerBar == null && activeTowerBar != null)
            activeTowerBar.clear();
        activeTowerBar = towerBar;
        if(activeTowerBar != null)
            activeTowerBar.fillClickables();
    }
}

