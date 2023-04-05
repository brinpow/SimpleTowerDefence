package project.Game.Implementations;

import project.CommonInterfaces.Drawer;
import project.Game.Projectile;
import project.Point;

public class ProjectileFactoryImpl implements Projectile.ProjectileFactory {
    private final Drawer imgHolder;

    ProjectileFactoryImpl(Drawer imgHolder) {
        this.imgHolder = imgHolder;
    }

    private void createShape(Projectile p, float rot) {
        float alpha;
        if(Math.PI /2f <= rot && rot < Math.PI)
            alpha = (float)Math.PI - rot;
        else if(Math.PI <= rot && rot < Math.PI + Math.PI / 2f)
            alpha = rot - (float)Math.PI;
        else if(Math.PI + Math.PI /2f <= rot)
            alpha = rot - (float)(Math.PI + Math.PI / 2d);
        else
            alpha = rot;
        float sin = (float) Math.sin(alpha);
        float cos = (float) Math.cos(alpha);
        float width = imgHolder.getImgWidth(p.getImgName());
        float height = imgHolder.getImgHeight(p.getImgName());
        p.addRectangle(0f, 0f, sin * height + cos * width, sin * width + cos * height);
    }

    @Override
    public Projectile produce(Point source, Point target, float speed, int power, String imgName) {
        ProjectileImpl result = new ProjectileImpl(source, target, speed, power, imgName);
        createShape(result, result.getRotation());
        return result;
    }

    @Override
    public Projectile produce(Point pos, float rotation, float speed, int power, String imgName) {
        ProjectileImpl result = new ProjectileImpl(pos, rotation, speed, power, imgName);
        createShape(result, rotation);
        return result;
    }
}
