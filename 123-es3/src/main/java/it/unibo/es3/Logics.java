package it.unibo.es3;

/**
 * The logic of the GUI.
 */
public interface Logics {

    /**
     * That method expand all the star.
     */
    void expand();

    /**
     * It returns true if all the map is turn on (*).
     * 
     * @return true if the applicatio must end
     */
    boolean toQuit();

    /**
     * This method returns the value in the (x, y) point.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @return the value of the cell, space or star
     */
    String getCell(int x, int y);
}
