package uk.co.aperistudios.firma.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import uk.co.aperistudios.firma.FirmaMod;

public class PlayerInv {
	public static int invXSize = 176;
	public static int invYSize = 87;
	private static ResourceLocation invTexture = new ResourceLocation(FirmaMod.MODID+":textures/gui/gui_inventory_lower.png");
	public static InventoryCrafting containerInv;
	//private static int index;
	public static void addInventory(Container container, InventoryPlayer inventory, int x, int y, boolean freezeHeld) {
		int index = 0;
		for (int i = 0; i < 3; ++i) {
			for (int k = 0; k < 9; ++k) {
				index = k + (i + 1) * 9;
				addSlotToContainer(container, new Slot(inventory, index, x + k * 18, y + i * 18));
			}
		}
		addToolbarSlots(container, inventory, x, y, freezeHeld);
	}

	private static void addToolbarSlots(Container container, InventoryPlayer inventory, int x, int y, boolean freezeHeld) {
		for(int j = 0; j < 9; ++j)
		{
			if(freezeHeld && j == inventory.currentItem){
				addSlotToContainer(container, new SlotForShowOnly(inventory, j, x + j * 18, y+58));
			}else{
				addSlotToContainer(container, new Slot(inventory, j, x + j * 18, y+58));
			}
		}
	}

	private static Slot addSlotToContainer(Container container, Slot slot) {
		slot.slotNumber = container.inventorySlots.size();
		container.inventorySlots.add(slot);
		container.inventoryItemStacks.add(new ItemStack(Item.getByNameOrId("minecraft:air")));
		return slot;
	}

	public static void drawInventory(FirmaGuiContainer gui, int width, int height, int shiftedYSize) {
		gui.mc.getTextureManager().bindTexture(invTexture);
		int l = (gui.width - invXSize) / 2;
		int i1 = (gui.height - (shiftedYSize+invYSize)) / 2 + shiftedYSize;
		gui.drawTexturedModalRect(l, i1, 0, 0, invXSize, invYSize);
		//gui.drawTexturedModalRect(0, 0, 0, 0, invXSize, invYSize);
	}
	

	public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot)
	{
		buildInventoryLayout(container, inventory, x, y, false, false);
	}

	public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y)
	{
		buildInventoryLayout(container, inventory, x, y, false);
	}
	
	public static void buildInventoryLayout(Container container, InventoryPlayer inventory, int x, int y, boolean freezeSlot, boolean toolBarAfterMainInv)
	{
		int index = 0;
		if(!toolBarAfterMainInv)
			addToolbarSlots(container, inventory, x, y, freezeSlot);

		for(int i = 0; i < 3; ++i)
		{
			for(int k = 0; k < 9; ++k)
			{
				index =  k + (i+1) * 9;
				addSlotToContainer(container, new Slot(inventory, index, x + k * 18, y + i * 18));
			}
		}

		if(toolBarAfterMainInv)
			addToolbarSlots(container, inventory, x, y, freezeSlot);
	}
}
