package uk.co.aperistudios.firma.blocks.liquids;

import java.util.function.Consumer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;

public class BaseLiquid extends Fluid {
	public Item i;
	private int col;

	public BaseLiquid(String fluidName, Consumer<Fluid> f, int col) {
		super(fluidName, new ResourceLocation(FirmaMod.MODID + ":blocks/water_still"), new ResourceLocation(FirmaMod.MODID + ":blocks/water_flow"));
		this.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		FluidRegistry.registerFluid(this);
		f.accept(this);
		block = new BaseBlockLiquid(this, Material.WATER);
		block.setRegistryName(FirmaMod.MODID + ":fluid." + fluidName);
		block.setUnlocalizedName(FirmaMod.MODID + ":fluid." + fluidName);
		block.setCreativeTab(FirmaMod.blockTab);
		block.setLightOpacity(3);
		block.setLightLevel(0);
		
		GameRegistry.register(block);
		i = new ItemBlock(block);
		i.setRegistryName(FirmaMod.MODID+":fluid."+fluidName);
		i.setUnlocalizedName(FirmaMod.MODID+":fluid."+fluidName);
		GameRegistry.register(i);
		FirmaMod.allFluids.add(this);
		this.col = col;
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
	
	@Override
	public int getColor() {
		return col;
	}
	
	@Override
	public int getColor(FluidStack stack) {
		return col;
	}
	
	@Override
	public int getColor(World world, BlockPos pos) {
		return col;
	}
}
