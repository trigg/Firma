package uk.co.aperistudios.firma.items;

import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public class MetaBlockItem extends ItemBlock {
	BaseBlock block;

	public MetaBlockItem(BaseBlock block) {
		super(block);
		this.block = block;
		// super(block, block, new ItemBlockMapper(block));
		String spec = block.getName();
		this.setRegistryName(spec);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(block.getName());
	}

	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return FirmaMod.MODID + ":" + block.getName() + "." + block.getSpecialName(stack);
	}
}
