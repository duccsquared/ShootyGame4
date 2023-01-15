package game.grids.iterators;

import game.grids.Grid;

import java.util.Iterator;

public class NonNullFullItr<T> implements Iterator<T> {
    private Grid<T> grid;
    private int cursorX;
    private int cursorY;
    private boolean hasNext = true;
    private T nextItem;
    public NonNullFullItr(Grid grid) {
        this.grid = grid;
        cursorX = grid.getXMin();
        cursorY = grid.getYMin();
        this.nextItem();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        T val = nextItem;
        this.nextItem();
        return val;
    }
    private void nextItem() {
        T val;
        do {
            if(cursorX > grid.getXMax() || cursorY > grid.getYMax()) {
                hasNext = false;
                val = null;
                break;
            }
            val = this.grid.get(cursorX,cursorY);
            cursorX += 1;
            if(cursorX>grid.getXMax()) {
                cursorX = grid.getXMin();
                cursorY += 1;
            }
        }
        while(val==null);
        nextItem = val;
    }
}
