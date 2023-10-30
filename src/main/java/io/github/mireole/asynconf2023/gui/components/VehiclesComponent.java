package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.backend.data.VehicleTypeEntry;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VehiclesComponent {
    private final TempConfig tempConfig;
    private DefaultListModel<VehicleTypeEntry> model;
    private JPanel contentPane;
    private JList list;
    private JButton newButton;
    private JButton deleteButton;
    private JTextField nameInput;
    private JTextField averageWeightInput;
    private JSpinner ecoScoreSpinner;

    public VehiclesComponent(TempConfig tempConfig) {
        this.tempConfig = tempConfig;
        addListeners();
    }

    private void createUIComponents() {
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> updateSelected());

        // float
        ecoScoreSpinner = new JSpinner(new SpinnerNumberModel(5.0f, 0.0f, 10.0f, 0.5f));
    }

    private void addListeners() {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onEdit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onEdit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onEdit();
            }
        };

        nameInput.getDocument().addDocumentListener(listener);
        averageWeightInput.getDocument().addDocumentListener(listener);
        ecoScoreSpinner.addChangeListener(e -> onEdit());

        newButton.addActionListener(e -> newEntry());
        deleteButton.addActionListener(e -> deleteEntry());

        setupList();
        updateSelected();
    }

    private void setupList() {
        model.clear();
        for (VehicleTypeEntry entry : tempConfig.getVehicleTypes()) {
            model.addElement(entry);
        }
    }

    private void updateSelected() {
        SwingUtilities.invokeLater(() -> {
            if (list.getSelectedIndex() != -1) {
                VehicleTypeEntry entry = (VehicleTypeEntry) list.getSelectedValue();
                nameInput.setText(entry.getName());
                nameInput.setEnabled(true);
                averageWeightInput.setText(entry.getAverageWeight());
                averageWeightInput.setEnabled(true);
                ecoScoreSpinner.setValue((double) entry.getEcoScore());
                ecoScoreSpinner.setEnabled(true);
                deleteButton.setEnabled(true);
            }
            else {
                // Disable everything
                nameInput.setText("");
                nameInput.setEnabled(false);
                averageWeightInput.setText("");
                averageWeightInput.setEnabled(false);
                ecoScoreSpinner.setValue(0d);
                ecoScoreSpinner.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });
    }

    private void onEdit() {
        if (list.getSelectedIndex() != -1) {
            // We have to create a new entry because otherwise we'd end up changing the one in the permanent config
            // I blame pointers.
            VehicleTypeEntry entry = new VehicleTypeEntry(nameInput.getText(), averageWeightInput.getText(), ((Double) ecoScoreSpinner.getValue()).floatValue());
            tempConfig.getVehicleTypes().set(list.getSelectedIndex(), entry);
            model.set(list.getSelectedIndex(), entry);
            tempConfig.setChanged(true);
        }
    }

    private void newEntry() {
        VehicleTypeEntry entry = new VehicleTypeEntry("Nouveau type", "1000-1500kg", 5);
        tempConfig.getVehicleTypes().add(entry);
        model.addElement(entry);
        list.setSelectedIndex(tempConfig.getVehicleTypes().size() - 1);
        tempConfig.setChanged(true);
    }

    private void deleteEntry() {
        if (list.getSelectedIndex() != -1) {
            tempConfig.getVehicleTypes().remove(list.getSelectedIndex());
            model.remove(list.getSelectedIndex());
            tempConfig.setChanged(true);
        }
    }

}
