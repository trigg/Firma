package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ToolMetals;

public class MetalHeads extends MetaItem {

	public MetalHeads(String name) {
		super(name);
		ArrayList<String> list = new ArrayList<String>();
		for (String type : new String[] { "axe", "chisel", "hammer", "hoe", "javelin", "knife", "mace", "pick", "propick", "saw", "scythe", "shovel",
				"sword" }) {
			for (ToolMetals metals : ToolMetals.values()) {
				list.add(metals.getName() + type);
			}
		}
		setSubs(list);
		this.setCreativeTab(FirmaMod.headTab);
	}

}
