package uk.co.aperistudios.firma.blocks.boring;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
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
		this.lightValue=0;
		this.lightOpacity=15; // Fuck Sake. Don't document something as 0-255 if it causes huge world-gen lag > 15 and assumes 0-15.
		this.fullBlock=true;
		this.translucent=false;
		this.setDefaultState(this.getStateFromMeta(0));
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
		int i = 0;
		Item item = Item.getItemFromBlock(this);
		ResourceLocation[] list = new ResourceLocation[getVariantNames().size()];
		for (String s : getVariantNames()) {

			String loc = this.getRegistryName().toString();// +"#variants="+s;
			ResourceLocation res = new ResourceLocation(loc);
			ModelResourceLocation mrl = new ModelResourceLocation(loc, "variants=" + s);
			ModelLoader.setCustomModelResourceLocation(item, i, mrl);
			list[i] = res;
			i++;
		}
		ModelBakery.registerItemVariants(Item.getItemFromBlock(this), list);
	}

	public String getName() {
		return name;
	}
	
	public abstract String getMetaName(int meta);
	
	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos){
		return false;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return true;
	}
	
	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return this.lightOpacity;
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return this.lightValue;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}
}