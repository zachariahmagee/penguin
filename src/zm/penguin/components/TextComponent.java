package zm.penguin.components;

import processing.core.*;
import zm.penguin.styles.Theme;

import static zm.penguin.styles.Style.base_font;
import static zm.penguin.styles.Theme.*;

public abstract class TextComponent extends Component {
    public String text = "";

    public PFont font;

    public int textColor;

    public int textSize = 12;

    public int alignX = PConstants.CENTER, alignY = PConstants.CENTER;

    public TextComponent() {
        this.font = base_font;
        this.textColor = Theme.black;
    }

    public TextComponent setText(String text) { this.text = text; return this; }

    public String getText() { return this.text; }

    public TextComponent setTextColor(int type) { this.textColor = type; return this; }

    public TextComponent setFont(PFont font) { this.font = font; return this; }

    public TextComponent setFontSize(int size) { this.textSize = size; return this; }

    public String toString() { return this.getClass() + text; }
}
