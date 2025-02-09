package zm.penguin.containers;


import zm.penguin.components.Component;

/**
 * Not sure if i'll keep this class as it could probably be a "row" instead.
 * The main difference being that ButtonRow is not scrollable
 * It can contain any component, not just buttons - so a name change is probably
 * necessary regardless.
 * **/
public class ButtonRow<T extends Component>  extends ButtonPanel<T> {
    public ButtonRow(int l, int t, int w, int h) {
        super(l, t, w, h);
        this.spacing = 5;
    }

    @Override
    public void setLocation(int x, int y) {
        this.l = x;
        this.t = y;
        this.r = this.l + this.w;
        this.b = this.t + this.h;

        int prevWidth = offsetX;
        for (Component c : components) {
            c.setLocation(l + spacing + prevWidth, t + h/2 - c.h / 2);
            prevWidth += c.w + spacing;
        }
    }

    @Override
    public String toString() {
        return "ButtonRow";
    }
}
