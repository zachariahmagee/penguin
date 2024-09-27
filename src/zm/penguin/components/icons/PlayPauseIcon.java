package zm.penguin.components.icons;

import zm.penguin.interactions.BooleanCondition;

import static zm.penguin.styles.Theme.*;

public class PlayPauseIcon extends IconToggle {
    public PlayPauseIcon(Runnable action, BooleanCondition condition) {
        super(action, condition);
        this.f = button;
    }

    public PlayPauseIcon(int l, int t, Runnable action, BooleanCondition condition) {
        super(l, t, action, condition);
        this.f = button;
    }

    public PlayPauseIcon(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l, t, w, h, action, condition);
        this.f = button;
    }

    @Override
    void drawTrue() {
        float q = (float)w/4;
        float one6th = (float)h/6;
        float two3rds = ((float)h/3) * 2;
        app.noStroke();
        app.fill(outline);
        app.rect(l + one6th, t + one6th, q, two3rds);
        app.rect(r - one6th - q, t + one6th, q, two3rds);
    }

    @Override
    void drawFalse() {
        float one6th = (float)h/6;
        float onehalf = (float)w/2;
        app.stroke(outline);
        app.fill(mouseInside?green:button);
        app.triangle(l + one6th, t + one6th, l + one6th, b - one6th, r - one6th, t + onehalf);
    }

    @Override
    public String toString() {
        return "PlayPauseIcon";
    }
}
