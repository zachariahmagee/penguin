package zm.penguin.containers;

import zm.penguin.components.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Container<T extends Component> extends Component implements Iterable<T> {
    List<T> components;

    public Container() {
        components = new ArrayList<T>();
    }

    public Container<T> add(T c) {
        this.components.add(c);
        return this;
    }

    public Container<T> add(T...args) {
        Collections.addAll(this.components, args);
        return this;
    }

    public T get(int i) {
        if (i >= 0 || i < size()) {
            return this.components.get(i);
        } else {
            return null;
        }
    }

    public T get() {
        return this.components.get(components.size() - 1);
    }

    public T remove(int i) {
        return this.components.remove(i);
    }

    public boolean remove(T obj) {
        return this.components.remove(obj);
    }

    public boolean contains(T obj) {
        return this.components.contains(obj);
    }

    public Stream<T> stream() {
        return this.components.stream();
    }

    public void clear() {
        this.components.clear();
    }

    public int size() {
        return this.components.size();
    }

    public void changeSize(int newL, int newT, int newR, int newB) {
        this.l = newL;

        this.t = newT;
        this.r = newR;
        this.b = newB;
    }

    @Override
    public Iterator<T> iterator() {
        return components.iterator();
    }


    public int indexOf(T c) {
        return components.indexOf(c);
    }
    //abstract void scrollWheel(float amount);

    //abstract void scrollbarUpdate(int xcoord, int ycoord);

    public void mouseMoved(int x, int y) {}

    public void mouseDragged(int x, int y) {}

    //abstract
    public void click(int x, int y) {
        for (T c : this) {
            if (c.mouseOver(x,y)) {
                if (c instanceof Container) {
                    ((Container<?>)c).click(x,y);
                } else {
                    c.action.run();
                }
            }
        }
    }

    //void onClick(Runnable fn) {}

    public void keypressed(char c, int keycode) {

    }

    public void deselectComponent(int x, int y) {

    }
}
