package zm.penguin.graphs.utils;

public enum GraphType {
    Cartesian("Cartesian"),
    Scatter("Scatter"),
    Polar("Polar");

    final private String type;

    GraphType(String type) {
        this.type = type;
    }
}
