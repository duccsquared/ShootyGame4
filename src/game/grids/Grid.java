package game.grids;

import java.util.ArrayList;

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
    default String toStr() {
        String result = "";
        ArrayList<ArrayList<String>> resultGrid = new ArrayList<>();
        int maxLength = 0;
        for(int i = getYMin(); i <= getYMax(); i++) {
            ArrayList<String> itemArray = new ArrayList<>();
            for(int j = getXMin(); j <= getXMax(); j++) {
                T item = this.get(j,i);
                String str;
                if(item==null) {str = "-";}
                else {str = item.toString();}
                maxLength = Math.max(maxLength,str.length());
                itemArray.add(str);
            }
            resultGrid.add(itemArray);
        }
        for(ArrayList<String> array: resultGrid) {
            for(int i = 0; i < array.size(); i++) {
                array.set(i,String.format("%"+maxLength+"s",array.get(i)));
            }
            result += "[" + String.join(" ",array) + "]\n";
        }
        return result;
    }
}
