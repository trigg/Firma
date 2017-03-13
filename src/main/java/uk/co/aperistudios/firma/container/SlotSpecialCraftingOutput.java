package uk.co.aperistudios.firma.container;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.gui.GuiKnapping;
import uk.co.aperistudios.firma.packet.KnapToServer;
import uk.co.aperistudios.firma.player.PlayerData;

public class SlotSpecialCraftingOutput extends Slot {
	private EntityPlayer thePlayer;
	private Container container;

	public SlotSpecialCraftingOutput(Container container, EntityPlayer entityplayer, IInventory iinventory1, int i, int j, int k) {
		super(iinventory1, i, j, k);
		this.container = container;
		thePlayer = entityplayer;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return false;
	}

	@Override
	public ItemStack onTake(EntityPlayer player, ItemStack itemstack) {
		//itemstack.onCrafting(thePlayer.world, thePlayer, slotNumber);
		//FMLCommonHandler.instance().firePlayerCraftingEvent(player, itemstack, null);
		
		//PlayerData pd = PlayerData.getPlayerData(thePlayer.getUniqueID());
		//pd.resetKnapCraft();
		if (player.world.isRemote) {
			KnapToServer nts = new KnapToServer(-1, -1, false);
			FirmaMod.dispatcher.sendToServer(nts);
			((GuiKnapping) Minecraft.getMinecraft().currentScreen).resetButtons(false);
		}
		return itemstack;
	}
}
