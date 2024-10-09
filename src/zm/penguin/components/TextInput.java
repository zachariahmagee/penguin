package zm.penguin.components;

import zm.penguin.interactions.KeyListenable;
import zm.penguin.styles.Theme;

import static processing.core.PApplet.println;
import static processing.core.PConstants.*;
import static zm.penguin.styles.Theme.*;
import static zm.penguin.styles.Theme.button_text;

public class TextInput extends TextComponent implements KeyListenable {
    public boolean selected = false, display = false;
    public String input = "";
    protected int curr = 0;
    public int cursorOffset = 0, limit = -1;

    public TextInput(int x, int y, int w, int h, int limit) {
        setLocation(x,y,w,h);
        this.action = () -> { this.selected = true; this.display = false; this.input = ""; };
        this.alignX = LEFT; this.alignY = CENTER;
        this.textSize = 11;
        this.f = button1;
        this.textColor = button_text;
        this.s = outline;
        this.accentColor = Theme.accent;
        this.limit = limit;
        this.action = () -> { this.selected = true; };
        this.text = "";

        this.onMouseEnter = () -> { app.cursor(TEXT); };
        this.onMouseExit = () -> { app.cursor(ARROW); };
    }

    public TextInput(int w, int h, int limit) {
        this(0, 0, w, h, limit);
    }

    public TextInput(int x, int y, int w, int h) {
        this(x, y, w, h, -1);
    }

    public TextInput(int w, int h) {
        this(w, h, -1);
    }

    @Override
    public void draw() {
        try {
            app.textAlign(alignX, alignY);

            app.textFont(font);
            app.textSize(textSize);
            app.rectMode(CORNER);
            app.strokeWeight(this.selected?2:1);
            app.stroke(this.selected? accentColor :s);
            app.fill(f);
            app.rect(l, t, w, h);
            if (selected) {
                cursorOffset = 5;
                drawCursor();
            }
            app.fill(textColor);
            app.text(display?text:input, l + 5, t, w - 5, h);
        } catch (Exception e) {
            println(this, "(draw)", e);
        }
    }

    public void addText(char c) {
        input += c;
    }

    public void backspace() {
        try {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
            }
        } catch (Exception e) {
            if (debug) println(this, "(backspace)",input, e);
        }
    }

    public void select(boolean select) {
        this.selected = select;
    }

    public void deselect() {
        selected = false;
        if (input.isEmpty()) display = true;
    }

    public void reset() {
        input = "";
        selected = false;
    }

    public boolean limitless() {
        return limit <= 0;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void done() {
        try {
            if (input.isEmpty()) {
                display = true;
            }
            selected = false;
        } catch (Exception e) {
            println(this, "(done)",e);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (!mouseInside) deselect();
    }

    @Override
    public String toString() {
        return "TextInput:" + input;
    }

    public boolean isEmpty() {
        return input.isEmpty();
    }

    public boolean isBlank() {
        return input.isBlank();
    }

    @Override
    public void keyPressed(char c, int keycode, boolean shift) {
        if (!selected) {
            return;
        } else if (keycode == BACKSPACE) {
            backspace();
            return;
        } else if (keycode == RETURN || keycode == ENTER) {
            done();
            return;
        }

        // apply shift transformations
        if (shift) c = KeyListenable.shiftCharacter(c, keycode);
        else if (Character.isAlphabetic(c)) c = Character.toLowerCase(c);

        if (limitless() || input.length() < limit) {
            addText(c);
        }
    }

    protected void drawCursor() {
        if (curr == 0) curr = app.millis();
        if (app.millis() - curr < 400) {
            app.stroke(tab_text);
            app.strokeWeight(0.5f);
            float cursorPosition = l + cursorOffset + app.textWidth(input);
            if (cursorPosition > l + w - 2) cursorPosition = l + w - 2;
            app.line(cursorPosition, t + (float) h /2 - 5, cursorPosition, t + (float) h /2 + 7);
        }
        if (app.millis() - curr > 800) {
            curr = app.millis();
        }
    }

    @Override
    public void keyReleased(char c, int keycode) {

    }
}
