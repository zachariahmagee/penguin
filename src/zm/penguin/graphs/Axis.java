package zm.penguin.graphs;

import zm.penguin.containers.Container;
import zm.penguin.graphs.utils.CustomGridLine;
import zm.penguin.graphs.utils.CustomGridLines;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

import static processing.core.PApplet.*;
import static processing.core.PApplet.println;

public abstract class Axis extends Container<GridComponent> {
    public float min, max, last_min, last_max;

    public double segment;
    public int divisions;
    public boolean isAuto = true, computeBounds = true,
            unLabeled, changeGrids, isLog, setDivisions, userSegments;
    public double minorSegment, majorSegment;
    final int minMinorSpacing = 5;

    int precision, textHeight, textWidth;
    float charWidth;
    double basePosition, startPosition;

    public int count = 0;

    public int padding = 5, graphMark = 8, border = 30;

    public int textColor;

    float zero = 0;

    public String unit = "", axisLabel = "";

    ArrayList<Float> values;
    CustomGridLines custom;

    public Axis() {}
    Axis(Graph graph) {

    }

    public Axis(int l, int t, int r, int b, float min, float max, int divisions) {
        setLocation(l, t, r - l, b - t);
        this.min = min;
        this.max = max;
        this.divisions = divisions;
        this.segment = 0;

        this.values = new ArrayList<Float>();
        this.custom = new CustomGridLines();
        this.textHeight = 24;
        this.charWidth = 9;
        this.textWidth = (int)(charWidth * 3);

        // TODO: initialize default colors

        computeBounds = true;
    }
    public Axis(int l, int t, int r, int b, float min, float max) {
        this(l,t,r,b,min,max,-1);
    }

//    abstract public <G extends Graph> void set(G Graph);

    @Override
    public void setLocation(int left, int top, int right, int bottom) {
        if (l != left || t != top || r != right || b != bottom) {
            this.l = left;
            this.t = top;
            this.r = right;
            this.b = bottom;
            computeBounds = true;
        }
    }
    public void setLocationMinMax(int l, int t, int r, int b, float min, float max) {
        this.setLocation(l, t, r, b);
        this.setMinMax(min, max);
    }

    public void setLocationMinMaxDivisions(int l, int t, int r, int b, float min, float max, int divisions) {
        this.setLocation(l, t, r, b);
        this.setMinMax(min, max);
        this.setDivisions(divisions);
    }

    public void set(int l, int t, int r, int b, float min, float max, int divisions, String unit) {
        this.setLocation(l, t, r, b);
        this.setMinMax(min, max);
        this.setDivisions(divisions);
        this.setUnit(unit);
    }

    public void setDivisions(int divisions) {
        if (this.divisions != divisions && divisions > 0) {
            this.divisions = divisions;
            computeBounds = true;
            userSegments = false;
            isAuto = false;
        } else if (divisions == -1) {
            this.divisions = divisions;
            computeBounds = true;
            //isAuto = true;
            userSegments = false;
        }
    }

    public void setMinMax(float min, float max) {
        if (this.min != min || this.max != max) {
            this.min = min;
            this.max = max;
            computeBounds = true;
        }
    }

    public void setMinMaxDivisions(float min, float max, int divisions) {
        this.setMinMax(min, max);
        this.setDivisions(divisions);
    }

    public void setMinorSegment(float segment) {
        this.minorSegment = segment;
        computeBounds = true;
    }
    public void setMajorSegment(float segment) {
        this.majorSegment = segment;
        computeBounds = true;
    }

    public void setSegments(float min, float maj) {
        this.minorSegment = min;
        this.majorSegment = maj;
        this.isAuto = false;
        this.userSegments = true;
        computeBounds = true;
    }
    public void setSegments(boolean seg) {
        this.userSegments = seg;
        computeBounds = true;
    }

    public void setSegment(double segment) {
        this.segment = segment;
        computeBounds = true;
    }

    public void setLogarithmic(boolean log) { this.isLog = log; computeBounds = true; }

    public void setUnit(String unit) {
        if (!this.unit.equals(unit)) {
            this.unit = unit;
            computeBounds = true;
        }
    }

    public void setUnlabeled(boolean un) { this.unLabeled = un; computeBounds = true; }

    public void setAxisMode(boolean log) {
        this.isLog = log;
    }

    public void setAutoAxis(boolean auto) {
        this.isAuto = auto;
        computeBounds = true;
    }

    public void setLabel(String label) {
        this.axisLabel = label;
        computeBounds = true;
    }

    public void setLabelColor(int l) {
        this.textColor = l;
    }
    public void setMajorColor(int m) {
        this.f = m;
    }
    public void setMinorColor(int m) {
        this.s = m;
    }
    public void setMajorMinorColor(int major, int minor) {
        if (major != this.f) this.f = major;
        if (minor != this.s) this.s = minor;
        computeBounds = true;
    }
    public void setGridStrokeWeight(float strokeWeight) {
        this.strokeWeight= strokeWeight;
        computeBounds = true;
    }
    public void setGridColorStroke(int major, int minor, float weight) {
        boolean changed = false;
        if (this.f != major) {
            this.f = major;
            changed = true;
        }
        if (this.s != minor) {
            this.s = minor;
            changed = true;
        }
        if (this.strokeWeight != weight) {
            this.strokeWeight = weight;
            changed = true;
        }
        if (changed) changeGrids = true;
    }

    public void setGridColorStroke(int label, int major, int minor, float weight) {
        this.textColor = label;
        this.f = major;
        this.s = minor;
        this.strokeWeight = weight;
        computeBounds = true;
    }

