package zm.penguin.utils;

public class DisplayValues {
    public float min, max, step;
    public int f = 0, fc = 0;

    public DisplayValues(float min, float max) {
        this(min, max, 0);
    }

    public DisplayValues(float min, float max, int f) {
        this(min, max, f, f);
    }

    public DisplayValues(float min, float max, int f, int fc) {
        this.min = min;
        this.max = max;
        this.f = f;
        this.fc = fc;
        if (fc == 0) step = 1f;
        if (fc == 1) step = 0.1f;
        if (fc == 2) step = 0.01f;
    }

    public void setStep(int step) {
        this.step = step;
    }

    static public String getDisplayText(float num, int f) {
        // TODO: Ensure f is not negative
        String format = String.format("%%.%df", f);
        return String.format(java.util.Locale.US, format ,num);
    }
}
