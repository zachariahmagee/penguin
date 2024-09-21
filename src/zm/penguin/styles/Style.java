package zm.penguin.styles;

import processing.core.*;

public class Style {
    // Static fields for global style
    Theme theme;

    public static int button_w = 120;
    public static int button_h = 30;
    public static int sidebar_w = 40;
    public static int sidepanel_w = 150;
    public static int controls_w = 200;
    public static int bottombar_h = 23;

    // Font Variables
    final String baseFont   = "JetBrains Mono NL Regular Nerd Font Complete.ttf";
    final String smallFont  = "JetBrains Mono NL Regular Nerd Font Complete.ttf";
    final String italicFont = "JetBrains Mono NL Italic Nerd Font Complete.ttf";
    final String thinFont   = "JetBrains Mono NL Thin Nerd Font Complete Mono.ttf";
    public static PFont small_font, base_font, italic_font, thin_font;

    public int alignX, alignY;

    public float strokeWeight = 1;

    public Style(PApplet app) {
        base_font   = app.createFont(baseFont, 15, true);
        small_font  = app.createFont(smallFont, 10, true);
        italic_font = app.createFont(italicFont, 15, true);
        thin_font   = app.createFont(thinFont, 15, true);

        alignX = PConstants.CENTER;
        alignY = PConstants.CENTER;
    }
}

