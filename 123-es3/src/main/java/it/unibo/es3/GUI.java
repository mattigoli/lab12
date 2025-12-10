package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final transient Logics logic;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel panel = new JPanel(new GridLayout(width, width));
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(j, i);
                final JButton button = new JButton(this.logic.getCell(pos.x(), pos.y()));
                this.cells.add(button);
                panel.add(button);
            }
        }
        final JPanel newPanel = new JPanel(new BorderLayout(width, width));
        this.getContentPane().add(newPanel);
        newPanel.add(panel, BorderLayout.CENTER);
        final JButton expandButton = new JButton(">");
        newPanel.add(expandButton, BorderLayout.SOUTH);
        expandButton.addActionListener(e -> {
            this.logic.expand();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    this.cells.get(j * width + i).setText(this.logic.getCell(i, j));
                }
            }
            if (this.logic.toQuit()) {
                dispose();
            }
        });
        pack();
        this.setVisible(true);
    }
}
