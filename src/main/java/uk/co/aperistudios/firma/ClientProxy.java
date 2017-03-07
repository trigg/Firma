package uk.co.aperistudios.firma;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.boring.GrassColor;
import uk.co.aperistudios.firma.blocks.boring.LeafColor;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
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
		super.preInit(e);
		
    	for(BaseBlock b : FirmaMod.allBlocks){
    		b.registerRender();
    	}
	}

	@Override
	public void init(FMLInitializationEvent e) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf2);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass2);
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
