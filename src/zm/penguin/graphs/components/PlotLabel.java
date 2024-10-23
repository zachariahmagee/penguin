package zm.penguin.graphs.components;

import zm.penguin.components.TextComponent;
import zm.penguin.components.TextInput;
import zm.penguin.interactions.KeyListenable;
import zm.penguin.styles.Theme;

import static processing.core.PApplet.*;
import static processing.core.PConstants.*;
import static zm.penguin.styles.Style.*;
import static zm.penguin.styles.Theme.*;

public class PlotLabel extends TextInput {

    public PlotLabel(String text, int l, int t) {
        super(l, t, 40, 25, -1);
    }

    @Override
    public void draw() {
        app.textFont(italic_font);
        app.textSize(11);
        this.h = (int)(app.textAscent() + app.textDescent() + 5);
        this.w = text.isEmpty() ? 40 : floor(app.textWidth(text)) + 40;
        setLocation(l, t, w, h);
        try {

            if (text != null && !text.isEmpty()) {
                app.noStroke();
                app.textAlign(LEFT, CENTER);
                app.textFont(italic_font);
                app.textSize(13);
                app.fill(textColor);
                app.text(text, l + 7, t + 3, w, 24);
            } else {
                app.fill(lightgrey, 50);
                app.rect(l + 3, t+8, 1, 13);
            }
            if (selected) {
                app.strokeWeight(0.5f);
                app.stroke(lightgrey);
                app.noFill();
                app.rect(l + 3, t + 5 + 3, w, 22 - 6, 5);
                app.text(text, l + 7, t + 3, w, 24);
                drawCursor();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            println("PlotLabel Error: ", text);

        }
    }

    void setLabel(String text, int l, int t) {
        this.l = l;
        this.t = t;
        this.text = text;
//        this.w = text.isEmpty() ? 40 : floor(app.textWidth(text)) + 40;
//        this.h = (int)(app.textAscent() + app.textDescent() + 5);
//        this.r = l + w;
//        this.b = t + h;
    }

    public void setLabel(String text) {
        this.text = text;
        //this.w = text.isEmpty() ? 40 : floor(app.textWidth(text)) + 40;
        //this.h = int(graphics.textAscent() + graphics.textDescent() + 5);
    }
}
