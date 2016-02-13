package org.kneelawk.pixelrotate;

public class RotatedProvider implements PixelProvider {

	private PixelProvider prov;
	private float centerX, centerY;
	private float rotation;

	public RotatedProvider(PixelProvider prov, float centerX, float centerY,
			float rotation) {
		this.prov = prov;
		this.centerX = centerX;
		this.centerY = centerY;
		this.rotation = rotation;
	}

	@Override
	public KColor getPixel(double x, double y) {
		double cx = x - centerX;
		double cy = y - centerY;
		double r = Math.atan2(cy, cx);
		double o = Math.sqrt(cx * cx + cy * cy);
		r += rotation;
		double nx = Math.cos(r) * o;
		double ny = Math.sin(r) * o;
		return prov.getPixel(nx + centerX, ny + centerY);
	}

}
