package uk.co.aperistudios.firma;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.aperistudios.firma.blocks.boring.RockBlock;

@Mod(modid = FirmaMod.MODID, version = FirmaMod.VERSION)
public class FirmaMod
{
	@SidedProxy(clientSide="uk.co.aperistudios.firma.ClientProxy", serverSide="uk.co.aperistudios.firma.ServerProxy")
	public static CommonProxy proxy;
	
    public static final String MODID = "firma";
    public static final String VERSION = "1.0";
    public static CreativeTabs blockTab, itemTab;
    public static RockBlock rock;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
    }
}
