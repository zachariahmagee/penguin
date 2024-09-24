package zm.penguin.containers;

import zm.penguin.components.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Container<T extends Component> extends Component implements Iterable<T> {
    protected List<T> components;

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

    public void setBorder(int border) {
        this.border = border;
        components.forEach((c)->{ c.setContainerBorder(border); });
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
        components.forEach((c)->{ c.setContainerSpacing(spacing); });
    }

    public void changeSize(int newL, int newT, int newR, int newB) {
        this.l = newL;
        this.t = newT;
        this.r = newR;
        this.b = newB;
        this.w = r - l;
        this.h = b - t;
    }

    @Override
    public Iterator<T> iterator() {
        return components.iterator();
    }


    public int indexOf(T c) {
        return components.indexOf(c);
    }

    @Override
    public void mouseMoved(int x, int y) {
        for (T c : this) {
            if (c.mouseOver(x,y)) {
                if (c instanceof Container) {
                    ((Container<?>)c).mouseMoved(x,y);
                } else {
                    c.mouseMoved(x,y);
                }
            }
        }
        move.run();
    }

    @Override
    public void mouseDragged(int x, int y) {
        for (T c : this) {
            if (c.mouseOver(x,y)) {
                if (c instanceof Container) {
                    ((Container<?>)c).mouseDragged(x,y);
                } else {
                    c.mouseDragged(x,y);
                }
            }
        }
    }

    @Override
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
        action.run();
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (Component c : components) {
           c.mouseReleased(x,y);
        }
        release.run();
    }

    @Override
    public void setVisibility(boolean newState) {
        this.isVisible = newState;
        components.forEach((c)->{
            c.setVisibility(newState);
        });
    }

    public void keypressed(char c, int keycode) {

    }

    public void deselectComponent(int x, int y) {

    }
}
