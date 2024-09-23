package zm.penguin;

import processing.core.*;

public class Context {
    private static Context instance;
    private PApplet applet;

    private Context(PApplet pApplet) {
        this.applet = pApplet;
    }

    public static void initialize(PApplet pApplet) {
        if (instance == null) {
            instance = new Context(pApplet);
        }
    }

    public static PApplet getApplet() {
        return instance.applet;
    }
}
