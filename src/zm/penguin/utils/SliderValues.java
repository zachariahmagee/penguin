package zm.penguin.utils;

public class SliderValues {
    public float min, max, current;
    public DisplayValues display;

    public SliderValues(float min, float max, float current) {
        this(min,max,current, new DisplayValues(0, 1, 1, 2));
    }

    public SliderValues(float min, float max, float current, int f) {
        this.min = min;
        this.max = max;
        this.current = current;
        this.display = new DisplayValues(min, max, f, f);
    }
    public SliderValues(float min, float max, float current, DisplayValues display) {
        this.min = min;
        this.max = max;
        this.current = current;
        this.display = display;
    }
}

