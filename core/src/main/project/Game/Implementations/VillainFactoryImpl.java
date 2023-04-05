package project.Game.Implementations;

import project.CommonInterfaces.Drawer;
import project.Game.Path;
import project.Game.Villain;
import project.Game.GameException;

public class VillainFactoryImpl implements Villain.VillainFactory {
    private final Drawer imgHolder;

    VillainFactoryImpl(Drawer imgHolder) {
        this.imgHolder = imgHolder;
    }

    @Override
    public Villain produce(Villain.VillainType type, Path path) {
        String name;
        VillainImpl result;
        switch(type) {
            case b1 -> {
                name = "Balloons/balloon1.png";
                result = new VillainImpl(5, 4, path, name, Villain.VillainType.b1, 100);
            }
            case b2 -> {
                name = "Balloons/balloon2.png";
                result = new VillainImpl(8, 4, path, name, Villain.VillainType.b2,150);
            }
            case b3 -> {
                name = "Balloons/balloon3.png";
                result = new VillainImpl(12, 4, path, name, Villain.VillainType.b3,200);
            }
            case b4 -> {
                name = "Balloons/balloon4.png";
                result = new VillainImpl(15, 4, path, name, Villain.VillainType.b4, 250);
            }
            case b5 -> {
                name = "Balloons/balloon5.png";
                result = new VillainImpl(18, 4, path, name, Villain.VillainType.b5, 300);
            }
            case b6 -> {
                name = "Balloons/balloon6.png";
                result = new VillainImpl(21, 4, path, name, Villain.VillainType.b6, 350);
            }
            case b7 -> {
                name = "Balloons/balloon7.png";
                result = new VillainImpl(30, 4, path, name, Villain.VillainType.b7, 400);
            }
            case b8 -> {
                name = "Balloons/balloon8.png";
                result = new VillainImpl(35, 4, path, name, Villain.VillainType.b8, 450);
            }
            default -> throw new GameException("Unexpected argument passed as villain type");
        }
        float w = imgHolder.getImgWidth(name);
        float h = imgHolder.getImgHeight(name);
        result.addRectangle(w / 3f, 0, w/ 4f, h / 9f); // tail
        result.addRectangle(w / 8f, h / 9f, 3f * w / 4f, 2f * h / 9f);
        result.addRectangle(0, 3 * h / 9f, w, 4f * h / 9f);
        result.addRectangle(w / 8f, 7 * h / 9f, 3f * w / 4f, 2f * h / 9f);
        return result;
    }
}
