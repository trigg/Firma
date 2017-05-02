	package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.gui.PlayerInv;

public class ContainerSpecialCrafting extends Container {
	private SlotSpecialCraftingOutput outputSlot;
	public IInventory craftResult = new InventoryCraftResult();

	public ContainerSpecialCrafting(InventoryPlayer inventoryplayer, int x, int y, int z) {
		outputSlot = new SlotSpecialCraftingOutput(this, inventoryplayer.player, craftResult, 0, 128, 44);
		addSlotToContainer(outputSlot);

		PlayerInv.buildInventoryLayout(this, inventoryplayer, 8, 108,true, true);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		if (!player.world.isRemote)
		{
			// DO NOT DROP THE ITEM FOR FREE
			//ItemStack is = this.craftResult.getStackInSlot(0); 
			//if (is != null)
			//	player.entityDropItem(is, 0);
		}
	}

}
