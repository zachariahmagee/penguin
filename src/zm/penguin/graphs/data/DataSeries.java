package zm.penguin.graphs.data;

public interface DataSeries {
    float getMin(boolean logarithmic);
    float getMax(boolean logarithmic);
    float getXMin();
    float getXMax();
}
