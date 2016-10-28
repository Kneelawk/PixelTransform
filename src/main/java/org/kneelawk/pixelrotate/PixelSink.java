package org.kneelawk.pixelrotate;

public interface PixelSink extends PixelProvider {
	public void setPixel(double x, double y, KColor color);
}
