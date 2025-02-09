package zm.penguin.interactions;

import zm.penguin.components.Component;

import static processing.core.PApplet.println;

public abstract class KeyListener extends Component implements KeyListenable {
    boolean selected;
    String input = "";

    KeyListener() {
        this.action = () -> { this.selected = true; };
    }

    public void addText(char c) {
        input += c;
    }

    void backspace() {
        try {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
            }
        } catch (Exception e) {
            if (debug) println(this, input, e);
        }
    }

    void select(boolean select) {
        this.selected = select;
    }

    void reset() {
        input = "";
        selected = false;
    }
}
