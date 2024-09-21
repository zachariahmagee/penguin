package zm.penguin.utils;

public enum Layout {
    VERTICAL("VERTICAL"),
    HORIZONTAL("HORIZONTAL");

    final private String description;

    Layout(String description) {
        this.description = description;
    }

    public boolean isVertical() { return this == VERTICAL; }
    public String getDescription() { return description; }

}
