package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import uk.co.aperistudios.firma.FirmaMod;

public class GemItem extends MetaItem {

	public GemItem(String name) {
		super(name);
		ArrayList<String> list = new ArrayList<String>();
		for (String type : new String[] { "agate", "amethyst", "beryl", "diamond", "emerald", "garnet", "jade", "jasper", "opal", "ruby", "sapphire", "topaz",
				"tourmaline" }) {
			for (String quality : new String[] { "chipped", "flawed", "normal", "flawless", "exquisite" }) {
				list.add(quality + type);
			}
		}
		setSubs(list);
		this.setCreativeTab(FirmaMod.gemTab);
	}

}
