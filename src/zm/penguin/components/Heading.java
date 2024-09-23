package zm.penguin.components;

import processing.core.PFont;
import zm.penguin.Penguin;
import zm.penguin.components.Component;
import static zm.penguin.styles.Theme.*;
import static zm.penguin.styles.Style.*;

import static processing.core.PApplet.*;
import static processing.core.PConstants.*;

public class Heading extends TextComponent {
    public Heading(String text) {
        this.text = text;
        this.font = heading_font;
        this.textColor = heading;
        this.w = heading_w;
        this.h = heading_h;
    }

    public Heading(String text, PFont font) {
        this.text = text;
        this.textColor = button_text;
        this.font = font;

        this.w = heading_w;
        this.h = heading_h;
    }

    public Heading(String text, int x, int y) {
        this(text);
        this.l = x;
        this.t = y;


    }

    public Heading(String text, int x, int y, PFont font) {
        this(text, font);
        this.l = x;
        this.t = y;
    }

    public Heading(String text, int x, int y, PFont font, int textColor) {
        this(text,x,y,font);
        this.textColor = textColor;
    }

    public Heading(String text, int x, int y, PFont font, int textColor, int textSize) {
        this(text,x,y,font,textColor);
        this.textSize = textSize;
    }

    public Heading(String text, int x, int y, int w, int h, PFont font, int textColor) {
        this.text = text;
        this.font = font;
        this.textColor = textColor;
        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;
        this.r = l + w;
        this.b = t + h;
    }

    public Heading(String text, int x, int y, int w, int h, PFont font, int textColor, int textSize) {
        this(text,x,y,w,h,font,textColor);
        this.textSize = textSize;
    }

    public Heading(String text, int x, int y, int w, int h) {
        this(text);

        this.textColor = button_text;

        this.l = x;
        this.t = y;
        this.w = w;
        this.h = h;
        this.r = l + w;
        this.b = t + h;
    }

    public Heading(String text, int textColor, int x, int y, int w, int h) {
        this(text,x,y,w,h);
        this.textColor = textColor;
    }

    public Heading setTextSize(int s) { this.textSize = s; return this; }

    @Override
    public void draw() {
        try {
            app.textAlign(alignX, alignY);

            app.textFont(font);
            app.textSize(textSize);
            app.fill(textColor);
            app.text(text, l-border, t, w, h);
            app.rectMode(CORNER);

        } catch (Exception e) {
            if (debug) {
                println(this, e);
            }
        }
    }

    boolean interactive() { return false; }

    @Override
    public void signalThemeChange(int theme) {

    }

    @Override
    public String toString() {
        return "";
    }
}
