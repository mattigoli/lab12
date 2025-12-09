package it.unibo.es2;

public class LogicsImpl implements Logics{

    private String[][] grid;

    public LogicsImpl(final int size) {
        this.grid = new String[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                this.grid[r][c] = " ";
            }
        }
    }

    @Override
    public String changeStatus(Pair<Integer, Integer> c) {
        this.grid[c.x()][c.y()] = this.grid[c.x()][c.y()] == "*" ? " " : "*"; 
        return this.grid[c.x()][c.y()];
    }

    @Override
    public boolean toQuit() {
        return check(true) || check(false);
    }

    public boolean check(boolean flag) {
        boolean exit = false;
        for (int r = 0; r < this.grid.length; r++) {
            exit = false;
            for (int c = 0; c < this.grid.length; c++) {
                if(flag){
                    exit = this.grid[r][c] == "*";
                } else {
                    exit = this.grid[c][r] == "*";
                }
                if(!exit){
                    break;
                }
            }
            if(exit){
                return exit;
            } 
        }
        return exit;
    }

}
