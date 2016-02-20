package org.kneelawk.pixelrotate;

public class ClampingProvider implements PixelProvider {

	public PixelProvider prov;

	/**
	 * Min and max are inclusive.
	 */
	public double minX, minY, maxX, maxY;

	/**
	 * Min and max are inclusive.
	 */
	public ClampingProvider(PixelProvider prov, double minX, double minY,
			double maxX, double maxY) {
		this.prov = prov;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public KColor getPixel(double x, double y) {
		if (x < minX)
			x = minX;
		if (y < minY)
			y = minY;
		if (x > maxX)
			x = maxX;
		if (y > maxY)
			y = maxY;
		return prov.getPixel(x, y);
	}
}
