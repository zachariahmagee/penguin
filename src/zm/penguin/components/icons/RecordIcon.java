package zm.penguin.components.icons;

import zm.penguin.interactions.BooleanCondition;

import static zm.penguin.styles.Theme.alt_button;
import static zm.penguin.styles.Theme.pink;

public class RecordIcon extends IconToggle {
    float r;
    boolean animate = false;
    public RecordIcon(Runnable action, BooleanCondition condition) {
        super(action, condition);
    }

    public RecordIcon(int l, int t, Runnable action, BooleanCondition condition) {
        super(l,t,action,condition);
    }

    public RecordIcon(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l,t,w,h, action, condition);
    }

    @Override
    void drawTrue() {
        app.fill(pink);
        float cw = d(w,2);
        float ch = d(h, 2);
        if (!animate) r = cw;
        app.circle(cw, ch, r);

    }

    @Override
    void drawFalse() {
        app.fill(pink);
        float cw = d(w,2);
        float ch = d(h, 2);
        if (!animate) r = cw;
        app.circle(cw, ch, r);
    }


}
