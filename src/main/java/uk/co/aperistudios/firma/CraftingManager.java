package uk.co.aperistudios.firma;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.Recipe;
import uk.co.aperistudios.firma.crafting.RecipeShape;
import uk.co.aperistudios.firma.items.FirmaItem;
import uk.co.aperistudios.firma.items.MetaItem;

public class CraftingManager {
	private static ArrayList<Recipe> list = new ArrayList<Recipe>();
	public static final int knapWidth = 5, knapHeight = 5; // Constants for
															// size of grid.
															// Maybe we'll want
															// to knap boulders
															// later?

	public static void addRecipe(Recipe r) {
		list.add(r);
	}

	public static Recipe getMatchingRecipe(CraftMat mat, ItemStack is, boolean[] b) {
		for (Recipe r : list) {
			if (r.getCraftMat() == mat) {
				if (r.getItemName() == null) {
					// Any Item of this crafting material will do.
				} else {
					if (is.getItem() instanceof FirmaItem) {
						String name = ((FirmaItem) is.getItem()).getName();
						if (!r.getItemName().equals(name)) {
							continue;
						} // Not the specific item
						String subName = null;
						if (is.getItem() instanceof MetaItem) {
							subName = ((MetaItem) is.getItem()).getSubName(is.getItemDamage());
						}
						if (!r.getSubItemName().equals(subName)) {
							continue;
						} // Not the specific sub-item
						// We're good!
					}
				}
				// Only get here once Craft / IS match
				// Now to check grid
				RecipeShape rs = r.getShape();
				if(rs.matches(mat==CraftMat.CLAY, b)){
					return r;
				}
			}
		}
		return null;
	}

	public static void addKnappingRecipes() {
		RecipeShape knifeShape1 = RecipeShape.makeRecipeShape("S ", "SS", "SS", "SS", "SS");
		RecipeShape knifeShape2 = RecipeShape.makeRecipeShape("S   S", "SS SS", "SS SS", "SS SS", "SS SS");
		RecipeShape axeShape = RecipeShape.makeRecipeShape(" S   ", "SSSS ", "SSSSS", "SSSS ", " S   ");
		RecipeShape hammerShape = RecipeShape.makeRecipeShape("SSSSS", "SSSSS", "  S  ");
		RecipeShape hoeShape1 = RecipeShape.makeRecipeShape("SSSS ", "    S");
		RecipeShape hoeShape2 = RecipeShape.makeRecipeShape("SSSS ", "    S", "     ", "SSSS ", "    S");
		RecipeShape javelinShape = RecipeShape.makeRecipeShape("  S  ", " SSS ", " SSS ", " SSS ", " SSS ");
		RecipeShape shovelShape = RecipeShape.makeRecipeShape(" SSS ", " SSS ", " SSS ", " SSS ", "  S  ");
		RecipeShape jugShape = RecipeShape.makeRecipeShape("S SSS", "    S", "   S ", "    S", "   SS");
		RecipeShape moldShape = RecipeShape.makeRecipeShape("SSSS", "S  S", "S  S", "S  S", "SSSS");
		RecipeShape chiselShape = RecipeShape.makeRecipeShape("S", "S", "S", "S", "S");
		RecipeShape maceShape = RecipeShape.makeRecipeShape(" S ", "SSS", "SSS", "SSS", "S");
		RecipeShape pickaxeShape = RecipeShape.makeRecipeShape(" SSS ", "S   S");
		RecipeShape propickaxeShape = RecipeShape.makeRecipeShape("SSSS ", "S   S", "S    ");
		RecipeShape sawShape = RecipeShape.makeRecipeShape("SSSSS", "S S S");
		RecipeShape swordShape = RecipeShape.makeRecipeShape("    S", "   S ", "  S  ", " S   ", "S    ");
		RecipeShape scytheShape = RecipeShape.makeRecipeShape("  SSS", " SSS ", "SSS  ");
		RecipeShape potShape = RecipeShape.makeRecipeShape(" SSS ", " SSS ", " SSS ", " SSS ", "     ");
		RecipeShape vesselShape = RecipeShape.makeRecipeShape("S   S", "     ", "     ", "     ", "S   S");

		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("knife")), CraftMat.STONE, null, knifeShape1);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 2, FirmaMod.stoneHeads.getSubMeta("knife")), CraftMat.STONE, null, knifeShape2);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("axe")), CraftMat.STONE, null, axeShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("hammer")), CraftMat.STONE, null, hammerShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("hoe")), CraftMat.STONE, null, hoeShape1);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 2, FirmaMod.stoneHeads.getSubMeta("hoe")), CraftMat.STONE, null, hoeShape2);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("javelin")), CraftMat.STONE, null, javelinShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.stoneHeads, 1, FirmaMod.stoneHeads.getSubMeta("shovel")), CraftMat.STONE, null, shovelShape);

		ItemStack normalClayOnly = new ItemStack(FirmaMod.clay, 1, FirmaMod.clay.getSubMeta("clay"));
		ItemStack fireClayOnly = new ItemStack(FirmaMod.clay, 1, FirmaMod.clay.getSubMeta("fireclay"));
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("jug")), CraftMat.CLAY, normalClayOnly, jugShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("mold")), CraftMat.CLAY, normalClayOnly, moldShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldaxe")), CraftMat.CLAY, normalClayOnly, axeShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldchisel")), CraftMat.CLAY, normalClayOnly,
				chiselShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldhammer")), CraftMat.CLAY, normalClayOnly,
				hammerShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldhoe")), CraftMat.CLAY, normalClayOnly, hoeShape1);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldjavelin")), CraftMat.CLAY, normalClayOnly,
				javelinShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldknife")), CraftMat.CLAY, normalClayOnly,
				knifeShape1);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldmace")), CraftMat.CLAY, normalClayOnly,
				maceShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldpick")), CraftMat.CLAY, normalClayOnly,
				pickaxeShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldpropick")), CraftMat.CLAY, normalClayOnly,
				propickaxeShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldsaw")), CraftMat.CLAY, normalClayOnly, sawShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldscythe")), CraftMat.CLAY, normalClayOnly,
				scytheShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldshovel")), CraftMat.CLAY, normalClayOnly,
				shovelShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("moldsword")), CraftMat.CLAY, normalClayOnly,
				swordShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("pot")), CraftMat.CLAY, normalClayOnly, potShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.unfiredClayBits, 1, FirmaMod.unfiredClayBits.getSubMeta("vessel")), CraftMat.CLAY, normalClayOnly,
				vesselShape);
		Recipe.makeRecipe(new ItemStack(FirmaMod.crucible), CraftMat.CLAY, fireClayOnly, potShape);
	}
}
