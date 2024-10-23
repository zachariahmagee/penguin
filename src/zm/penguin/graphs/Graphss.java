package zm.penguin.graphs;

import zm.penguin.containers.Container;
import zm.penguin.graphs.components.Labels;
import zm.penguin.graphs.components.PlotLabel;
import zm.penguin.graphs.utils.GraphType;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import static processing.core.PConstants.CORNERS;
import static zm.penguin.styles.Theme.background;
import static zm.penguin.styles.Theme.red;

public class Graphss extends Container<Graph> {
    boolean resizeGraphs;
    int selectedGraph = 0;
    int numberOfGraphs = 1;
    int MAX_NUM_OF_GRAPHS = 32;
    GraphType graphType = GraphType.Cartesian;
    int count = 0;

    boolean singleLegend;
    Labels labels;
    PlotLabel plotTitle;

    float newXminimum, newXmaximum;

    Set<Integer> spectrogramTraces, gradientTraces;

    boolean customTraces;


    public Graphss(int l, int t, int r, int b) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
        this.w = r - l;
        this.h = b - t;
//        graphType = GraphType.Cartesian;
//        threshold = new Threshold();
        labels = new Labels(l, r, b-30, b);
        plotTitle = new PlotLabel("", l, b-30);
        labels.add(plotTitle);
        labels.setVisibility(true);
        plotTitle.setVisibility(true);
        components = new CopyOnWriteArrayList<Graph>();

        spectrogramTraces = new HashSet<>();
        gradientTraces = new HashSet<>();

        //info = new ArrayList<GraphInfo>();
    }

    void setNumberOfGraphs(int num) {
        if (num > 0 && num <= MAX_NUM_OF_GRAPHS && num != numberOfGraphs) {
            this.numberOfGraphs = num;
            //resizeGraphs = true;
            resizeGraphs();
        }
    }

    void resizeGraphs() {
        if (graphType == GraphType.Polar) {// || get(0) instanceof PolarGraph) {// || numberOfGraphs == 1) {
            components.subList(1, size()).clear();
            components.getFirst().changeSize(l, r, t, b-labels.h);
            return;
        }

        int notVisible = 0;

        for (Graph g : components) {
            if (!g.isVisible()) notVisible++;
            if (size() - notVisible == 0) return;
        }
        if (size() - notVisible == 0) return;
        int graphsTop = t + 30;
        int graphsBottom = singleLegend ? b - 30 : b;
        int graphHeight = (graphsBottom - graphsTop) / (numberOfGraphs - notVisible) - (singleLegend ? 0 : (30 * notVisible));

        if (graphHeight <= 60) graphHeight = 60;
        int top = graphsTop; // Initialize
        int bottom = graphsTop + graphHeight; // Initialize bottom just above the bottom
        for (int i = 0; i < numberOfGraphs; i++) {
            if (components.size() <= i) {
                if (graphType == GraphType.Cartesian) {
                    components.add(new CartesianGraph(i, l, r, top, bottom, 0, 500, 0, 100));
//                    primaryAxisCounter(i, true);
                } else if (graphType == GraphType.Scatter) {
                    components.add(new ScatterGraph(i, l, r, top, bottom, 0, 500, 0, 100));
                }
            } else {
                components.get(i).changeSize(l, r, top, get(i).isVisible ? bottom : (top + (singleLegend ? 0 : 30)));
            }
            if (i != components.get(i).id) components.get(i).id = i;
//            if (graphType == GraphType.Cartesian && i != numberOfGraphs - 1) components.get(i).setUnlabeledX(true);
            top += get(i).isVisible() ? graphHeight : singleLegend ? 0 : 30;
            bottom += get(i).isVisible() ? graphHeight : singleLegend ? 0 : 30;
        }
        if (size() > numberOfGraphs) {
            components.subList(numberOfGraphs, size()).clear();
        }
    }

    void removeExcessGraphs() {
        components.subList(numberOfGraphs, size()).clear();
    }


    @Override
    public void draw() {

    }

    void clearGraphsArea() {
        app.rectMode(CORNERS);
        app.noStroke();
        app.stroke(red);
        app.fill(background);
        app.rect(l, t, r, b);
    }

    void drawLabels() {
        app.rectMode(CORNERS);
        app.noStroke();
        app.fill(background);
        app.rect(l, b-labels.h, r, b);
        labels.draw();
    }

    @Override
    public String toString() {
        return "Graphs: ";
    }
}
