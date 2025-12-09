package it.unibo.es2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of Logics.
 */
public class LogicsImpl implements Logics {

    private static final String ASTERISK = "*";
    private static final String SPACE = " ";

    private final String[][] grid;

    /**
     * Constructor.
     * 
     * @param size the size of the grid (3*3) or (4*4)
     */
    public LogicsImpl(final int size) {
        this.grid = new String[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.grid[r][c] = SPACE;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String changeStatus(final Pair<Integer, Integer> c) {
        this.grid[c.x()][c.y()] = ASTERISK.equals(this.grid[c.x()][c.y()]) ? SPACE : ASTERISK; 
        return this.grid[c.x()][c.y()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return check(Arrays.stream(this.grid)) || check(columnStream());
    }

    /**
     * It return true if one of the list passed as parameter is full of *.
     * 
     * @param list it can be the row or the column list 
     * @return true if one column or row is full of *
     */
    private boolean check(final Stream<String[]> list) {
        return list.anyMatch(line -> Arrays.stream(line).allMatch(ASTERISK::equals));
    }

    /**
     * This method create the stream of the column that is a List of string array.
     * 
     * @return the stream of the column
     */
    private Stream<String[]> columnStream() {
        final List<String[]> cols = new ArrayList<>();
        for (int c = 0; c < this.grid.length; c++) {
            cols.add(getColumn(c));
        }
        return cols.stream();
    }

    /**
     * This method return the column of the index passed as parameter from the grid.
     * 
     * @param colIdx the index of the column
     * @return one column of the grid
     */
    private String[] getColumn(final int colIdx) {
        final String[] col = new String[this.grid.length];
        for (int r = 0; r < this.grid.length; r++) {
            col[r] = this.grid[r][colIdx];
        } 
        return col;
    }

}
