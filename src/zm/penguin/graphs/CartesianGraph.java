package zm.penguin.graphs;

import zm.penguin.graphs.utils.CustomGridLine;

import static processing.core.PApplet.*;

import static processing.core.PConstants.*;
import static zm.penguin.styles.Style.base_font;
import static zm.penguin.styles.Theme.*;

public class CartesianGraph extends Cartesian {
    boolean userDefinedBounds = false;
    float budmin, budmax;
    float userDefinedMin = 0;
    float userDefinedMax = 0;

    public CartesianGraph(int id, int l, int r, int t, int b, float minx, float maxx, float miny, float maxy) {
        super(id, l, r, t, b, minx, maxx, miny, maxy);
        x = new HorizontalAxis(this);
    }

    @Override
    public void draw() {
        if (!isVisible && labels.isVisible()) {
            labels.draw();
            return;
        }
        if (!isVisible) return;

//        clearGraphArea();

//        if (spectr != null && spectr.isVisible()) {
//            spectr.draw();
//            scm.draw();
//        }
//        if (persistence != null && persistence.isVisible()) {
//            persistence.draw();
//        }
//
//        if (gradient != null && gradient.isVisible()) {
//            gradient.draw();
//        }

        drawGrid();
    }


    @Override
    void drawGrid() {
        try {
            graphics.textFont(base_font);
            final int padding = 5;
            final int textHeight = (int)(graphics.textAscent() + graphics.textDescent() + padding);
            final float charWidth = 9;//graphics.textWidth("0");

            // Define graph top and bottom
            gT = t + 5;
            gB = !labels.isVisible() ? b - textHeight - graphMark : b - textHeight * 2 - graphMark - 10;
            if (xAxisLabel != null && xAxisLabel.isVisible()) gB -= 5;

            gL = (int)(l + border + (charWidth * 5) + graphMark + padding);

            gR = r - 30; //app.width - 30;

//            Markers markers = (Markers) markersList.get(0);
//
//            markers.setGraphPosition(gL,gT,gR,gB);
//            markerDisplay.setGraphPosition(gL,gT,gR,gB);

            if (highlight())drawHighlight();

            // Add border and graph title
            graphics.strokeWeight(1);
            graphics.stroke(s);

            graphics.textAlign(LEFT, TOP);
            graphics.textFont(base_font);
            graphics.fill(lightgrey);

            if (y == null) {
                y = new VerticalAxis(gL,gT,gR,gB,minY,maxY);
            } else {
                if (ySeg) y.setSegments(yMinSeg,yMajSeg);
                y.setLocationMinMax(gL,gT,gR,gB,minY,maxY);
            }
            y.draw();
            if (x == null) {
                x = new HorizontalAxis(gL, gT, gR, gB, minX, maxX);
            } else {
                if (userDefinedBounds) {
                    HorizontalAxis xa = (HorizontalAxis) x;
                    xa.setUserDefinedBounds(userDefinedBounds);
                    xa.set(gL,gT,gR,gB,userDefinedMin,userDefinedMax,divisionsX,primaryUnits);
                } else {
                    x.setLocationMinMax(gL, gT, gR, gB, minX, maxX);
                }
            }
            x.draw();
//            if (threshold != null) {
//                if (!threshold.moved) {
//                    float mid = (y.max - y.min) / 2;
//                    if (mid > y.max && y.min < 0) mid = -mid;
//                    threshold.moveThreshold(mid, gL,gT,gR,gB);
//                }
//                threshold.setGraphPosition(gL,gT,gR,gB);
//                threshold.setMinMaxY(y.min,y.max);
//            }
//            spectr.setMinMaxY(y.min,y.max);
            if (xAxisLabel.isVisible()) xAxisLabel.draw();
            if (yAxisLabel.isVisible()) yAxisLabel.draw();
            if (labels.isVisible()) labels.draw();
//            if (threshold != null && markersList.peaksearch) {
//                threshold.setVisibility(true);
//                threshold.draw();
//            }
//            if (roi.isVisible()) roi.draw();
            //drawGraphOutline();
            //drawContentOutline();
        } catch (Exception e) {
            println("Cartesian Grid Error: ", e);
        }
    }

    @Override
    public void clearGraph() {
        if (!isVisible && labels.isVisible()) {
            labels.draw();
            return;
        }
        if (!isVisible) return;
        // Clear the content area
        // TODO: decide if i should be clearing the graph in the graph or in the application
        clearGraphArea();
        if (highlight())drawHighlight();
//        if (spectr != null && spectr.isVisible()) {
//            if (drawNewData && spectr.addRow())  {
//                spectr.update();
//            }
//            spectr.draw();
//            scm.draw();
//            spectr.setMinMaxY(y.min,y.max);
//        }
//        if (persistence != null && persistence.isVisible()) {
//            persistence.draw();
//        }
//        if (gradient != null && gradient.isVisible()) {
//            gradient.draw();
//        }
        // Setup drawing parameters
        graphics.strokeWeight(1);
        graphics.stroke(s);
        x.draw();
        y.draw();
        if (labels.isVisible()) labels.draw();
        if (xAxisLabel.isVisible()) xAxisLabel.draw();
        if (yAxisLabel.isVisible()) yAxisLabel.draw();
//        if (threshold != null && markersList.peaksearch) {
//            if (!threshold.moved) {
//                float mid = (maxY - minY) / 2;
//                if (mid > maxY && minY < 0) mid = -mid;
//                threshold.moveThreshold(mid, gL,gT,gR,gB);
//            }
//            threshold.setVisibility(true);
//            threshold.setGraphPosition(gL,gT,gR,gB);
//            threshold.setMinMaxY(minY,maxY);
//
//            threshold.draw();
//        }
//        if (roi.isVisible()) roi.draw();
        // Clear all previous data positions
        resetGraph();
        //drawGraphOutline();
        //drawContentOutline();
//        if (spectr != null && spectr.isVisible() && scm.isVisible()) scm.draw();
    }

