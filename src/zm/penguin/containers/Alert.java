package zm.penguin.containers;

import zm.penguin.components.Button;
import zm.penguin.components.Component;
import zm.penguin.components.Spacer;
import zm.penguin.components.icons.CloseIcon;

import static processing.core.PApplet.*;
import static zm.penguin.styles.Style.base_font;
import static zm.penguin.styles.Theme.*;

public class Alert extends Container<Component> {
    public String header = "Information";
    public String[] text;

    public String[] HEADERS = {"Information", "Error"};

    CloseIcon close;
    boolean include_buttons = false;
    ButtonRow<Component> row;
    public Alert(int w, int h) {
        this.w = w; this.h = h;
        this.cornerRadius = 5;
        this.border = 10;
        this.action = () -> { setVisibility(false); };
        close = new CloseIcon(this);
        add(close);
    }

    public Alert() {
        this(300, 150);
    }

    public Alert(String text, Button b1, Button b2) {
        this.w = 300; this.h = 150;
        this.text = split(text, "\n");
        this.text = append(this.text, "");
        this.cornerRadius = 5;
        this.border = 10;
        close = new CloseIcon(this);
        row = new ButtonRow<Component>(l, b - 30, w, 25);
        b1.w = b2.w = 100;
        b1.h = b2.h = 25;
        row.add(b1, new Spacer(25, 80), b2);
        add(close, row);
    }





    @Override
    public void draw() {
        try {
            this.l = app.width/2 - w/2;
            this.t = app.height/2 - h/2;
            setLocation(l, t);
            close.setLocation(r,t);

            app.rectMode(CORNER);
            app.noStroke();
            app.textAlign(CENTER, CENTER);

            int textbox_w = w - border * 2;
            int[] text_h = new int[text.length];
            int total_h = 0;

            for (int i = 0; i < text.length; i++) {
                text_h[i] = ceil(app.textAscent() - app.textDescent()) + 6;
                String t = text[i];
                final int text_w = ceil(app.textWidth(t));
                if (text_w > textbox_w) {
                    int lines = ceil((float) text_w /textbox_w);
                    text_h[i] *= lines;
                    text_h[i] += (20 * lines);
                } else {
                    text_h[i] += 10;
                }
                total_h += text_h[i];
            }

            app.fill(background, 80);
            app.rect(0, 0, app.width, app.height);
            if (total_h > h) {
                h = total_h + 25;
                t = app.height / 2 - h / 2;
                b = t + h;
            }

            app.stroke(outline);
            app.strokeWeight(1);
            app.fill(fill);
            app.rect(l, t, w, h, cornerRadius);

            app.textFont(base_font);
            app.textSize(13);
            app.fill(heading);
            app.text(header, l, t, w, 20);

            app.fill(button_text);
            app.textLeading(20);
            int vertical_sum = t + 20;
            for (int i = 0; i < text.length; i++) {
                if (i == text.length - 1) app.fill(lightgrey);
                app.text(text[i],
                        l + border,
                        vertical_sum,
                        w - 2 * border,
                        text_h[i]
                        );
                vertical_sum += text_h[i];
            }

            if (include_buttons) {
                row.setLocation(l, b - 30);
                row.draw();
            }
        } catch (Exception e) {
           println(this, e);
        }
    }

    public void setText(String lines) {
       setText(lines, true);
    }

    public void setText(String lines, boolean addDismissMessage) {
        this.text = split(lines, "\n");
        this.text = append(text, "");
        if (addDismissMessage) this.text = append(text, "[ Click to Dismiss ]");
        setVisibility(true);
    }

    public void setText(String[] lines, boolean addDismissMessage) {
        this.text = lines;
        this.text = append(text, "");
        if (addDismissMessage) this.text = append(text, "[ Click to Dismiss ]");
        setVisibility(true);
    }

    public void setText(String[] lines) {
        setText(lines, true);
    }

    @Override
    public String toString() {
        return "Alert:";
    }
}
