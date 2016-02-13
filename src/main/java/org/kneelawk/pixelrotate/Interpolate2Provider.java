package org.kneelawk.pixelrotate;

public class Interpolate2Provider implements PixelProvider {

	private PixelProvider prov;
	private double ratio;
	private double nratio;

	public Interpolate2Provider(PixelProvider prov, double ratio) {
		this.prov = prov;
		this.ratio = ratio;
		this.nratio = 1.0 - ratio;
	}

	@Override
	public KColor getPixel(double x, double y) {
		long cornX = Math.round(x);
		long cornY = Math.round(y);
		KColor exactly = prov.getPixel(x, y);
		KColor colPXPY = prov.getPixel(cornX + 0.5f, cornY + 0.5f);
		KColor colPXMY = prov.getPixel(cornX + 0.5f, cornY - 0.5f);
		KColor colMXPY = prov.getPixel(cornX - 0.5f, cornY + 0.5f);
		KColor colMXMY = prov.getPixel(cornX - 0.5f, cornY - 0.5f);
		double offX = x - cornX;
		double offY = y - cornY;
		double sx = offX + 0.5;
		double sy = offY + 0.5;
		double nsx = 1 - sx;
		double nsy = 1 - sy;
		// offset PXPY
		double mulPXPY = sx * sy;
		// offset PXMY
		double mulPXMY = sx * nsy;
		// offset MXPY
		double mulMXPY = nsx * sy;
		// offset MXMY
		double mulMXMY = nsx * nsy;

		int r = (int) ((((colPXPY.getRedI() * mulPXPY)
				+ (colPXMY.getRedI() * mulPXMY) + (colMXPY.getRedI() * mulMXPY) + (colMXMY
				.getRedI() * mulMXMY)) * ratio) + (exactly.getRedI() * nratio));
		int g = (int) ((((colPXPY.getGreenI() * mulPXPY)
				+ (colPXMY.getGreenI() * mulPXMY)
				+ (colMXPY.getGreenI() * mulMXPY) + (colMXMY.getGreenI() * mulMXMY)) * ratio) + (exactly
				.getGreenI() * nratio));
		int b = (int) ((((colPXPY.getBlueI() * mulPXPY)
				+ (colPXMY.getBlueI() * mulPXMY)
				+ (colMXPY.getBlueI() * mulMXPY) + (colMXMY.getBlueI() * mulMXMY)) * ratio) + (exactly
				.getBlueI() * nratio));
		int a = (int) ((((colPXPY.getAlphaI() * mulPXPY)
				+ (colPXMY.getAlphaI() * mulPXMY)
				+ (colMXPY.getAlphaI() * mulMXPY) + (colMXMY.getAlphaI() * mulMXMY)) * ratio) + (exactly
				.getAlphaI() * nratio));
		KColor color = new KColor(r, g, b, a);
		return color;
	}
}
