package uk.co.aperistudios.firma.items;

import org.lwjgl.input.Mouse;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.types.ToolMaterials;
import uk.co.aperistudios.firma.types.ToolType;

public class ToolItem extends FirmaItem {
	private ToolType tt;
	private ToolMaterials tm;

	public ToolItem(ToolMaterials tm, ToolType tt) {
		super("tool." + tm.getName() + tt.getName());
		this.tm = tm;
		this.tt = tt;
		this.maxStackSize=1;

		this.setCreativeTab(FirmaMod.toolTab);
	}

	private static boolean isDirt(Block b) {
		return (b == FirmaMod.dirt || b == FirmaMod.dirt2 || b == FirmaMod.grass || b == FirmaMod.grass2 || b == FirmaMod.gravel || b == FirmaMod.gravel2
				|| b == FirmaMod.sand || b == FirmaMod.sand2 || b == FirmaMod.grasss || b == FirmaMod.grasss2);
	}

	private static boolean isLeaf(Block b) {
		return (b == FirmaMod.leaf || b == FirmaMod.leaf2);
	}

	private static boolean isWood(Block b) {
		return (b == FirmaMod.plank || b == FirmaMod.plank2 || b == FirmaMod.log || b == FirmaMod.log2);
	}

	private static boolean isRock(Block b) {
		return (b == FirmaMod.rock || b == FirmaMod.rock2 || b == FirmaMod.rockb || b == FirmaMod.rockb2 || b == FirmaMod.rockc || b == FirmaMod.rockc2
				|| b == FirmaMod.rocks || b == FirmaMod.rocks2);
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		Block b = blockIn.getBlock();
		switch (tt) {
		case Axe:
			return isWood(b);
		case Chisel:
			break;
		case Hammer:
			break;
		case Hoe:
			break;
		case Javelin:
			break;
		case Knife:
			return isLeaf(b);
		case Mace:
			break;
		case Pick:
			return isRock(b);
		case ProPick:
			break;
		case Saw:
			break;
		case Scythe:
			return isLeaf(b);
		case Shovel:
			return isDirt(b);
		case Sword:
			break;
		default:
			break;
		}
		return super.canHarvestBlock(blockIn);
	}

	public void addRecipe() {
		GameRegistry.addShapelessRecipe(new ItemStack(this), new Object[] {Items.STICK, new ItemStack(FirmaMod.toolHeads, 1, FirmaMod.toolHeads.getSubMeta(tm.getName()+tt.getName()))});
	}

	@Override
	public String getBlockStateName() {
		return FirmaMod.MODID + ":tool";
	}

	@Override
	public String getVariant() {
		return tm.getName() + tt.getName();
	}
	
	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Block b = state.getBlock();
		float str= tm.getHarvestLevel();
		switch (tt) {
		case Axe:
			return isWood(b) ? str :1f;
		case Chisel:
			break;
		case Hammer:
			break;
		case Hoe:
			break;
		case Javelin:
			break;
		case Knife:
			return isLeaf(b) ? str : 1f;
		case Mace:
			break;
		case Pick:
			return isRock(b)? str : 1f;
		case ProPick:
			break;
		case Saw:
			break;
		case Scythe:
			return isLeaf(b)? str : 1f;
		case Shovel:
			return isDirt(b)? str : 1f;
		case Sword:
			break;
		default:
			break;
		}
		return 1f;
	}

}
