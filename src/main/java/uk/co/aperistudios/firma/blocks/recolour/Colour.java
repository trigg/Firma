package uk.co.aperistudios.firma.blocks.recolour;

public class Colour {
	float r, g, b;
	private ColourHSV hsv = null;

	protected static class ColourHSV {
		float h, s, v;

		protected ColourHSV(Colour c) {
			float max = Math.max(Math.max(c.r, c.b), c.g);
			float min = Math.min(Math.min(c.r, c.g), c.b);
			h = s = v = (max + min) / 2;

			if (max == min) {
				h = s = 0; // achromatic
			} else {
				float d = max - min;
				s = v > 0.5 ? d / (2 - max - min) : d / (max + min);
				if (c.r == max) {
					h = (c.g - c.b) / d + (c.g < c.b ? 6 : 0);
				}
				if (c.g == max) {
					h = (c.b - c.r) / d + 2;
				}
				if (c.b == max) {
					h = (c.r - c.g) / d + 4;
				}
				h /= 6;
			}
		}

		protected Colour getRGB() {
			float r, g, b;

			if (s == 0) {
				r = g = b = v; // achromatic
			} else {
				float q = v < 0.5 ? v * (1 + s) : v + s - v * s;
				float p = 2 * v - q;
				r = hue2rgb(p, q, h + 1 / 3);
				g = hue2rgb(p, q, h);
				b = hue2rgb(p, q, h - 1 / 3);
			}
			return new Colour(r, g, b);
		}

		protected float hue2rgb(float p, float q, float t) {
			if (t < 0)
				t += 1;
			if (t > 1)
				t -= 1;
			if (t < 1 / 6)
				return p + (q - p) * 6 * t;
			if (t < 1 / 2)
				return q;
			if (t < 2 / 3)
				return p + (q - p) * (2 / 3 - t) * 6;
			return p;
		}

	}

	public Colour(int in) {
		r = ((in & 0xff0000) >> 16) / 255f;
		g = ((in & 0x00ff00) >> 8) / 255f;
		b = (in & 0x0000ff) / 255f;
	}

	public Colour(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public ColourHSV getHSV() {
		if (hsv == null) {
			hsv = new ColourHSV(this);
		}
		return hsv;
	}

	public static Colour mix(Colour c1, Colour c2, float influence) {
		float notInfluence = 1f - influence;
		float r = c1.r * notInfluence + c2.r * influence;
		float g = c1.g * notInfluence + c2.g * influence;
		float b = c1.b * notInfluence + c2.b * influence;
		return new Colour(r,g,b);
		
	}

	public int toInt() {
		return ((int)(r * 255) << 16) + ((int)(g * 255) << 8) + (int)(b *255);
	}

}
