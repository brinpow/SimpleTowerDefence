package project.Game.Implementations;

import com.badlogic.gdx.utils.Array;
import project.Game.ListInterface;

import java.util.Iterator;

public class GdxList<T> implements ListInterface<T> {
    private final Array<T> array;

    GdxList() {
        array = new Array<>(false, 16);
    }

    @Override
    public void add(T t) {
        array.add(t);
    }

    @Override
    public Iterator<T> iterator() { return array.iterator(); }

    @Override
    public int getSize(){return array.size; }

    @Override
    public boolean isEmpty()
    {
        return array.isEmpty();
    }
}
