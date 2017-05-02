package uk.co.aperistudios.firma.items;

public class HideItem extends MetaItem {

	public HideItem(String name) {
		super(name);
		setSubs(new String[] { "raw", "soaked", "scraped", "prep", "sheep"});
	}
}
