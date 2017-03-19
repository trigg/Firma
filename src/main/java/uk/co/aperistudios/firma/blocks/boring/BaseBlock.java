package uk.co.aperistudios.firma.blocks.boring;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.GravityType;

public abstract class BaseBlock extends Block {
	String name;
	private GravityType gravityType;

	public BaseBlock(Material materialIn, String name) {
		super(materialIn);
		this.name = name;
		this.setUnlocalizedName(FirmaMod.MODID + ":" + name);
		this.setRegistryName(name);
		FirmaMod.allBlocks.add(this);
	}

	public GravityType getGravityType() {
		return gravityType;
	}

	public void setGravityType(GravityType gt) {
		gravityType = gt;
	}

	public abstract ArrayList<String> getVariantNames();

	public abstract String getSpecialName(ItemStack stack);

	public ArrayList<ResourceLocation> getResourceLocations() {
		ArrayList<ResourceLocation> mrl = new ArrayList<ResourceLocation>();
		for (String s : getVariantNames()) {
			mrl.add(new ResourceLocation(FirmaMod.MODID + ":" + this.getUnlocalizedName().substring(5) + "." + s));
		}
		return mrl;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
	}

	public void registerRender() {
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		// .register(Item.getItemFromBlock(this), 0, new
		// ModelResourceLocation(FirmaMod.MODID+":"+this.getUnlocalizedName().substring(5),
		// "inventory"));
		int i = 0;
		Item item = Item.getItemFromBlock(this);
		ResourceLocation[] list = new ResourceLocation[getVariantNames().size()];
		for (String s : getVariantNames()) {

			// Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
			// .register(Item.getItemFromBlock(this), i, new
			// ModelResourceLocation(FirmaMod.MODID + ":" +
			// this.getUnlocalizedName().substring(5)+"."+s, "inventory"));
			// String loc = this.getUnlocalizedName().substring(5)+"."+s;
			String loc = this.getRegistryName().toString();// +"#variants="+s;
			ResourceLocation res = new ResourceLocation(loc);
			// FirmaMod.MODID + ":" +
			// this.getUnlocalizedName().substring(5)+"."+s
			ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants=" + s);
			ModelLoader.setCustomModelResourceLocation(item, i, mrl);
			// ModelBakery.registerItemVariants(Item.getItemFromBlock(this),
			// res);
			list[i] = res;
			i++;
		}
		ModelBakery.registerItemVariants(Item.getItemFromBlock(this), list);
	}

	public String getName() {
		return name;
	}
	
	public abstract String getMetaName(int meta);

}