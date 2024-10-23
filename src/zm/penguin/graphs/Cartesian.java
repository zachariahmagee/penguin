package zm.penguin.graphs;

import zm.penguin.graphs.components.AxisLabel;
import zm.penguin.graphs.utils.CustomGridLine;
import zm.penguin.interactions.BooleanCondition;
import zm.penguin.utils.Orientation;

import java.util.*;

import static processing.core.PApplet.shorten;
import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.CORNERS;
import static zm.penguin.styles.Theme.red;
import static zm.penguin.styles.Theme.selected;

abstract public class Cartesian extends Graph {

    boolean ySeg, xSeg;
    float yMinSeg = 0, yMajSeg = 0, xMinSeg = 0, xMajSeg = 0;

    boolean setDivisions;
    int divisionsX = -1, divisionsY = -1;

    float bminx = 0, bmaxx = 500, bminy, bmaxy;
    int bautoAxis, bcustomXaxis;
    float minX, maxX, minY, maxY;
    float[] lastX = {0}, lastY = { -99999999 };

    AxisLabel yAxisLabel;
    AxisLabel xAxisLabel;

    Horizontal x;
    VerticalAxis y;

    boolean customTraces;
    Set<Integer> traceIDs;

    public BooleanCondition highlightGraph;

    public Cartesian(int id, int l, int r, int t, int b, float minx, float maxx, float miny, float maxy) {
        super(id, l, r, t, b);

        this.minX = this.bminx = minx;
        this.maxX = this.bmaxx = maxx;
        this.minY = this.bminy = miny;
        this.maxY = this.bmaxy = maxy;

        this.traceIDs = new HashSet<Integer>();

        this.graphMark = 8;
        this.border = 30;

        yAxisLabel = new AxisLabel(Orientation.VERTICAL);
        xAxisLabel = new AxisLabel(Orientation.HORIZONTAL);

        // TODO: Why are these final and temporary?
        final int padding = 5;
        final int textHeight = 24;
        final float charWidth = 9;

        gT = t + 5;
        gB = b - textHeight - graphMark;
        gL = (int)(l + border + (charWidth * 5) + graphMark + padding);
        gR = r - border;

        y = new VerticalAxis(this); //VerticalAxis(gL, gR, gT, gB, minY, maxX, -1);
    }

    @Override
    public void draw() {
        if (!isVisible && labels.isVisible()) {
            labels.draw();
            return;
        }
        if (!isVisible) return;

        clearGraphArea();
//        if (persistence.isVisible()) {
//            persistence.draw();
//        }
//
//        if (gradient.isVisible()) {
//            gradient.draw();
//        }
        drawGrid();
    }

    abstract void clearGraphArea();
    abstract void drawGrid();

    void resetGraph(){
        Arrays.fill(lastX, 0);
        Arrays.fill(lastY, -99999999);
    }
    void reset() {
        while(lastX.length > 0) lastX = shorten(lastX);
        while(lastY.length > 0) lastY = shorten(lastY);
    }

    // TODO: finish this function
    public void fitToWindow() {

    }

