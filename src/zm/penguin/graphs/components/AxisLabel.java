package zm.penguin.graphs.components;

import static processing.core.PApplet.println;
import static processing.core.PConstants.*;

import zm.penguin.components.TextComponent;
import zm.penguin.utils.Orientation;
import static zm.penguin.styles.Style.small_font;
import static zm.penguin.styles.Theme.darkgrey;

public class AxisLabel extends TextComponent {
    Orientation orientation;

    public AxisLabel(Orientation orientation) {
        this.orientation = orientation;
        this.text = "";
        this.textColor = darkgrey;
        isVisible = false;
    }

    AxisLabel(String label, int x, int y, Orientation orientation) {
        this(orientation);
        this.l = x;
        this.t = y;
        this.text = label;

    }

    @Override
    public void draw() {
        try {
            app.fill(textColor);
            app.textFont(small_font);
            if (text != null && !text.isEmpty()) {
                if (orientation.isVertical()) {
                    app.textAlign(CENTER, CENTER);
                    app.pushMatrix();
                    app.translate(l, t);
                    app.rotate(-HALF_PI);
                    app.text(text,0,35);
                    app.popMatrix();
                } else {
                    app.textAlign(CENTER, TOP);
                    app.text(text, l, t);
                }

            }
        } catch (Exception e) {
            println(this, e);
        }
    }

    void setLabel(String label) { this.text = label; }

    void setLabel(String label, int x, int y) {
        this.l = x;
        this.t = y;
        this.text = label;
    }

    boolean axisLabeled() { return !text.isEmpty(); }
}
