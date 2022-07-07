package game.grids.iterators;

import game.grids.Grid;

import java.util.Iterator;

public class GridItr<T> implements Iterator<T> {
    private Grid<T> grid;
    private int cursorX;
    private int cursorY;
    public GridItr(Grid grid) {
        this.grid = grid;
        cursorX = grid.getXMin();
        cursorY = grid.getYMin();
    }
    @Override
    public boolean hasNext() {
        return cursorX <= grid.getXMax() && cursorY <= grid.getYMax();
    }

    @Override
    public T next() {
        T val = this.grid.get(cursorX,cursorY);
        cursorX += 1;
        if(cursorX>grid.getXMax()) {
            cursorX = grid.getXMin();
            cursorY += 1;
        }
        return val;
    }
}
