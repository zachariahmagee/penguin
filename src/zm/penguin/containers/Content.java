package zm.penguin.containers;

import processing.core.PApplet;
import processing.core.PGraphics;
import zm.penguin.Context;
import zm.penguin.components.Component;

public abstract class Content<T extends Component> extends Container<T> {
    PGraphics graphics;

    public Content() {
        this.graphics = app.g;
    }

    public void setGraphicsCanvas(PGraphics newGraphics) {
        this.graphics = newGraphics;
    }
}