    @Override
    public void setAxisRange(String axis, float min, float max) {
        switch(axis) {
            case "x":
                if (!zoom_in) {
                    this.bminy = min;
                    this.bmaxy = max;
                    this.bcustomXaxis = customXaxis;
                }
                minX = min; maxX = max;
                if (x != null) {
                    customXaxis = -1;
                    x.setMinMax(minX,maxX);
                    x.setAutoAxis(false);
                }
                break;
            case "y":
                if (!zoom_in) {
                    this.bminy = min;
                    this.bmaxy = max;
                    this.bautoAxis = autoAxis;
                }
                minY = min; maxY = max;
                if (y != null) {
                    autoAxis = -1;
                    y.setMinMax(minY,maxY);
                    y.setAutoAxis(false);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setAxisSegments(String axis, float minor, float major) {
        switch(axis) {
            case "x":
                x.setSegments(minor,major);
                break;
            case "y":
                y.setSegments(minor,major);
                break;
            default:
                break;
        }
    }
    @Override
    public void setDivisions(String axis, int divisions) {
        switch(axis) {
            case "x":
                x.setDivisions(divisions);
                divisionsX = divisions;
                break;
            case "y":
                y.setDivisions(divisions);
                divisionsY = divisions;
                break;
            default:
                break;
        }
    }

    @Override
    public void setSegments(boolean seg) {
        ySeg = xSeg = seg;
        y.setSegments(seg);
        x.setSegments(seg);
    }

    // TODO this should only be on the polar graph
    @Override
    public void setSegment(float segment) {

    }

    @Override
    public void setMajorMinorGridColor(int major, int minor) {
        x.setMajorMinorColor(major,minor);
        y.setMajorMinorColor(major,minor);
        this.f = this.major = major;
        this.s = this.minor = minor;
    }

    @Override
    public void setMajorGridColor(int major) {
        x.setMajorColor(major);
        y.setMajorColor(major);
        this.f = this.major = major;
    }

    @Override
    public void setMinorGridColor(int minor) {
        x.setMinorColor(minor);
        y.setMinorColor(minor);
        this.s = this.minor = minor;
    }

    @Override
    public void setGridTextColor(int text) {
        x.setLabelColor(text);
        y.setLabelColor(text);
        this.textColor = text;
    }

    @Override
    public void setGridStrokeWeight(float strokeWeight) {
        x.setGridStrokeWeight(strokeWeight);
        y.setGridStrokeWeight(strokeWeight);
    }

    @Override
    public void setGridColorStroke(int major, int minor, float stroke) {
        x.setGridColorStroke(major,minor,stroke);
        y.setGridColorStroke(major,minor,stroke);
        grid_stroke_values.current = stroke;
        this.major = major; this.minor = minor;
    }

    @Override
    public void setGridColorStroke(int label, int major, int minor, float stroke) {
        x.setGridColorStroke(label,major,minor,stroke);
        y.setGridColorStroke(label,major,minor,stroke);
        yAxisLabel.setTextColor(label);
        xAxisLabel.setTextColor(label);
        labels.setTextColor(label);
        grid_stroke_values.current = stroke;
        this.textColor = label; this.major = major; this.minor = minor;
    }

    @Override
    public void setGridColors(int major, int minor, int text) {
        x.setMajorMinorColor(major,minor); x.setLabelColor(text);
        y.setMajorMinorColor(major,minor); y.setLabelColor(text);
        yAxisLabel.setTextColor(text);
        xAxisLabel.setTextColor(text);
        labels.setTextColor(text);
        this.textColor = text; this.f = this.major = major; this.s = this.minor = minor;
    }

    @Override
    public void addCustomGridLine(String axis, CustomGridLine gridline) {
        switch(axis) {
            case "x":
                x.addCustomGridLines(gridline);
                break;
            case "y":
                y.addCustomGridLines(gridline);
                break;
            default:
                break;
        }
    }

    @Override
    public void clearCustom() {
        x.clearCustom();
        y.clearCustom();
    }

    @Override
    public void setLogarithmic(boolean log) {
        this.isLogarithmic = log;
        y.setLogarithmic(log);
    }

    // TODO: change or remove
    @Override
    void setMinMax(float min1, float max1, float min2, float max2) {

    }

    void setPrimaryUnits(String primary) { x.setUnit(primary); this.primaryUnits = primary;}
    void setSecondaryUnits(String secondary) { y.setUnit(secondary); this.secondaryUnits = secondary; }
    void setUnits(String primary, String secondary) { x.setUnit(primary); y.setUnit(secondary); this.primaryUnits = primary; this.secondaryUnits = secondary; }
    void setAutoAxis(boolean auto) { y.setAutoAxis(auto); }
    void setAutoAxis(boolean auto, int kind) { y.setAutoAxis(auto); this.autoAxis = kind; }
    void setVerticalAuto(boolean auto, int kind) { y.setAutoAxis(auto); this.autoAxis = kind; }
    void setVerticalAuto(boolean auto) { y.setAutoAxis(auto); }
    void setHorizontalAuto(boolean auto) { x.setAutoAxis(auto); }
    void setHorizontalAuto(boolean auto, int kind) { x.setAutoAxis(auto); this.customXaxis = kind; }
    void setYDivisions(int divisions) { y.setAutoAxis(false); y.setDivisions(divisions); }

    void setUnlabeledX(boolean un) {
        x.setUnlabeled(un);
    }

    boolean highlight() {
        if (highlightGraph != null) highlighted = highlightGraph.evaluate();
        return highlighted;
    }

    void drawGraphOutline() {
        app.noFill();
        app.strokeWeight(0.5f);
        app.stroke(red);
        app.rectMode(CORNERS);
        app.rect(gL, gT, gR, gB);
        app.rectMode(CORNER);
    }
    void drawHighlight() {
        app.fill(selected, 60);
        app.strokeWeight(2);
        app.stroke(selected);
        app.rectMode(CORNERS);
        app.triangle(l + 5, gT, l + 5, gB, gL - 5 - graphMark - border, (float)(gT + gB) / 2);
    }
    void drawContentOutline() {
        app.noFill();
        app.strokeWeight(3);
        app.stroke(red);
        app.rectMode(CORNERS);
        app.rect(l, t, r, b);
        app.rectMode(CORNER);
    }
    void addTraces(List<Integer> ids) {
        traceIDs = new HashSet<Integer>(ids);
        customTraces = true;
    }
}
