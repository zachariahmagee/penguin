package zm.penguin.components;

import zm.penguin.styles.Theme;

import static zm.penguin.styles.Style.*;
import static zm.penguin.styles.Theme.*;
import static processing.core.PConstants.*;

public class RadioButton extends Component {
    boolean radio = false;
    int cx, cy;

    public RadioButton(int x, int y, Runnable action) {
        this.cx = x;
        this.cy = y;
        this.w = radio_width;
        this.h = radio_width;
        this.l = cx - w/2;
        this.t = cy - h/2;
        this.r = l + w;
        this.b = t + h;

        this.action = action;

        this.f = Theme.button;
        this.s = Theme.outline;
        this.accentColor = Theme.selected;
    }

    public RadioButton(int x, int y, int w, Runnable action) {
        this(x,y,action);
        this.w = w;
        this.h = w;
        this.l = cx - w/2;
        this.t = cy - h/2;
        this.r = l + w;
        this.b = t + h;
    }

    @Override
    public void draw() {
        app.stroke(idle_text);
        app.fill(radio? accentColor :f);
        app.ellipseMode(CENTER);
        app.circle(cx,cy,w);
    }

    @Override
    public void setLocation(int x, int y) {
        this.cx = x;
        this.cy = y;
        this.l = cx - w/2;
        this.t = cy - h/2;
        this.r = l + w;
        this.b = t + h;
    }

    @Override
    public String toString() {
        return "";
    }
}
