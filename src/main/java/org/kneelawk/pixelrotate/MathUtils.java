package org.kneelawk.pixelrotate;

public class MathUtils {
	public static double mod2(double value, double min, double max) {
		// size is the distance between min and max.
		double size = max - min;
		if (size == 0)
			throw new IllegalArgumentException(
					"min and max can not have the same value");
		if (size < 0)
			throw new IllegalArgumentException(
					"min can not have a value grater than max");
		if (value < min || value >= max) {
			// quot is the distance between the value and the min all divided by
			// the size. This means that quot is the ratio of the distance
			// between value and min to the size.
			double quot = (value - min) / size;
			// Remove the integer part of the ratio. This only leaves the
			// fractional part of the ratio. This would be the same as
			// subtracting enough multiples of size to put value within the
			// range.
			quot -= Math.floor(quot);
			// Multiply quot by the size and add the min, because quot is now
			// the ratio of the value inside the boundaries to the size.
			value = (size * quot) + min;
		}
		return value;
	}
}
