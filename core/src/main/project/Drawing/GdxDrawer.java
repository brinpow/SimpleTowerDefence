package project.Drawing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import project.Constants;
import project.CommonInterfaces.Drawable;
import project.CommonInterfaces.Drawer;
import project.Game.GameException;

import java.util.HashMap;
import java.util.ArrayList;

public class GdxDrawer implements Drawer {
    private final SpriteBatch batch;
    private final HashMap<String, Sprite> textureMap;
    private final OrthographicCamera camera;
    private final ArrayList<Texture> textureList;

    public GdxDrawer() {
        batch = new SpriteBatch();
        textureList = new ArrayList<>();
        textureMap = new HashMap<>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.screenWidth, Constants.screenHeight);
    }

    @Override
    public void begin() {
        ScreenUtils.clear(0, 1, 0, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    public void end() {
        batch.end();
    }

    @Override
    public void dispose() {
        for(Texture t : textureList)
            t.dispose();
        batch.dispose();
        textureMap.clear();
        textureList.clear();
    }

    @Override
    public void load(String imgName, int originWidth, int originHeight, float scale) {
        if(textureMap.containsKey(imgName))
            throw new GameException("Image already loaded");
        Texture texture = new Texture(Gdx.files.internal(imgName));
        textureList.add(texture);
        Sprite sprite = new Sprite(texture, 0, 0, originWidth, originHeight);
        sprite.setSize(scale * originWidth, scale * originHeight);
        textureMap.put(imgName, sprite);
    }

    @Override
    public void load(String imgName, int originWidth, int originHeight) {
        load(imgName, originWidth, originHeight, 1.0f);
    }

    private Sprite prepareSprite(Drawable object) {
        Sprite sprite = textureMap.get(object.getImgName());
        if (sprite == null)
            throw new GameException("Trying to draw image which hadn't been loaded: " + object.getImgName());
        sprite.setPosition(object.getX(), object.getY());
        return sprite;
    }

    @Override
    public void draw(Drawable object) {
        Sprite sprite = prepareSprite(object);
        sprite.draw(batch);
    }

    @Override
    public void draw(Drawable object, float scale, boolean centered) {
       Sprite sprite = prepareSprite(object);
       float oldW = sprite.getWidth();
       float oldH = sprite.getHeight();
       sprite.setSize(scale * oldW, scale * oldH);
       if(centered) {
           sprite.setPosition(object.getX() - sprite.getWidth() / 2,
                   object.getY() - sprite.getHeight() / 2);
       }
       else {
            sprite.setPosition(object.getX(), object.getY());
       }
       sprite.draw(batch);
       sprite.setSize(oldW, oldH);
    }

    @Override
    public void drawRotated(Drawable.DrawableRotated object) {
        Sprite sprite = prepareSprite(object);
        sprite.setOrigin(getImgWidth(object.getImgName()) / 2, getImgHeight(object.getImgName()) / 2);
        sprite.setRotation((float)Math.toDegrees(object.getRotation()));
        sprite.draw(batch);
    }

    @Override
    public void drawTinted(Drawable object, float r, float g, float b) {
        Sprite sprite = prepareSprite(object);
        sprite.setColor(r, g, b, 1.0f);
        sprite.draw(batch);
        sprite.setColor(Color.WHITE);
    }

    @Override
    public float getImgWidth(String imgName) {
        if(!textureMap.containsKey(imgName))
            throw new GameException("Trying to get data about image which hadn't been loaded: " + imgName);
        return textureMap.get(imgName).getWidth();
    }

    @Override
    public float getImgHeight(String imgName) {
        if(!textureMap.containsKey(imgName))
            throw new GameException("Trying to get data about image which hadn't been loaded");
        return textureMap.get(imgName).getHeight();
    }
}
