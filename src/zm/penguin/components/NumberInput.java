package zm.penguin.components;

import zm.penguin.interactions.KeyListenable;

import static processing.core.PApplet.parseFloat;
import static processing.core.PApplet.println;
import static processing.core.PConstants.BACKSPACE;
import static processing.core.PConstants.*;

public class NumberInput extends LabeledTextInput {
    public boolean auto = true, decimal = false, scientific = false, positiveInteger = false;
    public float value;

    public NumberInput(String label, int x, int y, int w, int h, boolean pi, int limit) {
        super(label, x, y, w, h, limit);
        this.positiveInteger = pi;
    }

    public NumberInput(String label, int w, int h, boolean pi, int limit) {
        this(label, 0, 0, w, h, pi, limit);
    }

    public NumberInput(String label, int x, int y, int w, int h, boolean pi) {
        this(label, x, y, w, h, pi, -1);
    }

    public NumberInput(String label, int w, int h, boolean pi) {
        this(label, 0,0, w, h, pi);
    }

    public NumberInput(String label, int x, int y, int w, int h) {
        this(label, x, y, w, h, false);
    }

    public NumberInput(String label, int w, int h) {
        this(label, w, h, false);
    }

    @Override
    public void reset() {
        input = "";
        selected = false;
        decimal = false;
    }

    @Override
    public void done() {
        try {
            value = parseFloat(input.isEmpty()?text:input);
        } catch (Exception e) {
           println(this, e);
        }
    }

    @Override
    public void backspace() {
        try {
            if (!input.isEmpty()) {
                char c = input.charAt(input.length() - 1);
                input = input.substring(0, input.length() - 1);
                if (c == '.') decimal = false;
                else if (c == 'e' || c == 'E') scientific = false;
            }
        } catch (Exception e) {
            if (debug) println(this, input, e);
        }
    }

    @Override
    public void keyPressed(char c, int keycode, boolean shift) {
        if (keycode == BACKSPACE) {
            backspace();
            return;
        } else if (keycode == RETURN || keycode == ENTER) {
            done();
            return;
        }

        // apply shift transformations
        if (shift) c = KeyListenable.shiftCharacter(c, keycode);
        else if (Character.isAlphabetic(c)) c = Character.toLowerCase(c);

        boolean add = false;

        if (Character.isDigit(c)) {
            add = true;
        } else if (c == '.') {
            decimal = add = true;
        } else if (c == 'e' || c == 'E') {
            scientific = add = true;
        }

        if (add && (limitless() || input.length() < limit)) {
                addText(c);
        }
    }

    @Override
    public String toString() {
        return "LabeledNumberInput: " + label + " = " + (input.isEmpty() ? text : input);
    }
}
