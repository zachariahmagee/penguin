package zm.penguin.components.icons;

import zm.penguin.components.Icon;

import static processing.core.PConstants.CORNER;

public class AddIcon extends Icon {
    public AddIcon(int x, int y, Runnable action) {
        super(x, y, action);
        cornerRadius = 0;
    }

    public AddIcon(int x, int y, int w, int h, Runnable action) {
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

        app.rect(l + half - 0.5f, t + quarter, line, half + line_border, cornerRadius);
        app.rect(l + quarter, t + half - 0.5f, half + line_border, line, cornerRadius);
    }
}
