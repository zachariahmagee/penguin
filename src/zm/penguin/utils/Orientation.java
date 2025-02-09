package zm.penguin.utils;

public enum Orientation {
    VERTICAL("VERTICAL"),
    HORIZONTAL("HORIZONTAL");

    final private String description;

    Orientation(String description) {
        this.description = description;
    }

    public boolean isVertical() { return this == VERTICAL; }
    public String getDescription() { return description; }

}
