package org.kneelawk.pixelrotate;

import java.io.File;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineHelper;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngWriter;

public class PngStore {
	private PngWriter writer;
	private ImageInfo info;

	private KColor[][] data;

	public PngStore(File file, int width, int height) {
		info = new ImageInfo(width, height, 8, true);
		writer = new PngWriter(file, info);
		data = new KColor[height][width];
	}

	public void setPixel(float x, float y, KColor color) {
		data[(int) y][(int) x] = color;
	}

	public void store() {
		for (int y = 0; y < info.rows; y++) {
			ImageLineInt line = new ImageLineInt(info);
			for (int x = 0; x < info.cols; x++) {
				ImageLineHelper.setPixelRGBA8(line, x, data[y][x].value);
			}
			writer.writeRow(line, y);
		}
		writer.end();
	}
}
