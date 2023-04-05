package project.Game.Implementations;

import project.Game.LogicalShape;

/* creates rectangle logical shape */
public class LogicalShapeFactory {
    public static LogicalShape produceRectangle(float x, float y,float width, float height) {
        Shape s = new Shape(x,y);
        s.addRectangle(0,0,width, height);
        return s;
    }
}
