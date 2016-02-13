package org.kneelawk.pixelrotate;

public class KColor {

	public static final int BYTES_PER_PIXEL = 4;
	public static final int BYTES_PER_COLOR = 1;

	public int value = 0;

	public KColor(int red, int green, int blue, int alpha) {
		value = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16)
				| ((green & 0xFF) << 8) | (blue & 0xFF);
	}

	public KColor(int red, int green, int blue) {
		this(red, green, blue, 255);
	}

	public KColor(int gray, int alpha) {
		this(gray, gray, gray, alpha);
	}

	public KColor(int value) {
		this.value = value;
	}

	public byte getRed() {
		return (byte) ((value >> 16) & 0xFF);
	}

	public int getRedI() {
		return ((value >> 16) & 0xFF);
	}

	public byte getGreen() {
		return (byte) ((value >> 8) & 0xFF);
	}

	public int getGreenI() {
		return ((value >> 8) & 0xFF);
	}

	public byte getBlue() {
		return (byte) (value & 0xFF);
	}

	public int getBlueI() {
		return (value & 0xFF);
	}

	public byte getAlpha() {
		return (byte) ((value >> 24) & 0xFF);
	}

	public int getAlphaI() {
		return ((value >> 24) & 0xFF);
	}

	@Override
	public String toString() {
		return "KColor[r: " + getRedI() + ", g: " + getGreenI() + ", b: "
				+ getBlueI() + ", a: " + getAlphaI() + "]";
	}

	public static KColor fromGray(int gray) {
		return new KColor(gray, 255);
	}

	public static KColor fromHSB(double hue, double saturation,
			double brightness) {
		int r = 0, g = 0, b = 0;
		if (saturation == 0) {
			r = g = b = (int) (brightness * 255.0 + 0.5);
		} else {
			double h = (hue - Math.floor(hue)) * 6.0f;
			double f = h - java.lang.Math.floor(h);
			double p = brightness * (1.0f - saturation);
			double q = brightness * (1.0f - saturation * f);
			double t = brightness * (1.0f - (saturation * (1.0f - f)));
			switch ((int) h) {
			case 0:
				r = (int) (brightness * 255.0f + 0.5f);
				g = (int) (t * 255.0f + 0.5f);
				b = (int) (p * 255.0f + 0.5f);
				break;
			case 1:
				r = (int) (q * 255.0f + 0.5f);
				g = (int) (brightness * 255.0f + 0.5f);
				b = (int) (p * 255.0f + 0.5f);
				break;
			case 2:
				r = (int) (p * 255.0f + 0.5f);
				g = (int) (brightness * 255.0f + 0.5f);
				b = (int) (t * 255.0f + 0.5f);
				break;
			case 3:
				r = (int) (p * 255.0f + 0.5f);
				g = (int) (q * 255.0f + 0.5f);
				b = (int) (brightness * 255.0f + 0.5f);
				break;
			case 4:
				r = (int) (t * 255.0f + 0.5f);
				g = (int) (p * 255.0f + 0.5f);
				b = (int) (brightness * 255.0f + 0.5f);
				break;
			case 5:
				r = (int) (brightness * 255.0f + 0.5f);
				g = (int) (p * 255.0f + 0.5f);
				b = (int) (q * 255.0f + 0.5f);
				break;
			}
		}
		return new KColor(r, g, b);
	}
}
