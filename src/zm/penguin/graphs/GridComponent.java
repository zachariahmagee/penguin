package zm.penguin.graphs;

import processing.core.PFont;
import zm.penguin.components.Component;
import zm.penguin.components.TextComponent;

/**
 *
 * */
public abstract class GridComponent extends TextComponent {
    public boolean isMajor, labeled, notch, custom;
    public float value;
    public int graphMark = 8, padding = 5;

    public void setValue(float value) { this.value = value; }
    //public float getValue() { return Float.parseFloat(text); }
    public void setLabel(String value) { this.text = value; }
    public void setFont(PFont font, int fontSize) { this.font = font; this.textSize = fontSize; }

    public void setMajor(boolean isMajor) { this.isMajor = isMajor; }
    public void setNotch(boolean notch) { this.notch = notch; }
    public void setLabeled(boolean labeled) { this.labeled = labeled; }

    public void setMajorColor(int m) { this.f = m; }
    public void setMinorColor(int m) { this.s = m; }
    public void setVariableColor(int v) { this.accentColor = v; }
    public void setMajorMinorColor(int major, int minor) { this.f = major; this.s = minor; }
    public void setLabelColor(int label) { this.textColor = label; }
    public void setCustom(boolean custom) { this.custom = custom; }
    public boolean isCustom() { return this.custom; }
}
