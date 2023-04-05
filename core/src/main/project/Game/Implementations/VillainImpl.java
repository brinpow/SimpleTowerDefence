package project.Game.Implementations;

import project.Game.Path;
import project.Game.Villain;
import project.Point;

public class VillainImpl extends DrawableShape implements Villain {
    private int hp;
    private final int speed;
    final private Path path;
    private int pathIndex = 0;
    private final Villain.VillainType type;
    private final int goldValue;
    public VillainImpl(int hp, int speed, Path path, String imgName, Villain.VillainType type,
                       int goldValue) {
        super(new Point(0,0), imgName);
        this.hp = hp;
        this.speed = speed;
        this.path = path;
        this.type = type;
        Point coordinates = path.getPoint(0);
        x = coordinates.x();
        y = coordinates.y();
        this.goldValue = goldValue;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void dealDamage(int amount) {
        hp -= amount;
    }

    @Override
    public void advance() {
        advance(1);
    }

    @Override
    public void advance(int times) {
        pathIndex += times * speed;
        if(!isOut()) {
            Point newCoordinates = path.getPoint(pathIndex);
            x = newCoordinates.x() - getWidth() / 2;
            y = newCoordinates.y() - getHeight() / 2;
        }
    }

    @Override
    public int timeAlive() {
        return pathIndex / speed;
    }

    @Override
    public int remainingPath() {
        return path.length() - pathIndex;
    }

    @Override
    public int getPathIndex() {
        return path.getIndex();
    }

    @Override
    public boolean isOut() { return path.length() - pathIndex <= 0; }

    @Override
    public int getHp() { return hp; }

    @Override
    public Path.direction getDirection() { return path.getDirection(pathIndex); }

    @Override
    public VillainType getType()
    {
        return type;
    }
    @Override
    public int getGoldValue() {
        return goldValue;
    }
}
