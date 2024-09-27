package zm.penguin.components;

import zm.penguin.interactions.KeyListenable;
import zm.penguin.interactions.KeyListener;

import static processing.core.PApplet.println;
import static processing.core.PConstants.BACKSPACE;

public abstract class TextInput extends Component implements KeyListenable {
    boolean selected;
    String input = "";

    public TextInput() {
        this.action = () -> { this.selected = true; };
    }

    @Override
    public void draw() {

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
            if (debug) println(this, input, e);
        }
    }

    public void select(boolean select) {
        this.selected = select;
    }

    public void reset() {
        input = "";
        selected = false;
    }

    abstract void done();

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void keyPressed(char c, int keycode, boolean shift) {
        if (keycode == BACKSPACE) {
            backspace();
            return;
        }
        else if (shift) c = KeyListenable.shiftCharacter(c, keycode);
        else if (Character.isAlphabetic(c)) c = Character.toLowerCase(c);

        addText(c);

    }

    @Override
    public void keyReleased(char c, int keycode) {

    }
}
