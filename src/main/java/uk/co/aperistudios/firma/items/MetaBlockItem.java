package uk.co.aperistudios.firma.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public class MetaBlockItem extends ItemBlock {
	BaseBlock baseblock;

	public MetaBlockItem(BaseBlock block) {
		super(block);
		this.baseblock = block;
		// super(block, block, new ItemBlockMapper(block));
		String spec = block.getName();
		this.setRegistryName(spec);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setUnlocalizedName(block.getName());
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return FirmaMod.MODID + ":" + baseblock.getName() + "." + baseblock.getSpecialName(stack);
	}
}
