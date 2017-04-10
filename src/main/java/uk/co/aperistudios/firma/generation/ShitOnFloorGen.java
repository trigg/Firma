package uk.co.aperistudios.firma.generation;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaOreTileEntity;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;
import uk.co.aperistudios.firma.types.OresEnum;

public class ShitOnFloorGen implements IWorldGenerator {
	boolean belowTree = false;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < random.nextInt(16); i++) {
			belowTree = false;
			int x = random.nextInt(16) + chunkX * 16;
			int z = random.nextInt(16) + chunkZ * 16;
			int y = world.getHeight();
			BlockPos pos = new BlockPos(x,y,z);
			while(!shitCheck(world, pos)) {
				pos = pos.down();
			}
			IBlockState b = world.getBlockState(pos);
			if (Util.isClay(b.getBlock()) || Util.isDirt(b.getBlock()) || Util.isGrass(b.getBlock()) || Util.isRawStone(b.getBlock())
					|| Util.isSand(b.getBlock()) || Util.isGravel(b.getBlock()) && b.getBlock() instanceof BaseBlock) { // Sensible
																														// locations
																														// for
																														// shit
				BaseBlock bb = (BaseBlock) b.getBlock();
				String meta = bb.getMetaName(b.getBlock().getMetaFromState(b));
				pos = pos.up();
				world.setBlockState(pos, FirmaMod.shitOnFloor.getDefaultState(), 2);
				SoFTileEntity te = (SoFTileEntity) world.getTileEntity(pos);
				if (te != null) {
					te.setItem(getItemForArea(random,meta));
					te.markDirty();
				}
			}
		}
	}

	private boolean shitCheck(World world, BlockPos pos) {
		Block shitfuck = world.getBlockState(pos).getBlock();
		if(shitfuck == FirmaMod.leaf || shitfuck == FirmaMod.leaf2){
			belowTree=true;
			return false;
		}
		return shitfuck != Blocks.AIR; 
	}
	
	public ItemStack getItemForArea(Random r,String meta){
		if(r.nextInt(100)<10){
			return new ItemStack(Items.STICK);
		}
		if(belowTree && r.nextInt(100)<80){
			return new ItemStack(Items.STICK);
		}
		if(r.nextInt(100)<20){
			OresEnum oe = Util.getOreForRock(r, meta);
			return new ItemStack(FirmaMod.oreItem, 1, FirmaMod.oreItem.getSubMeta(oe.getName()+"poor")); 
		}
		return new ItemStack(FirmaMod.pebble,1,FirmaMod.pebble.getSubMeta(meta));
	}

}
