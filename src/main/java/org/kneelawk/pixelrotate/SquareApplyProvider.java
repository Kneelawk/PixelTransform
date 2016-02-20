package org.kneelawk.pixelrotate;

public class SquareApplyProvider implements ApplyProvider {

	public PixelProvider prov;

	public double x1, y1, x2, y2;

	public SquareApplyProvider(PixelProvider prov, double x1, double y1,
			double x2, double y2) {
		this.prov = prov;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public KColor getPixel(double x, double y) {
		return prov.getPixel(x, y);
	}

	@Override
	public boolean applies(double x, double y) {
		return x >= x1 && x < x2 && y >= y1 && y < y2;
	}

}
