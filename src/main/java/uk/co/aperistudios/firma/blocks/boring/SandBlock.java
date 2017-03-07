package uk.co.aperistudios.firma.blocks.boring;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.RockEnum;

public class SandBlock extends BaseBlock {
	public static final IProperty<RockEnum> properties = PropertyEnum.create("variants",RockEnum.class);
	
	public SandBlock(Material materialIn) {
		super(materialIn,"sand");
		this.setHardness(10);
		this.setResistance(10);
		this.setCreativeTab(FirmaMod.blockTab);
		this.setDefaultState(this.getStateFromMeta(0));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, properties);
	}
	
	@Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list){
		for (final RockEnum enumType : RockEnum.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}
	
	@Override
    public int getMetaFromState(IBlockState state){
	    RockEnum type = (RockEnum) state.getValue(properties);

		return type.getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(properties, RockEnum.get(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if(stack==null){ throw new NullPointerException(); }
		return RockEnum.getName(stack.getMetadata());
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(RockEnum tr : RockEnum.values()){
			names.add(tr.getName());
		}
		return names;
	}

}
