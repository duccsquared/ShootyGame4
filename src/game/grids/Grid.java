package game.grids;

public interface Grid<T> {
    T get(int x, int y);
    void set(int x, int y, T value);
    void remove(int x, int y);
    boolean contains(T value);
    int getXMin();
    int getYMin();
    int getXMax();
    int getYMax();
    default int getRows() {return this.getYMax() - this.getYMin() + 1;}
    default int getColumns() {return this.getXMax() - this.getXMin() + 1;}
    default int getWidth() {return getColumns();}
    default int getHeight() {return getRows();}
}
