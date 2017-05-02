package uk.co.aperistudios.firma;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.blocks.CrucibleBlock;
import uk.co.aperistudios.firma.blocks.OreBlock;
import uk.co.aperistudios.firma.blocks.ShitOnFloor;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock2;
import uk.co.aperistudios.firma.blocks.boring.ClayBlock;
import uk.co.aperistudios.firma.blocks.boring.ClayBlock2;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock2;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock2;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock2;
import uk.co.aperistudios.firma.blocks.boring.IceBlock;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock2;
import uk.co.aperistudios.firma.blocks.boring.RockBlock;
import uk.co.aperistudios.firma.blocks.boring.RockBlock2;
import uk.co.aperistudios.firma.blocks.boring.SandBlock;
import uk.co.aperistudios.firma.blocks.boring.SandBlock2;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock2;
import uk.co.aperistudios.firma.blocks.liquids.BaseLiquid;
import uk.co.aperistudios.firma.blocks.living.GrassBlock;
import uk.co.aperistudios.firma.blocks.living.GrassBlock2;
import uk.co.aperistudios.firma.blocks.living.LeafBlock;
import uk.co.aperistudios.firma.blocks.living.LeafBlock2;
import uk.co.aperistudios.firma.blocks.living.LogBlock;
import uk.co.aperistudios.firma.blocks.living.LogBlock2;
import uk.co.aperistudios.firma.blocks.living.SaplingBlock;
import uk.co.aperistudios.firma.blocks.living.SaplingBlock2;
import uk.co.aperistudios.firma.blocks.living.SparseGrassBlock;
import uk.co.aperistudios.firma.blocks.living.SparseGrassBlock2;
import uk.co.aperistudios.firma.blocks.machine.AnvilBlock;
import uk.co.aperistudios.firma.blocks.recolour.LeafColor;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.blocks.tileentity.FirmaOreTileEntity;
import uk.co.aperistudios.firma.blocks.tileentity.SoFTileEntity;
import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.generation.FirmaBiome;
import uk.co.aperistudios.firma.generation.FirmaOreGen;
import uk.co.aperistudios.firma.generation.FirmaTreeGen;
import uk.co.aperistudios.firma.generation.FirmaWorld;
import uk.co.aperistudios.firma.generation.FirmaWorldProvider;
import uk.co.aperistudios.firma.generation.ShitOnFloorGen;
import uk.co.aperistudios.firma.generation.layers.Layer;
import uk.co.aperistudios.firma.generation.tree.FirmaTree;
import uk.co.aperistudios.firma.gui.GuiHandler;
import uk.co.aperistudios.firma.handler.JoinHandler;
import uk.co.aperistudios.firma.items.BrickItem;
import uk.co.aperistudios.firma.items.ClayItem;
import uk.co.aperistudios.firma.items.DoubleIngotItem;
import uk.co.aperistudios.firma.items.FirmaItem;
import uk.co.aperistudios.firma.items.GemItem;
import uk.co.aperistudios.firma.items.HideItem;
import uk.co.aperistudios.firma.items.IngotItem;
import uk.co.aperistudios.firma.items.MetaBlockItem;
import uk.co.aperistudios.firma.items.ToolHeads;
import uk.co.aperistudios.firma.items.MetalSheetItem;
import uk.co.aperistudios.firma.items.OreItem;
import uk.co.aperistudios.firma.items.PebbleItem;
import uk.co.aperistudios.firma.items.ScrapMetalItem;
import uk.co.aperistudios.firma.items.ToolItem;
import uk.co.aperistudios.firma.items.UnfiredClay;
import uk.co.aperistudios.firma.packet.KnapToServer;
import uk.co.aperistudios.firma.packet.SetDayPacket;
import uk.co.aperistudios.firma.types.AlcoholType;
import uk.co.aperistudios.firma.types.RockEnum;
import uk.co.aperistudios.firma.types.RockEnum2;
import uk.co.aperistudios.firma.types.ToolMaterials;
import uk.co.aperistudios.firma.types.ToolType;

public abstract class CommonProxy {

	public static DimensionType firmaDimension;
	public static IBlockState[] rockLayerTop, rockLayerMid, rockLayerBot, saplingLayer;
	public static int d = 0;

