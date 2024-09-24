package zm.penguin.components;

import static zm.penguin.styles.Style.*;
import static zm.penguin.styles.Theme.*;

import static processing.core.PConstants.*;

/**
 * TODO: This needs to be entirely rethought. Incorporated in a Tabs container or something
 * to bring some actual structure to it. get rid of the plain values for rounding corners
 * and just fix the sloppy taped together solution.
 * **/
public class TabTop extends TextComponent {
    boolean current = false;
    public int tr, tl, br, bl;
    public TabTop(String text, int x, int y, int w, int h, Runnable action) {
        this.text = text;
        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;
        this.r = l + w;
        this.b = t + h;
        this.action = action;
    }

    public TabTop setColors(int fill, int stroke, int textColor) {
        this.f = fill;
        this.s = stroke;
        this.textColor = textColor;
        return this;
    }

    public TabTop setFontSize(int fontSize) {
        this.textSize = fontSize;
        return this;
    }

    @Override
    public void draw() {
        app.fill(s);
        app.rectMode(CORNER);
        app.noStroke();
        app.rect(l,t,w,h, 5, 5, 0, 0);
        app.fill(f);
        app.rect(l+1,t+1,w-2,current?h:h-2, 5, 5, 0, 0);
        app.fill(current?heading:textColor);
        app.textSize(textSize);
        app.text(text, l + 5, t + 5, w - 10, h - 10);
    }
}
