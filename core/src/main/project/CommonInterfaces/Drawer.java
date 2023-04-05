package project.CommonInterfaces;

public interface Drawer {
    void begin();
    void draw(Drawable object);
    void draw(Drawable object, float scale, boolean centered); // if centered == true draw the image so that the center of the image
    // will be in the position of the drawable object, instead of the regular left corner
    // scale may be used to draw images of size bigger or lower than originally loaded
    void drawTinted(Drawable object, float r, float g, float b); // rgb parameters
    // are in the range [0, 1]. If higher or lower values are passed, they will
    // be clamped to the valid range
    void drawRotated(Drawable.DrawableRotated object);
    void end();
    void dispose();

    void load(String imgName, int originWidth, int originHeight, float scale);
    void load(String imgName, int originWidth, int originHeight);
    float getImgWidth(String imgName);
    float getImgHeight(String imgName);
}
