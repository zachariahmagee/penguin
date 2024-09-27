package zm.penguin.containers;

import zm.penguin.components.Component;
import zm.penguin.components.Heading;
import zm.penguin.components.icons.CloseIcon;
import zm.penguin.interactions.Resizable;
import zm.penguin.interactions.Scrollable;

import static processing.core.PConstants.CORNER;
import static zm.penguin.styles.Style.*;
import static zm.penguin.styles.Theme.*;

public class SidePanel extends Container<Component> implements Scrollable, Resizable {
    Column<Component> display;
    public Heading h1;
    public CloseIcon close;
    public int current = 0;

    public SidePanel(int l, int t, int w, int h, String h1) {
        setLocation(l,t,w,h);

        display = new Column<>(l, t + button_h, w, h - button_h);

        this.h1 = new Heading(h1, l + 15, t, base_font, tab_text);
        close = new CloseIcon(r, t, this);

        this.f = fill;
        this.s = divider;
    }

    @Override
    public void draw() {
        app.noStroke();
        app.rectMode(CORNER);
        app.fill(s);
        app.rect(l,t,w,1);
        app.fill(f);
        app.rect(l,t+1,w,h-1);

        app.fill(panel);
        app.rect(l, t, w, h, 5); //bg

        app.fill(button);
        app.rect(l, t, w, tab_h); // behind heading;

        app.fill(divider);
        app.rect(l-1, t, 1, h); // left side
        app.rect(r, t, 1, h); // right side
        app.rect(l, b - bottombar_h, w, 1); // bottom
        app.rect(l, t + tab_h - 1, w, 1); // under header

        display.draw();
        for (Component c : this) c.draw();
    }

    @Override
    public void setLocation(int l, int t, int w, int h) {
        super.setLocation(l,t,w,h);
//        assert display != null;
        if (display != null) display.setLocation(l, t + button_h, w, h - button_h);
//        assert close != null;
        if (close != null) close.setLocation(r, t);
//        assert h1 != null;
        if (h1 != null) h1.setLocation(l + 15, t);
    }

    @Override
    public void signalThemeChange(int theme) {

    }

    @Override
    public String toString() {
        return "SidePanel: " + h1.text;
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
