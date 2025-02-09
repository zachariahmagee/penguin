package zm.penguin.components.icons;

import zm.penguin.components.Icon;

import static processing.core.PConstants.CORNER;

public class SubtractIcon extends Icon {
    public SubtractIcon(int x, int y, Runnable action) {
        super(x, y, action);
        cornerRadius = 0;
    }

    public SubtractIcon(int x, int y, int w, int h, Runnable action) {
        super(x,y,w,h,action);
        cornerRadius = 0;
    }
    @Override
    public void draw() {
       if (drawBackdrop) drawBackdrop();
       float quarter = d(w,4);
       float half = d(w,2);
       float line = d(w, 10);
       float line_border = d(line, 2);

        app.rectMode(CORNER);
        app.noStroke();
        app.fill(s);

        app.rect(l + quarter, t + line_border, half + border, line, cornerRadius);
    }
}
