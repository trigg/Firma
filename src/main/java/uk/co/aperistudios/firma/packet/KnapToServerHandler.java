package uk.co.aperistudios.firma.packet;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import uk.co.aperistudios.firma.player.PlayerData;

public class KnapToServerHandler implements IMessageHandler<KnapToServer, IMessage>{

	@Override
	public IMessage onMessage(KnapToServer message, MessageContext ctx) {
		PlayerData pd = PlayerData.getPlayerData(ctx.getServerHandler().playerEntity.getUniqueID());
		pd.setKnapCraft(message.x, message.y, message.b);
		System.out.println("Removed knap chunk at : "+message.x+", "+message.y);
		return null;
	}
	
}
