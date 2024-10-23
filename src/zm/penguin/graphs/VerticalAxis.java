package zm.penguin.graphs;

import zm.penguin.graphs.utils.CustomGridLine;
import zm.penguin.graphs.utils.Ticks;

import static processing.core.PApplet.*;
import static zm.penguin.styles.Style.*;

public class VerticalAxis extends Axis {
    public int base = 10;
    private Cartesian graph;

    public VerticalAxis() {}

    public VerticalAxis(Cartesian graph) {
        this(graph.gL,graph.gT,graph.gR,graph.gB,graph.minY,graph.maxY,graph.divisionsY);
        this.graph = graph;
    }

    public VerticalAxis(int l, int t, int r, int b, float min, float max, int divisions) {
       super(l,t,r,b,min,max,divisions);
    }
    public VerticalAxis(int l, int t, int r, int b, float min, float max) {
        this(l,t,r,b,min,max,-1);
    }

//    @Override
//    public void set(Cartesian graph) {
//
//    }

    @Override
    public void draw() {
        if (computeBounds) {
            components.clear();
            computeBounds();
            computeBounds = false;
        }

        setGridComponents();

        for (GridComponent g : components) {
            g.draw();
        }
    }

    protected void computeBounds() {
        if (isLog) {
            computeCustomLogGridLines();
            computeLog();
            //computeCustomLog();
        } else {
            computeCustomGridLines();
            if (isAuto) {
                computeTicks();
            } else {
                if (userSegments) {
                    computeCustom();
                } else {
                    computeLinear();
                }
            }
        }

    }

    void computeCustomGridLines() {
        if (custom.size() > 0) {
            for (CustomGridLine c : custom.gridlines) {
                final float currentYpixel = map((float)c.getValue(), min, max, b, t);
                String label = formatLabelText(c.getValue(), precision);
                GridLine gl = new GridLine(label, l, (int)currentYpixel, r, (int)currentYpixel, c.isMajor());
                if (c.isCustom()) {
                    gl.setMinorColor(c.getMajor());
                    gl.setStrokeWeight(c.getStrokeWeight());
                    gl.setCustom(true);
                }
                components.add(gl);
            }
        }
    }

    void computeCustomLogGridLines() {
        try {
            if (custom.size() > 0) {
                for (CustomGridLine c : custom.gridlines) {
                    if (c.getValue() > 0) {
                        float maxLogY = 0;
                        if (max <= 0) max = 10;
                        else logb(max, base);
                        float minLogY = 0;
                        if (min <= 0) minLogY = 0;
                        else minLogY = logb(min, base);
                        final float currentYpixel = map((float)logb(c.getValue(), base), minLogY, maxLogY, b, t);
                        String label = formatLabelText(c.getValue(), precision);
                        GridLine gl = new GridLine(label, l, (int)currentYpixel, r, (int)currentYpixel, c.isMajor());
                        if (c.isCustom()) {
                            gl.setMinorColor(c.getMajor());
                            gl.setStrokeWeight(c.getStrokeWeight());
                            gl.setCustom(true);
                        }
                        components.add(gl);
                    }
                }
            }
        } catch (Exception e) {}
    }

    void computeTicks() {

        int div_temp = 5;

        Ticks ticks = new Ticks(min, max, div_temp);

        min = (float)Math.min(min, ticks.getTick(0));
        max = (float)Math.max(max, ticks.getTick(ticks.getTickCount() - 1));

        if (graph != null) {
            graph.minY = min;
            graph.maxY = max;
        }

        segment = (max - min) / div_temp;
        if (divisions > 0) segment = (max - min) / divisions;
        minorSegment = segment; majorSegment = max - min;
        precision = calculateRequiredPrecision(max, min, segment);

        for (int i = 0; i < ticks.getTickCount(); ++i) {
            if (custom.contains((float)i)) continue;
            double tick = ticks.getTick(i);
            final float currentYpixel = map((float) tick, min, max, b, t);
            String label = formatLabelText(tick, precision);
            if (i == ticks.getTickCount() - 1 && !unit.isEmpty()) {
                components.add(new GridLine(unit, l, (int)currentYpixel, r, (int)currentYpixel, i == min));
            } else {
                components.add(new GridLine(label, l, (int)currentYpixel, r, (int)currentYpixel, tick == min || tick == 0));
            }
        }


    }

