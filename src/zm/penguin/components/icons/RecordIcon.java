package zm.penguin.components.icons;

import zm.penguin.interactions.BooleanCondition;

import static zm.penguin.styles.Theme.*;

public class RecordIcon extends IconToggle {
    float r = 15;
    boolean animate = false;
    public RecordIcon(Runnable action, BooleanCondition condition) {
        super(action, condition);
//        this.f = lightgrey;
        this.accentColor = pink;
    }

    public RecordIcon(int l, int t, Runnable action, BooleanCondition condition) {
        super(l,t,action,condition);
//        this.f = lightgrey;
        this.accentColor = pink;
    }

    public RecordIcon(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l,t,w,h, action, condition);
//        this.f = lightgrey;
        this.accentColor = pink;
    }

    @Override
    void drawTrue() {
        float cw = d(w,2);
        float ch = d(h, 2);
        if (!animate) r = cw;
        app.strokeWeight(strokeWeight);
        app.stroke(s);
        app.fill(accentColor);
        app.circle(l + cw, t + ch, r);

    }

    @Override
    void drawFalse() {
        float cw = d(w,2);
        float ch = d(h, 2);
        if (!animate) r = cw;
        app.strokeWeight(strokeWeight);
        app.stroke(s);
        app.fill(lightgrey);
        app.circle(l + cw, t + ch, r);
    }


}
