package org.kneelawk.pixelrotate;

import java.io.File;

import ar.com.hjg.pngj.IImageLine;
import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineHelper;
import ar.com.hjg.pngj.PngReader;

public class PngLoader implements PixelProvider {

	private PngReader reader;
	private ImageInfo info;

	// store like data[y][x]
	private KColor[][] data;

	public PngLoader(File file) {
		reader = new PngReader(file);
		info = reader.imgInfo;
		data = new KColor[info.rows][info.cols];
	}

	public void load() {
		for (int y = 0; y < info.rows; y++) {
			IImageLine line = reader.readRow(y);
			for (int x = 0; x < info.cols; x++) {
				data[y][x] = new KColor(ImageLineHelper.getPixelARGB8(line, x));
			}
		}
		reader.end();
	}

	public KColor getPixel(double x, double y) {
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x >= info.cols)
			x = info.cols - 1;
		if (y >= info.rows)
			y = info.rows - 1;
		return data[(int) Math.floor(y)][(int) Math.floor(x)];
	}
}
