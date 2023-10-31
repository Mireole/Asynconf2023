package io.github.mireole.asynconf2023.gui.components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RunnableDocumentListener implements DocumentListener {
    private final Runnable runnable;

    public RunnableDocumentListener(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        runnable.run();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        runnable.run();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        runnable.run();
    }
}
