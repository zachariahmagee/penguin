package zm.penguin.components.icons;

import zm.penguin.components.Icon;

import static processing.core.PApplet.println;
import static zm.penguin.styles.Style.icon_width;
import static zm.penguin.styles.Theme.button;
import static zm.penguin.styles.Theme.outline;

public class CameraIcon extends Icon {
    public CameraIcon(Runnable fn) {
        super(fn);
    }

    public CameraIcon(int x, int y, Runnable action) {
        super(x,y,action);
    }

    public CameraIcon(int x, int y, int w, int h, Runnable action) {
        super(x,y,w,h,action);
    }

    @Override
    public void draw() {
        try {
            if (drawBackdrop) drawBackdrop();
            app.strokeWeight(1);
            //top-left corner
            app.line(l+2, t+2, l+7, t+2);
            app.line(l+2, t+2, l+2, t+7);
            //top-right corner
            app.line(l+w-2, t+2, l+w-7, t+2);
            app.line(l+w-2, t+2, l+w-2, t+7);
            //bottom-left corner
            app.line(l+2, t+h-2, l+7, t+h-2);
            app.line(l+2, t+h-2, l+2, t+h-7);
            //bottom-right corner
            app.line(l+w-2, t+h-2, l+w-7, t+h-2);
            app.line(l+w-2, t+h-2, l+w-2, t+h-7);
            //camera body
            app.fill(s);
            app.noStroke();
            float oneFifthH = d(h, 5);
            float oneThirdH = d(h,3);
            float halfH = d(h, 2);
            float quarterW = d(w, 4);
            float halfW = d(w, 2);
            app.rect(l+4, t+oneThirdH-2, w-7, halfH+1);

            app.ellipse(l + halfW+1, t+oneThirdH-2, quarterW+2, oneFifthH);

            app.rect(l+quarterW, t+oneThirdH-2-2, 2, 2);
            app.fill(f);
            app.circle(l+halfW+1, t+halfH+1, 10);
            app.fill(s);
            app.circle(l+halfW+1, t+halfH+1, 6);
            app.noStroke();
        } catch (Exception e) {
           if (debug) println(this, "(draw)", e);
        }
    }

    @Override
    public String toString() {
        return "CameraIcon";
    }
}
