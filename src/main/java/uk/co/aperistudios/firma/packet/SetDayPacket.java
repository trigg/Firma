package uk.co.aperistudios.firma.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.TimeData;

public class SetDayPacket implements IMessage {

	private TimeData td;

	public SetDayPacket(){
		
	}
	
	public SetDayPacket(TimeData td){
		this.td = td;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		td = new TimeData("");
		td.setYear(buf.readInt());
		td.setMonth(buf.readInt());
		td.setDay(buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(td.getYear());
		buf.writeInt(td.getMonth());
		buf.writeInt(td.getDay());
	}

	public TimeData getData() {
		return td;
	}

	public static void init() {
		FirmaMod.dispatcher.registerMessage(SetDayHandler.class, SetDayPacket.class, FirmaMod.packetCounter++, Side.CLIENT);
	}
}
