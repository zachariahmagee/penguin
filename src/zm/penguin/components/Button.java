package zm.penguin.components;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import zm.penguin.Context;
import zm.penguin.styles.Style;
import zm.penguin.styles.Theme;

public class Button extends TextComponent {
    public boolean flash, flash_error;
    public int flash_count = 0 ,max_flash_count = 30;
    public boolean accented = false, specialty_corners = false;
    public int cr1, cr2, cr3, cr4;

    public Button() {
        this.app = Context.getApplet();
        this.w = Style.button_w;
        this.h = Style.button_h;
        this.f = Theme.button1;
        this.s = Theme.outline;
        this.textColor = Theme.button_text;
    }

    public Button(String text) {
        this();
        this.text = text;
    }

    public Button(String text, Runnable action) {
        this(text);
        this.action = action;
    }

    public Button(String text, Runnable action, int fill) {
        this(text);
        this.action = action;
        this.f = fill;
    }

    public Button(String text, Runnable action, PFont font) {
        this(text, action);
        this.font = font;
    }

    public Button(String text, int x, int y, Runnable action) {
        this(text, action);
        setLocation(x,y);
    }

    public Button(String text, int x, int y, int w, int h, Runnable action) {
        this(text);
        setLocation(x,y,w,h);
        this.action = action;
    }

    public Button(String text, int x, int y, int w, int h, int fontSize, Runnable action) {
        this(text,x,y,w,h, action);
        this.textSize = fontSize;
        this.action = action;
    }

    public Button(String text, int x, int y, int w, int h, int fontSize, int fill, int t, Runnable action) {
        this(text,x,y,w,h, fontSize, action);
        this.f = fill;
        this.textColor = t;
    }

    public void draw() {
        try {
            app.rectMode(PConstants.CORNER);
            app.textAlign(alignX, alignY);
            app.fill(f);
            if (flash_error) {
                app.fill(Theme.error);
            } else if (flash) {
                app.fill(Theme.accent);
            }
            if (flash_count++ > max_flash_count) {
                flash = flash_error = false;
                flash_count = 0;
            }
            app.stroke(s);
            app.strokeWeight(strokeWeight);
            app.textFont(font);
            app.textSize(textSize);
            if (specialty_corners) {
                app.rect(l, t, w, h, cr1, cr2, cr3, cr4);
            } else {
                app.rect(l, t, w, h, cornerRadius);
            }

            app.fill(textColor);
            app.text(text, l+2, t, w-4, h);

        } catch (Exception e) {
            PApplet.println(toString(), e);
        }
    }

    public void setAccented(boolean a) {
        this.accented = a;
    }

    @Override
    public void signalThemeChange(int theme) {
        this.f = Theme.button;
        this.s = Theme.outline;
        this.textColor = Theme.button_text;
    }

    public String toString() {
        return "Button:" + text;
    }

}
