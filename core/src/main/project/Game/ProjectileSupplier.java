package project.Game;

import project.Game.Projectile;
import project.Point;

public interface ProjectileSupplier {
    Projectile get(Point source, Point target);
    int getPower();
}
