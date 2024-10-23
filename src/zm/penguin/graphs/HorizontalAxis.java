package zm.penguin.graphs;

import zm.penguin.graphs.utils.CustomGridLine;
import zm.penguin.graphs.utils.CustomGridLines;
import static processing.core.PApplet.*;
import static zm.penguin.styles.Style.*;

public class HorizontalAxis extends Horizontal {
    boolean userDefinedBounds = false;

    private CartesianGraph graph;

    public HorizontalAxis() {}

    public HorizontalAxis(CartesianGraph graph) {
        this(graph.gL,graph.gT,graph.gR,graph.gB,graph.minY,graph.maxY,graph.divisionsY);
        this.graph = graph;
    }

    public HorizontalAxis(int l, int t, int r, int b, float min, float max, int divisions) {
        super(l,t,r,b,min,max,divisions);
    }
    public HorizontalAxis(int l, int t, int r, int b, float min, float max) {
        this(l,t,r,b,min,max,-1);
    }

    public void draw() {
        app.textFont(base_font);
        this.charWidth = app.textWidth("0");
        //this.textWidth = int(charWidth * 3);
        this.textWidth = (int)(charWidth * 6);

        if (computeBounds) {
            computeBounds(); /*computeBounds = false;*/
        }

        components.clear();

        if (min > 0) zero = min;
        else if (max < 0) zero = max;

        if (userSegments) {
            computeCustom();
        } else {
            computeAuto();
        }

        computeCustomGridLines();
        // The outer grid axes
        app.textFont(base_font);

        setGridComponents();

        for (GridComponent g : components) {
            g.draw();
        }
    }

    @Override
    void computeCustomGridLines() {
        if (custom.size() > 0) {
            for (CustomGridLine c : custom.gridlines) {
                final float currentXpixel = map((float)c.getValue(), min, max, l, r);
                String label = userDefinedBounds?formatLabelText(c.getValue(), precision):""+(int)(c.getValue()+count);
                GridLine gl = new GridLine(label, (int)currentXpixel, t, (int)currentXpixel, b, c.isMajor());

                if (c.isCustom()) {
                    gl.setMinorColor(c.getMajor());
                    gl.setStrokeWeight(c.getStrokeWeight());
                    gl.setCustom(true);
                }
                components.add(gl);
            }
        }
    }

    @Override
    void computeAuto() {
        boolean halfLabels = false;
        if (userDefinedBounds) {
            values.clear();
            for (double i = min; i <= max; i += segment) {
                values.add((float)i);
            }
            app.textFont(small_font);
            app.textSize(11);
            float maxWidth = app.textWidth(formatLabelText(max, precision));
            if ((r - l) <= values.size() * maxWidth * 1.5) halfLabels = true;
        }
        minorSegment = segment; majorSegment = max - min;
        int labelCount = 1;
        for (double i = startPosition; i <= max; i += segment) {
            if (custom.contains((float)i)) continue;
            final float currentXpixel = map((float) i, min, max, l, r);
            String label = userDefinedBounds?formatLabelText(i, precision):""+(int)(i+count);
            boolean noLabel = unLabeled || halfLabels && labelCount % 2 == 0 && i > min && i < max; labelCount++;
            if (!unLabeled && i >= max && !unit.isEmpty()) {
                components.add(new GridLine(unit, (int)currentXpixel, t, (int)currentXpixel, b));
            } else {
                components.add(new GridLine(noLabel?"":label, (int)currentXpixel, t, (int)currentXpixel, b, i == startPosition));
            }
        }
    }

    @Override
    void computeCustom() {
        float minorSegmentUsed = (float) minorSegment;
        precision = calculateRequiredPrecision(max, min, majorSegment);

        double actualMinorSpacing =  (r - l) / ((max - min) / minorSegment);

        if (actualMinorSpacing < minMinorSpacing) {
            minorSegmentUsed = 0;
        } else {
            minorSegmentUsed = (float)minorSegment;
        }

        if (minorSegmentUsed != 0) {
            for (double i = startPosition; i <= max; i += minorSegmentUsed) {
                if (custom.contains((float)i)) continue;
                final float currentXpixel = map((float) i, min, max, l, r);
                if (i % majorSegment == 0) continue;
                components.add(new GridLine((int) currentXpixel, t, (int)currentXpixel, b - 1));
            }
        }

        values.clear();
        for (double i = min; i <= max; i += majorSegment) {
            values.add((float)i);
        }
        //int labelSpacing = (b - t) / values.size();
        boolean halfLabels = (r - l) <= values.size() * textWidth * 2;

        if ((r - l) <= values.size() * textWidth * 2) halfLabels = true;
        if ((r - l) <= (values.size() * textWidth * 2) / 2) {
            userSegments = false;
            computeBounds = true;
        }
        //println(r-l,values.size() * textWidth * 2);
        int labelCount = 1;
        for (double i = startPosition; i <= max; i += majorSegment) {
            if (custom.contains((float)i)) continue;
            final float currentXpixel = map((float) i, min, max, l, r);
            String label = userDefinedBounds?formatLabelText(i, precision):""+(int)(i+count);

            boolean noLabel = unLabeled || halfLabels && labelCount % 2 == 0 && i > min && i < max; labelCount++;
            if (!unLabeled && i >= max && !unit.isEmpty()) {
                components.add(new GridLine(unit, (int)currentXpixel, t, (int)currentXpixel, b, true));
            } else {
                components.add(new GridLine(noLabel?"":label, (int)currentXpixel, t, (int)currentXpixel, b, true));
            }
        }
    }

    // i dont think this needs to be rewritten here...
//    @Override
//    protected void computeBounds() {
//        // Calculate x-axis parameters
//        boolean solved = false;
//        // Since the solution of the calculations determines the width of the labels,
//        // which in turn influences the calculations, set up a loop so that the label
//        // widths are increased in length until they all fit in the axis area.
//        do {
//            // Figure out how many segments to divide the data into
//            segment = Math.abs(roundToIdeal((max - min) * (textWidth * 3) / (r - l)));
//            if (divisions != -1) segment = (abs(max) - abs(min)) / divisions;
//            // Figure out a base reference for all the segments
//            basePosition = zero;
//            if (zero > 0) basePosition = Math.ceil(min / segment) * segment;
//            else if (zero < 0) basePosition = -Math.ceil(-max / segment) * segment;
//
//            // Figure out how many decimal places need to be shown on the labels
//            precision = calculateRequiredPrecision(max, min, segment);
//
//            // Figure out where each of the labels should be drawn
//            startPosition = basePosition - (Math.floor((basePosition - min) / segment) * segment);
//
//            // Figure out the width of the largest label so we know how much room to make
//            int newxTextWidth = 0;
//            String lastLabel = "";
//            for (double i = startPosition; i <= max; i += segment) {
//                String label = formatLabelText(i, precision);
//                if (label.equals(lastLabel)) precision++;
//                int labelWidth = (int)(label.length() * (charWidth));
//                if (labelWidth > newxTextWidth) newxTextWidth = labelWidth;
//                lastLabel = label;
//            }
//
//            if (newxTextWidth <= textWidth) solved = true;
//            else textWidth = newxTextWidth;
//        } while (!solved);
//    }

    public void setUserDefinedBounds(boolean userDefined) {
        this.userDefinedBounds = userDefined;
    }
}
