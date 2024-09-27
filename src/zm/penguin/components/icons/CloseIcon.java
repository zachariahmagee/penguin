package zm.penguin.components.icons;

import zm.penguin.components.Component;
import zm.penguin.components.Icon;

import static processing.core.PApplet.println;
import static zm.penguin.styles.Theme.*;

public class CloseIcon extends Icon {

    public CloseIcon(int x, int y, Runnable action) {
        super(x,y,action);
        this.w = 20;
        this.h = 20;
    }

    public CloseIcon(int x, int y, Component container) {
        super(x,y);
        this.container = container;
        this.action = () -> {
            this.container.setVisibility(false);
        };
        this.w = 20;
        this.h = 20;
    }

    @Override
    public void draw() {
        try {
            app.stroke(outline);
            app.strokeWeight(1.5f);
            app.line(l - (float)w/2, t + (float)h/2, l - w, t + h);
            app.line(l - (float)w/2, t + h, l - w, t + (float)w/2);

        } catch (Exception e) {
            println(this, e);
        }
    }

    public boolean mouseOver(int x, int y) {
        return ((y >= t + 5)
                && (y <= t + 5 + h)
                && (x >= l - 25)
                && (x <= l - 25 + w)
        );
    }

    @Override
    public String toString() {
        String c = container != null ? container.toString() : "";
        return "Close: " + c;
    }
}
