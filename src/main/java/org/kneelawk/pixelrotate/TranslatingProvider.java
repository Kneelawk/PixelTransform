package org.kneelawk.pixelrotate;

public class TranslatingProvider implements PixelProvider {

	public PixelProvider prov;

	public double offsetX, offsetY;

	public TranslatingProvider(PixelProvider prov, double offsetX,
			double offsetY) {
		this.prov = prov;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	@Override
	public KColor getPixel(double x, double y) {
		return prov.getPixel(x + offsetX, y + offsetY);
	}

}
