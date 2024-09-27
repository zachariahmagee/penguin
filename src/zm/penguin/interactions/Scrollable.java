package zm.penguin.interactions;

public interface Scrollable {
    void scrollWheel(float amount);
    void scrollbarUpdate(int x, int y);
    boolean locked();
}
