package zm.penguin.components;

import zm.penguin.styles.Theme;

import static processing.core.PApplet.*;
import static processing.core.PConstants.CORNER;
import static zm.penguin.styles.Theme.*;

public class ColorGradientSquare extends Component {
    boolean current = false;
    int[] colors;

    ColorGradientSquare(int[] colors, int l, int t, int w, int h, boolean selected) {
        setLocation(l,t,w,h);

        this.current = selected;
        this.f = white;
        this.colors = colors;

        this.accentColor = Theme.selected;
        this.s = black;
    }

    ColorGradientSquare(int[] colors, int l, int t, int w, int h) {
        this(colors,l,t,w,h,false);
    }

    @Override
    public void draw() {
        app.rectMode(CORNER);
        if (current) {
            app.noStroke();
            app.fill(accentColor);
            app.rect(l - 3, t - 3, w + 6, h + 6);
        }
        app.stroke(1);
        app.stroke(s);
        app.fill(f);

        app.rect(l-1, t-1, w+2, h+2);

        for (int iy = l; iy <= r; iy++) {
            int c = lerpValue(iy);
            app.stroke(c);
            app.line(iy, t, iy, b);
        }
    }

    int lerpValue(float val) {
        int sections = colors.length - 1;
        float span = r - l;
        float step = span / sections;

        float w = abs(map(val, l, r, 0, sections));

        int i1 = constrain(abs(floor(w)), 0, sections); // Clamp i1 to the range [0, sections]
        int i2 = constrain(abs(ceil(w)), 0, sections); // Clamp i2 to the range [0, sections]

        return lerpColor(colors[i1], colors[i2], w - i1, RGB);

    }

    @Override
    public String toString() {
        return "";
    }
}
