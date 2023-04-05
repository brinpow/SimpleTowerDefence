package project.Game;

import project.Game.LogicalShape;
import project.Point;

public interface Path {
    enum direction {
        left, right, up, down
    }

    Point getPoint(int i);
    int length();
    LogicalShape getShape();
    direction getDirection(int i);
    int getIndex();
}
