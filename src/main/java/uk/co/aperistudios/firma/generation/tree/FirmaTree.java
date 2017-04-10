package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.types.WoodEnum;
import uk.co.aperistudios.firma.types.WoodEnum2;

public abstract class FirmaTree extends WorldGenerator implements IWorldGenerator {
	protected IBlockState bs, leaf;
	protected LeafFiller filler;


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		//System.out.println("Generating "+bs+" at "+chunkX+", "+chunkZ);
		int x = random.nextInt(16);
		int z = random.nextInt(16);
		BlockPos pos = new BlockPos((chunkX*16)+x,0,(chunkZ*16)+z);
		pos = world.getTopSolidOrLiquidBlock(pos);
		//pos = pos.up();
		generate(world, random, pos);
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		BlockPos pd = position.down();
		Block b = worldIn.getBlockState(pd).getBlock();
		Block b2 = worldIn.getBlockState(position.up()).getBlock(); // Allow one-deep in water for willows
		boolean canGrow =Util.isDirt(b) || Util.isGrass(b) || Util.isClay(b);
		if(canGrow && b2 == Blocks.AIR){
			return generateTree(worldIn, rand, position);	
		}
		return false;
	}

	public abstract boolean generateTree(World worldIn, Random rand, BlockPos position);

	public void set(IBlockState log, IBlockState leaf) {
		this.bs=log;this.leaf=leaf;
	}
	
	public void fill(World w, BlockPos p){
		this.setBlockAndNotifyAdequately(w, p, bs);
	}
	
	public boolean checkBlockAt(World w, BlockPos pos){
		IBlockState s = w.getBlockState(pos);
		Block b = s.getBlock();
		return b.canBeReplacedByLeaves(s, w, pos);
	}
	
	public FirmaTree set(String string) {
		WoodEnum we = WoodEnum.get(string);
		WoodEnum2 we2 = WoodEnum2.get(string);
		if(we!=null){
			this.bs = FirmaMod.log.getStateFromMeta(we.getMeta());
			this.leaf = FirmaMod.leaf.getStateFromMeta(we.getMeta());
		}
		if(we2!=null){
			this.bs = FirmaMod.log2.getStateFromMeta(we2.getMeta());
			this.leaf = FirmaMod.leaf2.getStateFromMeta(we2.getMeta());
		}
		return this;
	}
	
}
