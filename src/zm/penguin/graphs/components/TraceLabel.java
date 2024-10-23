package zm.penguin.graphs.components;

import zm.penguin.components.TextComponent;
import zm.penguin.interactions.BooleanCondition;

import static processing.core.PApplet.*;
import static zm.penguin.styles.Style.*;
import static zm.penguin.styles.Theme.*;

public class TraceLabel extends TextComponent {
    public int offset = 0, trace;
    int textWidth, textHeight;
    BooleanCondition condition;

    public TraceLabel(int id, String label, int traceColor, Runnable action, BooleanCondition condition) {
        this.trace = id;
        this.f = traceColor;
        this.h = 30;
        this.text = label;
        this.textColor = darkgrey;
        this.action = action;
        this.condition = condition;
    }

    @Override
    public void draw() {
        app.textSize(11);
        app.textFont(small_font);

        this.textWidth = floor(app.textWidth(text)) + 10;
        this.w = 16 + textWidth + 10;
        this.h = (int)(app.textAscent() + app.textDescent() + 5);

        try {
            app.rectMode(CORNER);
            app.noStroke();
            app.fill(background);

            app.fill(f);
            app.stroke(black);
            app.strokeWeight(1);

            app.rect(l + offset , t + 8, 12, 12);
            if (condition.evaluate()) {
                app.stroke(black);
                app.fill(background);
                app.rect(l + offset + 2, t + 8 + 2, 8, 8);
            }

            if (text != null && !text.isEmpty()) {
                app.textAlign(LEFT, TOP);
                app.textMode(MODEL);
                app.fill(textColor);
                app.textFont(small_font);
                app.text(text, l + 16 + offset, t + 7, textWidth, h);
            }
        } catch (Exception e) {
            println(this, e);
        }
    }

    public void setOffset(int offset) { this.offset = offset; }
    public void setLabel(String label, int traceColor) {
        this.f = traceColor;
        this.text = label;
    }

    @Override
    public boolean mouseOver(int x, int y) {
        return ((y >= t + 8)
                && (y <= t + 8 + 12)
                && (x >= l)
                && (x <= l + 12)
        );
    }

}