	FirmaWorld fw = new FirmaWorld();

	
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new JoinHandler());

		FirmaMod.rock = new RockBlock(Material.ROCK);
		FirmaMod.rock2 = new RockBlock2(Material.ROCK);
		FirmaMod.rockb = new BrickBlock(Material.ROCK);
		FirmaMod.rockb2 = new BrickBlock2(Material.ROCK);
		FirmaMod.rockc = new CobbleBlock(Material.ROCK);
		FirmaMod.rockc2 = new CobbleBlock2(Material.ROCK);
		FirmaMod.rocks = new SmoothBlock(Material.ROCK);
		FirmaMod.rocks2 = new SmoothBlock2(Material.ROCK);
		FirmaMod.dirt = new DirtBlock(Material.GROUND);
		FirmaMod.dirt2 = new DirtBlock2(Material.GROUND);
		FirmaMod.grass = new GrassBlock(Material.GROUND);
		FirmaMod.grass2 = new GrassBlock2(Material.GROUND);
		FirmaMod.grasss = new SparseGrassBlock(Material.GROUND);
		FirmaMod.grasss2 = new SparseGrassBlock2(Material.GROUND);
		FirmaMod.gravel = new GravelBlock(Material.GROUND);
		FirmaMod.gravel2 = new GravelBlock2(Material.GROUND);
		FirmaMod.plank = new PlankBlock(Material.WOOD);
		FirmaMod.plank2 = new PlankBlock2(Material.WOOD);
		FirmaMod.sand = new SandBlock(Material.GROUND);
		FirmaMod.sand2 = new SandBlock2(Material.GROUND);
		FirmaMod.leaf = new LeafBlock(Material.LEAVES);
		FirmaMod.leaf2 = new LeafBlock2(Material.LEAVES);
		FirmaMod.ice = new IceBlock(Material.ICE);
		FirmaMod.clayBlock = new ClayBlock(Material.GROUND);
		FirmaMod.clayBlock2 = new ClayBlock2(Material.GROUND);

		FirmaMod.sapling = new SaplingBlock(Material.PLANTS);
		FirmaMod.sapling2 = new SaplingBlock2(Material.PLANTS);
		FirmaMod.log = new LogBlock(Material.WOOD);
		FirmaMod.log2 = new LogBlock2(Material.WOOD);

		FirmaMod.shitOnFloor = new ShitOnFloor(Material.PLANTS);
		FirmaMod.crucible = new CrucibleBlock();
		FirmaMod.anvil = new AnvilBlock(Material.ANVIL);
		FirmaMod.ore = new OreBlock();

		FirmaMod.pebble = new PebbleItem("pebble");
		FirmaMod.oreItem = new OreItem("oreitem");
		FirmaMod.brick = new BrickItem("brickitem");
		FirmaMod.gem = new GemItem("gem");
		FirmaMod.ingot = new IngotItem("ingot");
		FirmaMod.doubleingot = new DoubleIngotItem("doubleingot");
		FirmaMod.metalsheet = new MetalSheetItem("metalsheet");
		FirmaMod.scrapmetal = new ScrapMetalItem("scrapmetal");
		FirmaMod.unfiredClayBits = new UnfiredClay("unfiredclay");
		FirmaMod.toolHeads = new ToolHeads("toolheads");
		FirmaMod.clay = new ClayItem("clay");
		FirmaMod.hide = new HideItem("hide");

		rockLayerTop = new IBlockState[] { FirmaMod.rock2.getStateFromMeta(RockEnum2.Shale.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Claystone.getMeta()), FirmaMod.rock2.getStateFromMeta(RockEnum2.RockSalt.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Limestone.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Conglomerate.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Dolomite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Chert.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Chalk.getMeta()), FirmaMod.rock2.getStateFromMeta(RockEnum2.Rhyolite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Basalt.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Andesite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Dacite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Quartzite.getMeta()),
				FirmaMod.rock2.getStateFromMeta(RockEnum2.Slate.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Phyllite.getMeta()),
				FirmaMod.rock2.getStateFromMeta(RockEnum2.Schist.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Gneiss.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Marble.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Granite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Diorite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Gabbro.getMeta()) };
		rockLayerMid = new IBlockState[] { FirmaMod.rock2.getStateFromMeta(RockEnum2.Rhyolite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Basalt.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Andesite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Dacite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Quartzite.getMeta()),
				FirmaMod.rock2.getStateFromMeta(RockEnum2.Slate.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Phyllite.getMeta()),
				FirmaMod.rock2.getStateFromMeta(RockEnum2.Schist.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Gneiss.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Marble.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Granite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Diorite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Gabbro.getMeta()), };
		rockLayerBot = new IBlockState[] { FirmaMod.rock2.getStateFromMeta(RockEnum2.Rhyolite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Basalt.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Andesite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Dacite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Granite.getMeta()),
				FirmaMod.rock.getStateFromMeta(RockEnum.Diorite.getMeta()), FirmaMod.rock.getStateFromMeta(RockEnum.Gabbro.getMeta()), };
		saplingLayer = new IBlockState[] { FirmaMod.sapling.getStateFromMeta(0), FirmaMod.sapling.getStateFromMeta(1), FirmaMod.sapling.getStateFromMeta(2),
				FirmaMod.sapling.getStateFromMeta(3), FirmaMod.sapling.getStateFromMeta(4), FirmaMod.sapling.getStateFromMeta(5),
				FirmaMod.sapling.getStateFromMeta(6), FirmaMod.sapling.getStateFromMeta(7), FirmaMod.sapling.getStateFromMeta(8),
				FirmaMod.sapling.getStateFromMeta(9), FirmaMod.sapling.getStateFromMeta(10), FirmaMod.sapling.getStateFromMeta(11),
				FirmaMod.sapling.getStateFromMeta(12), FirmaMod.sapling.getStateFromMeta(13), FirmaMod.sapling.getStateFromMeta(14),
				FirmaMod.sapling.getStateFromMeta(15), FirmaMod.sapling2.getStateFromMeta(0) };

		ToolItem thisTool = null;
		for (ToolMaterials tm : ToolMaterials.values()) {
			for (ToolType tt : ToolType.values()) {
				thisTool = new ToolItem(tm, tt);
				FirmaMod.bunchOfTools.add(thisTool);
				thisTool.addRecipe();
			}
		}
		
		FirmaMod.lava = new BaseLiquid("lava", fluid -> fluid.setLuminosity(100).setDensity(800).setViscosity(1500), 0xffff0000);
		FirmaMod.saltwater = new BaseLiquid("saltwater", fluid -> fluid.setLuminosity(0).setDensity(800).setViscosity(1500), 0xff0022ff);
		FirmaMod.freshwater = new BaseLiquid("freshwater", fluid -> fluid.setLuminosity(0).setDensity(800).setViscosity(1500), 0xff0022ff);
		for(AlcoholType at : AlcoholType.values()){ 
			new BaseLiquid(at.getName(), fluid -> fluid.setLuminosity(0).setDensity(800).setViscosity(1500), at.getCol());
		}
		
		NetworkRegistry.INSTANCE.registerGuiHandler(FirmaMod.instance, new GuiHandler());

		KnapToServer.init();
		SetDayPacket.init();
		
		CraftingManager.addKnappingRecipes();

		for (BaseBlock b : FirmaMod.allBlocks) {
			GameRegistry.register(b);
			Item i = new MetaBlockItem(b);
			GameRegistry.register(i);
		}

		GameRegistry.register(FirmaMod.crucible);
		GameRegistry.register(FirmaMod.shitOnFloor);

		for (FirmaItem i : FirmaMod.allItems) {
			GameRegistry.register(i);
		}
		for (Fluid f : FirmaMod.allFluids) {

			FluidRegistry.addBucketForFluid(f);
		}

		FirmaBiome.init();
		// Dirty hack to put Firma as first world to gen before default

		int i = fw.getWorldTypeID();
		WorldType.WORLD_TYPES[i] = WorldType.WORLD_TYPES[0];
		WorldType.WORLD_TYPES[0] = fw;

		// d = DimensionManager.getNextFreeDimId();
		DimensionManager.unregisterDimension(0);
		firmaDimension = DimensionType.register("Firma", "-" + d, d, FirmaWorldProvider.class, true);
		DimensionManager.registerDimension(d, firmaDimension);

		for (FirmaBiome b : FirmaBiome.biomes) {
			b.reg();
		}

		for (FirmaTree a : FirmaBiome.getAllTreeGen()) {
			GameRegistry.registerWorldGenerator(a, 0);
		}

		Layer.prep();

		GameRegistry.registerTileEntity(FirmaOreTileEntity.class, "firmaorete");
		GameRegistry.registerTileEntity(SoFTileEntity.class, "firmasof");
		GameRegistry.registerTileEntity(AnvilTileEntity.class, "firmaanvil");

		GameRegistry.registerWorldGenerator(new FirmaOreGen(), 0);
		GameRegistry.registerWorldGenerator(new FirmaTreeGen(), 0);
		GameRegistry.registerWorldGenerator(new ShitOnFloorGen(), 0);
		
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public abstract void setDate(TimeData data);
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if(event.world.isRemote){ return; } // Not called on client
		long time = event.world.getWorldTime();
		if (time > Util.ticksInDay) {
			MapStorage storage = event.world.getPerWorldStorage();
			TimeData td = (TimeData) storage.getOrLoadData(TimeData.class, "firmatime");
			if(td==null){
				td = new TimeData("");
				storage.setData("firmatime", td);
			}
			td.addDay();
			event.world.setWorldTime(time - Util.ticksInDay);
			td.setDirty(true);
			System.out.println("Day inceremented on Server " + td.toString());
		}
	}
}
