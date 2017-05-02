package uk.co.aperistudios.firma.gui;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.ITextComponent;
import uk.co.aperistudios.firma.container.AnvilContainer;

public class Inventory extends InventoryBasic {

	public Inventory(ITextComponent title, int slotCount) {
		super(title, slotCount);
	}
	
	public Inventory(String title, boolean customName, int slotCount) {
		super(title, customName, slotCount);
	}

	@Override
	public ITextComponent getDisplayName() {
		return super.getDisplayName();
	}
	
	@Override
	public String getName() {
		return super.getName();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}
	
	@Override
	public void markDirty() {
		super.markDirty();
	}
}
