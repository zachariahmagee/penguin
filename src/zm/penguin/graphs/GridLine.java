package zm.penguin.graphs;

import processing.core.PFont;
import zm.penguin.utils.Orientation;

import static processing.core.PApplet.println;
import static processing.core.PConstants.*;
import static processing.core.PConstants.PROJECT;

/**
 * I want to modify this class so that it always contains the VALUE of the grid line
 * regardless of if it contains (or shows) a label.
 * */
public class GridLine extends GridComponent {
    Orientation orientation = Orientation.HORIZONTAL;
    boolean horizontal = true;

    public GridLine(String value, int l, int t, int r, int b, boolean isMajor) {
        setLocation(l, t, r - l, b - t);
        this.isMajor = isMajor;
        this.text = value;
        if (t != b) {
            this.horizontal = false;
            orientation = Orientation.VERTICAL;
            alignX = CENTER;
            alignY = TOP;
        } else {
            alignX = RIGHT;
            alignY = CENTER;
        }
        if (text.isBlank()) this.labeled = false;
    }

    public GridLine(int l, int t, int r, int b, boolean isMajor) {
        this("",l,t,r,b,isMajor);
    }

    public GridLine(int l, int t, int r, int b, int c) {
        this("",l,t,r,b,false);
    }

    public GridLine(String value, int l, int t, int r, int b) {
        this(value,l,t,r,b,false);
    }

    public GridLine(String value, int l, int t, int r, int b, PFont font) {
        this(value,l,t,r,b,false);
        this.font = font;
        if (text.isEmpty()) this.notch = true;
    }

    public GridLine(int l, int t, int r, int b) {
        this("",l,t,r,b,false);
    }

    @Override
    public void draw() {
        try {
            app.strokeCap(PROJECT);
            app.strokeWeight(strokeWeight);
            app.stroke(isMajor?f:s);
            if (horizontal) {
                app.line(l + 1, t, r, b);
            } else {
                app.line(l, t, r, b - 1);
            }
            app.fill(f);
            app.stroke(f);
            app.textFont(font);
            if (text != null && labeled) {
                if (horizontal) {
                    app.line(l - graphMark, t, l, t);
                    app.fill(textColor);
                    app.textAlign(alignX, alignY);
                    app.text(text, l - graphMark - padding, t - 1);
                } else {
                    app.line(l, b + graphMark, l, b);
                    app.fill(textColor);
                    app.textAlign(alignX, alignY);
                    app.text(text, l + 1, b + graphMark + padding);
                }

                if (notch) {
                    if (horizontal) app.line(l - (float)graphMark / 2, t, l, t);
                    else app.line(l, b + (float)graphMark / 2, l, b);
                }
            }
        } catch (Exception e) {
           println(this, e);
        }
    }

    @Override
    public String toString() {
        return "GridLine: " + (labeled ? text : value) + " - ";
    }
}
