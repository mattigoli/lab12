package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int size;
    private final List<Integer> buttons;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.size = size;
        this.buttons = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            this.buttons.add(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return Collections.unmodifiableList(this.buttons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        final List<Boolean> en = new ArrayList<>();
        for (final Integer i: this.buttons) {
            en.add(i != this.size);
        }
        return en;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        this.buttons.set(elem, this.buttons.get(elem) + 1);
        return this.buttons.get(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        final StringBuilder res = new StringBuilder("<<");
        for (int i = 0; i < this.size; i++) {
            res.append(i == this.size - 1 ? this.buttons.get(i) + ">>" : this.buttons.get(i) + "|");
        }
        return res.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        final int val = this.buttons.get(0);
        boolean equal = false;
        for (int i = 1; i < this.size; i++) {
            equal = this.buttons.get(i) == val;
            if (!equal) {
                return equal;
            }
        }
        return equal;
    }
}
