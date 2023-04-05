package project.CommonInterfaces;

public interface Drawable {
    float getX();
    float getY();
    String getImgName();

    interface DrawableRotated extends Drawable {
        float getRotation();
    }
}
