package uk.co.aperistudios.firma.items;

import uk.co.aperistudios.firma.FirmaMod;

public class StoneHeads extends MetaItem {

	public StoneHeads(String name) {
		super(name);
		setSubs(new String[] { "knife", "axe", "hammer", "hoe", "javelin", "knife", "shovel" });
		this.setCreativeTab(FirmaMod.headTab);

	}

}
