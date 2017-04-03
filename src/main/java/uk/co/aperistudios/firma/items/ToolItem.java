package uk.co.aperistudios.firma.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ToolMetals;
import uk.co.aperistudios.firma.types.ToolType;

public class ToolItem extends FirmaItem {
	private ToolType tt;
	private ToolMetals tm;

	public ToolItem(ToolMetals tm, ToolType tt) {
		super("tool." + tm.getName() + tt.getName());
		this.tm = tm;
		this.tt = tt;

		this.setCreativeTab(FirmaMod.toolTab);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState bs) {
		Block b = bs.getBlock();
		boolean isRock = (b == FirmaMod.rock || b == FirmaMod.rock2 || b == FirmaMod.rockb || b == FirmaMod.rockb2 || b == FirmaMod.rockc
				|| b == FirmaMod.rockc2 || b == FirmaMod.rocks || b == FirmaMod.rocks2);
		boolean isWood = (b == FirmaMod.plank || b == FirmaMod.plank2); // (b==FirmaMod)
		boolean isLeaf = (b == FirmaMod.leaf || b == FirmaMod.leaf2);
		boolean isDirt = (b == FirmaMod.dirt || b == FirmaMod.dirt2 || b == FirmaMod.grass || b == FirmaMod.grass2 || b == FirmaMod.gravel
				|| b == FirmaMod.gravel2 || b == FirmaMod.sand || b == FirmaMod.sand2 || b == FirmaMod.grasss || b == FirmaMod.grasss2);
		switch (tt) {
		case Axe:
			if (isWood) {
				return tm.getHarvestLevel();
			}
			break;
		case Chisel:
			break;
		case Hammer:
			break;
		case Hoe:
			break;
		case Javelin:
			break;
		case Knife:
			if (isLeaf) {
				return tm.getHarvestLevel();
			}
			break;
		case Mace:
			break;
		case Pick:
			if (isRock) {
				return tm.getHarvestLevel();
			}
			break;
		case ProPick:
			break;
		case Saw:
			break;
		case Scythe:
			if (isRock) {
				return tm.getHarvestLevel();
			}
			break;
		case Shovel:
			if (isDirt) {
				return tm.getHarvestLevel();
			}
			break;
		case Sword:
			break;
		default:
			break;
		}
		return -1;
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		switch (tt) {
		case Axe:
			break;
		case Chisel:
			break;
		case Hammer:
			break;
		case Hoe:
			break;
		case Javelin:
			break;
		case Knife:
			break;
		case Mace:
			break;
		case Pick:
			break;
		case ProPick:
			break;
		case Saw:
			break;
		case Scythe:
			break;
		case Shovel:
			break;
		case Sword:
			break;
		default:
			break;
		}
		return super.canHarvestBlock(blockIn);
	}

	public void addRecipe() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBlockStateName() {
		return FirmaMod.MODID + ":tool";
	}

	@Override
	public String getVariant() {
		return tm.getName() + tt.getName();
	}

}
