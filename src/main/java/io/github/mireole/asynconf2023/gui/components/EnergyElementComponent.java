package io.github.mireole.asynconf2023.gui.components;

import io.github.mireole.asynconf2023.backend.TempConfig;
import io.github.mireole.asynconf2023.backend.data.FuelTypeEntry;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.util.List;

public class EnergyElementComponent extends SelectedElementComponent<FuelTypeEntry> {
    private final TempConfig tempConfig;
    private JPanel contentPane;
    private JTextField nameInput;
    private JSpinner ecoScoreSpinner;

    public EnergyElementComponent(TempConfig tempConfig) {
        super();
        this.tempConfig = tempConfig;
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
            } else {
                FuelTypeEntry entry = tempConfig.getFuelTypes().get(index);
                nameInput.setText(entry.getVisualName());
                nameInput.setEnabled(true);
                ecoScoreSpinner.setValue((double) entry.getEcoScore());
                ecoScoreSpinner.setEnabled(true);
            }
        });
    }

    @Override
    public void newEntry() {
        FuelTypeEntry entry = new FuelTypeEntry("Nouvelle énergie", 5);
        tempConfig.getFuelTypes().add(entry);
        add(entry);
    }

    @Override
    public void deleteEntry(int index) {
        tempConfig.getFuelTypes().remove(index);
        remove(index);
    }

    private void createUIComponents() {
        ecoScoreSpinner = new JSpinner(new SpinnerNumberModel(5.0f, 0.0f, 10.0f, 0.5f));
    }

    @Override
    protected void addListeners() {
        DocumentListener listener = new RunnableDocumentListener(this::onEdit);

        nameInput.getDocument().addDocumentListener(listener);
        ecoScoreSpinner.addChangeListener(e -> onEdit());
    }

    private void onEdit() {
        SwingUtilities.invokeLater(() -> {
            int index = getIndex();
            if (index == -1) return;
            FuelTypeEntry entry = new FuelTypeEntry(nameInput.getText(), ((Double) ecoScoreSpinner.getValue()).floatValue());
            tempConfig.getFuelTypes().set(index, entry);
            set(index, entry);
        });
    }

    @Override
    public List<FuelTypeEntry> getList() {
        return tempConfig.getFuelTypes();
    }
}
