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
import uk.co.aperistudios.firma.types.RockEnum2;

public class RockBlock2 extends BaseBlock {
	public static final IProperty<RockEnum2> properties = PropertyEnum.create("variants",RockEnum2.class);
	
	public RockBlock2(Material materialIn) {
		super(materialIn,"rock2");
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
		for (final RockEnum2 enumType : RockEnum2.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}
	
	@Override
    public int getMetaFromState(IBlockState state){
	    RockEnum2 type = (RockEnum2) state.getValue(properties);

		return type.getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(properties, RockEnum2.get(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if(stack==null){ throw new NullPointerException(); }
		return RockEnum2.getName(stack.getMetadata());
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(RockEnum2 tr : RockEnum2.values()){
			names.add(tr.getName());
		}
		return names;
	}

}
