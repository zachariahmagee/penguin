package zm.penguin.components;

import static processing.core.PApplet.*;

public class LabeledTextInput extends TextInput {
    String label;
    int labelWidth;

    public LabeledTextInput(String label, int x, int y, int w, int h, int limit) {
        super(x,y,w,h,limit);
        this.label = label;
        this.action = () -> { this.selected = true; this.display = false; this.input = ""; };
    }

    public LabeledTextInput(String label, int w, int h, int limit) {
        this(label, 0, 0, w, h, limit);
    }

    public LabeledTextInput(String label, int x, int y, int w, int h) {
        this(label, x, y, w, h, -1);
    }

    public LabeledTextInput(String label, int w, int h) {
        this(label, w, h, -1);
    }

    @Override
    public void draw() {
        try {
            app.textAlign(alignX, alignY);

            app.textFont(font);
            app.textSize(textSize);
            labelWidth = ceil(app.textWidth(label)) + 5;
            app.fill(textColor);
            app.text(label, l + 5, t, labelWidth, h);
            app.rectMode(CORNER);
            app.strokeWeight(selected?2:1);
            app.stroke(selected? accentColor :s);
            app.fill(f);

            app.rect(l + labelWidth + 5, t, w - labelWidth - 5, h);
            if (selected) {
                cursorOffset = labelWidth + 11;
                drawCursor();
            }
            app.fill(textColor);
            app.text(display?text:input, l + labelWidth + 10, t, w - labelWidth - 10, h);
        } catch (Exception e) {
    //            throw new RuntimeException(e);
            println(this, "(draw)", e);
        }
    }

    public void done() {
        try {
            if (input.isEmpty()) {
                display = true;
            }
            selected = false;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            println(this, e);
        }
    }

    @Override
    public String toString() {
        return "LabeledTextInput: " + label + " = " + (input.isEmpty() ? text : input);
    }


}
