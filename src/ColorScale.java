import java.awt.Color;

public class ColorScale {

    private final ColorPoint first;
    private final ColorPoint second;

    public ColorScale(ColorPoint first, ColorPoint second) {
        this.first = first;
        this.second = second;
    }

    public Color getPoint(int value) {
        if (value < first.value){
            return first.color;
        } else if (value > second.value){
            return second.color;
        } else {
            int R = interpolation(first.value, first.color.getRed(), second.value, second.color.getRed(), value);
            int G = interpolation(first.value, first.color.getGreen(), second.value, second.color.getGreen(), value);
            int B = interpolation(first.value, first.color.getBlue(), second.value, second.color.getBlue(), value);
            return new Color(R, G, B);
        }
    }

    public static int interpolation(int mX, int mY, int MX, int MY, int value) {
        double slope = ((double) (MY - mY)) / ((double) (MX - mX));
        return (int) Math.round(slope * value - slope * mX + mY);
    }
}
