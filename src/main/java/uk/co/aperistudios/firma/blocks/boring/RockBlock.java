package uk.co.aperistudios.firma.blocks.boring;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;

public class RockBlock extends BaseBlock {
	public static final IProperty<RockEnum> properties = PropertyEnum.create("rockvariants",RockEnum.class);
	
	public RockBlock(Material materialIn) {
		super(materialIn);
		this.setUnlocalizedName("rock");
		this.setHardness(10);
		this.setResistance(10);
		this.setRegistryName("rock");
		Item itemblock = new ItemBlock(this).setRegistryName(this.getRegistryName());
		GameRegistry.register(this);
		GameRegistry.register(itemblock);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, properties);
	}
	
	@Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list){
		for (final RockEnum enumType : RockEnum.values()) {
			list.add(new ItemStack(this, 1, 0));
		}
	}
	
	@Override
    public int getMetaFromState(IBlockState state){
	    RockEnum type = (RockEnum) state.getValue(properties);

		return type.getMeta();
	}

}
