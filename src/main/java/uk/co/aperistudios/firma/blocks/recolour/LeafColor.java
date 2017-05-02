package uk.co.aperistudios.firma.blocks.recolour;

import java.util.HashMap;
import org.lwjgl.input.Mouse;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import uk.co.aperistudios.firma.Util;
import uk.co.aperistudios.firma.types.WoodEnum;

public class LeafColor implements IBlockColor {
	static HashMap<Float, Colour> leaf = new HashMap<Float, Colour>();
	
	public static void init(){
		leaf.put(new Float(0f), new Colour(0xcccccc));
		leaf.put(new Float(5f), new Colour(0xff0000));
		leaf.put(new Float(8f), new Colour(0xffff00));
		leaf.put(new Float(9f), new Colour(0x00ff00));
		leaf.put(new Float(10f),new Colour(0x00ff00));
	}
	
	public Colour getColour(float fIn){
		float below= -100f,above=Float.MAX_VALUE;
		for(Float f : leaf.keySet()){
			if(f == fIn){ // We've found exact match. Great
				return leaf.get(fIn);
			}
			if(f > fIn){
				if(f < above){
					above = f;
				}
			}else if(f< fIn){
				if(f > below){
					below = f;
				}
			}
		}
		if(above == Float.MAX_VALUE && below == -100f){
			throw new RuntimeException("Didn't Init LeafColour");
		}
		if(above == Float.MAX_VALUE){
			return leaf.get(below);
		}
		if(below==-100f){
			return leaf.get(above);
		}
		float bInf = interp(below,above,fIn);
		
		return Colour.mix(leaf.get(below), leaf.get(above), bInf);
	}
	
	private float interp(float below, float above, float fIn) {
		float top = above - below;
		float mid = fIn - below;
		return mid / top;
	}

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		// return worldIn.getBiome(pos).getFoliageColorAtPos(pos);
		state.getBlock().getMetaFromState(state);
		boolean w1 = Util.isWoodEnum1(state), w2 = Util.isWoodEnum2(state);
		int meta = state.getBlock().getMetaFromState(state);
		if(w1){
			if(meta == WoodEnum.Sequoia.getMeta()){
				return 0x00ff00; // Should always be hot
			}else if(meta == WoodEnum.Pine.getMeta()){
				return 0x00ff00; // Evergreen
			}else if(meta == WoodEnum.Whitecedar.getMeta()){
				return 0x00ff00; // Evergreen
			}
		}
		// Otherwise, deciduous
		float a = Util.getHeat(Minecraft.getMinecraft().world, pos);
		return getColour(a).toInt();
		//return 0xff0000/;
	}

}