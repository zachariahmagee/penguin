package zm.penguin.graphs.displays;

import zm.penguin.components.Component;
import zm.penguin.containers.Content;
import zm.penguin.graphs.Graph;

public abstract class DisplayMode<G extends Graph> extends Content<Component> {
    G graph;

    int cL, cT, cR, cB;
    public int trace = 0;
    public int sw, sh, iw, ih;
    boolean receivedCommand, noLegend, clearHistory, initialized;

    int prevLoc = 0;

    public DisplayMode(G graph, int l, int r, int t, int b) {
        setLocation(l, r, b-t, r-l);
        this.graph = graph;
        cT = graph.gT;
        cB = graph.gB;
        cL = graph.gL;
        cR = graph.gR;

        this.sw = cR - cL;
        this.sh = cB - cT;
        iw = sw; ih = sh;
        if (iw < 500) iw = 500;
        if (ih < 200) ih = 200;
    }

    public DisplayMode(G graph) {
        this(graph, graph.l, graph.r, graph.t, graph.b);
    }

    abstract void update();

    public void clearHistory() { clearHistory(false); }
    abstract void clearHistory(boolean clear);

    public void setTrace(int trace) {
        this.trace = trace;
        receivedCommand = true;
    }

    @Override
    public void signalThemeChange(int theme) {
        clearHistory(false);
    }
}