    void computeCustom() {
        float minorSegmentUsed = (float)minorSegment;
        precision = calculateRequiredPrecision(max, min, majorSegment);
        double actualMinorSpacing =  (b - t) / ((max - min) / minorSegment);

        if (actualMinorSpacing < minMinorSpacing) {
            minorSegmentUsed = 0;//(b-t) / ((max - min) * minMinorSpacing);
        } else {
            minorSegmentUsed = (float)minorSegment;
        }

        if (minorSegmentUsed != 0) {
            for (double i = min; i <= max; i += minorSegmentUsed) {
                if (custom.contains((float)i)) continue;
                if (i % majorSegment == 0) continue;
                final float currentYpixel = map((float) i, min, max, b, t);
                components.add(new GridLine(l+1, (int) currentYpixel, r, (int)currentYpixel));
            }
        }

        values.clear();
        for (double i = min; i <= max; i += majorSegment) {
            values.add((float)i);
        }

        boolean halfLabels = false;

        if ((b - t) <= values.size() * 15) halfLabels = true;
        if ((b - t) <= (values.size() * 15) / 2) {
            userSegments = false;
            computeBounds = true;
        }

        int count = 1;
        for (double i = min; i <= max; i += majorSegment) {
            if (custom.contains((float)i)) continue;
            final float currentYpixel = map((float) i, min, max, b, t);
            String label = formatLabelText(i, precision);
            boolean noLabel = halfLabels && count++ % 2 == 0 && i > min && i < max;

            if (i >= max && !unit.equals("")) {
                components.add(new GridLine(unit, l, (int)currentYpixel, r, (int)currentYpixel, true));
            } else {
                components.add(new GridLine(noLabel?"":label, l, (int)currentYpixel, r, (int)currentYpixel, true));
            }
        }
    }

    void computeLinear() {
        if (min > 0) zero = min;
        else if (max < 0) zero = max;

        // Calculate y-axis parameters
        // Figure out how many segments to divide the data into
        segment = Math.abs(roundToIdeal((max - min) * (textHeight * 2) / (b - t)));
        if (divisions != -1) segment = (abs(max) + abs(min)) / divisions;
        minorSegment = segment; majorSegment = max - min;
        values.clear();
        for (double i = min; i <= max; i += segment) {
            values.add((float)i);
        }
        boolean halfLabels = false;

        if ((b - t) <= values.size() * 15) halfLabels = true;
        if ((b - t) <= (values.size() * 15) / 2) {
            //userSegments = false;
            //divisions = -1;
            segment = Math.abs(roundToIdeal((max - min) * (textHeight * 2) / (b - t)));
            halfLabels = false;
            computeBounds = true;
        }
        // Figure out a base reference for all the segments
        basePosition = zero;
        if (zero > 0) basePosition = Math.ceil(min / segment) * segment;
        else if (zero < 0) basePosition = -Math.ceil(-max / segment) * segment;
        // Figure out how many decimal places need to be shown on the labels
        precision = calculateRequiredPrecision(max, min, segment);
        // Figure out where each of the labels should be drawn
        startPosition = basePosition - (Math.floor((basePosition - min) / segment) * segment);
        count = 1;
        if (divisions != -1) startPosition = min;
        for (double i = startPosition; i <= max; i += segment) {
            //println(i);
            if (custom.contains((float)i)) continue;
            final float currentYpixel = map((float) i, min, max, b, t);

            String label = formatLabelText(i, precision);
            boolean noLabel = halfLabels && count++ % 2 == 0 && i > min && i < max;

            if (i >= max && !unit.isEmpty()) {
                components.add(new GridLine(unit, l, (int)currentYpixel, r, (int)currentYpixel, i == min));
            } else {
                components.add(new GridLine(noLabel?"":label, l, (int)currentYpixel, r, (int)currentYpixel, i == min));
            }
        }
        //components.add(new GridLine(l, (int)map(max, min, max, b, t), r, (int)map(max, min, max, b, t), true));
        if (divisions == -1) components.add(new GridLine(l, (int)map(zero, min, max, b, t), r, (int)map(zero, min, max, b, t), true));
        components.add(new GridLine(l, (int)map(min, min, max, b, t), r, (int)map(min, min, max, b, t), true));
    }

