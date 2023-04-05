package project.Game.Implementations;

import project.Game.Projectile;
import project.Game.ProjectileSupplier;
import project.Point;

public abstract class ProjectileSupplierImpl implements ProjectileSupplier {
    protected final Projectile.ProjectileFactory factory;
    protected final float speed;
    protected final int power;

    ProjectileSupplierImpl(Projectile.ProjectileFactory factory, float speed, int power) {
        this.factory = factory;
        this.speed = speed;
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    public static class PurpleSupplier extends ProjectileSupplierImpl {
        PurpleSupplier(Projectile.ProjectileFactory factory, float speed, int power) {
            super(factory, speed, power);
        }

        @Override
        public Projectile get(Point source, Point target) {
            return factory.produce(source, target, speed, power, "Towers/shot1.png");
        }
    }

    public static class RedSupplier extends ProjectileSupplierImpl {
        RedSupplier(Projectile.ProjectileFactory factory, float speed, int power) {
            super(factory, speed, power);
        }

        @Override
        public Projectile get(Point source, Point target) {
            return factory.produce(source, target, speed, power, "Towers/shot2.png");
        }
    }
}
