package zm.penguin.graphs;

import zm.penguin.graphs.utils.Ticks;

import static processing.core.PApplet.map;
import static zm.penguin.styles.Style.*;

public class HorizontalScatter extends Horizontal {

    @Override
    public void draw() {
        if (computeBounds) {
            computeBounds(); /*computeBounds = false;*/
        }
        components.clear();

        if (min > 0) zero = min;
        else if (max < 0) zero = max;

        if (isAuto) {
            computeTicks();
        } else {
            if (userSegments) {
                computeCustom();
            } else {
                computeAuto();
            }
        }
        computeCustomGridLines();
        // The outer grid axes
        app.textFont(base_font);
        setGridComponents();

        for (GridComponent g : components) {
            g.draw();
        }
    }

    void computeTicks() {
        Ticks ticks = new Ticks(min, max, 5);
        min = (float)Math.min(min, ticks.getTick(0));
        max = (float)Math.max(max, ticks.getTick(ticks.getTickCount() - 1));
        segment = (max - min) / 5;
        precision = calculateRequiredPrecision(max, min, segment);
        minorSegment = segment; majorSegment = max - min;

        for (int i = 0; i < ticks.getTickCount(); ++i) {
            if (custom.contains((float)i)) continue;
            double tick = ticks.getTick(i);
            final float currentXpixel = map((float) tick, min, max, l, r);
            String label = formatLabelText(tick, precision);
            if (i == ticks.getTickCount() - 1 && !unit.isEmpty()) {
                components.add(new GridLine(unit, (int)currentXpixel, t, (int)currentXpixel, b, i == min));
            } else {
                components.add(new GridLine(unLabeled?"":label, (int)currentXpixel, t, (int)currentXpixel, b, tick == min || tick == 0));
            }
        }


    }

}
