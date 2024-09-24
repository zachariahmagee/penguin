package zm.penguin.styles;

import processing.core.*;

public class Theme {
    // Color palette definitions using hex values
    public static final int white          = 0xFFFFFFFF;  // White
    public static final int offwhite       = 0xFFFAF9F6;  // Off-white (250, 249, 246)
    public static final int paleblue       = 0xFFAFFEEE;  // Pale blue (175, 238, 238)
    public static final int darkpaleblue   = 0xFF24B4B3;  // Dark pale blue (36, 180, 179)
    public static final int lightblue      = 0xFF60C8DC;  // Light blue (96, 200, 220)
    public static final int blue           = 0xFF2850FF;  // Blue (40, 80, 255)
    public static final int purple         = 0xFF936FD4;  // Purple (147, 111, 212)
    public static final int lavender       = 0xFFBC75FF;  // Lavender (188, 117, 255)
    public static final int red            = 0xFFD02662;  // Red (208, 38, 98)
    public static final int yellow         = 0xFFD7C460;  // Yellow (215, 196, 96)
    public static final int green          = 0xFF23CD41;  // Green (35, 205, 65)
    public static final int orange         = 0xFFE65525;  // Orange (230, 85, 37)
    public static final int norange        = 0xFFD47D02;  // Dark orange (212, 125, 2)

    public static final int darkaqua       = 0xFF0A0A6F;  // Dark aqua (10, 10, 111)

    public static final int black          = 0xFF000000;  // Black
    public static final int greenplay      = 0xFF228B22;  // Green (34, 139, 34)
    public static final int pink           = 0xFFFFC0CB;  // Pink (255, 192, 203)
    public static final int darkred        = 0xFF8B0000;  // Dark red (139, 0, 0)
    public static final int cream          = 0xFFE0D2BF;  // Cream (224, 210, 191)
    public static final int bluegreen      = 0xFF07D2BE;  // Blue-green (7, 210, 190)
    public static final int violet         = 0xFFCFA3FF;  // Violet (207, 159, 255)

    // Shades of grey
    public static final int xlightgrey     = 0xFFE5E5E5;  // Extra light grey (229)
    public static final int xlgrey         = 0xFFD3D3D3;  // Light grey (211)
    public static final int lgrey          = 0xFFBFBFBD;  // Light grey (191, 190, 189)
    public static final int lightgrey      = 0xFF86868A;  // Light grey (134, 134, 138)
    public static final int grey           = 0xFF6F6C5A;  // Grey (111, 108, 90)
    public static final int midgrey        = 0xFF505050;  // Medium grey (80)
    public static final int darkgrey       = 0xFF31322C;  // Dark grey (49, 50, 44)
    public static final int greyblue       = 0xFF202731;  // Grey-blue (32, 39, 49)

    public static final int grey10         = 0xFF0A0A0A;  // Dark grey (10)
    public static final int grey25         = 0xFF191919;  // Dark grey (25)
    public static final int grey30         = 0xFF232323;  // Dark grey (35)
    public static final int grey40         = 0xFF282828;  // Dark grey (40)
    public static final int grey49         = 0xFF313131;  // Dark grey (49)
    public static final int grey60         = 0xFF3E3E3E;  // Dark grey (62)
    public static final int grey80         = 0xFF505050;  // Dark grey (80)
    public static final int grey95         = 0xFF5F5F5F;  // Dark grey (95)
    public static final int grey111        = 0xFF6F6F6F;  // Grey (111)
    public static final int grey125        = 0xFF7D7D7D;  // Grey (125)
    public static final int grey140        = 0xFF8C8C8C;  // Grey (140)
    public static final int grey160        = 0xFFA0A0A0;  // Grey (160)
    public static final int grey190        = 0xFFBEBEBE;  // Grey (190)
    public static final int grey211        = 0xFFD3D3D3;  // Light grey (211)
    public static final int grey223        = 0xFFDFDFDF;  // Light grey (223)
    public static final int grey229        = 0xFFE5E5E5;  // Light grey (229)
    public static final int grey245        = 0xFFF5F5F5;  // Light grey (245)

    // Static hex color values
    public static final int background     = 0xFFFFFFFF;  // White
    public static final int opposite       = 0xFF000000;  // Black
    public static final int divider        = 0xFFD9D9D9;  // Light grey (217)
    public static final int outline        = 0xFF0A0A0A;  // Dark grey (10)
    public static final int sidepanel      = 0xFFE5E5E5;  // Light grey (229)
    public static final int panel          = offwhite;
    public static final int fill           = 0xFFF5F5F5;  // Light grey (245)

    public static final int tabs           = 0xFFE5E5E5;  // Light grey (229)
    public static final int tab_text       = 0xFF323232;  // Dark grey (50)

    public static final int button         = white;
    public static final int button1        = white;
    public static final int button2        = white;
    public static final int alt_button     = offwhite;

    public static final int button_text    = 0xFF3C505A;  // Custom grey (60, 80, 90)

    public static final int idle           = 0xFFF0F0F0;  // Light grey (240)
    public static final int idle_text      = darkgrey;

    public static final int selected       = paleblue;
    public static final int selected_text  = darkpaleblue;

    public static final int heading        = 0xFF162BBD;  // Blue (22, 43, 189)

