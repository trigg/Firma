package uk.co.aperistudios.firma.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;

public class GuiHandler implements IGuiHandler {
	public static int GUI_KNAPPING=0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_KNAPPING)
            return new ContainerSpecialCrafting(player.inventory, null, world, z, z, z);

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_KNAPPING)
			return new GuiKnapping(player,x,y,z);
		return null;
	}

}
