package project.Game.Implementations;

import project.CommonInterfaces.DrawableText;

public class DrawableTextImpl implements DrawableText {
    private String text;
    private final float x;
    private final float y;
    private final float scale;

    public DrawableTextImpl(String text, float x, float y, float scale) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public float getScale() {
        return scale;
    }
}
