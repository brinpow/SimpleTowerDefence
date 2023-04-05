package project.Game.Implementations;

import project.Constants;

import project.Game.*;
import project.CommonInterfaces.*;
import project.Point;

public class TowerFactoryImpl implements Tower.TowerFactory {
    private final Drawer imgHolder;
    private final Projectile.ProjectileFactory projectileFactory;
    private final Manager.ObjectManager<Clickable> clickableManager;
    private final Tower.TowerManager towerManager;
    TowerFactoryImpl(Drawer imgHolder, Projectile.ProjectileFactory projectileFactory, Manager.ObjectManager<Clickable> clickableManager,
                     Tower.TowerManager towerManager) {
        this.imgHolder = imgHolder;
        this.projectileFactory = projectileFactory;
        this.clickableManager = clickableManager;
        this.towerManager = towerManager;
    }
    @Override
    public Tower produce(Tower.TowerType type, Point place) {
        String name;
        TowerImpl result;
        switch (type) {
            case a1 -> {
                name = "Towers/a1.png";
                result = new TowerImpl(place, 200f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 10f, 5));
            }
            case a2 -> {
                name = "Towers/a2.png";
                result = new TowerImpl(place, 220f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case a3 -> {
                name = "Towers/a3.png";
                result = new TowerImpl(place, 240f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case b1 -> {
                name = "Towers/b1.png";
                result = new TowerImpl(place, 260f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case b2 -> {
                name = "Towers/b2.png";
                result = new TowerImpl(place, 270f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case b3 -> {
                name = "Towers/b3.png";
                result = new TowerImpl(place, 280f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case c1 -> {
                name = "Towers/c1.png";
                result = new TowerImpl(place, 290f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case c2 -> {
                name = "Towers/c2.png";
                result = new TowerImpl(place, 300f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case c3 -> {
                name = "Towers/c3.png";
                result = new TowerImpl(place, 310f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case d1 -> {
                name = "Towers/d1.png";
                result = new TowerImpl(place, 320f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case d2 -> {
                name = "Towers/d2.png";
                result = new TowerImpl(place, 330f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            case d3 -> {
                name = "Towers/d3.png";
                result = new TowerImpl(place, 340f, Constants.fps, towerManager, name, type,
                        new ProjectileSupplierImpl.PurpleSupplier(projectileFactory, 15f, 5));
            }
            default -> throw new GameException("Unexpected argument passed as tower type.");
        }
        result.addRectangle(0, 0, imgHolder.getImgWidth(name), imgHolder.getImgHeight(name));
        result.addClickFunction(new TowerImpl.TowerClickFunction(result, clickableManager, imgHolder));
        clickableManager.addNewObject(result);
        return result;
    }

    public Tower upgrade(Tower tower) {
        return switch (tower.getType()) {
            case a1 -> produce(Tower.TowerType.a2, new Point(tower.getX(), tower.getY()));
            case a2 -> produce(Tower.TowerType.a3, new Point(tower.getX(), tower.getY()));
            case b1 -> produce(Tower.TowerType.b2, new Point(tower.getX(), tower.getY()));
            case b2 -> produce(Tower.TowerType.b3, new Point(tower.getX(), tower.getY()));
            case c1 -> produce(Tower.TowerType.c2, new Point(tower.getX(), tower.getY()));
            case c2 -> produce(Tower.TowerType.c3, new Point(tower.getX(), tower.getY()));
            case d1 -> produce(Tower.TowerType.d2, new Point(tower.getX(), tower.getY()));
            case d2 -> produce(Tower.TowerType.d3, new Point(tower.getX(), tower.getY()));
            default -> throw new GameException("Upgrade called on a full level tower");
        };
    }

}
