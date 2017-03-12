package uk.co.aperistudios.firma.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSpecialCrafting extends Container {
	private InventoryPlayer invPlayer;
	private World worldObj;
	private SlotSpecialCraftingOutput outputSlot;
	public IInventory craftResult = new InventoryCraftResult();


	public ContainerSpecialCrafting(InventoryPlayer inventoryplayer, ItemStack is, World world, int x, int y, int z)
	{
		invPlayer = inventoryplayer;
		this.worldObj = world; // Must be set before inventorySlotContents to prevent NPE
		int bagsSlotNum = inventoryplayer.currentItem;
		outputSlot = new SlotSpecialCraftingOutput(this, inventoryplayer.player, craftResult, 0, 128, 44);
		addSlotToContainer(outputSlot);

		//PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 108, true, true);

		//this.onCraftMatrixChanged(this.craftMatrix);
		//isConstructing = false;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
