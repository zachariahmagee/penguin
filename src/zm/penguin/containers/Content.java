package zm.penguin.containers;

import processing.core.PApplet;
import processing.core.PGraphics;
import zm.penguin.Context;

public abstract class Content {
    PApplet app;
    PGraphics graphics;

    public Content() {
        this.app = Context.getApplet();
        this.graphics = app.g;
    }

    public void setGraphicsCanvas(PGraphics newGraphics) {
        this.graphics = newGraphics;
    }
}
