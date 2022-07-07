package game.grids;

import game.grids.iterators.GridIterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SparseGrid<T> implements Grid<T> {
    private HashMap<String,T> sparseMap = new HashMap<>();
    private int xMin = 0;
    private int yMin = 0;
    private int xMax = 0;
    private int yMax = 0;
    @Override
    public T get(int x, int y) {
        String coords = String.format("%d %d",x,y);
        return sparseMap.getOrDefault(coords, null);
    }
    @Override
    public void set(int x, int y, T value) {
        String coords = String.format("%d %d",x,y);
        xMin = Math.min(xMin,x);
        xMax = Math.max(xMax,x);
        yMin = Math.min(yMin,y);
        yMax = Math.max(yMax,y);
        sparseMap.put(coords,value);
    }
    @Override
    public void remove(int x, int y) {
        String coords = String.format("%d %d",x,y);
        sparseMap.remove(coords);
    }
    @Override
    public boolean contains(T value) {
        return sparseMap.containsValue(value);
    }
    @Override
    public int getXMin() {return xMin;}
    @Override
    public int getYMin() {return yMin;}
    @Override
    public int getXMax() {return xMax;}
    @Override
    public int getYMax() {return yMax;}

    @Override
    public Iterable<T> nonNullIterator() {
        return new GridIterable<T>(sparseMap.values().iterator());
    }

    public String toString() {
        return this.toStr();
    }
    public ArrayList<T> getValues() {
        return (ArrayList<T>) sparseMap.values();
    }

}
