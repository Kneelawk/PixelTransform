package org.kneelawk.pixelrotate;

import java.io.File;

public class PixelPortal {

	public static final float ROTATION = 360f;
	public static final int NUM_STEPS = 256;
	public static final float IN_CENTER_X = 16;
	public static final float IN_CENTER_Y = 16;
	public static final int OUT_WIDTH = 16;
	public static final int OUT_HEIGHT = 16;
	public static final float IN_X1 = IN_CENTER_X - OUT_WIDTH / 2;
	public static final float IN_Y1 = IN_CENTER_Y - OUT_HEIGHT / 2;
	public static final float IN_X2 = IN_CENTER_X + OUT_WIDTH / 2;
	public static final float IN_Y2 = IN_CENTER_Y + OUT_HEIGHT / 2;
	public static final String FILE = "blockCactosinePortal.full.png";
	public static final String OUT_FILE = "blockCactosinePortal.rotating.256.5.png";

	public static void main(String[] args) {
		PngLoader loader = new PngLoader(new File(FILE));
		loader.load();
		PngStore store = new PngStore(new File(OUT_FILE), OUT_WIDTH, OUT_HEIGHT
				* NUM_STEPS);
		for (int i = 0; i < NUM_STEPS; i++) {
			System.out.println("Building image: " + i);
			float rot = (ROTATION / NUM_STEPS) * i;
			RotatedProvider rotprov = new RotatedProvider(
					new Interpolate2Provider(new WrappingProvider(loader,
							IN_X1, IN_Y1, IN_X2, IN_Y2), 3.0 / 4.0),
					IN_CENTER_X, IN_CENTER_Y, (float) Math.toRadians(rot));
			SquareApplyProvider centerAP = new SquareApplyProvider(rotprov,
					IN_X1, IN_Y1, IN_X2, IN_Y2);
			SquareApplyProvider xpypAP = new SquareApplyProvider(
					new TranslatingProvider(rotprov, -(OUT_WIDTH / 2),
							-(OUT_HEIGHT / 2)), IN_X1, IN_Y1, IN_X2, IN_Y2);
			SquareApplyProvider xzypAP = new SquareApplyProvider(
					new TranslatingProvider(rotprov, 0, -OUT_HEIGHT), IN_X1,
					IN_CENTER_Y, IN_X2, IN_Y2);
			Average2Provider prov = new Average2Provider(centerAP, xpypAP,
					xzypAP);
			for (int y = 0; y < OUT_HEIGHT; y++) {
				for (int x = 0; x < OUT_WIDTH; x++) {
					store.setPixel(x, y + i * 16,
							prov.getPixel(x + 0.5f + IN_X1, y + 0.5f + IN_Y1));
				}
			}
			System.out.println("Image built.");
		}
		store.store();
		System.out.println("Done.");
	}
}
