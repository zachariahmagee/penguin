package zm.penguin.graphs.components;

import zm.penguin.components.Component;
import zm.penguin.components.TextComponent;
import zm.penguin.containers.Row;
import zm.penguin.interactions.Resizable;

import static processing.core.PApplet.println;
import static processing.core.PApplet.round;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class Labels extends Row<TextComponent> { //implements Resizable {
    public Labels(int l, int t, int r, int b) {
        super(l, t, r, b);
        this.border = 5;
    }

    @Override
    public void draw() {
        app.rectMode(CORNER);
        app.noStroke();
        app.textAlign(CENTER, CENTER);

        h = b - t;
        w = app.width - (int)border - l;
        int sT = t;
        int sL = l;
        int sR = r;

        if (getContentWidth() > w) {
            if (scrollAmount == -1) scrollAmount = 0;
            else if (scrollAmount > contentWidth - w) scrollAmount = contentWidth - w;
            int scrollbarSize = w - round(w * (contentWidth - (float)w / contentWidth));
            if (scrollbarSize < getAverageComponentWidth()) scrollbarSize = getAverageComponentWidth();
            int scrollbarOffset = round((w - scrollbarSize) * (scrollAmount / (float)(contentWidth - w)));
            scroll.update(contentWidth,
                    w,
                    l + scrollbarOffset,
                    b - 15/2,
                    scrollbarSize,
                    15/2);

            sL -= scrollAmount;
            scroll.setVisibility(true);
        } else {
            scroll.setVisibility(false);
        }

        app.fill(f);
        app.noFill();
        app.noStroke();

        try {
            int prevWidth = intitialWidth;
            if (size() > 0) {
                if (get(0) != null) {
                    TextComponent tc = (TextComponent) get(0);
                    tc.setLocation(sL + prevWidth, t);
                    if (tc.getText().isEmpty()) {
                        prevWidth += 40;
                    } else {
                        prevWidth += 10;
                    }
                }
            }
            for (int i = 0; i < size(); i++) {
                Component c = get(i);
                if (i != 0) {
                    prevWidth += get(i - 1).getWidth() + spacing;
                }
                c.setLocation(sL + prevWidth, t);
                c.draw();
            }
            if (scroll.isVisible()) scroll.draw();
        } catch (Exception e) {
            if (debug) {
                println(this, e);
            }
        }
    }

    int containsID(int id) {
        for (TextComponent c : this) {
            if (c instanceof TraceLabel && ((TraceLabel)c).trace == id) return indexOf(c);
        }
        return -1;
    }

    public void setTextColor(int c) {
        for (TextComponent t : this) {
            if (t instanceof TraceLabel) {
                t.setTextColor(c);
            } else if (t instanceof PlotLabel) {
                t.setTextColor(c);
            } else if (t != null) {
                t.setTextColor(c);
            }
        }
    }

//    @Override
//    public void changeWindowSize(int newW, int newH) {
//
//    }
}
