package zm.penguin.graphs.utils;
import static zm.penguin.styles.Theme.*;

public class CustomGridLine {
    public float value;
    public float strokeWeight;
    public int major = lightgrey, minor = darkgrey;
    public String label = "";
    public boolean custom = false;
    public boolean isMajor = false;
    public CustomGridLine(float value, int c, float strokeWeight) {
        this.value = value;
        this.strokeWeight = strokeWeight;
        this.major = c;
    }

    public CustomGridLine(float value, int c, float strokeWeight, boolean custom) {
        this(value,c,strokeWeight);
        this.custom = custom;
    }

    public CustomGridLine(float value, int c, float strokeWeight, boolean custom, boolean isMajor) {
        this(value,c,strokeWeight);
        this.custom = custom;
        this.isMajor = isMajor;
    }

    public CustomGridLine(float value, int c) {
        this(value, c, 1);
    }

    public CustomGridLine(float value, float strokeWeight) {
        this(value, lightgrey, strokeWeight);
    }

    public CustomGridLine(float value) {
        this(value, lightgrey, 1);
    }

    public void setMajor(int   p) { this.major = p; }
    public void setMinor(int   s) { this.minor = s; }
    public void setColors(int   p, int s) { this.major = p; this.minor = s; }

    public void setStrokeWeight(float sw) { this.strokeWeight = sw; }
    public void setValue(float value) { this.value = value; }

    public float getValue() { return this.value; }
    public float getStrokeWeight() { return this.strokeWeight; }
    public int getMajor() { return this.major; }
    public int getMinor() { return this.minor; }
    public String getLabel() { return this.label; }

    public void setLabel(String l) { this.label = l; }

    public void setCustom(boolean custom) { this.custom = custom; }
    public boolean isCustom() { return this.custom; }
    public void setIsMajor(boolean m) { this.isMajor = m; }
    public boolean isMajor() { return this.isMajor; }
}