    void computeLog() {
        try {
            base = 10;
            //if (min < 0) min = 0.1;
            if (min > 0) zero = min;
            else if (max < 0) zero = max;

            segment = Math.abs(roundToIdeal((max - min) * (textHeight * 2) / (b - t)));

            // Figure out a base reference for all the segments
            basePosition = zero;
            if (zero > 0) basePosition = Math.ceil(min / segment) * segment;
            else if (zero < 0) basePosition = -Math.ceil(-max / segment) * segment;

            float maxLogY = logb(max, base);
            float minLogY = logb(min, base);

            segment = (double) Math.pow(base, Math.floor(logb((max - min), base)));
            basePosition = Math.pow(base, Math.floor(min));
            // Figure out how many decimal places need to be shown on the labels
            // Figure out where each of the labels should be drawn
            startPosition = basePosition - (Math.floor((basePosition - min) / segment) * segment);
            startPosition = 10;

            int minPower = (int) Math.floor(minLogY);

            int currentPower = minPower;
            // Calculate the maximum power of the base  within the range
            int maxPower = (int) Math.ceil(maxLogY);

            // Calculate the number of powers of the base within the range
            int numPowers = maxPower - minPower;

            // Calculate the y_segment for consistency
            segment = 0;
            boolean hitMax = false;
            for (double i = min; i <= max; i += segment) {
                //println(segment);
                final float currentYpixel;
                float map_i = 0;

                if (i == 0) map_i = 0;
                else map_i = logb((float)i, base);

                currentYpixel = map(map_i, minLogY, (float)maxLogY, b, t);

                String label = formatLabelText(i, 0);

                components.add(new GridLine(label, l, (int)currentYpixel, r, (int)currentYpixel, true));

                if (i == max) hitMax = true;

                float y_section = (float) (b - t) / numPowers;

                float lower = (float)Math.pow(base, currentPower);
                float upper = (float)Math.pow(base, currentPower + 1);
                for (float j = lower + lower; j < upper; j += lower) {
                    if (j > min && j < max) {
                        float j_map = logb(j, base);
                        float minorYLine = map(j_map, (float)minLogY, (float)maxLogY, b, t);
                        String minorLabel = formatLabelText(j, 1);
                        components.add(new GridLine(spaceForLogLabel(abs(j), y_section)?minorLabel:"", l, (int)minorYLine, r, (int)minorYLine, small_font));
                    }
                }
                segment = (float)(Math.pow(base, ++currentPower) - i);
            }
            if (!hitMax) {
                final float currentYpixel = map(logb(max, base), minLogY, (float)maxLogY, b, t);
                String label = formatLabelText(max, 1);
                components.add(new GridLine(label, l, (int)currentYpixel, r, (int)currentYpixel, true));
            }
            components.add(new GridLine(l, (int)map(zero, min, max, b, t), r, (int)map(zero, min, max, b, t), true));
        } catch (Exception e) {
            println(this, "(computeLog)", e);
        }
    }

    void computeCustomLog() {

    }
    // TODO: Test this function
    boolean spaceForLogLabel(float num, float segment) {
        if (segment >= 168) return true;

        while (num < 1) {
            num *= this.base;
        }
        num = floor(num);

        while (num % this.base == 0 && num != 0) {
            num /= this.base;
        }

        if (segment < 168 && segment > 150) return num != 9;
        else if (segment <= 150 && segment > 120) return !(num == 9 || num == 7);
        else if (segment <= 120 && segment > 100) return num % 2 == 0 || num == 3;
        else if (segment <= 100 && segment > 75) return (num % 2 == 0 && num != 8);// maybe ignore this one...
        else if (segment <= 75 && segment > 45) return (num == 3 || num == 6);
        else if (segment <= 45 && segment > 32) return num == 5;
        return false;
    }
}
