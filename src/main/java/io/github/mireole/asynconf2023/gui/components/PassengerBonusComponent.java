package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.TempConfig;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class PassengerBonusComponent {
    private final TempConfig tempConfig;
    private JButton removeRow;
    private JButton addRow;
    private JTable table;
    private JPanel contentPanel;
    private boolean isUpdating;

    public PassengerBonusComponent(TempConfig tempConfig) {
        this.tempConfig = tempConfig;
        initListeners();
        initTable();
    }

    private void createUIComponents() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        model.addColumn("Nombre de passagers");
        model.addColumn("Malus");
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void initListeners() {
        removeRow.addActionListener(e -> {
            removeLastRow();
            if (table.getRowCount() == 0) {
                removeRow.setEnabled(false);
            }
        });
        addRow.addActionListener(e -> {
            addRow();
            removeRow.setEnabled(true);
        });
        table.getModel().addTableModelListener(this::tableChanged);
    }

    private void initTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < tempConfig.getPassengers().size(); i++) {
            model.addRow(new Object[]{i + 1, tempConfig.getPassengers().get(i)+""});
        }
        if (model.getRowCount() == 0) {
            removeRow.setEnabled(false);
        }
    }

    private void removeLastRow() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(model.getRowCount() - 1);
        tempConfig.getPassengers().remove(tempConfig.getPassengers().size() - 1);
    }

    private void addRow() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{model.getRowCount() + 1, 0f+""});
        tempConfig.getPassengers().add(0f);
    }

    private void setRow(int index, float value) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(value+"", index, 1);
        tempConfig.getPassengers().set(index, value);
    }

    public void tableChanged(TableModelEvent e) {
        if (e.getType() != TableModelEvent.UPDATE || isUpdating) {
            return;
        }
        isUpdating = true;
        int row = e.getFirstRow();

        if (row < 0 || row >= tempConfig.getPassengers().size()) {
            return;
        }

        try {
            float value = Float.parseFloat(table.getValueAt(row, 1).toString());
            setRow(row, value);
        } catch (NumberFormatException ignored) {
            setRow(row, tempConfig.getPassengers().get(row));
        }
        finally {
            isUpdating = false;
        }

    }

}
