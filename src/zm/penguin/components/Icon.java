package zm.penguin.components;

import static zm.penguin.styles.Style.icon_width;
import static zm.penguin.styles.Theme.*;

public abstract class Icon extends Component {

    public Icon(Runnable fn) {
        this.action = fn;
        this.f = button;
        this.s = divider;
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
}
