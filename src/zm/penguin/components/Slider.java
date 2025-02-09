package zm.penguin.components;

import processing.core.PConstants;
import processing.core.PFont;
import zm.penguin.containers.Container;
import zm.penguin.interactions.Slidable;
import zm.penguin.utils.DisplayValues;
import zm.penguin.utils.Map;
import zm.penguin.utils.SliderValues;

import java.util.function.Consumer;
import java.util.function.Function;

import static processing.core.PApplet.map;
import static processing.core.PConstants.CORNER;
import static zm.penguin.styles.Style.base_font;
import static zm.penguin.styles.Theme.*;

public class Slider extends Slidable<Float> {
    public SliderValues vals;
    public Function<Float, String> conversion; Boolean convert;

    public int MAPPING = 0;
    final public int regular_mapping = 0, logarithmic = 1, exponential = 2, sigmoid = 3;

    int pad = 10, line_h;
    float current_h;
    boolean up, down, initial_click;

    public PFont font;

    public int textColor = tab_text;

    public int textSize = 9;

    public int alignX = PConstants.LEFT, alignY = PConstants.TOP;

    Slider(int w, int space, SliderValues vals) {
        this.w = w;
        this.space = space;
        this.h = space * 6;
        this.pad = space * 5;
        this.line_h = space * 4;
        this.vals = vals;
        this.current_h = space * 1.5f;

        this.font = base_font;

    }

    Slider(int w, int space, SliderValues vals, Function<Float, String> c) {
        this(w,space,vals);
        this.conversion = c; this.convert = true;
    }

    Slider(Component container, int space, SliderValues vals) {
        this(container.w - space * 2, space, vals);
        this.container = container;
    }

    Slider(int l, int t, int w, int h, int space, SliderValues vals) {
        //this(w, space, vals);
        setLocation(l, t, w, h);
        this.space = space;
        this.pad = space * 5;
        this.vals = vals;
        this.line_h = space * 4;
        this.current_h = space * 1.5f;

        this.font = base_font;
    }

    @Override
    public void draw() {
        app.textFont(font);
        app.textSize(textSize);
        app.fill(textColor);
        app.textAlign(alignX, alignY);

        String min, max, current;
        if (convert && conversion != null) {
            min = conversion.apply(vals.display.min);
            max = conversion.apply(vals.display.max);
            current = conversion.apply(vals.current);
        } else {
            min = DisplayValues.getDisplayText(vals.display.min, vals.display.f);
            max = DisplayValues.getDisplayText(vals.display.max, vals.display.f);
            current = DisplayValues.getDisplayText(map(vals.current, vals.min, vals.max, vals.display.min, vals.display.max), vals.display.fc);
        }

        app.text(min, l + space, t + line_h - 2);
        app.rectMode(CORNER);
        app.fill(selected);
        app.stroke(idle_text);
        app.strokeWeight(0.5f);
        app.rect(l + pad, t + line_h - 1.5f, w - pad * 2 - 10, 3, 5);

        app.fill(tab_text);
        app.text(max, r - pad + 4, t + line_h - 2);

        cx = map_to_slider(vals.current, vals.min, vals.max, slider_left, slider_right);

        app.text(current, cx - space * 2, t + current_h);

        cy = t + line_h;
        app.strokeWeight(1f);

        app.stroke(idle_text);
        app.fill(locked?selected:alt_button);
        app.circle(cx, cy, space * 2);
    }

    void addAction(Consumer<Float> action) {
        this.consumer = action;
    }

    void addConversion(Function<Float, String> c) {
        this.conversion = c;
        convert = true;
    }

    void setMappingFunction(int mapType) {
        this.MAPPING = mapType;
    }

//    float map_from_slider(float value, float min, float max, float newMin, float newMax) {
//        return switch (MAPPING) {
//            case logarithmic -> Map.fromLog(value, min, max, newMin, newMax);
//            case exponential -> Map.fromExp(value, min, max, newMin, newMax);
//            case sigmoid -> Map.fromSigmoid(value, min, max, newMin, newMax);
//            default -> map(value, min, max, newMin, newMax);
//        };
//    }

