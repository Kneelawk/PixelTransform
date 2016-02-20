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
		int nx = (int) Math.floor(x), ny = (int) Math.floor(y);
		if (nx < 0)
			nx = 0;
		if (ny < 0)
			ny = 0;
		if (nx >= info.cols)
			nx = info.cols - 1;
		if (ny >= info.rows)
			ny = info.rows - 1;
		return data[ny][nx];
	}
}
