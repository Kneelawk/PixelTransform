package org.kneelawk.pixelrotate;

public class WrappingProvider implements PixelProvider {

	public PixelProvider prov;

	/**
	 * Min is inclusive, max is exclusive.
	 */
	public double minX, minY, maxX, maxY;

	/**
	 * Min is inclusive, max is exclusive.
	 */
	public WrappingProvider(PixelProvider prov, double minX, double minY,
			double maxX, double maxY) {
		this.prov = prov;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public KColor getPixel(double x, double y) {
		if (x < minX || x >= maxX)
			x = mod2(x, minX, maxX);
		if (y < minY || y >= maxY)
			y = mod2(x, minY, maxY);
		return prov.getPixel(x, y);
	}

	public static double mod2(double value, double min, double max) {
		double size = max - min;
		if (size == 0)
			throw new IllegalArgumentException(
					"min and max can not have the same value");
		if (size < 0)
			throw new IllegalArgumentException(
					"min can not have a value grater than max");
		if (value < min || value >= max) {
			double quot = (value - min) / size;
			quot -= Math.floor(quot);
			value = (size * quot) + min;
		}
		return value;
	}
}
