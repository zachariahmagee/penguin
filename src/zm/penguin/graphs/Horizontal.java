package zm.penguin.graphs;

import zm.penguin.graphs.utils.CustomGridLine;

import static processing.core.PApplet.*;

public abstract class Horizontal extends Axis {

    public Horizontal() {}
    public Horizontal(int l, int t, int r, int b, float min, float max, int divisions) {
        super(l,t,r,b,min,max,divisions);
    }

    void computeCustomGridLines() {
        if (custom.size() > 0) {
            for (CustomGridLine c : custom.gridlines) {
                final float currentXpixel = map((float)c.getValue(), min, max, l, r);
                String label = formatLabelText(c.getValue(), precision);
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

    void computeAuto() {
        boolean halfLabels = false;

        int labelCount = 1;
        minorSegment = segment; majorSegment = max - min;
        for (double i = startPosition; i <= max; i += segment) {
            if (custom.contains((float)i)) continue;
            final float currentXpixel = map((float) i, min, max, l, r);
            String label = formatLabelText(i, precision);
            boolean noLabel = unLabeled;// || halfLabels && labelCount % 2 == 0 && i > min && i < max;
            labelCount++;
            if (!unLabeled && i >= max && !unit.isEmpty()) {
                components.add(new GridLine(unit, (int)currentXpixel, t, (int)currentXpixel, b));
            } else {
                components.add(new GridLine(noLabel?"":label, (int)currentXpixel, t, (int)currentXpixel, b, i == startPosition));
            }
        }
    }

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
        boolean halfLabels = false;

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
            String label = formatLabelText(i, precision);

            boolean noLabel = unLabeled || halfLabels && labelCount % 2 == 0 && i > min && i < max; labelCount++;
            if (!unLabeled && i >= max && !unit.isEmpty()) {
                components.add(new GridLine(unit, (int)currentXpixel, t, (int)currentXpixel, b, true));
            } else {
                components.add(new GridLine(noLabel?"":label, (int)currentXpixel, t, (int)currentXpixel, b, true));
            }
        }
    }

    protected void computeBounds() {
        // Calculate x-axis parameters
        boolean solved = false;
        // Since the solution of the calculations determines the width of the labels,
        // which in turn influences the calculations, set up a loop so that the label
        // widths are increased in length until they all fit in the axis area.
        do {
            // Figure out how many segments to divide the data into
            segment = Math.abs(roundToIdeal((max - min) * (textWidth * 2) / (r - l)));
            if (divisions != -1) segment = (abs(max) + abs(min)) / divisions;
            // Figure out a base reference for all the segments
            basePosition = zero;
            if (zero > 0) basePosition = Math.ceil(min / segment) * segment;
            else if (zero < 0) basePosition = -Math.ceil(-max / segment) * segment;
            // Figure out how many decimal places need to be shown on the labels
            precision = calculateRequiredPrecision(max, min, segment);
            // Figure out where each of the labels should be drawn
            startPosition = min;//basePosition - (Math.floor((basePosition - min) / segment) * segment);
            // Figure out the width of the largest label so we know how much room to make
            int newxTextWidth = 0;
            String lastLabel = "";
            for (double i = startPosition; i <= max; i += segment) {
                String label = formatLabelText(i, precision);
                if (label.equals(lastLabel)) precision++;
                int labelWidth = (int)(label.length() * (charWidth));
                if (labelWidth > newxTextWidth) newxTextWidth = labelWidth;
                lastLabel = label;
            }
            if (newxTextWidth <= textWidth) solved = true;
            else textWidth = newxTextWidth;
        } while (!solved);
    }
}
