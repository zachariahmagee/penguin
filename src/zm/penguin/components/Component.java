package zm.penguin.components;

import processing.core.*;
import zm.penguin.Context;

public abstract class Component {
    protected PApplet app;
    public boolean debug = true;
    public boolean isVisible = false;
    public String name;

    public int l;
    public int r;
    public int t;
    public int b;

    public int w;
    public int h;

    public int f;
    public int s;
    public int accent;

    public int offsetX = 0, offsetY = 0;

    public float strokeWeight = 1;
    public int cornerRadius = 2;

    public int border = 15;
    public int spacing = 5;

    boolean onTop = false;

    public Runnable action = () -> {};

    public Component() {
        this.app = Context.getApplet();
    }

    public abstract void draw();

    public abstract String toString();
//    {
//        return this.getClass().toString();
//    }

    public void setPApplet(PApplet app) {
        this.app = app;
    }

    public void setVisibility(boolean isVisible) { this.isVisible = isVisible; }

    public boolean isVisible() { return isVisible; }

    public void action() {
        this.action.run();
    }

    public void setTop(int t) {
        this.t = t;
    }

    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = l + w;
        this.b = t + h;

    }

    public void setLocation(int x, int y, int w, int h) {
        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;

        this.r = l + w;
        this.b = t + h;
    }


    public boolean locationIsSet() { return false; }

    public void setColors() {}

    public void setFill(int fill) { this.f = fill; }

    public void setStroke(int stroke) { this.s = stroke; }

    public void setStroke(int weight, int stroke) {
        this.strokeWeight = weight;
        this.s = stroke;
    }

    public void setAccent(int c) { this.accent = c; }

    public void setStrokeWeight(float weight) { this.strokeWeight = weight; }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setContainerBorder(int border) { this.border = border; }

    public void setContainerSpacing(int spacing) { this.spacing = spacing; }

    public void setHeight(int h) { this.h = h; }

    public void setWidth(int w) { this.w = w; }

    public int getHeight() { return h; }

    public int getWidth() { return w; }



    public void onClick(Runnable function) {
        this.action = function;
    }

    public boolean mouseOver(int x, int y) {
        return ((y >= t)
                && (y < t + (b - t))
                && (x >= l)
                && (x <= l + (r - l))
        );
    }
    public void mouseDragged(int x, int y) {}
    public void mouseMoved(int x, int y) {}
    public void mouseReleased(int x, int y) {}

    public void signalThemeChange(int theme) {

    }

    public void click(int x, int y) {
       this.action.run();
    }

    public void rightClick(int x, int y) {}
}
