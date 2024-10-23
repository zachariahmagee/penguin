package zm.penguin.graphs;

import zm.penguin.components.Component;
import zm.penguin.containers.Content;
import zm.penguin.graphs.components.Labels;
import zm.penguin.graphs.components.PlotLabel;
import zm.penguin.graphs.utils.CustomGridLine;
import zm.penguin.interactions.Resizable;
import zm.penguin.utils.DisplayValues;
import zm.penguin.utils.SliderValues;

import java.util.Objects;

public abstract class Graph extends Content<Component> implements Resizable { // Container<Component> {
    public int id;
    public int gL, gR, gT, gB;

    public String plotTitle = "", // plotName = "",
            yAxisLabel = "", xAxisLabel = "",
            primaryUnits = "", secondaryUnits = "";

    public int graphMark = 8, border = 30, base = 10, customXaxis = -1, autoAxis = 2;

    public boolean highlighted, isLogarithmic, zoom_in;

    int textColor, major, minor;

    PlotLabel plotLabel;
    Labels labels;

    SliderValues grid_stroke_values;


    public Graph(int id, int l, int r, int t, int b) {
        this.id = id;
        this.graphics = app.g;

        this.l = gL = l;
        this.r = gR = r;
        this.t = gT = t;
        this.b = gB = b;
        this.w = this.r - this.l;
        this.h = this.b - this.t;

        this.labels = new Labels(l + 2, r - border, b - 35, b - 5);
        this.plotLabel = new PlotLabel(plotTitle, l, b-35);
        labels.add(plotLabel);
        labels.setVisibility(false);

        grid_stroke_values = new SliderValues(0.5f, 4, 1, new DisplayValues(0.5f, 4, 1, 2));

        this.highlighted = false;
        this.isVisible = true;
    }
//    @Override
//    public void draw() {
//
//    }
//
//    @Override
//    public void changeWindowSize(int newW, int newH) {
//
//    }

    public abstract void clearGraph();
    public abstract void updateBefore();
    public abstract void updateAfter();

    public abstract void plotData(float dataY, float dataX, int id, int traceColor);
    public abstract void drawMarker(float dataY, float dataX, int id, int c);

    public void setHighlight(boolean state) {
        highlighted = state;
    }

    public void setPlotTitle(String title) {
        setPlotName(title);
    }

    public void setPlotName(String title) {
        if (!plotTitle.equals(title)) {
            this.plotTitle = title;
            if (this.plotLabel == null) {
                plotLabel = new PlotLabel(plotTitle, l + 2, b - 35);//PlotLabel(plotName, cL, cT);
            } else {
                plotLabel.setLabel(title);
            }
        }
    }

    public abstract void setAxisRange(String axis, float min, float max);
    public abstract void setAxisSegments(String axis, float minor, float major);
    public abstract void setDivisions(String axis, int divisions);
    public abstract void setLogarithmic(boolean log);
//    public abstract void setLogarithmic(String axis, boolean log);
    public abstract void setSegments(boolean seg);
    public abstract void setSegment(float segment);

    public abstract void setMajorMinorGridColor(int major, int minor);
    public abstract void setMajorGridColor(int major);
    public abstract void setMinorGridColor(int minor);
    public abstract void setGridTextColor(int text);
    public abstract void setGridStrokeWeight(float strokeWeight);

    public abstract void setGridColorStroke(int major, int minor, float stroke);
    public abstract void setGridColorStroke(int label, int major, int minor, float stroke);
    public abstract void setGridColors(int major, int minor, int text);
    public abstract void addCustomGridLine(String axis, CustomGridLine gridline);
    public abstract void clearCustom();

    abstract void setMinMax(float min1, float max1, float min2, float max2);

    boolean validFloat(float newNumber) {
        return newNumber != Float.POSITIVE_INFINITY && newNumber != Float.NEGATIVE_INFINITY;
    }
    @Override
    public String toString() {
        return "Graph";
    }


}
