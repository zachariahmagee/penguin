package zm.penguin.containers;

import zm.penguin.components.Component;

/**
 * Considering a rename. ButtonPanel centers a single component, button or otherwise.
 * **/
public class ButtonPanel<T extends Component> extends Container<T> {

    public ButtonPanel(int l, int t, int w, int h) {
        this.l = l;
        this.t = t;
        this.w = w;
        this.h = h;
        this.r = this.l + this.w;
        this.b = this.t + this.h;
    }

    @Override
    public void draw() {
        for (Component c : this) c.draw();
    }

    @Override
    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = this.l + this.w;
        this.b = this.t + this.h;

        for (Component c : components) {
            c.setLocation(l + w/2 - c.w/2, t + h/2 - c.h / 2);
        }
    }

    @Override
    public String toString() {
        return getClass().toString();
    }
}
