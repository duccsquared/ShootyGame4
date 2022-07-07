package game.test;

import game.grids.FullGrid;
import game.grids.Grid;
import game.grids.SparseGrid;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GridTest {
    @Test
    public void testSetGet() {
        for(Grid<Integer> grid: new Grid[] {new FullGrid<>(), new SparseGrid<>()}) {
            for(int i = -3; i <= 4; i++) {
                for(int j = -5; j <= 6; j++) {
                    if((i+j)/2 == (i+j)/2.0) {
                        grid.set(i,j,i+j);
                    }
                }
            }
            System.out.println(grid);
            for(int i = -3; i <= 4; i++) {
                for (int j = -5; j < 6; j++) {
                    if((i+j)/2 == (i+j)/2.0) {Assert.assertEquals(i+j,grid.get(i,j).intValue());
                    }
                    else {Assert.assertEquals(null,grid.get(i,j));}
                }
            }
            Assert.assertEquals(-3,grid.getXMin());
            Assert.assertEquals(4,grid.getXMax());
            Assert.assertEquals(-5,grid.getYMin());
            Assert.assertEquals(6,grid.getYMax());
            Assert.assertEquals(12,grid.getRows());
            Assert.assertEquals(8,grid.getColumns());
        }

    }
    @Test
    public void testDelete() {

        for(Grid<String> grid: new Grid[] {new FullGrid<>(), new SparseGrid<>()}) {
            grid.set(2,4,"apple");
            grid.set(-5,2,"banana");
            grid.set(1,0,"orange");
            grid.set(-2,-8,"grape");

            System.out.println(grid);
            Assert.assertEquals("apple",grid.get(2,4));
            grid.remove(2,4);
            Assert.assertEquals(null,grid.get(2,4));
            Assert.assertEquals("banana",grid.get(-5,2));
            Assert.assertEquals("orange",grid.get(1,0));
            Assert.assertEquals("grape",grid.get(-2,-8));
            grid.remove(-5,2);
            grid.remove(1,0);
            grid.remove(-2,-8);
            Assert.assertEquals(null,grid.get(-5,2));
            Assert.assertEquals(null,grid.get(1,0));
            Assert.assertEquals(null,grid.get(-2,-8));
        }

    }
    @Test
    public void testContains() {
        for(Grid<Double> grid: new Grid[] {new FullGrid<>(), new SparseGrid<>()}) {
            grid.set(0,-5,4.2);
            grid.set(-2,0,6.9);
            grid.set(0,2,4.2);
            grid.set(-2,-6,-1.2);
            Assert.assertTrue(grid.contains(4.2));
            Assert.assertTrue(grid.contains(6.9));
            Assert.assertTrue(grid.contains(-1.2));
            Assert.assertFalse(grid.contains(8.5));
        }
    }
    @Test
    public void testIterNonNull() {
        for(Grid<Integer> grid: new Grid[] {new FullGrid<>(), new SparseGrid<>()}) {
            grid.set(0,0,1);
            grid.set(-1,14,2);
            grid.set(8,1,3);
            grid.set(-3,-1,4);
            grid.set(3,-1,5);
            ArrayList<Integer> intArray = new ArrayList<>();
            for(Integer val : grid.nonNullIterator()) {
                intArray.add(val);
            }
            Assert.assertEquals(5,intArray.size());
            for(int i = 1; i <= 5; i++) {
                Assert.assertTrue(intArray.contains(i));
            }
        }
    }
}