    @Override
    public void updateBefore() {

    }

    @Override
    public void updateAfter() {

    }

    @Override
    public void plotData(float dataY, float dataX, int id, int traceColor) {
        try {
            if (validFloat(dataY) && validFloat(dataX)) {
                minY = y.min;
                maxY = y.max;

                int x1, y1, x2 = gL, y2;

                while(lastY.length < id + 1) lastY = append(lastY, -99999999);
                while(lastX.length < id + 1) lastX = append(lastX, 0);

                // Bound the Y-axis data
                if (dataY > maxY)  dataY = maxY;
                if (dataY < minY)  dataY = minY;

                // Bound the X-axis
                if (dataX > maxX) dataX = maxX;
                if (dataX < minX) dataX = minX;
                // Set colours
                //graphics.fill(traceColor);
                app.fill(traceColor);
                app.stroke(traceColor);
                app.strokeWeight(2);
                // Only draw line if last value is set
                if (lastY[id] != -99999999) {

                    if (isLogarithmic) {
                        if (dataY <= 0) dataY = minY;
                        x1 = round(map(lastX[id], minX, maxX, gL, gR));
                        x2 = round(map(dataX, minX, maxX, gL, gR));
                        y1 = round(map(y.logb(lastY[id], base), y.logb(minY, base), y.logb(maxY, base), gB, gT));
                        y2 = round(map(y.logb(dataY, base), y.logb(minY, base), y.logb(maxY, base), gB, gT));

                        if (dataX != 0) graphics.line(x1, y1, x2, y2);

                    } else {
                        // Determine x and y coordinates
                        x1 = round(map(lastX[id],minX, maxX, gL, gR));
                        x2 = round(map(dataX, minX, maxX, gL, gR));
                        y1 = round(map(lastY[id], minY, maxY, gB, gT));
                        y2 = round(map(dataY, minY, maxY, gB, gT));

                        if (dataX > 1)  graphics.line(x1, y1, x2, y2);

//                        if (persistence != null && persistence.isVisible() && (persistence.trace == id || persistence.trace == -1)) {
//                            //persistence.addPoint(x1, y1, x2, y2, traceColor);
//                            persistence.addValue(dataY, lastY[id], dataX, lastX[id], traceColor);
//                        }
                    }
                }
                lastY[id] = dataY;
                lastX[id] = dataX;
            }
        } catch (Exception e) {
           println(this, e);
        }
    }

    @Override
    public void drawMarker(float dataY, float dataX, int id, int c) {

    }

    void setUserDefinedBounds(boolean userDefined) {
        userDefinedBounds = userDefined;
        if (x != null) {
            HorizontalAxis xa = (HorizontalAxis) x;
            xa.setUserDefinedBounds(userDefined);
        }
    }

    @Override
    public void setAxisRange(String axis, float min, float max) {
        switch(axis) {
            case "p":
                if (!zoom_in) {
                    this.bminx = min;
                    this.bmaxx = max;
                }
                this.minX = min;
                this.maxX = max;
                break;
            case "x":
                if (!zoom_in) {
                    this.budmin = min;
                    this.budmax = max;
                }
                this.userDefinedBounds = true;
                this.userDefinedMin = min;
                this.userDefinedMax = max;
                //this.minX = min;
                //this.maxX = max;
                if (x != null) {
                    HorizontalAxis xa = (HorizontalAxis) x;
                    xa.setUserDefinedBounds(userDefinedBounds);
                    xa.setMinMax(userDefinedMin,userDefinedMax);
                    //x.setMinMax(minX,maxX);
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
                    y.setMinMax(minY,maxY);
                    y.setAutoAxis(false);
                    autoAxis = -1;
                }
                //println(minY);
//                if (spectr != null && spectr.isVisible()) spectr.setMinMaxY(minY,maxY);
                break;
            default:
                break;
        }
    }

    @Override
    void clearGraphArea() {
        // Clear the content area
        graphics.rectMode(CORNERS);
        graphics.noStroke();
        graphics.fill(background);
        if (id == 0) graphics.rect(l, 0, r, b);
        else graphics.rect(l, t, r, b);
    }

    @Override
    public void changeWindowSize(int newW, int newH) {

    }

    void primaryAxisCounter(boolean reset) {
        if (x != null) {
            if (reset) {
                x.resetCount();
            } else {
                x.count();
            }
        }
    }

    void setPrimaryAxisCounter(int count) {
        if (x != null) x.setCount(count);
    }


}
