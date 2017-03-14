package uk.co.aperistudios.firma;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.main.Main;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.blocks.CrucibleBlock;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock2;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock2;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock2;
import uk.co.aperistudios.firma.blocks.boring.GrassBlock;
import uk.co.aperistudios.firma.blocks.boring.GrassBlock2;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock2;
import uk.co.aperistudios.firma.blocks.boring.LeafBlock;
import uk.co.aperistudios.firma.blocks.boring.LeafBlock2;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock2;
import uk.co.aperistudios.firma.blocks.boring.RockBlock;
import uk.co.aperistudios.firma.blocks.boring.RockBlock2;
import uk.co.aperistudios.firma.blocks.boring.SandBlock;
import uk.co.aperistudios.firma.blocks.boring.SandBlock2;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock2;
import uk.co.aperistudios.firma.blocks.liquids.BaseLiquid;
import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.gui.GuiHandler;
import uk.co.aperistudios.firma.items.BrickItem;
import uk.co.aperistudios.firma.items.ClayItem;
import uk.co.aperistudios.firma.items.DoubleIngotItem;
import uk.co.aperistudios.firma.items.GemItem;
import uk.co.aperistudios.firma.items.IngotItem;
import uk.co.aperistudios.firma.items.MetaBlockItem;
import uk.co.aperistudios.firma.items.MetalHeads;
import uk.co.aperistudios.firma.items.MetalSheetItem;
import uk.co.aperistudios.firma.items.PebbleItem;
import uk.co.aperistudios.firma.items.ScrapMetalItem;
import uk.co.aperistudios.firma.items.StoneHeads;
import uk.co.aperistudios.firma.items.UnfiredClay;
import uk.co.aperistudios.firma.packet.KnapToServer;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
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
		FirmaMod.gravel = new GravelBlock(Material.GROUND);
		FirmaMod.gravel2 = new GravelBlock2(Material.GROUND);
		FirmaMod.plank = new PlankBlock(Material.WOOD);
		FirmaMod.plank2 = new PlankBlock2(Material.WOOD);
		FirmaMod.sand = new SandBlock(Material.GROUND);
		FirmaMod.sand2 = new SandBlock2(Material.GROUND);
		FirmaMod.leaf = new LeafBlock(Material.LEAVES);
		FirmaMod.leaf2 = new LeafBlock2(Material.LEAVES);
		FirmaMod.crucible = new CrucibleBlock();

		FirmaMod.pebble = new PebbleItem("pebble");
		FirmaMod.brick = new BrickItem("brickitem");
		FirmaMod.gem = new GemItem("gem");
		FirmaMod.ingot = new IngotItem("ingot");
		FirmaMod.doubleingot = new DoubleIngotItem("doubleingot");
		FirmaMod.metalsheet = new MetalSheetItem("metalsheet");
		FirmaMod.scrapmetal = new ScrapMetalItem("scrapmetal");
		FirmaMod.unfiredClayBits = new UnfiredClay("unfiredclay");
		FirmaMod.metalHeads = new MetalHeads("metalheads");
		FirmaMod.stoneHeads = new StoneHeads("stoneheads");
		FirmaMod.clay = new ClayItem("clay");

		FirmaMod.saltwater = new BaseLiquid("saltwater", fluid -> fluid.setLuminosity(10).setDensity(800).setViscosity(1500), MapColor.WATER);
		FirmaMod.freshwater = new BaseLiquid("freshwater", fluid -> fluid.setLuminosity(10).setDensity(800).setViscosity(1500), MapColor.WATER);

		NetworkRegistry.INSTANCE.registerGuiHandler(FirmaMod.instance, new GuiHandler());

		KnapToServer.init();
		CraftingManager.addKnappingRecipes();

		for (BaseBlock b : FirmaMod.allBlocks) {
			GameRegistry.register(b);
			Item i = new MetaBlockItem(b);
			GameRegistry.register(i);
		}

		for (Item i : FirmaMod.allItems) {
			GameRegistry.register(i);
		}
		FluidRegistry.enableUniversalBucket();
		for (Fluid f : FirmaMod.allFluids) {

			FluidRegistry.addBucketForFluid(f);
		}
		
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
