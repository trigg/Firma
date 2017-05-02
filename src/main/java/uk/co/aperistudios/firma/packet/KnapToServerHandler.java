package uk.co.aperistudios.firma.packet;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;
import uk.co.aperistudios.firma.crafting.CraftMat;
import uk.co.aperistudios.firma.crafting.CraftingManager;
import uk.co.aperistudios.firma.crafting.Recipe;
import uk.co.aperistudios.firma.gui.GuiHandler;
import uk.co.aperistudios.firma.player.PlayerData;

public class KnapToServerHandler implements IMessageHandler<KnapToServer, IMessage> {

	@Override
	public IMessage onMessage(KnapToServer message, MessageContext ctx) {
		PlayerData pd = PlayerData.getPlayerData(ctx.getServerHandler().playerEntity.getUniqueID());
		if (message.x == -1 || message.y == -1) {
			// Get recipe
			Recipe r = CraftingManager.getMatchingRecipe(pd.getCraftingMaterial(), pd.getItemStack(), pd.getKnapLayout());
			if (r != null) {
				// If valid, pay the price
				if (r.getCraftMat() == CraftMat.STONE || r.getCraftMat() == CraftMat.CLAY) {
					if (r.payPrice(ctx.getServerHandler().playerEntity)) {
						// Then spawn the item on them. I got lazy trying to
						// figure
						// out more vanilla crafting. Sue me.
						if (!ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(r.getOutput())) {
							// TODO Spawn item at feet if crafting with full
							// invent
							// ctx.getServerHandler().playerEntity.world.spawn
						}
					}
				} else {
					// Block Based!
					if (r.payPrice(pd.getTileEntity())) {
						if (pd.getTileEntity() instanceof AnvilTileEntity) {
							((AnvilTileEntity) pd.getTileEntity()).setInventorySlotContents(2, r.getOutput());
							ctx.getServerHandler().playerEntity.openGui(FirmaMod.instance, GuiHandler.GUI_SMITHING, pd.getWorld(), pd.getPosition().getX(),
									pd.getPosition().getY(), pd.getPosition().getZ());
							pd.resetKnapCraft();
							return null;
						}
						// Give item to player. Break block in process
						if (!ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(r.getOutput())) {
							// TODO Spawn item at feet if crafting with full
							// invent
							// ctx.getServerHandler().playerEntity.world.spawn
						}
						pd.getWorld().setBlockState(pd.getPosition(), Blocks.AIR.getDefaultState());

					}
				}

			}
			pd.resetKnapCraft();
			ctx.getServerHandler().playerEntity.closeScreen();
		} else {
			pd.setKnapCraft(message.x, message.y, message.b);
			Recipe r = CraftingManager.getMatchingRecipe(pd.getCraftingMaterial(), pd.getItemStack(), pd.getKnapLayout());
			System.out.println(r);
			if (ctx.getServerHandler().playerEntity.openContainer instanceof ContainerSpecialCrafting && r != null) {
				((ContainerSpecialCrafting) ctx.getServerHandler().playerEntity.openContainer).craftResult.setInventorySlotContents(0, r.getOutput());
			}
		}
		return null;
	}

}
