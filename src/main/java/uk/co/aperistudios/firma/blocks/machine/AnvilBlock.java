package uk.co.aperistudios.firma.blocks.machine;

import java.util.ArrayList;
import net.minecraft.block.BlockChest;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.boring.BaseBlock;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.gui.GuiHandler;
import uk.co.aperistudios.firma.gui.GuiKnapping;
import uk.co.aperistudios.firma.items.ToolItem;
import uk.co.aperistudios.firma.player.PlayerData;
import uk.co.aperistudios.firma.types.MetalEnum;
import uk.co.aperistudios.firma.types.ToolType;

public class AnvilBlock extends BaseBlock implements ITileEntityProvider {
	public static final IProperty<MetalEnum> properties = PropertyEnum.create("variants", MetalEnum.class);

	public AnvilBlock(Material materialIn) {
		super(materialIn, "anvil");
		this.setHardness(10);
		this.setResistance(10);
		this.setCreativeTab(FirmaMod.blockTab);
		this.setDefaultState(this.getStateFromMeta(0));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, properties);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
		for (final MetalEnum enumType : MetalEnum.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		MetalEnum type = state.getValue(properties);

		return type.getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(properties, MetalEnum.get(meta));
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack == null) {
			throw new NullPointerException();
		}
		return MetalEnum.getName(stack.getMetadata());
	}

	@Override
	public ArrayList<String> getVariantNames() {
		ArrayList<String> names = new ArrayList<String>();
		for (MetalEnum tr : MetalEnum.values()) {
			names.add(tr.getName());
		}
		return names;
	}

	@Override
	public String getMetaName(int meta) {
		return MetalEnum.getName(meta);
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new AnvilTileEntity();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof AnvilTileEntity) {
			// Item in hand, hopefully a hammer
			ItemStack itemInHand = player.getHeldItemMainhand();
			// Item in bar slot of anvil, hopefully a bar
			ItemStack itemInAnvil = ((AnvilTileEntity) tileentity).getStackInSlot(0);
			ItemStack itemInAnvil2 = ((AnvilTileEntity) tileentity).getStackInSlot(2);

			if (itemInAnvil.getItem() == FirmaMod.ingot && itemInAnvil2.getItem() == Items.AIR) {
				if (itemInHand.getItem() instanceof ToolItem) { // What a Tool
					if (((ToolItem) itemInHand.getItem()).isToolType(ToolType.Hammer)) {
						// TODO Check the hammer/anvil is high enough tier
						// TODO Check for a heatsource & water source or other cooling
						if (worldIn.isRemote) {
							GuiKnapping.staticMaterial = CraftMat.ANVIL;
							GuiKnapping.staticMaterialSub = FirmaMod.ingot.getSubName(itemInAnvil.getItemDamage());
						} else {
							PlayerData pd = PlayerData.getPlayerData(player.getUniqueID());
							pd.resetKnapCraft();
							pd.setTileEntity(player.world,pos);
							pd.setCraftingMaterial(CraftMat.ANVIL);
							pd.setItemStack(itemInAnvil);
						}
						player.openGui(FirmaMod.instance, GuiHandler.GUI_KNAPPING, player.world, pos.getX(), pos.getY(), pos.getZ());
						return true;
						// Open Metal knapping.
					}
				}
			}
			player.openGui(FirmaMod.instance, GuiHandler.GUI_SMITHING, player.world, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}
}
