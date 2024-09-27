package zm.penguin.components;

import static processing.core.PConstants.CORNER;
import static zm.penguin.styles.Style.icon_width;
import static zm.penguin.styles.Theme.*;

public abstract class Icon extends Component {

    public Icon() {
        this.w = icon_width;
        this.h = icon_width;
        this.f = button;
        this.s = divider;
    }
    public Icon(Runnable fn) {
        this();
        this.action = fn;
    }

    public Icon(int x, int y, Runnable action) {
        this(x,y);
        this.action = action;
    }

    public Icon(int x, int y) {
        this(x,y,icon_width,icon_width);
    }

    public Icon(int x, int y, int w, int h) {
        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;
        this.r = this.l + this.w;
        this.b = this.t + this.h;
        this.f = button;
        this.s = divider;
    }

    public void drawFirst() {
        app.rectMode(CORNER);
        app.strokeWeight(1);
        app.stroke(outline);
        app.fill(button);
        app.rect(l,t,w,h);
    }
}
