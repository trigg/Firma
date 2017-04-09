package uk.co.aperistudios.firma.items;

import java.util.ArrayList;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ToolMaterials;

public class ToolHeads extends MetaItem {

	public ToolHeads(String name) {
		super(name);
		ArrayList<String> list = new ArrayList<String>();
		for (String type : new String[] { "axe", "chisel", "hammer", "hoe", "javelin", "knife", "mace", "pick", "propick", "saw", "scythe", "shovel",
				"sword" }) {
			for (ToolMaterials metals : ToolMaterials.values()) {
				list.add(metals.getName() + type);
			}
		}
		setSubs(list);
		this.setCreativeTab(FirmaMod.headTab);
	}

}
