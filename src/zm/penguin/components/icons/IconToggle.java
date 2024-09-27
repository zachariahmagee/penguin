package zm.penguin.components.icons;

import zm.penguin.components.Icon;
import zm.penguin.interactions.BooleanCondition;

import static processing.core.PConstants.CORNER;
import static zm.penguin.styles.Theme.*;

public abstract class IconToggle extends Icon {
    BooleanCondition condition;

    public IconToggle(Runnable action, BooleanCondition condition) {
        super(action);
        this.condition = condition;
    }

    public IconToggle(int l, int t, Runnable action, BooleanCondition condition) {
        super(l,t,action);
        this.condition = condition;
    }

    public IconToggle(int l, int t, int w, int h, Runnable action, BooleanCondition condition) {
        super(l,t,w,h);
        this.action = action;
        this.condition = condition;
    }

    abstract void drawTrue();
    abstract void drawFalse();

    @Override
    public void draw() {
        drawFirst();
        if (condition.evaluate()) {
            drawTrue();
        } else {
            drawFalse();
        }
    }

    public void setCondition(BooleanCondition condition) {
        this.condition = condition;
    }
}
