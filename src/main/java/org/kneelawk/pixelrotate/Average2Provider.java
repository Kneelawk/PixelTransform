package org.kneelawk.pixelrotate;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Average2Provider implements PixelProvider {

	public List<ApplyProvider> provs;

	public Average2Provider(List<ApplyProvider> provs) {
		this.provs = provs;
	}

	public Average2Provider(ApplyProvider... provs) {
		this.provs = Arrays.asList(provs);
	}

	@Override
	public KColor getPixel(double x, double y) {
		BigInteger r = BigInteger.ZERO, g = BigInteger.ZERO, b = BigInteger.ZERO, a = BigInteger.ZERO;
		int num = 0;
		for (ApplyProvider prov : provs) {
			if (prov.applies(x, y)) {
				num++;
				KColor col = prov.getPixel(x, y);
				r = r.add(BigInteger.valueOf(col.getRedI()));
				g = g.add(BigInteger.valueOf(col.getGreenI()));
				b = b.add(BigInteger.valueOf(col.getBlueI()));
				a = a.add(BigInteger.valueOf(col.getAlphaI()));
			}
		}
		return new KColor(r.divide(BigInteger.valueOf(num)).byteValue(), g
				.divide(BigInteger.valueOf(num)).byteValue(), b.divide(
				BigInteger.valueOf(num)).byteValue(), a.divide(
				BigInteger.valueOf(num)).byteValue());
	}

}
