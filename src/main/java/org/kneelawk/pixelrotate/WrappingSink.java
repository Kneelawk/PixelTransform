package org.kneelawk.pixelrotate;

public class WrappingSink implements PixelSink {
	private PixelSink sink;
	private double minX, minY, maxX, maxY;

	public WrappingSink(PixelSink sink, double minX, double minY, double maxX,
			double maxY) {
		this.sink = sink;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	@Override
	public void setPixel(double x, double y, KColor color) {
		if (x < minX || x >= maxX) {
			x = MathUtils.mod2(x, minX, maxX);
		}
		if (y < minY || y >= maxY) {
			y = MathUtils.mod2(y, minY, maxY);
		}

		sink.setPixel(x, y, color);
	}

	@Override
	public KColor getPixel(double x, double y) {
		return sink.getPixel(x, y);
	}
}