    public static final int highlight      = lightblue;
    public static final int accent         = lightblue;
    public static final int alt_accent     = bluegreen;

    public static final int error          = orange;
    public static final int error_text     = norange;

    public static final int scrollbar_bg = idle;
    public static final int scrollbar = lightgrey;

}


//public class Theme {
//
//    PApplet app;
//    // Color Variables
//    public static int white;
//    public static int offwhite;
//    public static int paleblue;
//    public static int darkpaleblue;
//    public static int lightblue;
//    public static int blue;
//    public static int purple;
//    public static int lavender;
//    public static int red;
//    public static int yellow;
//    public static int green;
//    public static int orange;
//    public static int norange;
//
//    public static int darkaqua;
//
//    public static int black;
//    public static int greenplay;
//    public static int pink;
//    public static int darkred;
//    public static int cream;
//    public static int bluegreen;
//    public static int violet;
//
//    public static int xlightgrey;
//    public static int xlgrey;
//    public static int lgrey;
//    public static int lightgrey;
//    public static int grey;
//    public static int midgrey  ;
//    public static int darkgrey ;
//
//    public static int  greyblue;
//
//    public static int grey10;
//    public static int grey25;
//    public static int grey30;
//    public static int grey40;
//    public static int grey49;
//    public static int grey60;
//    public static int grey80;
//    public static int grey95;
//    public static int grey111;
//    public static int grey125;
//    public static int grey140;
//    public static int grey160;
//    public static int grey190;
//    public static int grey211;
//    public static int grey223;
//    public static int grey229;
//    public static int grey245;
//
//    public static int background;
//    public static int opposite;
//
//    public static int divider;
//    public static int outline;
//
//    public static int sidepanel;
//    public  static int panel;
//    public static int fill;
//
//    public  static int tabs;
//    public  static int tab_text;
//
//    public static int button;
//    public static int button1;
//    public static int button2;
//    public static int alt_button;
//
//    public static int button_text;
//
//    public  static int idle;
//    public static int idle_text;
//
//    public static int selected;
//    public static int selected_text;
//
//    public static int heading;
//
//    public static int highlight;
//    public static int accent;
//    public static int alt_accent;
//
//    public static int error;
//    public static int error_text;
//
//    Theme(PApplet app) {
//        this.app = app;
//        createColors();
//        background = app.color(255);
//        opposite = app.color(0);
//        divider = app.color(217);
//        outline = app.color(10);
//        sidepanel = app.color(229);
//        panel = offwhite;
//        fill = app.color(245);
//
//        tabs = app.color(229);
//        tab_text = app.color(50);
//
//        button = white;
//        button1 = white;
//        button2 = white;
//        alt_button = offwhite;;
//
//        button_text = app.color(60, 80, 90);
//
//        idle = app.color(240);
//        idle_text = darkgrey;
//
//        selected = paleblue;
//        selected_text = darkpaleblue;
//
//        heading = app.color(22, 43, 189);
//
//        highlight = lightblue;
//        accent = lightblue;
//        alt_accent = bluegreen;
//
//        error = orange;
//        error_text = norange;
//    }
//
//    private void createColors() {
//        white     = app.color(255, 255, 255);
//        offwhite  = app.color(250, 249, 246);
//        paleblue  = app.color(175,238,238);
//        darkpaleblue = app.color(36, 180, 179);
//        lightblue = app.color(96, 200, 220);
//        blue      = app.color(40, 80, 255);
//        purple    = app.color(147, 111, 212);
//        lavender  = app.color(188, 117, 255);
//        red       = app.color(208, 38, 98);
//        yellow    = app.color(215, 196, 96);
//        green     = app.color(35, 205, 65);
//        orange    = app.color(230, 85, 37);
//        norange   = app.color(212, 125, 2);
//
//        darkaqua  = app.color(10, 10, 111);
//
//
//        black     = app.color(0, 0, 0);
//        greenplay = app.color(34,139,34);
//        pink      = app.color(255,192,203);
//        darkred   = app.color(139,0,0);
//        cream     = app.color(224, 210, 191);
//        bluegreen = app.color(7,210,190);
//        violet    = app.color(207, 159, 255);
//
//        xlightgrey = app.color(229, 229, 229);
//        xlgrey    = app.color(211, 211, 211);
//        lgrey     = app.color(191, 190, 189);
//        lightgrey = app.color(134, 134, 138);
//        grey      = app.color(111, 108, 90);
//        midgrey   = app.color(80);
//        darkgrey  = app.color(49, 50, 44);
//        greyblue  = app.color(32, 39, 49);
//
//        grey10    = app.color(10);
//        grey25    = app.color(25);
//        grey30    = app.color(35, 32, 35);
//        grey40    = app.color(40, 40, 40);
//        grey49    = app.color(49);
//        grey60    = app.color(62);
//        grey80    = app.color(80);
//        grey95    = app.color(95);
//        grey111   = app.color(111);
//        grey125   = app.color(125);
//        grey140   = app.color(140);
//        grey160   = app.color(160);
//        grey190   = app.color(190);
//        grey211   = app.color(211);
//        grey223   = app.color(223);
//        grey229   = app.color(229);
//        grey245   = app.color(245);
//
//    }
//}
