package game.grids.iterators;

import java.util.Iterator;

public class GridIterable<T> implements Iterable<T> {
    Iterator<T> iterator;
    public GridIterable(Iterator<T> iterator) {
        this.iterator = iterator;
    }
    @Override
    public Iterator<T> iterator() {
        return iterator;
    }
}
