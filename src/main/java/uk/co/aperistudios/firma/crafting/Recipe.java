package uk.co.aperistudios.firma.crafting;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;

public class Recipe {
	CraftMat material;
	String itemName;
	String metaSub;
	RecipeShape rs;
	ItemStack is;

	public static Recipe makeRecipe(ItemStack output, CraftMat mat, ItemStack is, RecipeShape rs) {
		Recipe r = new Recipe();
		r.is = output.copy();
		r.material = mat;
		r.rs = rs;

		if (is != null) {
			if (r.material == CraftMat.STONE && is.getItem() == FirmaMod.pebble) {
				r.itemName = "pebble";
				r.metaSub = FirmaMod.pebble.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.CLAY && is.getItem() == FirmaMod.clay) {
				r.itemName = "clay";
				r.metaSub = FirmaMod.clay.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.ingot) {
				r.itemName = "ingot";
				r.metaSub = FirmaMod.ingot.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.doubleingot) {
				r.itemName = "doubleingot";
				r.metaSub = FirmaMod.doubleingot.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.ANVIL && is.getItem() == FirmaMod.metalsheet) {
				r.itemName = "metalsheet";
				r.metaSub = FirmaMod.metalsheet.getSubName(is.getItemDamage());
			} else if (r.material == CraftMat.LEATHER) { // Takes no subtypes
															// yet
				System.err.println("Leather cannot have a specified ItemStack");
				assert r == null;
			} else {
				// Specifying an itemstack with a craftmat that does not match.
				// So specifying leather crafting with metal or stonecrafting
				// with a sandwich
				System.err.println("Itemstack " + is + " does not match Craft Mat " + mat);
				assert r == null;
			}
		}
		CraftingManager.addRecipe(r);
		return r;
	}

	public CraftMat getCraftMat() {
		return material;
	}

	public String getItemName() {
		return itemName;
	}

	public String getSubItemName() {
		return metaSub;
	}

	public int getWidth() {
		return rs.width;
	}

	public int getHeight() {
		return rs.height;
	}

	public RecipeShape getShape() {
		return rs;
	}

	public ItemStack getOutput() {
		return is.copy();
	}

	public boolean payPrice(EntityPlayerMP p) {
		ItemStack i = p.getHeldItemMainhand();
		if (material == CraftMat.STONE) {
			if (i.getItem() == FirmaMod.pebble) {
				if (i.getCount() > 1) {
					i.setCount(i.getCount() - 1);
					return true;
				}
			}
			return false;
		} else if (material == CraftMat.CLAY) {
			if (i.getItem() == FirmaMod.clay) {
				if (i.getCount() > 5) {
					i.setCount(i.getCount() - 5);
					return true;
				}
			}
			return false;
		} else if (material == CraftMat.LEATHER) {
			// BLOCK BASED
			assert 1 == 0;
		} else if (material == CraftMat.ANVIL) {
			// BLOCK BASED
			assert 1 == 0;
		}
		return false;
	}

}
