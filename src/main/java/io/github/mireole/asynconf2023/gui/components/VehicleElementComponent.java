package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.backend.data.VehicleTypeEntry;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.util.List;

public class VehicleElementComponent extends SelectedElementComponent<VehicleTypeEntry> {
    private final TempConfig config;

    private JPanel componentPane;
    private JTextField nameInput;
    private JSpinner ecoScoreSpinner;
    private JTextField averageWeight;

    public VehicleElementComponent(TempConfig config) {
        super();
        this.config = config;
        addListeners();
        updateSelected(-1);
    }

    @Override
    public void updateSelected(int index) {
        SwingUtilities.invokeLater(() -> {
            if (index == -1) {
                nameInput.setText("");
                nameInput.setEnabled(false);
                ecoScoreSpinner.setValue(0.0d);
                ecoScoreSpinner.setEnabled(false);
                averageWeight.setText("");
                averageWeight.setEnabled(false);
            } else {
                VehicleTypeEntry entry = getList().get(index);
                nameInput.setText(entry.name());
                nameInput.setEnabled(true);
                ecoScoreSpinner.setValue((double) entry.ecoScore());
                ecoScoreSpinner.setEnabled(true);
                averageWeight.setText(String.valueOf(entry.averageWeight()));
                averageWeight.setEnabled(true);
            }
        });
    }

    @Override
    public void newEntry() {
        VehicleTypeEntry entry = new VehicleTypeEntry("Nouveau véhicule", "800-1500kg", 5);
        getList().add(entry);
        add(entry);
    }

    @Override
    public void deleteEntry(int index) {
        getList().remove(index);
        remove(index);
    }

    private void createUIComponents() {
        ecoScoreSpinner = new JSpinner(new SpinnerNumberModel(5.0f, 0.0f, 10.0f, 0.5f));
    }

    private void addListeners() {
        DocumentListener listener = new RunnableDocumentListener(this::onEdit);
        nameInput.getDocument().addDocumentListener(listener);
        averageWeight.getDocument().addDocumentListener(listener);
        ecoScoreSpinner.addChangeListener(e -> onEdit());
    }

    private void onEdit() {
        SwingUtilities.invokeLater(() -> {
            int index = getIndex();
            if (index == -1) {
                return;
            }
            VehicleTypeEntry entry = new VehicleTypeEntry(nameInput.getText(), averageWeight.getText(), ((Double) ecoScoreSpinner.getValue()).floatValue());
            getList().set(index, entry);
            set(index, entry);
        });
    }

    @Override
    public List<VehicleTypeEntry> getList() {
        return config.getVehicleTypes();
    }
}
