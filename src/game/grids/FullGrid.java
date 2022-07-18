package game.grids;

import game.grids.iterators.GridIterable;
import game.grids.iterators.NonNullFullItr;

import java.util.ArrayList;
import java.util.Iterator;

public class FullGrid<T> implements Grid<T> {
    ArrayList<ArrayList<T>> posXPosY = new ArrayList<>();
    ArrayList<ArrayList<T>> negXPosY = new ArrayList<>();
    ArrayList<ArrayList<T>> posXNegY = new ArrayList<>();
    ArrayList<ArrayList<T>> negXNegY = new ArrayList<>();
    public FullGrid() {
        posXPosY.add(new ArrayList<T>());
        negXPosY.add(new ArrayList<T>());
        posXNegY.add(new ArrayList<T>());
        negXNegY.add(new ArrayList<T>());
    }
    public FullGrid(int xMin, int yMin, int xMax, int yMax) {
        posXPosY.add(new ArrayList<T>());
        negXPosY.add(new ArrayList<T>());
        posXNegY.add(new ArrayList<T>());
        negXNegY.add(new ArrayList<T>());
        this.set(xMin,yMin,null);
        this.set(xMax,yMin,null);
        this.set(xMin,yMax,null);
        this.set(xMax,yMax,null);

    }
    public int getXMin() {return Math.min(0,-(negXPosY.get(0).size() - 1));}
    public int getYMin() {return Math.min(0,-(posXNegY.size() - 1));}
    public int getXMax() {return Math.max(0,posXPosY.get(0).size() - 1);}
    public int getYMax() {return Math.max(0,posXPosY.size() - 1);}

    public T get(int x, int y) {
        ArrayList<ArrayList<T>> subGrid = this.getCorrectSubgrid(x,y);
        return subGrid.get(Math.abs(y)).get(Math.abs(x));
    }
    public void set(int x, int y, T value) {
        ArrayList<ArrayList<T>> subGrid = this.getCorrectSubgrid(x,y);
        int absX = Math.abs(x);
        int absY = Math.abs(y);
        if(absX>subGrid.get(0).size()-1) {
            if(x>=0) {extendPosX(absX);}
            else {extendNegX(absX);}
        }
        if(absY>subGrid.size()-1) {
            if(y>=0) {extendPosY(absY);}
            else {extendNegY(absY);}
            extendY(subGrid,absY);
        }
        subGrid.get(absY).set(absX,value);
    }

    @Override
    public void remove(int x, int y) {
        ArrayList<ArrayList<T>> subGrid = this.getCorrectSubgrid(x,y);
        subGrid.get(Math.abs(y)).set(Math.abs(x),null);
    }

    @Override
    public boolean contains(T value) {
        for(T iterValue : this) {
            if(value.equals(iterValue)) {return true;}
        }
        return false;
    }

    @Override
    public void clear() {
        for(int i = this.getXMin(); i <= this.getXMax(); i++) {
            for(int j = this.getYMin(); j <= this.getYMax(); j++) {
                this.set(i,j,null);
            }
        }
    }

    @Override
    public int indexXOf(T value) {
        for(int i = this.getXMin(); i <= this.getXMax(); i++) {
            for(int j = this.getYMin(); j <= this.getYMax(); j++) {
                T comparedVal = this.get(i,j);
                if(value.equals(comparedVal)) {
                    return i;
                }
            }
        }
        throw new RuntimeException();
    }

    @Override
    public int indexYOf(T value) {
        for(int i = this.getXMin(); i <= this.getXMax(); i++) {
            for(int j = this.getYMin(); j <= this.getYMax(); j++) {
                T comparedVal = this.get(i,j);
                if(value.equals(comparedVal)) {
                    return j;
                }
            }
        }
        throw new RuntimeException();
    }

    private ArrayList<ArrayList<T>> getCorrectSubgrid(int x, int y) {
        ArrayList<ArrayList<T>> subGrid;
        if(x>=0 && y>=0) {subGrid = posXPosY;}
        else if(x<0 && y>=0) {subGrid = negXPosY;}
        else if(x>=0) {subGrid = posXNegY;} //x>=0 && y<0
        else {subGrid = negXNegY;} //x<0 && y<0
        return subGrid;
    }
    private void extendPosY(int totalColumns) {
        extendY(posXPosY,totalColumns);
        extendY(negXPosY,totalColumns);

    }
    private void extendNegY(int totalColumns) {
        extendY(posXNegY,totalColumns);
        extendY(negXNegY,totalColumns);
    }
    private void extendPosX(int totalRows) {
        extendX(posXPosY,totalRows);
        extendX(posXNegY,totalRows);
    }
    private void extendNegX(int totalRows) {
        extendX(negXPosY,totalRows);
        extendX(negXNegY,totalRows);
    }

    private void extendX(ArrayList<ArrayList<T>> subGrid, int totalColumns) {
        int columnsToExtend = totalColumns - (subGrid.get(0).size()-1);
        if(columnsToExtend<=0) {return;}
        for(ArrayList<T> arrayList : subGrid) {
            for(int i = 0; i < columnsToExtend; i++) {
                arrayList.add(null);
            }
        }
    }
    private void extendY(ArrayList<ArrayList<T>> subGrid, int totalRows) {
        int rowsToExtend = totalRows - (subGrid.size()-1);
        int size = subGrid.get(0).size();
        if(rowsToExtend<=0) {return;}
        for(int i = 0; i < rowsToExtend; i++) {
            ArrayList<T> arrayList = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                arrayList.add(null);
            }
            subGrid.add(arrayList);
        }
    }

    public String toString() {
        return this.toStr();
    }
    @Override
    public Iterable<T> nonNullIterator() {
        return new GridIterable<T>(new NonNullFullItr<T>(this));
    }
}
