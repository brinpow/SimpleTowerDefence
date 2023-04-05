package project.Game;

import java.util.List;

/*
    Logical shape behind objects and their textures.
    It is represented as a set of rectangles.
*/

public interface LogicalShape {
    class Rectangle {
        // the translation of coordinates of owning shape
        // used to calculate the coordinates of the rectangle
        float relativeX;
        float relativeY;
        float width;
        float height;
        LogicalShape owner;

        public Rectangle(LogicalShape owner, float dx, float dy, float width, float height) {
            this.owner = owner;
            this.relativeX = dx;
            this.relativeY = dy;
            this.width = width;
            this.height = height;
        }

        private float realX() {
            return owner.getX() + relativeX;
        }

        private float realY() {
            return owner.getY() + relativeY;
        }

        public boolean intersects(Rectangle other) {
            if(realX() > other.realX() + other.width)
                return false;
            if(other.realX() > realX() + width)
                return false;
            if(realY() > other.realY() + other.height)
                return false;
            if(other.realY() > realY() + height)
                return false;
            return true;
        }

        public boolean contains(float x, float y) {
            return realX() <= x && x <= realX() + width &&
                    realY() <= y && y <= realY() + height;
        }
    }

    float getX();
    float getY();
    float getWidth();
    float getHeight();
    List<Rectangle> getRectangles();
    void addRectangle(float relX, float relY, float width, float height);
    boolean intersects(LogicalShape other);
    boolean contains(float x, float y);

    static float getCenterX(LogicalShape s) {
        return s.getX() + s.getWidth() / 2.0f;
    }

    static float getCenterY(LogicalShape s) {
        return s.getY() + s.getHeight() / 2.0f;
    }
}
