package zm.penguin.components.icons;

import processing.core.PConstants;
import processing.core.PImage;
import zm.penguin.components.Icon;

import static zm.penguin.styles.Theme.*;

public  class ImageIcon extends Icon {
    public PImage img;

    public ImageIcon(PImage img, int w, int h) {
        this(img, 0,0, w, h);
    }

    public ImageIcon(PImage img, int x, int y, Runnable action) {
        this(img, x,y);
        this.action = action;
    }

    public ImageIcon(PImage img, int x, int y, int w, int h) {
        super(x,y,w,h);
        this.img = img;
    }

    public ImageIcon(PImage img, int x, int y, int w, int h, Runnable action) {
        this(img,x,y,w,h);
        this.action = action;
    }

    @Override
    public void draw() {
        app.rectMode(PConstants.CORNER);
        app.fill(button);
        app.rect(l,t,w,h);
        app.imageMode(PConstants.CENTER);
        app.image(img, l + (float)w/2, t + (float)h/2);
        app.fill(divider);
        app.rect(l, t, w, 1); // top
        app.rect(l, h, w, 1); // bottom
        app.rect(l,t,1,h); // left
        app.rect(r, t, 1, h); // right
    }

    @Override
    public String toString() {
        return "";
    }
}
