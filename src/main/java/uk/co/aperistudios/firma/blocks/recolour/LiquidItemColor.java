package uk.co.aperistudios.firma.blocks.recolour;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class LiquidItemColor implements IItemColor {

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		return 0xaaff00;
	}

}
