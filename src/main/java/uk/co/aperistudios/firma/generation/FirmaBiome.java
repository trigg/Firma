package uk.co.aperistudios.firma.generation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.generation.tree.BasicLeafFiller;
import uk.co.aperistudios.firma.generation.tree.ConicalLeafFiller;
import uk.co.aperistudios.firma.generation.tree.CedarFiller;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;
import uk.co.aperistudios.firma.generation.tree.TreeCedar;
import uk.co.aperistudios.firma.generation.tree.TreeFir;
import uk.co.aperistudios.firma.generation.tree.TreeGeneric;
import uk.co.aperistudios.firma.generation.tree.TreeWillow;
import uk.co.aperistudios.firma.generation.tree.WillowFiller;

public class FirmaBiome extends Biome {
	private static FirmaBiome[] biomeList = new FirmaBiome[256];
	
	private Type[] types;
	private Color col;

	public FirmaBiome(BiomeProperties properties, int id, Type... types) {
		super(properties.setHeightVariation(0f));
		this.types = types;
		if(FirmaBiome.biomeList[id]==null){
			FirmaBiome.biomeList[id] = this;
		}else{
			throw new RuntimeException("Already got a Biome for id "+id+" / "+FirmaBiome.biomeList[id]);
		}
	}

	public static HashMap<String, FirmaTree> firmaTrees = new HashMap<String, FirmaTree>();

	public static void init() {
		firmaTrees.put("oak", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("oak"));
		firmaTrees.put("ash", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("ash"));
		firmaTrees.put("aspen", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("aspen"));
		firmaTrees.put("birch", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("birch"));
		firmaTrees.put("chestnut", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("chestnut"));
		firmaTrees.put("hickory", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("hickory"));
		firmaTrees.put("spruce", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("spruce"));
		firmaTrees.put("sycamore", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("sycamore"));
		firmaTrees.put("whiteelm", new TreeGeneric(3, 1, new BasicLeafFiller(), 2).set("whiteelm"));
		firmaTrees.put("pine", new TreeGeneric(6, 1, new ConicalLeafFiller(), 1).set("pine"));
		firmaTrees.put("maple", new TreeGeneric(4, 1, new BasicLeafFiller(), 3).set("maple"));
		firmaTrees.put("acacia", new TreeWillow(5, new BasicLeafFiller()).set("acacia"));
		firmaTrees.put("willow", new TreeWillow(0, new WillowFiller()).set("willow"));
		firmaTrees.put("whitecedar", new TreeCedar(new CedarFiller()).set("whitecedar"));
		firmaTrees.put("kapok", new TreeWillow(3, new CedarFiller()).set("kapok"));
		firmaTrees.put("sequoia", new TreeFir(4, new ConicalLeafFiller()).set("sequoia"));
		firmaTrees.put("douglasfir", new TreeFir(2, new CedarFiller()).set("douglasfir"));
	}

	public static FirmaTree getTreeGen(String name) {
		return firmaTrees.get(name);
	}

	public static ArrayList<FirmaTree> getAllTreeGen() {
		ArrayList<FirmaTree> treeList = new ArrayList<FirmaTree>();

		return treeList;
	}

	public static FirmaBiome GRAVELBEACH = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmagravelbeach").setBaseBiome("").setBaseHeight(0.01f).setHeightVariation(0.02f),17, Type.BEACH).setBiomeColor(Color.YELLOW).setRegistryName("firmagravelbeach");
	public static FirmaBiome BEACH = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmabeach").setBaseHeight(0.01f).setHeightVariation(0.02f),16, Type.BEACH).setBiomeColor(Color.YELLOW).setRegistryName("firmabeach");
	public static FirmaBiome PLAINS = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaplains").setBaseHeight(0.1f).setHeightVariation(0.16f),1, Type.PLAINS).setBiomeColor(Color.GREEN).setRegistryName("firmaplains");
	public static FirmaBiome HILLS = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmahills").setBaseHeight(0.8f).setHeightVariation(1.6f),30, Type.HILLS).setBiomeColor(Color.LIGHT_GRAY).setRegistryName("firmahills");
	public static FirmaBiome EXHILLS = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaexhills").setBaseHeight(0.8f).setHeightVariation(1.6f),3, Type.MOUNTAIN).setBiomeColor(Color.GRAY).setRegistryName("firmaexhills");
	public static FirmaBiome OCEAN = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaocean").setBaseHeight(-0.9f).setHeightVariation(0.0001f),0, Type.OCEAN).setBiomeColor(new Color(0f,0f,1f)).setRegistryName("firmaocean");
	public static FirmaBiome FOREST = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaforest").setBaseHeight(0.1f).setHeightVariation(0.16f),35, Type.FOREST).setBiomeColor(Color.GREEN).setRegistryName("firmaforest");
	public static FirmaBiome DEEPOCEAN = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmadocean").setBaseHeight(-1.5f).setHeightVariation(0.0001f),36, Type.OCEAN).setBiomeColor(new Color(0f,0f,.5f)).setRegistryName("firmadocean");
	public static FirmaBiome LAKE = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmalake").setBaseHeight(-0.8f).setHeightVariation(0.001f),2, Type.RIVER).setBiomeColor(new Color(.3f,.3f,.8f)).setRegistryName("firmalake");
	public static FirmaBiome SWAMP = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaswamp").setBaseHeight(-0.1f).setHeightVariation(0.11f),6, Type.SWAMP).setBiomeColor(new Color(0f,.4f,.8f)).setRegistryName("firmaswmap");
	public static FirmaBiome EXHILLSEDGE = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmaexhillsedge").setBaseHeight(0.4f).setHeightVariation(0.8f),32, Type.MOUNTAIN).setBiomeColor(Color.DARK_GRAY)
			.setRegistryName("firmaexhillsedge");
	public static FirmaBiome HILLSEDGE = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmahillsedge").setBaseHeight(0.2f).setHeightVariation(0.4f),20, Type.HILLS).setBiomeColor(Color.GRAY).setRegistryName("firmahillsedge");
	public static FirmaBiome RIVER = (FirmaBiome) new FirmaBiome(new BiomeProperties("firmariver").setBaseHeight(-0.5f).setHeightVariation(-0.0f),7, Type.RIVER).setBiomeColor(new Color(.3f,.3f,.8f)).setRegistryName("firmariver");

	// All Biomes
	public static FirmaBiome[] biomes = { GRAVELBEACH, BEACH, PLAINS, HILLS, EXHILLS, OCEAN, FOREST, DEEPOCEAN, LAKE, SWAMP, EXHILLSEDGE, HILLSEDGE, RIVER };
	

	public void reg() {
		GameRegistry.register(this);
		BiomeDictionary.addTypes(this, types);
	}

	public FirmaBiome setBiomeColor(Color col){
		this.col =col;
		return this;
	}

	public Color getBiomeColor() {
		return col;
	}
	
	public static FirmaBiome[] getBiomeList() {
		return biomeList.clone();
	}
}
