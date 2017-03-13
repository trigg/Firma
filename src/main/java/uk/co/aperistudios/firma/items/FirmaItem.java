package uk.co.aperistudios.firma.items;

import net.minecraft.item.Item;

public class FirmaItem extends Item {
	protected String name;

	public FirmaItem(String name) {
		this.name = name;
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
	
	public String getName(){
		return name;
	}

}
