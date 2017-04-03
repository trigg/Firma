package uk.co.aperistudios.firma.blocks.liquids;

import java.util.function.Consumer;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;

public class BaseLiquid extends Fluid {
	public Item i;

	public BaseLiquid(String fluidName, Consumer<Fluid> f, MapColor col) {
		super(fluidName, new ResourceLocation(FirmaMod.MODID + ":blocks/water_still"), new ResourceLocation(FirmaMod.MODID + ":blocks/water_flow"));
		this.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		FluidRegistry.registerFluid(this);
		f.accept(this);
		block = new BaseBlockLiquid(this, Material.WATER);
		block.setRegistryName(FirmaMod.MODID + ":" + fluidName);
		block.setUnlocalizedName(FirmaMod.MODID + ":blocks.fluid." + fluidName);
		block.setCreativeTab(FirmaMod.blockTab);
		block.setLightOpacity(3);
		block.setLightLevel(0);
		
		GameRegistry.register(block);
		i = new ItemBlock(block);
		i.setRegistryName(block.getRegistryName());
		GameRegistry.register(i);
		FirmaMod.allFluids.add(this);
	}

	public Block getFluidBlock() {
		return block;
	}

	public ResourceLocation getModelPath() {
		return new ResourceLocation(FirmaMod.MODID + ":fluid");
	}

	public Item getFluidItem() {
		return i;
	}
}
