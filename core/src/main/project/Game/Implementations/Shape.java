package project.Game.Implementations;

import project.Game.LogicalShape;
import project.Game.GameException;

import java.util.ArrayList;
import java.util.List;

/*
    Implementation of LogicalShape.
    All classes extending this must use addRectangle at least once somewhere around their creation.
    Use designated factories.
 */

public class Shape implements LogicalShape{
    protected float x;
    protected float y;
    private float width = 0;
    private float height = 0;
    private final ArrayList<LogicalShape.Rectangle> recList;

    public Shape(float x, float y) {
        this.x = x;
        this.y = y;
        recList = new ArrayList<>();
    }

    @Override
    public float getX() { return x; }

    @Override
    public float getY() { return y; }

    @Override
    public float getWidth() { return width; }

    @Override
    public float getHeight() { return height; }

    @Override
    public void addRectangle(float relX, float relY, float width, float height) {
        if(relX + width > this.width)
            this.width = relX + width;
        if(relY + height > this.height)
            this.height = relY + height;
        Rectangle rec = new Rectangle(this, relX, relY, width, height);
        recList.add(rec);
    }

    @Override
    public boolean intersects(LogicalShape other) {
        if(recList.isEmpty() || other.getRectangles().isEmpty())
            throw new GameException("Logical shape not initialized");
        for(Rectangle rec1 : recList)
            for(Rectangle rec2 : other.getRectangles())
                if(rec1.intersects(rec2))
                    return true;
        return false;
    }

    @Override
    public boolean contains(float x, float y) {
        if(recList.isEmpty())
            throw new GameException("Logical shape not initialized");
        for(Rectangle rec: recList) {
           if(rec.contains(x, y))
               return true;
        }
        return false;
    }

    @Override
    public List<Rectangle> getRectangles() { return recList; }
}
