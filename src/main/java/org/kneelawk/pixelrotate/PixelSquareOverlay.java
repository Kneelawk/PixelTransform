package org.kneelawk.pixelrotate;

import java.io.File;

public class PixelSquareOverlay {
	public static void main(String[] args) {
		PngStore store = new PngStore(new File("panelOverlayTexture.png"), 1000, 1000);

	}

	public static class Square {
		public int x, y, width, height;
	}
}
