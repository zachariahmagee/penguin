package zm.penguin.components.icons;

import zm.penguin.components.Icon;
import zm.penguin.interactions.Lockable;

import static zm.penguin.styles.Theme.alt_button;
import static zm.penguin.styles.Theme.outline;

public class DragIcon extends Icon implements Lockable {
    boolean locked = false,
            vertical = false;
    DragIcon(int x, int y, int w, int h) {
        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;
        this.r = this.l + this.w;
        this.b = this.t + this.h;

        this.f = alt_button;
        this.s = outline;
        this.cornerRadius = 2;
    }
    DragIcon(int x, int y, int w, int h, Runnable action) {
        this(x,y,w,h);
        this.action = action;
    }

    @Override
    public void draw() {
        if (drawBackdrop) drawBackdrop();
        app.strokeWeight(strokeWeight);
        app.stroke(s);
        if (vertical) {
            float sw = d(w,6);
            float sh = d(h, 6);

            app.line(l + sw, t + sh, l + sw, b - sh);
            app.line( l + sw * 2.5f, t + sh, l + sw * 2.5f, b - sh);
            app.line(l + sw * 4, t + sh, l + sw * 4, b - sh);
        } else {

            float sh = d(h, 4);
            float sw = d(w, 6);

            app.line(l + sw, t + sh, r - sw, t + sh);
            app.line(l + sw, t + sh * 2, r - sw, t + sh * 2);
            app.line(l + sw, t + sh * 3, r - sw, t + sh * 3);
        }
        app.noStroke();
    }

    @Override
    public boolean locked() {
        return locked;
    }
}
