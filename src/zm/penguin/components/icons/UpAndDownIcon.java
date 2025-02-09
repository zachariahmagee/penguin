package zm.penguin.components.icons;

import zm.penguin.interactions.BooleanCondition;

public class UpAndDownIcon extends IconToggle {

    public UpAndDownIcon(Runnable action, BooleanCondition condition) {
        super(action,condition);
    }

    public UpAndDownIcon(int l, int t, Runnable action, BooleanCondition condition) {
        super(l,t,action,condition);
    }

    public UpAndDownIcon(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l,t,w,h,action,condition);
    }

    @Override
    void drawTrue() {
        app.strokeWeight(0.5f);
        app.stroke(s);
        app.fill(s);
        app.line(l + (float)w / 5, t + (float)h / 5, l + (float)w / 2, b - (float)h / 5);
        app.line(l + (float)w / 2, b - (float)h / 5, r - (float)w / 5, t + (float)h / 5);
    }

    @Override
    void drawFalse() {
        app.strokeWeight(0.5f);
        app.stroke(s);
        app.fill(s);
        app.line(l + (float)w / 5, b - (float)h / 5, l + (float)w / 2, t + (float)h / 5);
        app.line(l + (float)w / 2, t + (float)h / 5, r - (float)w / 5, b - (float)h / 5);
    }
}
