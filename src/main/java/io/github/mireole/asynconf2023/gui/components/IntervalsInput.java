package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.data.IntervalEntry;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class IntervalsInput<E extends IntervalEntry> {
    private final DefaultTableModel model;
    private final List<E> entries;
    private final IntervalEntryFactory<E> factory;
    private JPanel contentPane;
    private JTable table;
    private JButton insertButton;
    private JButton deleteButton;
    private boolean isUpdating;

    public IntervalsInput(List<E> entries, DefaultTableModel model, String[] columnNames, IntervalEntryFactory<E> factory) {
        this.model = model;
        this.entries = entries;
        this.factory = factory;
        this.model.addColumn(columnNames[0]);
        this.model.addColumn(columnNames[1]);
        this.model.addColumn(columnNames[2]);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        initListeners();
        initTable();
    }

    public IntervalsInput(List<E> entries, IntervalEntryFactory<E> factory) {
        this(entries, new DefaultTableModel(), new String[]{"Entre", "Et", "Score éco"}, factory);
    }

    private void createUIComponents() {
        table = new JTable();
        table.setModel(model);
    }

    private void initListeners() {
        insertButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            E entry = factory.create(0, 0, 0);
            if (index == -1) {
                entries.add(entry);
            } else {
                entries.add(index, entry);
            }
            insert(index, entry);
        });
        deleteButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            entries.remove(index);
            remove(index);
        });
        table.getSelectionModel().addListSelectionListener(e -> deleteButton.setEnabled(table.getSelectedRow() != -1));
        deleteButton.setEnabled(false);
        model.addTableModelListener(this::tableChanged);
    }

    private void initTable() {
        for (IntervalEntry entry : entries) {
            addRow(entry);
        }
    }

    /**
     * Adds a row at the end of the table.
     */
    private void addRow(IntervalEntry entry) {
        model.addRow(new Object[]{entry.getMinValue() + "", entry.getMaxValue() + "", entry.getEcoScore() + ""});
    }

    private void insert(int index, IntervalEntry entry) {
        if (index == -1) {
            addRow(entry);
            return;
        }
        model.insertRow(index, new Object[]{entry.getMinValue() + "", entry.getMaxValue() + "", entry.getEcoScore() + ""});
    }

    private void setRow(int index, IntervalEntry entry) {
        model.setValueAt(entry.getMinValue() + "", index, 0);
        model.setValueAt(entry.getMaxValue() + "", index, 1);
        model.setValueAt(entry.getEcoScore() + "", index, 2);
    }

    private void remove(int index) {
        model.removeRow(index);
    }

    private void tableChanged(TableModelEvent e) {
        // Looks like removing the listener is not enough to prevent infinite recursion
        if (e.getType() != TableModelEvent.UPDATE || isUpdating) {
            return;
        }
        isUpdating = true;

        int row = e.getFirstRow();

        model.removeTableModelListener(this::tableChanged);
        try {
            int min = Integer.parseInt((String) model.getValueAt(row, 0));
            int max = Integer.parseInt((String) model.getValueAt(row, 1));
            float score = Float.parseFloat((String) model.getValueAt(row, 2));
            E entry = factory.create(min, max, score);
            entries.set(row, entry);
        } catch (NumberFormatException ex) {
            IntervalEntry entry = entries.get(row);
            setRow(row, entry);
        } finally {
            model.addTableModelListener(this::tableChanged);
            isUpdating = false;
        }
    }

    @SuppressWarnings("unused")
    @FunctionalInterface
    public interface IntervalEntryFactory<E extends IntervalEntry> {
        E create(int min, int max, float score);
    }

}
