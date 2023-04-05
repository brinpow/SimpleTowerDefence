package project.Game;

import project.CommonInterfaces.Drawable;
import project.Point;

public interface Projectile extends LogicalShape, Drawable.DrawableRotated {
    void advance();
    int getPower();
    float getSpeed();

    interface ProjectileFactory {
        Projectile produce(Point source, Point target, float speed, int power, String imgName);
        Projectile produce(Point pos, float rotation, float speed, int power, String imgName);
    }
}
