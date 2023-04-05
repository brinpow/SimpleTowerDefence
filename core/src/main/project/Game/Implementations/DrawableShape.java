package project.Game.Implementations;

import project.CommonInterfaces.Drawable;
import project.Point;

public class DrawableShape extends Shape implements Drawable {
    protected String imgName;
    public DrawableShape(Point p, String imgName) {
        super(p.x(), p.y());
        this.imgName = imgName;
    }

    @Override
    public String getImgName() {
        return imgName;
    }
}
