package project.Drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import project.CommonInterfaces.DrawableText;
import project.CommonInterfaces.TextDrawer;

public class GdxTextDrawer implements TextDrawer {
    private final SpriteBatch batch;
    private final BitmapFont font;

    public GdxTextDrawer() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void begin() {
        batch.begin();
    }

    @Override
    public void end() {
        batch.end();
    }

    @Override
    public void write(DrawableText text) {
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        font.getData().setScale(text.getScale());
        font.draw(batch, text.getText(), text.getX(), text.getY());
    }

    @Override
    public void writeTinted(DrawableText text, float r, float g, float b) {
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        font.getData().setScale(text.getScale());
        font.setColor(r,g,b,1.0f);
        font.draw(batch, text.getText(), text.getX(), text.getY());
        font.setColor(Color.WHITE);
    }
}
