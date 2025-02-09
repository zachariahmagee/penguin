package zm.penguin.components.icons;

import zm.penguin.components.Icon;

public class RightArrowIcon extends Icon {

    public RightArrowIcon(int x, int y, int w, int h, Runnable action) {
        super(x,y,w,h,action);
    }

    @Override
    public void draw() {
       if (drawBackdrop) drawBackdrop();
       app.stroke(s);
       app.strokeWeight(strokeWeight);
       app.line(l + d(w, 5), t + d(h, 2), r - d(w, 5), t + d(h,  2));
       app.line(r - d(w, 5), t + d(h, 2), r - d(w, 3), t + d(h, 4));
       app.line(r - d(w, 5), t + d(h, 2), r - d(w, 3), b - d(h, 4));
    }
}
