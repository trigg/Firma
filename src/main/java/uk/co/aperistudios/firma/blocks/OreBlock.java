package uk.co.aperistudios.firma.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaOreTileEntity;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RocksEnum;

public class OreBlock extends BaseBlock implements ITileEntityProvider {
	public enum OreEnum implements IStringSerializable {
		OneOreToRuleThemAll("oneoretofindthem");

		private String id;

		OreEnum(String s) {
			id = s;
		}

		@Override
		public String getName() {
			return id;
		}

		public static String getName(int metadata) {
			return OreEnum.values()[0].id;
		}
	}

	public static final IProperty<OreBlock.OreEnum> properties = PropertyEnum.create("variants", OreBlock.OreEnum.class);
	public static final IProperty<OresEnum> oreLayer = PropertyEnum.create("ore", OresEnum.class);
	public static final IProperty<RocksEnum> rockLayer = PropertyEnum.create("rock", RocksEnum.class);

	public OreBlock() {
		super(Material.ROCK, "ore");
		this.isBlockContainer = true;
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (OreEnum tr : OreEnum.values()) {
			names.add(tr.getName());
		}
		return names;
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack == null) {
			throw new NullPointerException();
		}
		return OreEnum.getName(stack.getMetadata());
	}

	@Override
	public String getMetaName(int meta) {
		return OreEnum.getName(meta);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new FirmaOreTileEntity();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		super.eventReceived(state, worldIn, pos, id, param);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
	//	Mouse.setGrabbed(false);
		FirmaOreTileEntity te = (FirmaOreTileEntity) worldIn.getTileEntity(pos);
		if(te!=null && te.ore != null && te.rock!=null){
			return super.getActualState(state, worldIn, pos).withProperty(oreLayer, te.ore).withProperty(rockLayer, te.rock);
		}
		return super.getActualState(state, worldIn, pos);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, properties, oreLayer, rockLayer);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		FirmaOreTileEntity te = (FirmaOreTileEntity) worldIn.getTileEntity(pos);
		if(te!=null && te.ore != null && te.rock!=null){ // TODO Prospectors pick
			playerIn.sendMessage(new TextComponentString(te.ore+" "+te.rock));
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return super.getDrops(world, pos, state, fortune);
	}
}
