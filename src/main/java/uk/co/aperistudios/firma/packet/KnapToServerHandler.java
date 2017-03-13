package uk.co.aperistudios.firma.packet;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.crafting.Recipe;
import uk.co.aperistudios.firma.gui.FirmaGuiContainer;
import uk.co.aperistudios.firma.gui.GuiKnapping;
import uk.co.aperistudios.firma.player.PlayerData;

public class KnapToServerHandler implements IMessageHandler<KnapToServer, IMessage> {

	@Override
	public IMessage onMessage(KnapToServer message, MessageContext ctx) {
		PlayerData pd = PlayerData.getPlayerData(ctx.getServerHandler().playerEntity.getUniqueID());
		if (message.x == -1 || message.y == -1) {
			// Get recipe
			Recipe r = CraftingManager.getMatchingRecipe(pd.getCraftingMaterial(), ctx.getServerHandler().playerEntity.getHeldItemMainhand(),
					pd.getKnapLayout());
			if (r != null) {
				// If valid, pay the price
				if (r.payPrice(ctx.getServerHandler().playerEntity)) {
					// Then spawn the item on them. I got lazy trying to figure
					// out more vanilla crafting. Sue me.
					if (!ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(r.getOutput())) {
						// TODO Spawn item at feet if crafting with full invent
						// ctx.getServerHandler().playerEntity.world.spawn
					}
				}
			}
			pd.resetKnapCraft();
			ctx.getServerHandler().playerEntity.closeScreen();
		} else {
			pd.setKnapCraft(message.x, message.y, message.b);
			Recipe r = CraftingManager.getMatchingRecipe(pd.getCraftingMaterial(), ctx.getServerHandler().playerEntity.getHeldItemMainhand(),
					pd.getKnapLayout());
			if (ctx.getServerHandler().playerEntity.openContainer instanceof ContainerSpecialCrafting) {
				((ContainerSpecialCrafting) ctx.getServerHandler().playerEntity.openContainer).craftResult.setInventorySlotContents(0, r.getOutput());
			}
		}
		return null;
	}

}