    public void setGridComponents() {
        //if (changeGrids) {
        for (GridComponent gc : components) {
            if (!gc.isCustom()) {
                gc.setMajorColor(f);
                gc.setMinorColor(s);
                gc.setStrokeWeight(strokeWeight);
            }
            gc.setLabelColor(textColor);
            changeGrids = false;
        }
    }

    public void addCustomGridLines(float...args) {
        for (float arg : args) {
            custom.add(new CustomGridLine(arg));
        }
    }

    public void addCustomGridLines(CustomGridLine...args) {
        for (CustomGridLine arg : args) {
            custom.add(arg);
        }
        computeBounds = true;
    }

    public void clearCustom() {
        custom.clear();
        computeBounds = true;
    }

    protected abstract void computeBounds();

    public void count() { this.count++; }
    public void resetCount() { this.count = (int)-max; }
    public void setCount(int count) { this.count = count; }

    float logb(float num, int base) {
        try {
            if (num == 0.0) num = 0.1f;
            if (num < 0 || base <= 0) throw new IllegalArgumentException("");
            return (float)(Math.log(num) / Math.log(base));
        }
        catch (Exception e) {
            println("logb: ", num);
        }
        return 99999999;
    }

    // Round a number to the closest suitable axis division size
    double roundToIdeal(float num) {
        if (num == 0) {
            return 0;
        }

        final int n = 2;

        final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
        final int power = n - (int) d;

        final double magnitude = Math.pow(10, power);
        long shifted = Math.round(num*magnitude);

        // Apply rounding to nearest useful divisor
        if (abs(shifted) > 75) shifted = (num < 0)? -100:100;
        else if (abs(shifted) > 30) shifted = (num < 0)? -50:50;
        else if (abs(shifted) > 23) shifted = (num < 0)? -25:25;
        else if (abs(shifted) > 15) shifted = (num < 0)? -20:20;
        else shifted = (num < 0)? -10:10;

        //println(num, shifted, magnitude, shifted/magnitude);

        return shifted/magnitude;
    }

    // Determine what type of axis label text to display
    String formatLabelText(double labelNumber, int precision) {
        // Scientific notation
        String labelScientific = String.format("%." + precision + "E", labelNumber);

        if (labelScientific.contains(".")) {
            labelScientific = labelScientific.replaceAll("\\+", ""); // Remove the "+" sign
            labelScientific = labelScientific.replaceAll("(\\.\\d*?)0*E", "$1E"); // Remove trailing zeros after the decimal point before "E" and add "E" back
            if (labelScientific.contains(".E")) {
                labelScientific = labelScientific.replaceAll("(\\d\\.*?)E", "$10E");
            }
            labelScientific = labelScientific.replaceAll("E0*(\\d)", "E$1"); // Remove trailing zeros in the exponent part while keeping one digit
            labelScientific = labelScientific.replaceAll("\\.$", ""); // Remove the decimal point if there are no decimal digits left
        }

        // Decimal notation
        String labelDecimal = String.format("%." + precision + "f", labelNumber);
        if (labelDecimal.contains(".")) labelDecimal = labelDecimal.replaceAll("[0]+$", "").replaceAll("[.]+$", "");
        if (labelNumber < 1 && labelNumber > 0) {
            BigDecimal bigDecimal = new BigDecimal(Double.toString(labelNumber));
            float[] digits = { 0.1f, 0.01f, 0.001f, 0.0001f, 0.00001f, 0.000001f };
            int i = 0;
            while (digits[i] >= labelNumber) {

                precision = i + 2;
                if (++i == 6) break;
            }

            bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_DOWN);
            //println(labelNumber, precision, labelDecimal, bigDecimal);

            labelDecimal = bigDecimal.toString();


        } else if (labelNumber > -1 && labelNumber < 0) {
            BigDecimal bigDecimal = new BigDecimal(Double.toString(labelNumber));
            float[] digits = { -1.0f, -0.1f, -0.01f, -0.001f, -0.0001f, -0.00001f, -0.000001f };
            int i = 0;
            while (digits[i] >= labelNumber) {
                precision = i + 2;
                if (++i == 6) break;
            }

            bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_DOWN);
            //println(labelNumber, precision, labelDecimal, bigDecimal);

            labelDecimal = bigDecimal.toString();
        }
        //if (labelNumber == 0.01) { println(labelNumber); labelDecimal = "0.010"; }
        if (labelNumber == 0.10) { labelDecimal = "0.10"; }
        // If decimal notation is shorter than 8 characters, use it
        if (labelDecimal.length() == 2 && labelDecimal.charAt(0) == '-' && labelDecimal.charAt(1) == '0') return "0";
        if (labelDecimal.length() < 8 || (labelDecimal.charAt(0) == '-' && labelDecimal.length() < 9)) return labelDecimal;
        return labelScientific;
    }

    int calculateLogarithmicPrecision(double maxValue, double minValue, double segments) {
        return 0;
    }

    // Calculate the precision with which the graph axes should be drawn
    int calculateRequiredPrecision(double maxValue, double minValue, double segments) {
        if (segments == 0 || maxValue == minValue) return 1;

        double largeValue = (maxValue < 0) ? -maxValue : maxValue;
        if (maxValue == 0 || -minValue > largeValue) largeValue = (minValue < 0) ? -minValue : minValue;

        final double d1 = Math.floor( Math.log10( (segments < 0) ? -segments : segments ) );
        final double d2 = Math.floor( Math.log10( largeValue ) );
        final double removeMSN = Math.round( (segments % Math.pow( 10, d1 )) / Math.pow( 10, d1 - 1 ) );

        int value = abs((int) d2 - (int) d1) + 1;

        if (removeMSN > 0 && removeMSN < 10) value++;

        return  value;
    }

}
