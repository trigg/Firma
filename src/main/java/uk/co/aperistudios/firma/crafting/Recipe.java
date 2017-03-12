package uk.co.aperistudios.firma.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.CraftingManager;
import uk.co.aperistudios.firma.FirmaMod;

public class Recipe {
	int width, height;
	CraftMat material;
	String metaSub;
	ArrayList<String> map;
	
	public static Recipe makeRecipe( ItemStack output, Object... objects){
		Recipe r = new Recipe();
		r.width=-1;
		r.height=0;
		r.material = null;
		for(int i = 0; i < objects.length;i++){
			if(objects[i] instanceof String){
				String line = ((String)objects[i]);
				int strlen = line.length();
				if(r.width == -1){
					r.width = strlen;
				}
				assert r.width==strlen;
				for(int c = 0; c < line.length(); c++){
					r.map.add(line.substring(c, c+1));
				}
				r.height++;
			}else if(objects[i] instanceof ItemStack){
				ItemStack is = (ItemStack) objects[i];
				if(r.material == CraftMat.STONE && is.getItem() == FirmaMod.pebble){
					
				}else if(r.material == CraftMat.CLAY && is.getItem() == FirmaMod.clay){
					
				}else if(r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.ingot){
					
				}else if(r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.doubleingot){
					
				}else if(r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.metalsheet){
					
				}else if(r.material == CraftMat.LEATHER){ // Takes no subtypes yet
					assert r==null;
				}else{
					assert r==null;
				}
			}else if(objects[i] instanceof CraftMat){
				assert r.material == null;
				r.material = (CraftMat) objects[i];
			}
		}
		CraftingManager.addRecipe(r);
		return r;
	}
	
}
