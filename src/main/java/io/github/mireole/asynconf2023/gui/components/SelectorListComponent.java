package io.github.mireole.asynconf2023.gui.components;


import javax.swing.*;
import java.util.List;

/**
 * A component that displays a list of items and allows the user to add and remove items.
 * It calls the SelectedElementComponent it is given when the user selects, adds or removes an item.
 */
@SuppressWarnings("rawtypes")
public class SelectorListComponent<E> {
    private final SelectedElementComponent<E> component;
    private DefaultListModel<E> model;
    private JList list;
    private JButton newButton;
    private JButton deleteButton;
    private JPanel contentPane;

    public SelectorListComponent(SelectedElementComponent<E> component) {
        this.component = component;
        component.setCallbacks(this::add, this::setItem, this::remove, () -> list.getSelectedIndex());
        addListeners();
        initList();
        deleteButton.setEnabled(false);
    }

    private void createUIComponents() {
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addListeners() {
        list.addListSelectionListener(e -> {
            deleteButton.setEnabled(list.getSelectedIndex() != -1);
            component.updateSelected(this.list.getSelectedIndex());
        });
        newButton.addActionListener(e -> {
            component.newEntry();
            list.setSelectedIndex(model.size() - 1); // Even if model is empty, this is safe
        });
        deleteButton.addActionListener(e -> {
            component.deleteEntry(list.getSelectedIndex());
            list.setSelectedIndex(-1);
        });
    }

    private void initList() {
        List<E> list = component.getList();
        for (E item : list) {
            add(item);
        }
        // Select first item if there is one
        if (!list.isEmpty()) {
            this.list.setSelectedIndex(0);
        }
    }

    private void add(E item) {
        model.addElement(item);
    }

    private void setItem(int index, E item) {
        model.set(index, item);
    }

    private void remove(int index) {
        model.remove(index);
    }

}
