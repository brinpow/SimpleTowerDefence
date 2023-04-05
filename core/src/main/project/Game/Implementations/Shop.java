package project.Game.Implementations;

import project.Constants;

import project.Game.Clickable;
import project.Game.ItemsManager;
import project.Game.Manager;
import project.Game.Tower;
import project.CommonInterfaces.*;
import project.Point;

import java.util.*;

public class Shop {
    private boolean isShown = false;
    private final Clickable icon;
    private final Drawable eqShop;
    private final List<Clickable> items = new ArrayList<>();
    private final List<Tower.TowerType> shopTowerTypes;
    private static final Map<Tower.TowerType, Integer> towerCosts = new HashMap<>();
    static {
        towerCosts.put(Tower.TowerType.a1, 100);
        towerCosts.put(Tower.TowerType.b1, 300);
        towerCosts.put(Tower.TowerType.c1, 500);
        towerCosts.put(Tower.TowerType.d1, 700);
    }

    public Shop(Manager.ObjectManager<Clickable> clickableManager, Drawer drawer, ItemsManager itemsManager) {
        shopTowerTypes = new ArrayList<>();
        shopTowerTypes.add(Tower.TowerType.a1);
        shopTowerTypes.add(Tower.TowerType.b1);
        shopTowerTypes.add(Tower.TowerType.c1);
        shopTowerTypes.add(Tower.TowerType.d1);
        icon = ClickableFactory.produce(new Point(Constants.screenWidth - 238,Constants.screenHeight - 67),
                "shop/eqLogo.png", () -> isShown = !isShown, clickableManager, drawer);

        for(int i=0; i<shopTowerTypes.size(); i++) {
            Point itemPos = new Point(Constants.screenWidth - 380 + 100 * i,Constants.screenHeight - 157);
            Tower.TowerType itemType = shopTowerTypes.get(i);
            String itemName = "Towers/"+itemType.toString()+".png";
            Clickable item = ClickableFactory.produce(itemPos, itemName,
                    ()->{ if(isShown) {
                        itemsManager.notifyTowerUsed(itemType);
                    }},clickableManager, drawer);
            items.add(item);
        }
        // skills
        items.add(ClickableFactory.produce(new Point(Constants.screenWidth - 290, Constants.screenHeight - 240),
                "Towers/heal.png",
                ()->{ if(isShown) {
                    itemsManager.notifyHealUsed();
                }}, clickableManager, drawer));
        items.add(ClickableFactory.produce(new Point(Constants.screenWidth - 228, Constants.screenHeight - 278),
                "Towers/magic.png",
                ()->{ if(isShown) {
                    itemsManager.notifyMagicUsed();
                }}, clickableManager, drawer));

        eqShop = new DrawableShape(new Point(Constants.screenWidth - 400,Constants.screenHeight - 260),
                "shop/shopEq.png");
    }

    public void draw(Drawer drawer, TextDrawer textDrawer) {
        if(isShown) {
            //all towers
            drawer.draw(eqShop);
            float startPos = items.get(0).getX() + 5;
            for(int i=0;i<shopTowerTypes.size();i++) {
                Clickable item = items.get(i);
                drawer.draw(item);
                DrawableText gold = new DrawableTextImpl(towerCosts.get(shopTowerTypes.get(i)).toString(),
                        startPos + 97 * i,item.getY() + 90,1.8f);
                textDrawer.writeTinted(gold, 1,1,0);
            }
            //heal
            drawer.draw(items.get(shopTowerTypes.size()));
            DrawableText goldForHeal = new DrawableTextImpl(String.valueOf(ItemsManager.healCost),
                    startPos+97,items.get(shopTowerTypes.size()).getY() + 83,1.8f);
            textDrawer.writeTinted(goldForHeal, 1,1,0);
            //magic
            drawer.draw(items.get(shopTowerTypes.size()+1));
            DrawableText goldForMagic = new DrawableTextImpl(String.valueOf(ItemsManager.magicCost),
                    startPos+97*2,items.get(shopTowerTypes.size()).getY() + 83,1.8f);
            textDrawer.writeTinted(goldForMagic, 1,1,0);
        }
        drawer.draw(icon);
    }

    public static int getTowerCost(Tower.TowerType type)
    {
        return towerCosts.get(type);
    }
}
