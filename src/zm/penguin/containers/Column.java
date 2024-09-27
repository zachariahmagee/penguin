package zm.penguin.containers;

import zm.penguin.components.Component;
import zm.penguin.components.Heading;
import zm.penguin.interactions.ScrollBar;

import static zm.penguin.styles.Theme.*;
import static zm.penguin.styles.Style.*;

import zm.penguin.interactions.ScrollableContainer;
import zm.penguin.utils.Layout;

import static processing.core.PApplet.*;

public class Column<T extends Component> extends ScrollableContainer<T> {
    public int initialHeight = 0;

    public Column(int l, int t, int w, int h) {
        setLocation(l,t,w,h);

        this.layout = Layout.VERTICAL;
        this.scroll = new ScrollBar(layout, false, this);

        this.border = 15;
        this.spacing = 5;

        this.f = sidepanel;
        this.s = divider;
    }


    @Override
    public void draw() {
        try {
            app.rectMode(CORNER);
            app.noStroke();
            app.textAlign(CENTER, CENTER);

            app.fill(f);
            app.rect(l, t, w, h);

            app.fill(s);
            app.rect(r, t, 1, h);

            h = b - bottombar_h - t;
            int sT = t;
            int sL = l;

            if (contentHeight > h) {
                if (scrollAmount == -1) scrollAmount = 0;
                else if (scrollAmount > contentHeight - h) scrollAmount = contentHeight - h;

//                app.noStroke();
//                app.fill(scrollbar_bg);
//                app.rect(r - 15f / 2, t, 15f / 2, h);

                int scrollbarSize = h - round(h * (float) (contentHeight - h / contentHeight));
                if (scrollbarSize < getAverageComponentHeight()) scrollbarSize = getAverageComponentHeight();
                int scrollbarOffset = round((h - scrollbarSize) * (scrollAmount / (float) (contentHeight - h)));

//                app.fill(scrollbar);
//                app.rect(r - 15f / 2, sT + scrollbarOffset, 15f / 2, scrollbarSize, 5, 5, 5, 5);

                scroll.update(contentHeight,
                        h,
                        r - 15 / 2,
                        t + scrollbarOffset,
                        15 / 2,
                        scrollbarSize);

                sT -= scrollAmount;
                scroll.setVisibility(true);
            } else {
                scroll.setVisibility(false);
            }

            int prevHeight = initialHeight;
            // TODO: this is currently wrong - need to figure out (i == 0) / LOCATION IS SET
            for (int i = 0; i < size(); i++) {
                Component c = get(i);
                if (i == 0) {
                    prevHeight += spacing;
                } else {
                    if (c instanceof Heading) {
                        prevHeight += get(i - 1).getHeight();
                    } else {
                        prevHeight += get(i - 1).getHeight() + spacing;
                    }
                }
                c.setLocation(sL + border, sT + prevHeight);
                c.draw();

            }

            app.noStroke();
            app.fill(f);
            app.rect(l, b - bottombar_h, w, bottombar_h);
            app.fill(s);
            app.rect(l, b - bottombar_h, w, 1);
            app.rect(r, b - bottombar_h, 1, b);

            if (scroll.isVisible()) scroll.draw();
        } catch (Exception e) {
            println(e);
        }

    }

    @Override
    public String toString() {
        return "";

    }
}