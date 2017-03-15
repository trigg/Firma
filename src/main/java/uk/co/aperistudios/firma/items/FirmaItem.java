package uk.co.aperistudios.firma.items;

import net.minecraft.item.Item;
import uk.co.aperistudios.firma.FirmaMod;

public class FirmaItem extends Item {
	protected String name;

	public FirmaItem(String name) {
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		FirmaMod.allItems.add(this);
	}

	public String getName() {
		return name;
	}

	public String getBlockStateName() {
		return null;
	}

	public String getVariant() {
		return null;
	}

}
