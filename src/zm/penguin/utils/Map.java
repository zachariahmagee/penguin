package zm.penguin.utils;

public class Map {
    // Logarithmic Mapping: maps a value logarithmically between two ranges
    public static float toLog(float value, float min, float max, float newMin, float newMax) {
        double logMin = min <= 0 ? 0 : Math.log(min);
        double logMax = Math.log(max);
        double normalizedValue = (Math.log(value) - logMin) / (logMax - logMin);
        return (float) (normalizedValue * (newMax - newMin)) + newMin;
    }

    // Logarithmic Reverse Mapping
    public static float fromLog(float value, float min, float max, float newMin, float newMax) {
        double normalizedValue = (value - min) / (max - min);
        double logMin = min <= 0 ? 0 : Math.log(newMin);
        double logMax = Math.log(newMax);
        double scale = logMin + (logMax - logMin) * normalizedValue;
        return (float) Math.exp(scale);
    }

    // Exponential Mapping: maps a value exponentially between two ranges
    public static float toExp(float value, float min, float max, float newMin, float newMax) {
        float safeMin = min <= 0 ? 0.1f : min;
        double normalizedValue = Math.log(value / safeMin) / Math.log((double) max / safeMin);
        return (float) (normalizedValue * (newMax - newMin)) + newMin;
    }

    // Exponential Reverse Mapping
    public static float fromExp(float value, float min, float max, float newMin, float newMax) {
        float safeMin = newMin <= 0 ? 1 : newMin;
        double normalizedValue = (value - min) / (max - min);
        return newMin * (float) Math.pow((double) newMax / safeMin, normalizedValue);
    }

    // Sigmoid Mapping: applies sigmoid transformation for smoother scaling
    public static float toSigmoid(float value, float min, float max, float newMin, float newMax) {
        float normalizedValue = (value - min) / (max - min);
        double sigmoidValue = 1 / (1 + Math.exp(-100 * (normalizedValue - 0.5))); // Adjust steepness with constant
        return (float) (newMin + (sigmoidValue * (newMax - newMin)));
    }

    // Sigmoid Reverse Mapping
    public static float fromSigmoid(float value, float min, float max, float newMin, float newMax) {
        float normalizedTime = (value - newMin) / (newMax - newMin);
        double inverseSigmoidValue = -Math.log((1 / normalizedTime) - 1) / 100 + 0.5; // Match steepness with constant
        return (float) (inverseSigmoidValue * (max - min)) + min;
    }
}
