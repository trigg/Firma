package uk.co.aperistudios.firma.container;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import uk.co.aperistudios.firma.gui.GuiKnapping;
import uk.co.aperistudios.firma.player.PlayerData;

public class SlotSpecialCraftingOutput extends Slot {
	private EntityPlayer thePlayer;
	private Container container;

	public SlotSpecialCraftingOutput(Container container, EntityPlayer entityplayer, IInventory iinventory1, int i, int j, int k)
	{
		super(iinventory1, i, j, k);
		this.container = container;
		thePlayer = entityplayer;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}

	

	@Override
	public ItemStack onTake(EntityPlayer player, ItemStack itemstack)
	{
		itemstack.onCrafting(thePlayer.world, thePlayer, slotNumber);
		//TerraFirmaCraft.proxy.takenFromCrafting(thePlayer, itemstack, craftMatrix);
		
		PlayerData pd = PlayerData.getPlayerData(thePlayer.getUniqueID());
		pd.resetKnapCraft();
		if (player.world.isRemote)
		{
			((GuiKnapping) Minecraft.getMinecraft().currentScreen).resetButtons();
		}
		return itemstack;
	}
}
