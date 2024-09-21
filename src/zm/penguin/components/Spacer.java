package zm.penguin.components;

public class Spacer extends Component {
    Spacer() {
        this.h = 15;
        this.w = 120;
    }

    Spacer(int h) {
        this.h = h;
        this.w = 120;
    }

    Spacer(int h, int w) {
        this.h = h;
        this.w = w;
    }

    public void draw() {

    }

    public String toString() {
        return "Spacer: " + w + "x" + h;
    }
}
