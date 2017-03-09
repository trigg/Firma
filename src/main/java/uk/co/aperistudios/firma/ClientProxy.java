package uk.co.aperistudios.firma;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.boring.GrassColor;
import uk.co.aperistudios.firma.blocks.boring.LeafColor;
import uk.co.aperistudios.firma.items.MetaItem;

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
				return new ItemStack(FirmaMod.brick);
			}
		};
		FirmaMod.gemTab = new CreativeTabs("FirmaGems"){
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.gem, 1, 40);
			}
		};
		super.preInit(e);
		
    	for(BaseBlock b : FirmaMod.allBlocks){
    		b.registerRender();
    	}
    	
    	for(Item i : FirmaMod.allItems){
    		if(i instanceof MetaItem){
    			MetaItem mi = (MetaItem) i;
    			ResourceLocation[] list = new ResourceLocation[mi.getSubCount()];
    			for(int d=0;d<mi.getSubCount();d++){
    				String loc = mi.getRegistryName().toString();//+"#variants="+s;
    				ResourceLocation res = new ResourceLocation(loc);
    				// FirmaMod.MODID + ":" + this.getUnlocalizedName().substring(5)+"."+s
    				ModelResourceLocation mrl=new ModelResourceLocation(loc, "variants="+mi.getSubName(d));
    				ModelLoader.setCustomModelResourceLocation(i, d, mrl);
    				//ModelBakery.registerItemVariants(Item.getItemFromBlock(this), res);
    				list[d]=res;
    			}
    			ModelBakery.registerItemVariants(mi,list);
    		}
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
