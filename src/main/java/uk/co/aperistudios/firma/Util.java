package uk.co.aperistudios.firma;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;

public class Util {

	public static boolean isGrass(Block b) {
		return b == FirmaMod.grass || b == FirmaMod.grass2;
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
		return b == FirmaMod.rock || b == FirmaMod.dirt || b == FirmaMod.grass || b == FirmaMod.gravel || b == FirmaMod.sand || b == FirmaMod.rockb || b == FirmaMod.rockc || b==FirmaMod.rocks;
	}
	
	public static boolean isRockEnum2(Block b) {
		return b == FirmaMod.rock2 || b == FirmaMod.dirt2 || b==FirmaMod.grass2 || b== FirmaMod.gravel2 || b== FirmaMod.sand2 || b== FirmaMod.rockb2 || b == FirmaMod.rockc2 || b == FirmaMod.rocks2;
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
	
	

}
