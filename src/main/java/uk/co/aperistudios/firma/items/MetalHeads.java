package uk.co.aperistudios.firma.items;

import java.util.ArrayList;

import uk.co.aperistudios.firma.FirmaMod;

public class MetalHeads extends MetaItem {

	public MetalHeads(String name) {
		super(name);
		ArrayList<String> list = new ArrayList<String>();
		for(String type : new String[] {"axe", "chisel","hammer","hoe","javelin","knife","mace","pick","propick","saw","scythe","shovel","sword"}){
			for(String mat : new String[] {"bismuthbronze","blackbronze","blacksteel","bluesteel","bronze","copper","redsteel","steel","wroughtiron"}){
				list.add(mat+type+"head");
			}
		}
		setSubs(list);
		this.setCreativeTab(FirmaMod.headTab);
	}

}
