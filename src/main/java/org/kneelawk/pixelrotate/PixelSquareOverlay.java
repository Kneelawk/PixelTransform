package org.kneelawk.pixelrotate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PixelSquareOverlay {
	public static final int NUM_SQUARES = 1000;
	public static final int IMAGE_WIDTH = 1000;
	public static final int IMAGE_HEIGHT = 1000;

	// public static final int GEN_WIDTH = IMAGE_WIDTH * 2;
	// public static final int GEN_HEIGHT = IMAGE_HEIGHT * 2;
	// public static final int GEN_X = -IMAGE_WIDTH / 2;
	// public static final int GEN_MAX_X = GEN_X + GEN_WIDTH;
	// public static final int GEN_MAX_Y = GEN_Y + GEN_HEIGHT;
	// public static final int NUM_PIXELS = IMAGE_WIDTH * IMAGE_HEIGHT;

	public static void main(String[] args) {
		Random r = new Random();

		PngStore output = new PngStore(new File("panelOverlayTexture.png"),
				IMAGE_WIDTH, IMAGE_HEIGHT, new KColor(0, 255));
		PixelSink store =
				new WrappingSink(output, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

		List<Square> squares = new ArrayList<>();

		System.out.println("Placing squares...");

		for (int i = 0; i < NUM_SQUARES; i++) {
			int width = r.nextInt(190) + 10;
			int height = r.nextInt(190) + 10;
			int x = r.nextInt(IMAGE_WIDTH);
			int y = r.nextInt(IMAGE_HEIGHT);
			int brightness = r.nextInt(255);
			squares.add(new Square(x, y, width, height, brightness));
			if (i % 100 == 0) {
				System.out.println((i * 100 / NUM_SQUARES) + "%");
			}
		}

		System.out.println("Done placing squares.");

		System.out.println("Setting pixels...");

		int size = squares.size();

		for (int i = 0; i < size; i++) {
			Square s = squares.get(i);
			for (int y = s.y; y < s.y + s.height; y++) {
				for (int x = s.x; x < s.x + s.width; x++) {
					store.setPixel(x + 0.5, y + 0.5, new KColor(s.brightness, 255));
				}
			}
			if (i % 100 == 0) {
				System.out.println((i * 100 / NUM_SQUARES) + "%");
			}
		}

		System.out.println("Done setting pixels.");

		System.out.println("Writing image...");
		output.store();
		System.out.println("Done writing image.");
	}

	public static class Square {
		public int x, y, width, height;
		public int brightness;

		public Square(int x, int y, int width, int height, int brightness) {
			super();
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.brightness = brightness;
		}
	}
}
