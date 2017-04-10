package uk.co.aperistudios.firma;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.blocks.OreBlock.OreEnum;
import uk.co.aperistudios.firma.types.OresEnum;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;

public class Util {
	static int daysInMonth=28, monthsInYear=12, daysInYear = daysInMonth * monthsInYear;
	static int ticksInDay=24000;
	public static long mainWorldTick;

	public static boolean isGrass(Block b) {
		return b == FirmaMod.grass || b == FirmaMod.grass2  || b == FirmaMod.grasss || b == FirmaMod.grasss2;
	}

	public static boolean isDirt(Block b) {
		return b == FirmaMod.dirt || b == FirmaMod.dirt2;
	}

	public static boolean isRawStone(Block b) {
		return b == FirmaMod.rock || b == FirmaMod.rock2;
	}

	public static boolean isSoilOrGravel(Block b) {
		return isSoil(b) || isGravel(b);
	}

	public static boolean isGravel(Block b) {
		return b == FirmaMod.gravel || b == FirmaMod.gravel2;
	}
	
	public static boolean isSoil(Block b){
		return isDirt(b) || isClay(b) || isGrass(b);
	}

	public static boolean isClay(Block b) {
		// TODO CLAY
		return false;
	}

	public static boolean isSand(Block b) {
		return b == FirmaMod.sand || b== FirmaMod.sand2;
	}

	public static IBlockState getRockStrata(double noise, int layer) {
		noise = Math.abs(noise) % 1.0;
		if(layer == 0){
			// Top Layer Rocks
			int count = CommonProxy.rockLayerTop.length;
			int opt = (int)(noise * count);
			return CommonProxy.rockLayerTop[opt];
		}else if(layer == 1){
			// Mid Layer Rocks
			int count = CommonProxy.rockLayerMid.length;
			int opt = (int)(noise * count);
			return CommonProxy.rockLayerMid[opt];
		}else if(layer == 2){
			// Bottom Layer Rocks
			int count = CommonProxy.rockLayerBot.length;
			int opt = (int)(noise * count);
			return CommonProxy.rockLayerBot[opt];
		}else if(layer == -1){
			// Tree. Not really rocks I guess?
			int count = CommonProxy.saplingLayer.length;
			int opt = (int)(noise * count);
			return CommonProxy.saplingLayer[opt];
		}
		return null;
	}

	public static IBlockState getDirt(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.dirt.getStateFromMeta(meta) : FirmaMod.dirt2.getStateFromMeta(meta);
	}
	
	public static boolean isRockEnum1(Block b){
		return b == FirmaMod.rock || b == FirmaMod.dirt || b == FirmaMod.grass || b == FirmaMod.gravel || b == FirmaMod.sand || b == FirmaMod.rockb || b == FirmaMod.rockc || b==FirmaMod.rocks || b == FirmaMod.grasss;
	}
	
	public static boolean isRockEnum2(Block b) {
		return b == FirmaMod.rock2 || b == FirmaMod.dirt2 || b==FirmaMod.grass2 || b== FirmaMod.gravel2 || b== FirmaMod.sand2 || b== FirmaMod.rockb2 || b == FirmaMod.rockc2 || b == FirmaMod.rocks2 || b == FirmaMod.grasss2;
	}
	
	public static RockEnum getRockEnum(IBlockState b){
		if(isRockEnum1(b.getBlock())){
			return RockEnum.get(b.getBlock().getMetaFromState(b));
		}
		return null;
	}
	
	public static RockEnum2 getRockEnum2(IBlockState b){
		if(isRockEnum2(b.getBlock())){
			return RockEnum2.get(b.getBlock().getMetaFromState(b));
		}
		return null;		
	}

	public static IBlockState getSparseGrass(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.grasss.getStateFromMeta(meta) : FirmaMod.grasss2.getStateFromMeta(meta);
	}

