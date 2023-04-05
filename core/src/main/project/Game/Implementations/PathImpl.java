package project.Game.Implementations;

import project.Game.LogicalShape;
import project.Game.Path;
import project.Point;
import project.Game.GameException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathImpl implements Path {
    private final ArrayList<Line> lines = new ArrayList<>();
    private int length = 0;
    private final float w;
    private final int index;

    private static class Line {
        public final float x;
        public final float y;
        public final int length;
        public final direction d;

        Line(Point begin, Point end) {
            x = begin.x();
            y = begin.y();
            if(begin.x() == end.x()) {
                if(begin.y() > end.y()) {
                    length = (int) (begin.y() - end.y());
                    d = direction.down;
                }
                else {
                    length = (int) (end.y() - begin.y());
                    d = direction.up;
                }
            }
            else if(begin.y() == end.y()) {
                if(begin.x() > end.x()) {
                    length = (int) (begin.x() - end.x());
                    d = direction.left;
                }
                else {
                    length = (int) (end.x() - begin.x());
                    d = direction.right;
                }
            }
            else {
                String errMsg = "Incorrect points used to construct a path: (" +
                        begin.x() + ", " + begin.y() + ") (" + end.x() + ", " + end.y() + ")";
                throw new GameException(errMsg);
            }

        }

        public Point getPoint(int i) {
            return switch(d) {
                case up -> new Point(x, y + i);
                case down -> new Point(x, y - i);
                case left -> new Point(x - i, y);
                case right -> new Point(x + i, y);
            };
        }

        public direction getD() {
            return d;
        }
    }

    PathImpl(List<Point> turningPoints, float w, int index) {
        if(w <= 0f)
            throw new GameException("Non-positive w in path constructor: " + w);
        this.w = w;
        this.index = index;
        if(turningPoints.size() < 2)
            throw new GameException("Insufficient number of points to create a path");
        Iterator<Point> it = turningPoints.iterator();
        Point previous = it.next();
        while(it.hasNext()) {
            Point current = it.next();
            Line toAdd = new Line(previous, current);
            length += toAdd.length;
            lines.add(toAdd);
            previous = current;
        }
    }

    @Override
    public Point getPoint(int i) {
        for(Line line : lines) {
            if(i <= line.length)
                return line.getPoint(i);
            i -= line.length;
        }
        throw new GameException("Point index out of path");
    }

    @Override
    public direction getDirection(int i) {
        for(Line line : lines) {
            if(i <= line.length)
                return line.getD();
            i -= line.length;
        }
        throw new GameException("Point index out of Path");
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public LogicalShape getShape(){
        Shape s = new Shape(0, 0);
        for(Line line : lines) {
            switch(line.d) {
                case right -> s.addRectangle(line.x - w / 2f, line.y - w / 2f,
                        line.length + w, w);
                case left -> s.addRectangle(line.x - line.length - w / 2f, line.y - w / 2f,
                        line.length + w, w);
                case up -> s.addRectangle(line.x - w / 2f, line.y - w / 2f,
                        w, line.length + w);
                case down -> s.addRectangle(line.x - w / 2f, line.y - line.length - w / 2f,
                        w, line.length + w);
            }
        }
        return s;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
