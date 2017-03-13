package uk.co.aperistudios.firma.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.player.PlayerData;

public class KnapToServer implements IMessage {

	int x = -1, y = -1;
	boolean b = false;

	public KnapToServer() {

	}

	public KnapToServer(int x, int y, boolean b) {
		this.x = x;
		this.y = y;
		this.b = b;
	}

	public static void init() {
		FirmaMod.dispatcher.registerMessage(KnapToServerHandler.class, KnapToServer.class, FirmaMod.packetCounter++, Side.SERVER);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		b = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeBoolean(b);
	}

}
