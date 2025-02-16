package zm.penguin.containers;

import zm.penguin.components.Component;
import zm.penguin.components.Icon;
import zm.penguin.interactions.Resizable;
import zm.penguin.interactions.Scrollable;

import static processing.core.PConstants.*;
import static zm.penguin.styles.Style.bottombar_h;
import static zm.penguin.styles.Style.button_h;
import static zm.penguin.styles.Theme.*;

public class Sidebar extends Container<Component> implements Scrollable, Resizable {
    public Column<Icon> display;
    Icon first;

    public Sidebar(int l, int t, int w, int h, Icon first) {

        setLocation(l,t,w,h);
        this.first = first;

        display = new Column<>(l,t + first.getHeight(),w,h-first.getHeight());
        display.spacing = display.border = border = spacing = 5;
        this.f = display.f = sidepanel;
        this.s = display.s = divider;
        add(this.first, display);
    }

    public Sidebar(int l, int t, int w, int h, int border, Icon first) {
        this(l,t,w,h,first);
        this.border = border;
        display.border = border;
    }

    public Sidebar(int l, int t, int w, int h, int border, int spacing, Icon first) {
        this(l,t,w,h,border,first);
        this.spacing = spacing;
        display.spacing = spacing;
    }

    @Override
    public void draw() {
        app.rectMode(CORNER);
        app.fill(f);
        app.rect(l, t, w, h, 5); //bg

        display.draw();


        app.noStroke();
        app.fill(f);
        app.rect(l, b - bottombar_h, w, bottombar_h); // bottom
        app.fill(s);
        app.rect(l, b - bottombar_h, w, 1); // bottom left
        app.rect(r, b - bottombar_h, 1, b); // bottom right



        app.fill(divider);
        app.rect(l-1, t, 1, h); // left side
        app.rect(r, t, 1, h); // right side

        first.draw();

        app.noStroke();
        app.fill(divider);
        app.rect(r, t, 1, first.h);
    }

    @Override
    public void setLocation(int l, int t, int w, int h) {
        super.setLocation(l,t,w,h);
        if (display != null) display.setLocation(l, t + first.getHeight(), w, h - first.getHeight());
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean locked() { return display.locked(); }

    @Override
    public void scrollWheel(float amount) {
        display.scrollWheel(amount);
    }

    @Override
    public void scrollbarUpdate(int x, int y) {
        display.scrollbarUpdate(x,y);
    }

    @Override
    public void changeWindowSize(int newW, int newH) {
        setLocation(l, t, w, newH - t);
    }
}
