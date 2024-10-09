package zm.penguin.components;

import zm.penguin.interactions.Slidable;

import java.util.function.Consumer;

import static processing.core.PApplet.*;
import static processing.core.PApplet.map;
import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.PROJECT;
import static zm.penguin.styles.Theme.*;

public class ColorSliderSelector extends Slidable<Integer> {
    public int[] colors;
    public int currentColor;

    public ColorSliderSelector(int l, int t, int w, int h, int[] colors, Consumer<Integer> consumer) {
        setLocation(l, t, w, h);

        slider_left = l + space;
        slider_right = r - space;

        space = h / 2;
        cx = l + space * 2;
        cy = t + space;

        this.colors = colors;

        this.consumer = consumer;
    }

    public ColorSliderSelector(int l, int t, int w, int h, int color1, int color2, Consumer<Integer> consumer) {
        this(l,t,w,h,new int[]{color1,color2},consumer);
    }

    public ColorSliderSelector(int l, int t, int w, int h, boolean horiz, int[] colors, Consumer<Integer> consumer) {
        this(l,t,w,h,colors,consumer);
        this.horizontal = horiz;

        if (!horizontal) {
            space = w / 2;
            cx = l + space;
        }
    }

    @Override
    public void draw() {
        app.rectMode(CORNER);
        app.strokeWeight(1);
        app.stroke(idle_text);
        app.fill(black);
        app.rect(l - 1, t - 1, w + 2, h + 2);

        app.noStroke();
        app.fill(colors[0]);
        app.rect(l,t,space,h+1);
        app.fill(colors[colors.length-1]);
        app.rect(r-space,t,space + 1, h + 1);

        app.strokeCap(PROJECT);
        for (int i = slider_left; i <= slider_right; i++) {
            int c = lerpValue(i);
            app.stroke(c);
            app.line(i, t, i, b);
        }

        int str = white;
        if (app.brightness(currentColor) > 175) str = black;
        app.stroke(str);
        app.fill(currentColor);
        app.circle(cx, cy, space * 2 + 1);
        app.strokeWeight(1);
        app.circle(cx, cy, space * 2);

    }

    int lerpValue(float val) {
        int sections = colors.length - 1;
        float span = slider_right - slider_left;

        float w = abs(map(val, slider_left, slider_right, 0, sections));

        int i1 = constrain(abs(floor(w)), 0, sections); // Clamp i1 to the range [0, sections]
        int i2 = constrain(abs(ceil(w)), 0, sections); // Clamp i2 to the range [0, sections]

        return lerpColor(colors[i1], colors[i2], w - i1, RGB);//color(c1, 255);

    }

    void setCurrentColor(int c) {
        currentColor = c;
        cx = (int)map(app.brightness(currentColor), 0, 255, slider_left, slider_right);
    }

    int findXfromColor(int c) {
        return (int)map((int)app.brightness(currentColor), 0, 255, slider_left, slider_right);
    }

    @Override
    public void click(int x, int y) {
        this.locked = true;
        super.click(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (horizontal) {
            if (x < l) x = l + 5;
            else if (x > r) x = r - 5;
        } else {
            if (y < t) y = t;
            else if (y > t) y = t;
        }
        cx = x;
        currentColor = lerpValue(cx);
        if (consumer != null) {
            consumer.accept(currentColor);
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = l + w;
        this.b = t + h;

        slider_left = l + space;
        slider_right = r - space;

        setCurrentColor(currentColor);
        if (horizontal) {
            cy = t + space;
        } else {
            cx = l + space;
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
