package zm.penguin.components;

import processing.core.PImage;

public abstract class ImageIcon extends Icon {
    PImage img;

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
}
