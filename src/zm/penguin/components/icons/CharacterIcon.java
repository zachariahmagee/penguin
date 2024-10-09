package zm.penguin.components.icons;

import zm.penguin.components.Icon;

public class CharacterIcon extends Icon {
    char c;

    public CharacterIcon(char c, Runnable action) {
        this.c = c;
        this.action = action;
    }

    @Override
    public void draw() {

    }
}
