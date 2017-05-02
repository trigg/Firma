package uk.co.aperistudios.firma.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import uk.co.aperistudios.firma.FirmaMod;
import uk.co.aperistudios.firma.container.AnvilContainer;
import uk.co.aperistudios.firma.blocks.tileentity.AnvilTileEntity;
import uk.co.aperistudios.firma.container.ContainerSpecialCrafting;

public class GuiHandler implements IGuiHandler {
	public static int GUI_KNAPPING = 0;
	public static int GUI_SMITHING = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_KNAPPING){
			return new ContainerSpecialCrafting(player.inventory, x, y, z);
		}else if(ID == GUI_SMITHING){
			return new AnvilContainer(player.inventory, world, new BlockPos(x,y,z), (AnvilTileEntity) world.getTileEntity(new BlockPos(x,y,z)));
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUI_KNAPPING){
			return new GuiKnapping(player, x, y, z);
		}else if(ID == GUI_SMITHING){
			return new GuiSmithing(player, player.world, (AnvilTileEntity) world.getTileEntity(new BlockPos(x,y,z)));
		}
		return null;
	}

}