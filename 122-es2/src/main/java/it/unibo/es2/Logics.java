package it.unibo.es2;

public interface Logics {
    /**
     * It change the status of a label, if is * it returns a space
     * 
     * @return the new status of the label
     */
    String changeStatus(Pair<Integer, Integer> c);

    /**
     * It return true if one casual row or column is full of *
     * 
     * @return if the application must close
     */
    boolean toQuit();
}
