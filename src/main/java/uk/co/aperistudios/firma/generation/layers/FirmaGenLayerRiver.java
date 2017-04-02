package uk.co.aperistudios.firma.generation.layers;

import net.minecraft.world.gen.layer.GenLayer;

public class FirmaGenLayerRiver extends FirmaGenLayer {

	public FirmaGenLayerRiver(long par1, GenLayer par3GenLayer)
	{
		super(par1);
		super.parent = par3GenLayer;
	}

	@Override
	public int[] getInts(int x, int y, int w, int h)
	{
		int i1 = x - 1;
		int j1 = y - 1;
		int k1 = w + 2;
		int l1 = h + 2;
		int[] aint = this.parent.getInts(i1, j1, k1, l1);
		int[] aint1 = new int[w * h];

		for (int i2 = 0; i2 < h; ++i2)
		{
			for (int j2 = 0; j2 < w; ++j2)
			{
				int k2 = FirmaGenLayerRiver.calcWidth(aint[j2 + 0 + (i2 + 1) * k1]);
				int l2 = FirmaGenLayerRiver.calcWidth(aint[j2 + 2 + (i2 + 1) * k1]);
				int i3 = FirmaGenLayerRiver.calcWidth(aint[j2 + 1 + (i2 + 0) * k1]);
				int j3 = FirmaGenLayerRiver.calcWidth(aint[j2 + 1 + (i2 + 2) * k1]);
				int k3 = FirmaGenLayerRiver.calcWidth(aint[j2 + 1 + (i2 + 1) * k1]);

				if (k3 == k2 && k3 == i3 && k3 == l2 && k3 == j3)
				{
					aint1[j2 + i2 * w] = 0;
				}
				else
				{
					aint1[j2 + i2 * w] = Layer.RIVER;
				}
			}
		}

		return aint1;
	}

	private static int calcWidth(int i)
	{
		return i >= 2 ? 2 + (i & 1) : i; // Spits back 2 for even numbers >= 2 and 3 for odd numbers.
	}
}
