package uk.co.aperistudios.firma.blocks.living;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.types.WoodEnum;

public class SaplingBlock extends BaseBlock {

	public static final IProperty<WoodEnum> properties = PropertyEnum.create("variants", WoodEnum.class);

	public SaplingBlock(Material materialIn) {
		super(materialIn, "sapling");
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
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (final WoodEnum enumType : WoodEnum.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		WoodEnum type = (WoodEnum) state.getValue(properties);

		return type.getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(properties, WoodEnum.get(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack == null) {
			throw new NullPointerException();
		}
		return WoodEnum.getName(stack.getMetadata());
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (WoodEnum tr : WoodEnum.values()) {
			names.add(tr.getName());
		}
		return names;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return BlockRenderLayer.CUTOUT == layer;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

}
