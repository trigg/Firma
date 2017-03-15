package uk.co.aperistudios.firma;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.liquids.BaseLiquid;
import uk.co.aperistudios.firma.blocks.recolour.GrassColor;
import uk.co.aperistudios.firma.blocks.recolour.LeafColor;
import uk.co.aperistudios.firma.blocks.recolour.LiquidColor;
import uk.co.aperistudios.firma.blocks.recolour.LiquidItemColor;
import uk.co.aperistudios.firma.items.FirmaItem;
import uk.co.aperistudios.firma.items.MetaItem;
import net.minecraft.client.renderer.ItemMeshDefinition;

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
		FirmaMod.gemTab = new CreativeTabs("FirmaGems") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.gem, 1, 40);
			}
		};
		FirmaMod.headTab = new CreativeTabs("FirmaHead") {
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.metalHeads, 1, 14);
			}
		};
		FirmaMod.toolTab = new CreativeTabs("FirmaTools"){
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(FirmaMod.bunchOfTools.get(4));
			}
		};
		super.preInit(e);

		for (BaseBlock b : FirmaMod.allBlocks) {
			b.registerRender();
		}

		for (FirmaItem i : FirmaMod.allItems) {
			if (i instanceof MetaItem) {
				MetaItem mi = (MetaItem) i;
				ResourceLocation[] list = new ResourceLocation[mi.getSubCount()];
				for (int d = 0; d < mi.getSubCount(); d++) {
					String loc = mi.getRegistryName().toString();// +"#variants="+s;
					ResourceLocation res = new ResourceLocation(loc);
					// FirmaMod.MODID + ":" +
					// this.getUnlocalizedName().substring(5)+"."+s
					ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants=" + mi.getSubName(d));
					ModelLoader.setCustomModelResourceLocation(i, d, mrl);
					// ModelBakery.registerItemVariants(Item.getItemFromBlock(this),
					// res);
					list[d] = res;
				}
				ModelBakery.registerItemVariants(mi, list);
			}else{
				FirmaItem fi = (FirmaItem) i;
				String loc = fi.getBlockStateName();
				ResourceLocation rl = new ResourceLocation(loc);
				ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants="+fi.getVariant());
				ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
				//ModelBakery.registerItemVariants(fi, rl);
			}
		}

		for (BaseLiquid f : FirmaMod.allFluids) {
			final Item item = Item.getItemFromBlock((Block) f.getFluidBlock());
			assert !(item instanceof ItemAir);

			ModelBakery.registerItemVariants(item);

			ItemMeshDefinition imd = new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return new ModelResourceLocation(f.getModelPath(), ((IFluidBlock) f.getFluidBlock()).getFluid().getName());
				}
			};
			ModelLoader.setCustomMeshDefinition(item, imd);

			ModelLoader.setCustomStateMapper((Block) f.getFluidBlock(), new StateMapperBase() {
				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
					return new ModelResourceLocation(f.getModelPath(), ((IFluidBlock) f.getFluidBlock()).getFluid().getName());
				}
			});
		}
	}

	@Override
	public void init(FMLInitializationEvent e) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LeafColor(), FirmaMod.leaf2);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new GrassColor(), FirmaMod.grass2);
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LiquidColor(0xffaaff00), FirmaMod.saltwater.getFluidBlock());
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new LiquidColor(0xffaaff00), FirmaMod.freshwater.getFluidBlock());
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new LiquidItemColor(), FirmaMod.saltwater.getFluidItem());
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
