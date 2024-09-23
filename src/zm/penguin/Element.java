package zm.penguin;

public abstract class Element {
    public boolean isVisible;
    public String name;

    public Element() {
        isVisible = false;
        this.name = getClass().toString();
    }

    public abstract void draw();
    public void setVisibility(boolean newState) { this.isVisible = newState; }
    public boolean isVisible() { return isVisible; }

    public abstract boolean mouseOver(int x, int y);

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public String toString() {
        return name;
    }
}
