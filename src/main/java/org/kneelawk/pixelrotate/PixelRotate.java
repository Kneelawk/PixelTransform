package org.kneelawk.pixelrotate;

import java.io.File;

public class PixelRotate {

	public static final float ROTATION = 360f;
	public static final int NUM_STEPS = 256;
	public static final float IN_CENTER_X = 16;
	public static final float IN_CENTER_Y = 16;
	public static final int OUT_WIDTH = 16;
	public static final int OUT_HEIGHT = 16;
	public static final String FILE = "blockCactosineLamp.full.png";
	public static final String OUT_FILE = "blockCactosineLamp.rotating.256.5.png";

	public static final KColor BRIGHT = new KColor(0, 78, 140);
	public static final KColor MIDDLE = new KColor(0, 65, 117);
	public static final KColor DARK = new KColor(0, 52, 94);

	public static void main(String[] args) {
		PngLoader loader = new PngLoader(new File(FILE));
		loader.load();
		PngStore store = new PngStore(new File(OUT_FILE), OUT_WIDTH, OUT_HEIGHT
				* NUM_STEPS);
		for (int i = 0; i < NUM_STEPS; i++) {
			System.out.println("Building image: " + i);
			float rot = (ROTATION / NUM_STEPS) * i;
			RotatedProvider prov = new RotatedProvider(
					new Interpolate2Provider(loader, 3.0 / 4.0), IN_CENTER_X,
					IN_CENTER_Y, (float) Math.toRadians(rot));
			for (int y = 0; y < OUT_HEIGHT; y++) {
				for (int x = 0; x < OUT_WIDTH; x++) {
					if ((x < OUT_WIDTH - 1 && y < 1)
							|| (x < 1 && y < OUT_HEIGHT - 1)) {
						store.setPixel(x, y + i * 16, BRIGHT);
					} else if ((x > OUT_WIDTH - 2 && y > 0)
							|| (x > 0 && y > OUT_HEIGHT - 2)) {
						store.setPixel(x, y + i * 16, DARK);
					} else if ((x == OUT_WIDTH - 1 && y == 0)
							|| (x == 0 && y == OUT_HEIGHT - 1)) {
						store.setPixel(x, y + i * 16, MIDDLE);
					} else {
						store.setPixel(x, y + i * 16, prov.getPixel(x + 0.5f
								+ (IN_CENTER_X - OUT_WIDTH / 2), y + 0.5f
								+ (IN_CENTER_Y - OUT_HEIGHT / 2)));
					}
				}
			}
			System.out.println("Image built.");
		}
		store.store();
		System.out.println("Done.");
	}

}
