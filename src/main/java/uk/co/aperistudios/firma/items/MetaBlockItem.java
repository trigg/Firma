package uk.co.aperistudios.firma.items;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;

public class MetaBlockItem extends ItemBlock {
	private static Function<ItemStack, String> f= new Function<ItemStack, String>(){
		@Override
		public String apply(ItemStack input) {
			Block b = Block.getBlockFromItem(input.getItem());
			if(b instanceof BaseBlock){
				return ((BaseBlock)b).getSpecialName(input);
			}
			return "";
		}
	};
	
	Block block;

	public MetaBlockItem(BaseBlock block) {
		super(block);
		this.block = block;
        //super(block, block, new ItemBlockMapper(block));
		String spec=block.getName();
        this.setRegistryName(spec);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(block.getName());
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
    	BaseBlock b = ((BaseBlock)this.block);
        return  FirmaMod.MODID+":"+b.getName()+ "." + b.getSpecialName(stack);
    }
}
