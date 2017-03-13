package uk.co.aperistudios.firma.items;

public class BrickItem extends MetaItem {

	public BrickItem(String name) {
		super(name);
		setSubs(new String[] { "andesite", "basalt", "chalk", "chert", "claystone", "conglomerate", "dacite", "diorite", "dolomite", "gabbro", "gneiss",
				"granite", "limestone", "marble", "phyllite", "quartzite", "rhyolite", "rocksalt", "schist", "shale", "slate", "clayfirebrick", "firebrick" });
	}

}
