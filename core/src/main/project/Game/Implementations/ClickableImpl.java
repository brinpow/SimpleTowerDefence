package project.Game.Implementations;

import project.Game.Clickable;
import project.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClickableImpl extends DrawableShape implements Clickable {
    private final List<ClickFunction> functions;
    public ClickableImpl(Point p, String imgName) {
        super(p, imgName);
        functions = new ArrayList<>();
    }

    @Override
    public void addClickFunction(ClickFunction function) {
        functions.add(function);
    }

    @Override
    public void removeClickFunction(ClickFunction function) {
        functions.remove(function);
    }

    @Override
    public Iterator<ClickFunction> getClickFunctions() {
        return functions.iterator();
    }
}
