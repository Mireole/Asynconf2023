package io.github.mireole.asynconf2023.gui.components;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class SelectedElementComponent<E> {
    private Consumer<E> add;
    private BiConsumer<Integer, E> set;
    private Consumer<Integer> remove;
    private Supplier<Integer> getIndex;

    /**
     * Called when the user selects an item in the list.
     */
    public abstract void updateSelected(int index);

    /**
     * Called when a new item is added to the list.
     * don't forget to call add() in this method
     */
    public abstract void newEntry();

    /**
     * Called when an item is removed from the list.
     * don't forget to call remove() in this method
     */
    public abstract void deleteEntry(int index);

    public void setCallbacks(Consumer<E> add, BiConsumer<Integer, E> set, Consumer<Integer> remove, Supplier<Integer> getIndex) {
        this.add = add;
        this.set = set;
        this.remove = remove;
        this.getIndex = getIndex;
    }

    protected void add(E item) {
        add.accept(item);
    }

    protected void set(int index, E item) {
        set.accept(index, item);
    }

    protected void remove(int index) {
        remove.accept(index);
    }

    protected int getIndex() {
        return getIndex.get();
    }

    public abstract List<E> getList();

}