	public static IBlockState getGrass(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.grass.getStateFromMeta(meta) : FirmaMod.grass2.getStateFromMeta(meta);
	}

	public static boolean isWater(Block b) {
		return b==FirmaMod.freshwater.getBlock() || b==FirmaMod.saltwater.getBlock();
	}

	public static IBlockState getSand(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.sand.getStateFromMeta(meta) : FirmaMod.sand2.getStateFromMeta(meta);
	}
	
	public static IBlockState getGravel(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.gravel.getStateFromMeta(meta) : FirmaMod.gravel2.getStateFromMeta(meta);
	}

	public static IBlockState getClay(IBlockState in) {
		int meta = in.getBlock().getMetaFromState(in);
		Block b = in.getBlock();
		return isRockEnum1(b) ? FirmaMod.clayBlock.getStateFromMeta(meta) : FirmaMod.clayBlock2.getStateFromMeta(meta);
	}

	public static boolean isRockEnum1(IBlockState in) {
		return isRockEnum1(in.getBlock());
	}
	
	/**
	 * Get Temperature ignoring given season and any heat producing blocks. 
	 * Mostly used for world gen.
	 * @param pos
	 * @return
	 */
	public static int getEquatorialHeat(BlockPos pos){
		return getEquatorialHeat(pos.getZ());
	}

	/**
	 * Get Temperature ignoring given season and any heat producing blocks. 
	 * Mostly used for world gen.
	 * @param z
	 * @return
	 */
	public static int getEquatorialHeat(int z) {
		z = Math.abs(z);
		int temp = 25 - (z/250);
		if(temp < -20){
			temp = -20;
		}
		return temp;
	}

	public static boolean isWoodEnum1(IBlockState state) {
		return isWoodEnum1(state.getBlock());
	}
	
	public static boolean isWoodEnum2(IBlockState state){
		return isWoodEnum2(state.getBlock());
	}

	public static boolean isWoodEnum1(Block b) {
		return b == FirmaMod.log || b== FirmaMod.leaf || b==FirmaMod.plank || b==FirmaMod.sapling;
	}
	
	public static boolean isWoodEnum2(Block b){
		return b == FirmaMod.log2 || b == FirmaMod.leaf2 || b == FirmaMod.plank2 || b == FirmaMod.sapling2;
	}

	public static float getHeat(long ticks, BlockPos pos) {
		return getEquatorialHeat(pos) + getSeasonModifier(ticks);
	}

	public static float getSeasonModifier(long time) {
		return 0; // TODO this
	}
	
	public static long getYear(long time){
		return time / daysInYear;
	}
	
	public static long getTotalDays(long time){
		return time / ticksInDay;
	}
	
	public static long getTotalMonths(long time){
		return time / daysInMonth;
	}
	
	public static int getMonthOfYear(long time){
		return (int) (getTotalMonths(time) % monthsInYear);
	}
	
	public static int getDayOfYear(long time){
		return (int) (getTotalDays(time) % daysInYear);
	}
	
	public static int getDayOfMonth(long time){
		return getDayOfYear(time) - getMonthOfYear(time) * daysInMonth;
	}
	
	public static int getTimeOfDay(long time){
		return (int) (time - getTotalDays(time) * ticksInDay);
	}

	public static long getMainWorldTicks() {
		return -1;// TODO Fill
	}

	public static OresEnum getOreForRock(Random r, String meta) {
		OresEnum ret = null;
		int iter = 0;
		while(true){
			if(iter>100){ break; }
			ret = OresEnum.values()[r.nextInt(OresEnum.values().length)];
			for(RockEnum re : RockEnum.values()){
				if(re.getName().equals(meta)){
					return ret;
				}
			}
			for(RockEnum2 re : RockEnum2.values()){
				if(re.getName().equals(meta)){
					return ret;
				}
			}
			ret = null;
			iter++;
		}
		throw new RuntimeException("No ores can spawn in "+meta);
	}
}
