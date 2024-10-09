package zm.penguin.interactions;

public interface Scrollable extends Lockable {
    void scrollWheel(float amount);
    void scrollbarUpdate(int x, int y);
}
