package uk.co.aperistudios.firma.packet;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import uk.co.aperistudios.firma.FirmaMod;

public class SetDayHandler implements IMessageHandler<SetDayPacket, IMessage> {

	@Override
	public IMessage onMessage(SetDayPacket message, MessageContext ctx) {
		FirmaMod.proxy.setDate(message.getData());
		return null;
	}

}
