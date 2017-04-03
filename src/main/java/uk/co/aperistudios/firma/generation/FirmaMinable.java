package uk.co.aperistudios.firma.generation;

import java.util.Random;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaOreTileEntity;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;

public class FirmaMinable extends WorldGenerator {

	private int numberOfBlocks;
	private OresEnum ore;
	private final Predicate<IBlockState> predicate;
	private int grade;

	public FirmaMinable(OresEnum ore, int blockCount, Predicate<IBlockState> isStone, int grade) {
		this.ore = ore;
		this.numberOfBlocks = blockCount;
		this.predicate = isStone;
		this.grade = grade;
	}

	public FirmaMinable(OresEnum ore, int blockCount, int grade) {
		this(ore,blockCount,new FirmaMinable.StonePredicate(), grade);
	}
	
	public boolean generate(World worldIn, Random rand, int cX, int cZ){
		return generate(worldIn, rand, new BlockPos(rand.nextInt(16)+cX*16, rand.nextInt(200), rand.nextInt(16)+cZ*16));
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		float f = rand.nextFloat() * (float) Math.PI;
		double d0 = position.getX() + 8 + MathHelper.sin(f) * this.numberOfBlocks / 8.0F;
		double d1 = position.getX() + 8 - MathHelper.sin(f) * this.numberOfBlocks / 8.0F;
		double d2 = position.getZ() + 8 + MathHelper.cos(f) * this.numberOfBlocks / 8.0F;
		double d3 = position.getZ() + 8 - MathHelper.cos(f) * this.numberOfBlocks / 8.0F;
		double d4 = position.getY() + rand.nextInt(3) - 2;
		double d5 = position.getY() + rand.nextInt(3) - 2;

		for (int i = 0; i < this.numberOfBlocks; ++i) {
			float f1 = (float) i / (float) this.numberOfBlocks;
			double d6 = d0 + (d1 - d0) * f1;
			double d7 = d4 + (d5 - d4) * f1;
			double d8 = d2 + (d3 - d2) * f1;
			double d9 = rand.nextDouble() * this.numberOfBlocks / 16.0D;
			double d10 = (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			double d11 = (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			int j = MathHelper.floor(d6 - d10 / 2.0D);
			int k = MathHelper.floor(d7 - d11 / 2.0D);
			int l = MathHelper.floor(d8 - d10 / 2.0D);
			int i1 = MathHelper.floor(d6 + d10 / 2.0D);
			int j1 = MathHelper.floor(d7 + d11 / 2.0D);
			int k1 = MathHelper.floor(d8 + d10 / 2.0D);

			for (int l1 = j; l1 <= i1; ++l1) {
				double d12 = (l1 + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D) {
					for (int i2 = k; i2 <= j1; ++i2) {
						double d13 = (i2 + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D) {
							for (int j2 = l; j2 <= k1; ++j2) {
								double d14 = (j2 + 0.5D - d8) / (d10 / 2.0D);

								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D) {
									BlockPos blockpos = new BlockPos(l1, i2, j2);

									IBlockState state = worldIn.getBlockState(blockpos);
									if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, this.predicate)) {
										RockEnum r=null;
										RockEnum2 r2=null;
										if(state.getBlock() instanceof BaseBlock){
											r = Util.getRockEnum(state);
											r2=Util.getRockEnum2(state);
										}
										boolean isAllowed = false;
										if(r==null && r2==null){
											isAllowed=true;
											// Non-Firma-Rock that says it allows replacing? Must be some other mod! Let them have it
										}else if(r!=null){
											for(int v = 0; v < ore.getRock1().length; v++){
												if(ore.getRock1()[v] == r){
													isAllowed=true;
												}
											}
										}else if(r2!=null){
											for(int v = 0; v < ore.getRock2().length; v++){
												if(ore.getRock2()[v] == r2){
													isAllowed=true;
												}
											}
										}
										if(isAllowed){
											setToOre(worldIn, blockpos, state, ore, grade);
										}else{
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	public void setToOre(World world, BlockPos pos, IBlockState currentBlockState, OresEnum ore, int grade) {
		world.setBlockState(pos, FirmaMod.ore.getDefaultState(), 2);
		FirmaOreTileEntity te = (FirmaOreTileEntity) world.getTileEntity(pos);
		if (te != null) {
			te.setState(currentBlockState);
			te.grade = grade;
			te.ore = ore;
			te.markDirty();
			FirmaOreGen.count++;
		}
	}

	static class StonePredicate implements Predicate<IBlockState> {
		private StonePredicate() {
		}

		@Override
		public boolean apply(IBlockState p_apply_1_)
            {
                return p_apply_1_ != null && Util.isRawStone(p_apply_1_.getBlock());
            }
	}

}
