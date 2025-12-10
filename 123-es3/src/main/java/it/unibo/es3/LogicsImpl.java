package it.unibo.es3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Implementation of Logics.
 */
public class LogicsImpl implements Logics {

    private static final String STAR = "*";
    private static final Random RAND = new Random();

    private final String[][] grid;

    /**
     * Constructor.
     * 
     * @param size the size of the row and column
     */
    public LogicsImpl(final int size) {
        this.grid = new String[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.grid[r][c] = " ";
            }
        }
        for (int i = 0; i < 3; i++) {
            this.grid[RAND.nextInt(0, 10)][RAND.nextInt(0, 10)] = STAR;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expand() {
        final List<Pair<Integer, Integer>> queue = new ArrayList<>();
        for (int r = 0; r < this.grid.length; r++) {
            for (int c = 0; c < this.grid.length; c++) {
                if (STAR.equals(this.grid[r][c])) {
                    queue.add(new Pair<>(r, c));
                }
            }
        }

        for (final var point: queue) {
            expandPoint(point.x(), point.y());
        }
    }

    /**
     * This method take as input a point to expand and it replace with * the cell that are near the point.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    private void expandPoint(final int x, final int y) {
        if (x + 1 < this.grid.length) {
            this.grid[x + 1][y] = STAR;
            if (y + 1 < this.grid.length) {
                this.grid[x + 1][y + 1] = STAR;
            }
            if (y - 1 >= 0) {
                this.grid[x + 1][y - 1] = STAR;
            }
        }
        if (y + 1 < this.grid.length) {
            this.grid[x][y + 1] = STAR;
        }
        if (y - 1 >= 0) {
            this.grid[x][y - 1] = STAR;
        }
        if (x - 1 >= 0) {
            this.grid[x - 1][y] = STAR;
            if (y + 1 < this.grid.length) {
                this.grid[x - 1][y + 1] = STAR;
            }
            if (y - 1 >= 0) {
                this.grid[x - 1][y - 1] = STAR;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() { 
        return Arrays.stream(this.grid)
            .flatMap(Arrays::stream)
            .allMatch(STAR::equals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCell(final int x, final int y) {
        return this.grid[x][y];
    }
}
