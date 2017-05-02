package uk.co.aperistudios.firma.container;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.gui.Inventory;
import uk.co.aperistudios.firma.gui.PlayerInv;

public class AnvilContainer extends Container {

	private Random rand;
	private World worldPointer;
	private BlockPos position;
	private AnvilTileEntity te;

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

	@SideOnly(Side.CLIENT)
	public AnvilContainer(InventoryPlayer playerInv, World worldIn, AnvilTileEntity ate) {
		this(playerInv, worldIn, BlockPos.ORIGIN,ate);
		
	}

	public AnvilContainer(InventoryPlayer playerInv, World worldIn, BlockPos pos, AnvilTileEntity ate) {
		this.te = ate;
		this.rand = new Random();
		this.worldPointer = worldIn;
		this.position = pos;
		this.addSlotToContainer(new Slot(ate, 0, 15, 47) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return true;
			}

			@Override
			public int getSlotStackLimit() {
				return 1;
			}
		});
		this.addSlotToContainer(new Slot(ate, 1, 35, 47) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlotToContainer(new Slot(ate, 2, 69, 47) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		PlayerInv.buildInventoryLayout(this, playerInv, 8, 108,true, true);


	}

}
