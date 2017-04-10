package uk.co.aperistudios.firma.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;

public class ShitOnFloor extends Block implements ITileEntityProvider  {

	public ShitOnFloor(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(FirmaMod.blockTab);
		this.setRegistryName("shitonfloor");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1f, 0.1f, 1f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1f, 0.1f, 1f);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1f, 0.1f, 1f);
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SoFTileEntity();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		SoFTileEntity te = (SoFTileEntity) worldIn.getTileEntity(pos);
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX()+0.5f,pos.getY()+0.2f, pos.getZ()+0.5f, te.getItem()));
		super.breakBlock(worldIn, pos, state);
	}
}
