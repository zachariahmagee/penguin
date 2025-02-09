package zm.penguin.components.icons;

import zm.penguin.interactions.BooleanCondition;

public class ThemeIcon extends IconToggle {
    public ThemeIcon(Runnable action, BooleanCondition condition) {
        super(action, condition);
    }

    public ThemeIcon(int l, int t, Runnable action, BooleanCondition condition) {
        super(l,t,action,condition);
    }

    public ThemeIcon(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l,t,w,h, action, condition);
    }

    @Override
    void drawTrue() {

    }

    @Override
    void drawFalse() {

    }
}
