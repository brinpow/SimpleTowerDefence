package project.Game;

public interface ListInterface<T> extends Iterable<T> {
    /* when only iterator and appending are required and order does not matter
       more efficient implementations than standard Java lists are possible
     */
    void add(T t);
    int getSize();
    boolean isEmpty();
}
