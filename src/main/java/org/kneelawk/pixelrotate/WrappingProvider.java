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
			x = MathUtils.mod2(x, minX, maxX);
		if (y < minY || y >= maxY)
			y = MathUtils.mod2(x, minY, maxY);
		return prov.getPixel(x, y);
	}
}
