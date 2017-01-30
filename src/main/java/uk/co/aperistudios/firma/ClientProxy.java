package uk.co.aperistudios.firma;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		// Init Creative Tabs
		FirmaMod.blockTab = new CreativeTabs("FirmaBlocks") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(Item.getItemFromBlock(FirmaMod.rock));
			}
		};
		FirmaMod.itemTab = new CreativeTabs("FirmaItems") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(Item.getItemFromBlock(FirmaMod.rock));
			}
		};
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(FirmaMod.rock), 0, new ModelResourceLocation("firma:rock", "inventory"));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
