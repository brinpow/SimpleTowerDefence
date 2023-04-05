package project.CommonInterfaces;


public interface TextDrawer {
    void dispose();
    void begin();
    void end();
    void write(DrawableText text);
    void writeTinted(DrawableText text, float r, float g, float b);
}
