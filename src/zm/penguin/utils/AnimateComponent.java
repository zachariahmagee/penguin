package zm.penguin.utils;

import zm.penguin.components.Component;

public class AnimateComponent {
    public int l, t, r, b, w, h;
    Component component;
    private long startTime;
    public int duration;
    Coordinate start, end;

    public AnimateComponent(Component component) {
        this.component = component;
        this.startTime = System.currentTimeMillis();
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
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
