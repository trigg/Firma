package uk.co.aperistudios.firma;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.Recipe;

public class CraftingManager {
	private static ArrayList<Recipe> list = new ArrayList<Recipe>();
	
	public static void addRecipe(Recipe r){
		list.add(r);
	}
	
	public static Recipe getMatchingRecipe(CraftMat mat, ItemStack is,boolean[] b){
		// TODO : Implement
		return null;
	}
	
	public static void addKnappingRecipes(){
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("knife")), CraftMat.STONE, "S ", "SS", "SS", "SS", "SS");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,2,FirmaMod.stoneHeads.getSubMeta("knife")), CraftMat.STONE, "S   S", "SS SS", "SS SS", "SS SS", "SS SS");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("axe")), CraftMat.STONE, " S   ","SSSS ","SSSSS","SSSS "," S   ");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("hammer")), CraftMat.STONE, "SSSSS","SSSSS","  S  ");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("hoe")), CraftMat.STONE, "SSSS ","    S");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,2,FirmaMod.stoneHeads.getSubMeta("hoe")), CraftMat.STONE, "SSSS ","    S","     ","SSSS ","    S");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("javelin")), CraftMat.STONE, "  S  "," SSS "," SSS "," SSS "," SSS ");
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads,1,FirmaMod.stoneHeads.getSubMeta("shovel")), CraftMat.STONE, " SSS "," SSS "," SSS "," SSS ","  S  ");
		
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("jug")), null);
		
		
	}
}
