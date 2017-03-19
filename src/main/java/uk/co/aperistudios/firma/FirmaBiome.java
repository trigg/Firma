package uk.co.aperistudios.firma;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import uk.co.aperistudios.firma.generation.tree.BasicLeafFiller;
import uk.co.aperistudios.firma.generation.tree.ConicalLeafFiller;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;
import uk.co.aperistudios.firma.generation.tree.TreeGeneric;
import uk.co.aperistudios.firma.generation.tree.TreeWillow;
import uk.co.aperistudios.firma.generation.tree.WillowFiller;

public class FirmaBiome extends Biome{
	
	public static FirmaTree normalTree = new TreeGeneric(3, 1, new BasicLeafFiller(), 2),
						    largeTree = new TreeGeneric(4, 1, new BasicLeafFiller(), 3),
						    conicalTree = new TreeGeneric(6, 1, new ConicalLeafFiller(),1),
						    willowTree = new TreeWillow(new WillowFiller());

	public FirmaBiome(BiomeProperties properties) {
		super(properties);
	}

	public static FirmaTree getTreeGen(String name) {
		switch(name){
		case "oak":
		case "ash":
		case "aspen":
		case "birch":
		case "chestnut":
		case "hickory":
		case "spruce":
		case "sycamore":
		case "whiteelm":
			return normalTree;
		case "pine":
			return conicalTree;
			
		case "maple":
			return largeTree;
			
		case "sequoia": // Tall, Teardrop
			return null;
		case "acacia": // Huge, Sprawling
			return null;
		case "douglasfir": // Med Tall, teardrop
			return null;
		case "kapok":    //Tall, some short branches
			return null;
		case "whitecedar": // Med tall, one wide leaves
			return null;
		case "willow": // Short, sprawling
			return willowTree;
		}
		return normalTree;
	}

}
