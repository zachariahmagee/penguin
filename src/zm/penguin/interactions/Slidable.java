package zm.penguin.interactions;

import zm.penguin.components.Component;

import java.util.function.Consumer;

public abstract class Slidable<T> extends Component implements Lockable {
    public boolean locked = false, horizontal = true;
    protected float cx, cy;
    protected int slider_left, slider_right, space;
    public Consumer<T> consumer;

    @Override
    public boolean locked() {
        return locked;
    }

    @Override
    public void mouseReleased(int x, int y) {
        this.locked = false;
        super.mouseReleased(x,y);
    }

    @Override
    public boolean mouseOver(int x, int y) {
        return ((x >= (cx - space)) &&
                (x <= (cx + space)) &&
                (y >= (cy - space)) &&
                (y <= (cy + space)));
    }
}

