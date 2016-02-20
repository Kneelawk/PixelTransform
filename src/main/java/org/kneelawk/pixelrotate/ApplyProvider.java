package org.kneelawk.pixelrotate;

public interface ApplyProvider extends PixelProvider {
	public abstract boolean applies(double x, double y);
}
