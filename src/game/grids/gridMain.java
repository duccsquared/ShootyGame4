package game.grids;

public class gridMain {
    public static void main(String[] args) {
        Grid<String> grid = new FullGrid();
        grid.set(2,3,"a");
        grid.set(3,2,"b");
        grid.set(-2,3,"c");
        grid.set(-3,2,"d");
        grid.set(2,-3,"e");
        grid.set(3,-2,"f");
        grid.set(-2,-3,"g");
        grid.set(-3,-2,"h");
        grid.set(0,0,"i");
        System.out.println(grid.get(2,3));
        System.out.println(grid.get(3,2));
        System.out.println(grid.get(-2,3));
        System.out.println(grid.get(-3,2));
        System.out.println(grid.get(2,-3));
        System.out.println(grid.get(3,-2));
        System.out.println(grid.get(-2,-3));
        System.out.println(grid.get(-3,-2));
        System.out.println(grid.get(0,0));
        System.out.println(grid.getXMin());
        System.out.println(grid.getYMin());
        System.out.println(grid.getXMax());
        System.out.println(grid.getYMax());
        System.out.println(grid.getRows());
        System.out.println(grid.getColumns());
        System.out.println(grid);

    }
}
