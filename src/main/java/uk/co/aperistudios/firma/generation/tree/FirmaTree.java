package uk.co.aperistudios.firma.generation.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.WoodEnum;
import uk.co.aperistudios.firma.types.WoodEnum2;

public abstract class FirmaTree extends WorldGenerator implements IWorldGenerator {
	protected IBlockState bs, leaf;
	protected LeafFiller filler;


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		System.out.println("Generating "+bs+" at "+chunkX+", "+chunkZ);
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return generateTree(worldIn, rand, position);
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
