package zm.penguin.components;

import zm.penguin.containers.Container;
import zm.penguin.styles.Theme;

public class ButtonToggle extends Container<Button> {
    Button b1, b2;
    int w1, w2;

    public ButtonToggle(String label1, String label2, int l, int t, int w, int h, Runnable action1, Runnable action2) {
        super();
        this.l = l;
        this.t = t;
        this.w = w;
        this.h = h;
        this.r = this.l + this.w;
        this.b = this.t + this.h;

        this.w1 = this.w2 = w/2;

        b1 = new Button(label1, l, t + 5, w1, h - 10, action1);
        b2 = new Button(label2, l, t + 5, w2, h - 10, action2);

        b1.textSize = b2.textSize = 9;


        b1.specialty_corners = true;

        b1.cr1 = b1.cr4 = b2.cr2 = b2.cr3 = 2;
        b1.cr2 = b1.cr3 = b2.cr1 = b2.cr4 = 0;


        b2.specialty_corners = true;

        add(b1, b2);
    }

    public  ButtonToggle(String label1, String label2, int l, int t, int w1, int w2, int h, Runnable action1, Runnable action2) {
        this(label1, label2, l, t, w1 + w2, h, action1, action2);
        b1.w = w1;
        b2.w = w2;
    }

    ButtonToggle(Button b1, Button b2) {

    }

    public void draw() {
        for (Button b : this) b.draw();
    }

    public void toggle(boolean toggle) {
        if (toggle) {
            b1.f = Theme.selected;
            b2.f = Theme.button;
        } else {
            b2.f = Theme.selected;
            b1.f = Theme.button;
        }
    }

    @Override
    public void click(int x, int y) {
        if (b1.mouseOver(x,y)) {
            b1.action.run();
            b1.f = Theme.selected;
            b2.f = Theme.button;
        } else if (b2.mouseOver(x,y)){
            b2.action.run();
            b2.f = Theme.selected;
            b1.f = Theme.button;
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = this.l + this.w;
        this.b = this.t + this.h;

        b1.setLocation(x, y + 5);
        b2.setLocation(b1.r, y + 5);
    }

    @Override
    public void signalThemeChange(int theme) {
        b1.f = b2.f = Theme.button;
        b1.s = b2.s = Theme.outline;
        b1.textColor = b2.textColor = Theme.button_text;
    }

    @Override
    public String toString() {
        return "";
    }
}