    float map_from_slider(float value, float min, float max, float newMin, float newMax) {
        switch (MAPPING) {
            case logarithmic:
                return Map.fromLog(value, min, max, newMin, newMax);
            case exponential:
                return Map.fromExp(value, min, max, newMin, newMax);
            case sigmoid:
                return Map.fromSigmoid(value, min, max, newMin, newMax);
            default:
                return map(value, min, max, newMin, newMax);
        }
    }

//    float map_to_slider(float value, float min, float max, float newMin, float newMax) {
//        return switch (MAPPING) {
//            case logarithmic -> Map.toLog(value, min, max, newMin, newMax);
//            case exponential -> Map.toExp(value, min, max, newMin, newMax);
//            case sigmoid -> Map.toSigmoid(value, min, max, newMin, newMax);
//            default -> map(value, min, max, newMin, newMax);
//        };
//    }

    float map_to_slider(float value, float min, float max, float newMin, float newMax) {
        switch (MAPPING) {
            case logarithmic:
                return Map.toLog(value, min, max, newMin, newMax);
            case exponential:
                return Map.toExp(value, min, max, newMin, newMax);
            case sigmoid:
                return Map.toSigmoid(value, min, max, newMin, newMax);
            default:
                return map(value, min, max, newMin, newMax);
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = l + w - 10;
        this.b = t + h;

        this.slider_left = l + pad + space;
        this.slider_right = r - pad - space;
    }

    @Override
    public void click(int x, int y) {

        if (mouseOverHandle(x,y)) locked = true;

        else if (mouseOverLine(x,y) || mouseOverLeftLabel(x,y) || mouseOverRightLabel(x,y)) {
            stepAbout(x,y);
        }
    }

    public void stepAbout(int x, int y) {
        locked = false;
        if (x > cx) {
            if (vals.current + vals.display.step <= vals.max) vals.current += vals.display.step;
            else if (vals.max < vals.min && vals.current - vals.display.step >= vals.max) vals.current -= vals.display.step;
        } else {
            if (vals.current - vals.display.step >= vals.min) vals.current -= vals.display.step;
            else if (vals.min > vals.max && vals.current + vals.display.step <= vals.min) vals.current += vals.display.step;
        }
        if (consumer != null) {
            consumer.accept(vals.current);
        }
    }


    @Override
    public void mouseDragged(int x, int y) {
        if (x < slider_left) x = slider_left;
        else if (x > slider_right) x = slider_right;

        cx = x;
        float value = map_from_slider(cx, slider_left, slider_right, vals.min, vals.max);
        vals.current = value;
        if (consumer != null) {
            consumer.accept(value);
        }
    }

    @Override
    public boolean mouseOver(int x, int y) {
        return ((x >= l &&
                x <= r &&
                y >= t &&
                y <= b));

        //return ((x >= (cx - space)) &&
        //        (x <= (cx + space)) &&
        //        (y >= (cy - space)) &&
        //        (y <= (cy + space)));
    }

    public boolean mouseOverHandle(int x, int y) {
        return ((x >= (cx - space)) &&
                (x <= (cx + space)) &&
                (y >= (cy - space)) &&
                (y <= (cy + space)));
    }

    public boolean mouseOverLine(int x, int y) {
        return ((x >= slider_left  &&
                x <= slider_right &&
                y >= t + line_h - 2.5 &&
                y <= t + line_h + 2.5));
    }

    public boolean mouseOverLeftLabel(int x, int y) {
        return ((x >= l + space  &&
                x <= slider_left &&
                y >= t + line_h - space &&
                y <= t + line_h + space));
    }

    public boolean mouseOverRightLabel(int x, int y) {
        return ((x >= slider_right  &&
                x <= r - space &&
                y >= t + line_h - space &&
                y <= t + line_h + space));
    }

    public  boolean mouseOverSlider_NotHandle(int x, int y) {
        return !mouseOverHandle(x,y) && mouseOverLine(x,y) && mouseOverLeftLabel(x,y) && mouseOverRightLabel(x,y);
    }

    @Override
    public String toString() {
        return "";
    }
}
