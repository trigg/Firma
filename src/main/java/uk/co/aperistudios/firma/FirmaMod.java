package uk.co.aperistudios.firma;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock;
import uk.co.aperistudios.firma.blocks.boring.BrickBlock2;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock;
import uk.co.aperistudios.firma.blocks.boring.CobbleBlock2;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock;
import uk.co.aperistudios.firma.blocks.boring.DirtBlock2;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock;
import uk.co.aperistudios.firma.blocks.boring.GravelBlock2;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock;
import uk.co.aperistudios.firma.blocks.boring.PlankBlock2;
import uk.co.aperistudios.firma.blocks.boring.RockBlock;
import uk.co.aperistudios.firma.blocks.boring.RockBlock2;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock;
import uk.co.aperistudios.firma.blocks.boring.SmoothBlock2;
import uk.co.aperistudios.firma.items.MetaBlockItem;

@Mod(modid = FirmaMod.MODID, version = FirmaMod.VERSION)
public class FirmaMod
{
	@SidedProxy(clientSide="uk.co.aperistudios.firma.ClientProxy", serverSide="uk.co.aperistudios.firma.ServerProxy")
	public static CommonProxy proxy;
	
    public static final String MODID = "firma";
    public static final String VERSION = "1.0";
    public static CreativeTabs blockTab, itemTab;
    public static RockBlock rock;
    public static RockBlock2 rock2;
    public static BrickBlock rockb;
    public static BrickBlock2 rockb2;
    public static CobbleBlock rockc;
    public static CobbleBlock2 rockc2;
    public static SmoothBlock rocks;
    public static SmoothBlock2 rocks2;
    public static PlankBlock plank;
    public static PlankBlock2 plank2;
	public static DirtBlock dirt;
	public static DirtBlock2 dirt2;
	public static GravelBlock gravel;
	public static GravelBlock2 gravel2;
	public static ArrayList<BaseBlock> allBlocks=new ArrayList<BaseBlock>();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
//s    	MinecraftForge.EVENT_BUS.register(this);
    	proxy.preInit(e);
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
    	FirmaMod.gravel = new GravelBlock(Material.GROUND);
    	FirmaMod.gravel2 = new GravelBlock2(Material.GROUND);
    	
    	FirmaMod.plank = new PlankBlock(Material.WOOD);
    	FirmaMod.plank2 = new PlankBlock2(Material.WOOD);
    	
    	for(BaseBlock b : allBlocks){
    		GameRegistry.register(b);
    		Item i = new MetaBlockItem(b);
    		GameRegistry.register(i);
    		b.registerRender();
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
    
}
