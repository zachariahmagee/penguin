package zm.penguin.interactions;

import processing.core.PApplet;
import zm.penguin.components.Component;
import static zm.penguin.styles.Theme.*;

import static processing.core.PApplet.*;

public class ScrollBar extends Component {
    //int l, w, t, h;
    int totalElements;
    int totalLength;
    int startPosition;
    int mouseOffset;
    float movementScaler;
    boolean orientation;
    boolean inverted;
    boolean active;
    public boolean locked = false;

    static public final boolean HORIZONTAL = true;
    static public final boolean VERTICAL = false;
    static public final boolean INVERT = true;
    static public final boolean NORMAL = false;

    public ScrollBar(boolean orientation, boolean inverted) {
        this.orientation = orientation;
        this.inverted = inverted;
        this.mouseOffset = 0;
        this.active = false;

        this.f = scrollbar_bg;
        this.s = scrollbar;
//        this.w = 15f/2;
    }

    @Override
    public void draw() {
        app.rectMode(CORNER);
        app.noStroke();
        PApplet.println("DRAWING", orientation);
        if (orientation == VERTICAL) {
            app.fill(blue);
            app.rect(l - w, t, w, totalLength);

            app.fill(blue);
            app.rect(l, t, w, h, 5, 5, 5, 5);
        } else {
            app.fill(scrollbar_bg);
            app.rect(l, t, totalLength,h);

            app.fill(scrollbar);
            app.rect(l, t + 1, w, h, 5,5,5,5);
        }
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean active() {
        return this.active;
    }

    public void active(boolean setActive) {
        this.active = setActive;
    }

    public void update(int totalElements, int totalLength, int newL, int newT, int newW, int newH) {
        this.totalElements = totalElements;
        this.totalLength = totalLength;
        if (orientation == VERTICAL) this.mouseOffset += (newT + newH) - (t + h);
        else this.mouseOffset += (newL + newW) - (l + w);
        this.l = newL;
        this.w = newW;
        this.t = newT;
        this.h = newH;
        this.movementScaler = (totalElements / (float) totalLength);
    }

    public boolean mouseOver(int x, int y) {
        if ((x >= l) && (x <= l + w) && (y >= t) && (y <= t + h)) {
            if (orientation == VERTICAL) {
                mouseOffset = t + h - y;
                startPosition = y;
            } else {
                mouseOffset = l + w - x;
                startPosition = x;
            }
            active = true;
            return true;
        }
        active = false;
        return false;
    }

    public int move(int x, int y, int currentScroll, int minScroll, int maxScroll) {
        int mainCoord = y;
        if (orientation == HORIZONTAL) mainCoord = x;

        int currentPosition = mainCoord + mouseOffset;
        int elementsMoved = (int) ((currentPosition - (startPosition + mouseOffset)) * movementScaler);

        if (abs(elementsMoved) >= 1) {
            if (inverted) elementsMoved = -elementsMoved;
            if ( ( (elementsMoved < 0) && (currentScroll == minScroll) )
                    || ((elementsMoved > 0) && (currentScroll == maxScroll)) ) {
                if (orientation == VERTICAL) {
                    mouseOffset = t + h - y;
                    startPosition = y;
                } else {
                    mouseOffset = l + w - x;
                    startPosition = x;
                }
            } else {
                currentScroll += elementsMoved;
                if (currentScroll < minScroll) currentScroll = minScroll;
                else if (currentScroll > maxScroll) currentScroll = maxScroll;
                startPosition = mainCoord;
            }
        }
        return currentScroll;
    }
}