package zm.penguin.containers;

import zm.penguin.components.Component;

public class ContentPanel extends Container<Component> {

    public ContentPanel(int l, int t, int w, int h) {
        setLocation(l,t,w,h);

    }
    public ContentPanel(int l, int t, int w, int h, int border) {
        setLocation(l,t,w,h);
        this.border = border;
    }
    public ContentPanel(int l, int t, int w, int h, int border, int spacing) {
        setLocation(l,t,w,h);
        this.border = border;
        this.spacing = spacing;
    }

    @Override
    public void draw() {
        int prevHeight = 0;
        for (int i = 0; i < size(); i++) {
            Component c = get(i);
            if (i != 0) {
                prevHeight += get(i-1).getHeight() + spacing;
            }
            c.setLocation(l + border + c.offsetX, t + prevHeight + c.offsetY);
            c.draw();
        }
    }

    @Override
    public Container<Component> add(Component...args) {
        for (Component c : args) {
            if (c == null) continue;
            c.container = this;
            this.components.add(c);
            this.h += c.h + spacing;
            this.b = t + h;
//            if (layout.isVertical()) {
//                this.contentHeight += c.getHeight() + spacing;
//            } else {
//                this.contentWidth += c.getWidth() + spacing;
//            }
        }
        return this;
    }

    @Override
    public Container<Component> add(Component c) {
        this.components.add(c);
        c.container = this;
        this.h += c.h + spacing;
        this.b = t + h;
//        if (layout.isVertical()) {
//            this.contentHeight += c.getHeight() + spacing;
//        } else {
//            this.contentWidth += c.getWidth() + spacing;
//        }
        return this;
    }





    @Override
    public String toString() {
        return "";
    }
}
