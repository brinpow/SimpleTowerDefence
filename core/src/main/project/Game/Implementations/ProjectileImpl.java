package project.Game.Implementations;

import project.Game.Projectile;
import project.Point;

public class ProjectileImpl extends DrawableShape implements Projectile {
    private float dx;
    private float dy;
    private final int power;
    private float rotation; // in radians
    private final float speed;

    ProjectileImpl(Point source, Point target, float speed, int power, String imgName) {
        super(source, imgName);
        this.imgName = imgName;
        if(target.x() == source.x()) {
            dx = 0;
            dy = (target.y() > source.y()) ? speed : -speed;
        }
        else {
            float a = (source.y() - target.y()) / (source.x() - target.x());
            rotation = (float)(Math.atan(a));
            if(target.x() < source.x())
                rotation += Math.PI;
            if(rotation < 0f)
                rotation += 2f * Math.PI;
            if(a < 0)
                a = -a;
            dx = (float) Math.sqrt(speed * speed / (a * a + 1));
            dy = a * dx;
            if(target.x() < source.x())
                dx = -dx;
            if(target.y() < source.y())
                dy = -dy;
        }
        this.power = power;
        this.speed = speed; // maybe useless;
    }
    
    ProjectileImpl(Point pos, float rotation, float speed, int power, String imgName) {
        super(pos, imgName);
        this.rotation = rotation;
        dx = (float)Math.cos(rotation) * speed;
        dy = (float)Math.sin(rotation) * speed;
        this.speed = speed; // maybe useless
        this.power = power;
        this.imgName = imgName;
    }

    @Override
    public void advance() {
        x += dx;
        y += dy;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public float getSpeed()
    {
        return speed;
    }
}
