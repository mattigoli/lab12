package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Integer> buttons;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.buttons = new ArrayList<>(size);
        Stream.iterate(0, i -> i + 1).limit(size).forEach(i -> this.buttons.add(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.buttons.size();
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
        final List<Boolean> en = new ArrayList<>(this.buttons.size());
        for (final Integer i: this.buttons) {
            en.add(i != this.buttons.size());
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
        for (int i = 0; i < this.buttons.size(); i++) {
            res.append(i == this.buttons.size() - 1 ? this.buttons.get(i) + ">>" : this.buttons.get(i) + "|");
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
        for (int i = 1; i < this.buttons.size(); i++) {
            equal = this.buttons.get(i) == val;
            if (!equal) {
                return equal;
            }
        }
        return equal;
    }
}
