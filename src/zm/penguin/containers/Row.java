package zm.penguin.containers;

import zm.penguin.Penguin;
import zm.penguin.components.Component;
import zm.penguin.interactions.ScrollBar;
//import zm.penguin.interactions.Scrollable;
import zm.penguin.utils.Layout;

import static processing.core.PApplet.*;

public class Row<T extends Component> extends Scrollable<T> {
    public int intitialWidth = 0;

    public Row(int l, int t, int r, int b) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        this.w = r - l;
        this.h = b - t;

        this.layout = Layout.HORIZONTAL;
        this.scroll = new ScrollBar(true, false);

        this.border = 5;
        this.spacing = 5;
    }


    @Override
    public void draw() {

        app.rectMode(CORNER);
        app.noStroke();
        app.textAlign(CENTER, CENTER);

        h = b - t;
        //w = app.width - (int)border - l;
        int sT = t;
        int sL = l;
        int sR = r;

        if (getContentWidth() > w) {
            if (scrollAmount == -1) scrollAmount = 0;
            else if (scrollAmount > contentWidth - w) scrollAmount = contentWidth - w;

            app.fill(scrollbar_bg);
            app.rect(l, b - 15f/2, w, 15f/2);

            int scrollbarSize = w - round(w * (contentWidth - (float)w / contentWidth));
            if (scrollbarSize < getAverageComponentWidth()) scrollbarSize = getAverageComponentWidth();
            int scrollbarOffset = round((w - scrollbarSize) * (scrollAmount / (float)(contentWidth - w)));

            app.noStroke();
            app.fill(scrollbar_f);
            app.rect(sL + scrollbarOffset, b - 15f/2 + 1, (15F/2)-2, 5,5,5,5,5);

            scroll.update(contentWidth,
                    w,
                    l + scrollbarOffset,
                    b - 15/2,
                    scrollbarSize,
                    15/2);

            sL -= scrollAmount;
        }

        app.fill(f);
        app.noFill();
        app.noStroke();

        try {
            int prevWidth = intitialWidth;
            // TODO: add the following part to "Labels" class
//            if (size() > 0) {
//                if (get(0) instanceof TextComponent) {
//                    TextComponent tc = (TextComponent) get(0);
//                    tc.setLocation(sL + prevWidth, t);
//                    if (tc.getText().equals("")) {
//                        prevWidth += 40;
//                    } else {
//                        prevWidth += 10;
//                    }
//                }
//            }
            for (int i = 0; i < size(); i++) {
                Component c = get(i);
                if (i != 0) {
                    prevWidth += get(i - 1).getWidth() + spacing;
                }
                c.setLocation(sL + prevWidth, t);
                c.draw();
            }
        } catch (Exception e) {
            if (debug) {
                println(this, e);
            }
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
